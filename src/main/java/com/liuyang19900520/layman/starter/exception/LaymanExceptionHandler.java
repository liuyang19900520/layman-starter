package com.liuyang19900520.layman.starter.exception;

import com.liuyang19900520.layman.starter.common.api.CommonResult;
import com.liuyang19900520.layman.starter.common.api.ResultCode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author Max Liu
 * @since 2020/10/05
 */
@ControllerAdvice
public class LaymanExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResult<Exception> exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {

        switch (response.getStatus()) {
            case 400:
                break;


        }

        return CommonResult.failed(ResultCode.UNAUTHORIZED, e);
    }
}
