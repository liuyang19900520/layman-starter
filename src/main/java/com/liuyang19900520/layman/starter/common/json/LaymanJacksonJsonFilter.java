package com.liuyang19900520.layman.starter.common.json;


import cn.hutool.core.map.MapUtil;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Max Liu
 */

@JsonFilter("laymanJacksonFilter")
public class LaymanJacksonJsonFilter extends FilterProvider {

    Map<Class<?>, Set<String>> includeMap = MapUtil.newHashMap();
    Map<Class<?>, Set<String>> filterMap = MapUtil.newHashMap();

    public void include(Class<?> type, String[] fields) {
        addToMap(includeMap, type, fields);
    }

    public void filter(Class<?> type, String[] fields) {
        addToMap(filterMap, type, fields);
    }

    private void addToMap(Map<Class<?>, Set<String>> map, Class<?> type, String[] fields) {
        Set<String> fieldSet = map.getOrDefault(type, new HashSet<>());
        fieldSet.addAll(Arrays.asList(fields));
        map.put(type, fieldSet);
    }

    @Override
    @Deprecated
    public BeanPropertyFilter findFilter(Object filterId) {
        throw new UnsupportedOperationException("Access to deprecated filters not supported");
    }

    @Override
    public PropertyFilter findPropertyFilter(Object filterId, Object valueToFilter) {

        return new SimpleBeanPropertyFilter() {

            @Override
            public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider prov, PropertyWriter writer)
                    throws Exception {
                if (apply(pojo.getClass(), writer.getName())) {
                    writer.serializeAsField(pojo, jgen, prov);
                } else if (!jgen.canOmitFields()) {
                    writer.serializeAsOmittedField(pojo, jgen, prov);
                }
            }
        };
    }

    public boolean apply(Class<?> type, String name) {
        Set<String> includeFields = includeMap.get(type);
        Set<String> filterFields = filterMap.get(type);
        if (includeFields != null && includeFields.contains(name)) {
            return true;
        } else if (filterFields != null && !filterFields.contains(name)) {
            return true;
        } else {
            return includeFields == null && filterFields == null;
        }
    }

}
