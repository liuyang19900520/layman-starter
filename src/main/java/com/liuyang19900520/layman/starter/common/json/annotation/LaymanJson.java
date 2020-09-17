package com.liuyang19900520.layman.starter.common.json.annotation;


import java.lang.annotation.*;

/**
 * @author Max Liu
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(com.liuyang19900520.layman.starter.common.json.annotation.LaymanJsons.class)
public @interface LaymanJson {
    Class<?> type();

    String include() default "";

    String filter() default "";
}

