package com.liuyang19900520.layman.starter.common.json;


import com.liuyang19900520.layman.starter.common.json.annotation.LaymanJson;
import com.liuyang19900520.layman.starter.common.json.annotation.LaymanJsons;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Arrays;


/**
 * @author Max Liu
 */
@Component
public class LaymanJsonReturnHandler implements HandlerMethodReturnValueHandler {

//    List<ResponseBodyAdvice<Object>> advices = new ArrayList<>();

    /**
     * 如果拦截到注解就返回true，进行拦截进行操作
     *
     * @param returnType
     * @return
     */
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return returnType.getMethodAnnotation(LaymanJson.class) != null || returnType.getMethodAnnotation(LaymanJsons.class) != null;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        mavContainer.setRequestHandled(true);
//        for (int i = 0; i < advices.size(); i++) {
//            ResponseBodyAdvice<Object> ad = advices.get(i);
//            if (ad.supports(returnType, null)) {
//                returnValue = ad.beforeBodyWrite(returnValue, returnType, MediaType.APPLICATION_JSON, null,
//                        new ServletServerHttpRequest(webRequest.getNativeRequest(HttpServletRequest.class)),
//                        new ServletServerHttpResponse(webRequest.getNativeResponse(HttpServletResponse.class)));
//            }
//        }
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
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf8");
        String json = jsonSerializer.toJson(returnValue);
        response.getWriter().write(json);
        response.getWriter().close();
    }

}
