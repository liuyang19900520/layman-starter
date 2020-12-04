package com.liuyang19900520.layman.starter.common.json.config;

import com.liuyang19900520.layman.starter.common.json.LaymanJsonReturnHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author Max Liu
 * @since 2020/09/16
 */

@Configuration
public class LaymanJacksonWebConfig extends WebMvcConfigurationSupport {

    @Autowired
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @Autowired
    private LaymanJsonReturnHandler returnValueHandler;

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(returnValueHandler);
    }

    @PostConstruct
    public void init() {
        final List<HandlerMethodReturnValueHandler> newHandlers = new LinkedList<>();
        final List<HandlerMethodReturnValueHandler> originalHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();
        if (null != originalHandlers) {
            newHandlers.addAll(originalHandlers);
            //获取处理器应处于的位置，需要在RequestResponseBodyMethodProcessor之前
            final int index = obtainValueHandlerPosition(originalHandlers, RequestResponseBodyMethodProcessor.class);
            newHandlers.add(index, returnValueHandler);
        } else {
            newHandlers.add(returnValueHandler);
        }
        requestMappingHandlerAdapter.setReturnValueHandlers(newHandlers);
    }

    /**
     * 获取让自定义处理器生效应处于的位置
     *
     * @param originalHandlers 已经添加的返回值处理器
     * @param handlerClass     返回值处理器的类型
     * @return 自定义处理器需要的位置
     */
    private int obtainValueHandlerPosition(final List<HandlerMethodReturnValueHandler> originalHandlers, Class<?> handlerClass) {
        for (int i = 0; i < originalHandlers.size(); i++) {
            final HandlerMethodReturnValueHandler valueHandler = originalHandlers.get(i);
            if (handlerClass.isAssignableFrom(valueHandler.getClass())) {
                return i;
            }
        }
        return -1;
    }

    @Bean
    RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        return new RequestMappingHandlerAdapter();
    }

    @Bean
    public LaymanJsonReturnHandler laymanJsonReturnHandler() {
        return new LaymanJsonReturnHandler();
    }

}
