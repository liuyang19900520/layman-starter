package com.liuyang19900520.layman.starter.common.json;


import com.liuyang19900520.layman.starter.common.json.annotation.LaymanJson;
import com.liuyang19900520.layman.starter.common.json.annotation.LaymanJsons;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Arrays;


/**
 * @author Max Liu
 */
public class LaymanJsonReturnHandler implements HandlerMethodReturnValueHandler {

//    List<ResponseBodyAdvice<Object>> advices = new ArrayList<>();

    /**
     * 如果拦截到注解就返回true，进行拦截进行操作
     *
     * @param returnType 返回类型
     * @return 是否拦截
     */
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return returnType.getMethodAnnotation(LaymanJson.class) != null || returnType.getMethodAnnotation(LaymanJsons.class) != null;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        mavContainer.setRequestHandled(true);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        Annotation[] annos = returnType.getMethodAnnotations();
        LaymanJsonSerializer jsonSerializer = new LaymanJsonSerializer();
        Arrays.asList(annos).forEach(a -> {
            if (a instanceof LaymanJson) {
                LaymanJson json = (LaymanJson) a;
                jsonSerializer.filter(json);
            } else if (a instanceof LaymanJsons) {
                LaymanJsons jsons = (LaymanJsons) a;
                Arrays.asList(jsons.value()).forEach(jsonSerializer::filter);
            }
        });
        assert response != null;
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf8");
        String json = jsonSerializer.toJson(returnValue);
        response.getWriter().write(json);
        response.getWriter().close();
    }

}
