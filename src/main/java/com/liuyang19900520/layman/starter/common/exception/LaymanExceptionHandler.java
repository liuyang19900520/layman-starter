package com.liuyang19900520.layman.starter.common.exception;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.liuyang19900520.layman.starter.common.api.AResultCode;
import com.liuyang19900520.layman.starter.common.api.BResultCode;
import com.liuyang19900520.layman.starter.common.api.CommonResult;
import com.liuyang19900520.layman.starter.common.logger.LogManager;
import com.liuyang19900520.layman.starter.common.logger.factory.LogTaskFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletRequest;
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
@Order(-100)
@Slf4j
public class LaymanExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult handleValidException(HttpServletRequest request, MethodArgumentNotValidException e) {

        return CommonResult.failed(AResultCode.COMMON_400_ERROR, createErrorDetail(request, e));
    }

    /**
     * 认证异常--认证失败（账号密码错误，账号被冻结，token过期等）
     */
    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public CommonResult unAuth(HttpServletRequest request, AuthException e) {

        return CommonResult.failed(AResultCode.COMMON_401_ERROR, createErrorDetail(request, e));
    }

    /**
     * 认证异常--没有访问权限
     */
    @ExceptionHandler(PermissionException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public CommonResult permissionException(HttpServletRequest request, PermissionException e) {
        return CommonResult.failed(AResultCode.COMMON_403_ERROR, createErrorDetail(request, e));
    }

    /**
     * 404
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public CommonResult notFoundException(HttpServletRequest request, NoHandlerFoundException e) {
        return CommonResult.failed(AResultCode.COMMON_404_ERROR, createErrorDetail(request, e));
    }

    /**
     * 拦截业务异常
     */
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public CommonResult businessException(HttpServletRequest request, BizException e) {
        return CommonResult.failed(AResultCode.COMMON_A_ERROR, createErrorDetail(request, e));
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CommonResult internalException(HttpServletRequest request, RuntimeException e) {

        HashMap<Object, Object> errorDetail = createErrorDetail(request, e);
        LogManager.getInstance().executeLog(LogTaskFactory.exceptionLog(null, BResultCode.COMMON_B_ERROR, (String) errorDetail.get("className"),
                (String) errorDetail.get("methodName"), (String) errorDetail.get("path"), (String) errorDetail.get("detail")));
        CommonResult result = CommonResult.failed(BResultCode.COMMON_B_ERROR, errorDetail);
        log.error(JSONUtil.toJsonStr(result));
        return result;
    }


    /**
     * 创建error详细
     *
     * @param request
     * @param e
     * @return map
     */
    private HashMap<Object, Object> createErrorDetail(HttpServletRequest request, Exception e) {

        StackTraceElement stackTraceElement = e.getStackTrace()[0];
        HashMap<Object, Object> error = MapUtil.newHashMap();
        error.put("path", request.getServletPath());
        error.put("className", stackTraceElement.getClassName());
        error.put("methodName", stackTraceElement.getMethodName());
        error.put("line", stackTraceElement.getLineNumber());
        error.put("detail", e.getMessage());

        return error;
    }


}
