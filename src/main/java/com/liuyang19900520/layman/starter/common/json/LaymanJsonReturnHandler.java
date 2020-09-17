package com.liuyang19900520.layman.starter.common.json;



import com.liuyang19900520.layman.starter.common.json.annotation.LaymanJson;
import com.liuyang19900520.layman.starter.common.json.annotation.LaymanJsons;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author Max Liu
 */
public class LaymanJsonReturnHandler implements HandlerMethodReturnValueHandler, BeanPostProcessor {


    ThreadLocal<String> threadStoreCode = new ThreadLocal<String>();
    List<ResponseBodyAdvice<Object>> advices = new ArrayList<>();


    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        boolean hasJSONAnno = returnType.getMethodAnnotation(LaymanJson.class) != null || returnType.getMethodAnnotation(LaymanJsons.class) != null;
        return hasJSONAnno;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        mavContainer.setRequestHandled(true);
        for (int i = 0; i < advices.size(); i++) {
            ResponseBodyAdvice<Object> ad = advices.get(i);
            if (ad.supports(returnType, null)) {
                returnValue = ad.beforeBodyWrite(returnValue, returnType, MediaType.APPLICATION_JSON_UTF8, null,
                        new ServletServerHttpRequest(webRequest.getNativeRequest(HttpServletRequest.class)),
                        new ServletServerHttpResponse(webRequest.getNativeResponse(HttpServletResponse.class)));
            }
        }

        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        Annotation[] annos = returnType.getMethodAnnotations();
        LaymanJsonSerializer jsonSerializer = new LaymanJsonSerializer();
        Arrays.asList(annos).forEach(a -> {
            if (a instanceof LaymanJson) {
                LaymanJson json = (LaymanJson) a;
                jsonSerializer.filter(json);
            } else if (a instanceof LaymanJsons) {
                LaymanJsons jsons = (LaymanJsons) a;
                Arrays.asList(jsons.value()).forEach(json -> {
                    jsonSerializer.filter(json);
                });
            }
        });

        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        String json = jsonSerializer.toJson(returnValue);
        response.getWriter().write(json);
        response.getWriter().close();
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ResponseBodyAdvice) {
            advices.add((ResponseBodyAdvice<Object>) bean);
        } else if (bean instanceof RequestMappingHandlerAdapter) {
            List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>(
                    ((RequestMappingHandlerAdapter) bean).getReturnValueHandlers());
            LaymanJsonReturnHandler jsonHandler = null;
            for (int i = 0; i < handlers.size(); i++) {
                HandlerMethodReturnValueHandler handler = handlers.get(i);
                if (handler instanceof LaymanJsonReturnHandler) {
                    jsonHandler = (LaymanJsonReturnHandler) handler;
                    break;
                }
            }
            if (jsonHandler != null) {
                handlers.remove(jsonHandler);
                handlers.add(0, jsonHandler);
                // change the jsonhandler sort
                ((RequestMappingHandlerAdapter) bean).setReturnValueHandlers(handlers);
            }
        }
        return bean;
    }

}
