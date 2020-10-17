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

/**
 * <p>
 * Exception Handler
 * </p>
 *
 * @author Max Liu
 * @since 2020/10/05
 */
@ControllerAdvice
public class LaymanExceptionHandler {

    private static final String STATUS_PREFIX_4 = "4";
    private static final String STATUS_PREFIX_5 = "5";

    private static final String STATUS_400 = "400";
    private static final String STATUS_401 = "401";
    private static final String STATUS_403 = "403";
    private static final String STATUS_404 = "404";

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {

        CommonResult result = null;

        HashMap<Object, Object> error = MapUtil.newHashMap();

        error.put("detail", e.getMessage());
        error.put("path", request.getServletPath());

        result = CommonResult.failed(BResultCode.COMMON_B_ERROR, error);

        String status = String.valueOf(response.getStatus());
        if (StrUtil.startWith(status, STATUS_PREFIX_4)) {
            result = CommonResult.failed(AResultCode.COMMON_A_ERROR, error);
        } else if (StrUtil.startWith(status, STATUS_PREFIX_5)) {
            result = CommonResult.failed(BResultCode.COMMON_B_ERROR, error);
        }

        switch (status) {
            case STATUS_400:
                result = CommonResult.failed(AResultCode.COMMON_400_ERROR, error);
                break;
            case STATUS_401:
                result = CommonResult.failed(AResultCode.COMMON_401_ERROR, error);
                break;
            case STATUS_403:
                result = CommonResult.failed(AResultCode.COMMON_403_ERROR, error);
                break;
            case STATUS_404:
                result = CommonResult.failed(AResultCode.COMMON_404_ERROR, error);
                break;
            default:
                break;
        }

        if (e instanceof BizException) {
            BizException e2 = (BizException) e;
            result = CommonResult.failed(e2.getResultCode(), error);
        }

        return result;
    }

}
