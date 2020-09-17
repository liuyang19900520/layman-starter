package com.liuyang19900520.layman.starter.common.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.FilterProvider;

import java.io.IOException;

/**
 * @author Max Liu
 */
public class JsonObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = 1L;

    public JsonObjectMapper() {
        super();

        this.getSerializerProvider().setDefaultKeySerializer(new JsonSerializer<Object>() {

            @Override
            public void serialize(Object value, JsonGenerator jg, SerializerProvider sp)
                    throws IOException {
                FilterProvider filterProvider = getSerializerProvider().getFilterProvider();

                filterProvider.findPropertyFilter("includeMap", null);
            }

        });

        // format the null data to ""
//        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
//            @Override
//            public void serialize(Object value, JsonGenerator jg, SerializerProvider sp)
//                    throws IOException, JsonProcessingException {
//                jg.writeString("");
//            }
//        });
    }

}
