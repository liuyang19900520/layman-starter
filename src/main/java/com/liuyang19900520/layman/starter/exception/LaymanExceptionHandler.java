package com.liuyang19900520.layman.starter.exception;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.liuyang19900520.layman.starter.common.api.AResultCode;
import com.liuyang19900520.layman.starter.common.api.BResultCode;
import com.liuyang19900520.layman.starter.common.api.CommonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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

    private static final String STATUS_PREFIX_4 = "4";
    private static final String STATUS_PREFIX_5 = "5";

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResult<Exception> exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {

        CommonResult result = null;

        String status = String.valueOf(response.getStatus());

        if (StrUtil.startWith(status, STATUS_PREFIX_4)) {
            result = CommonResult.failed(AResultCode.COMMON_A_ERROR, e.getMessage());
        } else if (StrUtil.startWith(status, STATUS_PREFIX_5)) {
            Error error = new Error(response.getStatus(), request.getServletPath(), e.getMessage());

            result = CommonResult.failed(BResultCode.COMMON_B_ERROR, error);
        }

        if (e instanceof BizException) {
            BizException e2 = (BizException) e;
            result = CommonResult.failed(e2.getResultCode(), ((BizException) e).getError());
        }

        return result;
    }

}
