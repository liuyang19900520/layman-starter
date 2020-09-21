package com.liuyang19900520.layman.starter.common.json;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.liuyang19900520.layman.starter.common.json.annotation.LaymanJson;

import java.io.IOException;
import java.text.DecimalFormat;


/**
 * depend on jackson
 *
 * @author Liuyang
 */
public class LaymanJsonSerializer {

    ObjectMapper mapper = new ObjectMapper();
    LaymanJacksonJsonFilter jacksonFilter = new LaymanJacksonJsonFilter();

    /**
     * @param clazz   target type
     * @param include include fields
     * @param filter  filter fields
     */
    public void filter(Class<?> clazz, String include, String filter) {
        if (clazz == null) {
            return;
        }
        if (StrUtil.isNotBlank(include)) {
            jacksonFilter.include(clazz, include.split(","));
        }
        if (StrUtil.isNotBlank(filter)) {
            jacksonFilter.filter(clazz, filter.split(","));
        }
        mapper.addMixIn(clazz, jacksonFilter.getClass());
    }

    public String toJson(Object object) throws JsonProcessingException {
        mapper.setFilterProvider(jacksonFilter);
        //long 2 String
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(Float.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Float.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(Double.class, CustomDoubleSerialize.getInstance());
        simpleModule.addSerializer(Double.TYPE, CustomDoubleSerialize.getInstance());
        mapper.registerModule(simpleModule);
        return mapper.writeValueAsString(object);
    }

    public void filter(LaymanJson json) {
        this.filter(json.type(), json.include(), json.filter());
    }

    public static class CustomDoubleSerialize extends JsonSerializer<Double> {

        private static CustomDoubleSerialize instance = new CustomDoubleSerialize();

        private CustomDoubleSerialize() {
        }

        public static CustomDoubleSerialize getInstance() {
            return instance;
        }

        private DecimalFormat df = new DecimalFormat("0.00");

        @Override
        public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException, JsonProcessingException {
            if (value != null) {
                gen.writeString(df.format(value));
            }
        }
    }

}
