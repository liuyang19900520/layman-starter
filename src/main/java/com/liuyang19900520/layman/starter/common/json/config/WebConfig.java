package com.liuyang19900520.layman.starter.common.json.config;

import com.liuyang19900520.layman.starter.common.json.LaymanJsonReturnHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(getJsonReturnHandler());
    }

    public HandlerMethodReturnValueHandler getJsonReturnHandler() {
        return new LaymanJsonReturnHandler();
    }
}
