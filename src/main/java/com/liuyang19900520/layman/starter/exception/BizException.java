package com.liuyang19900520.layman.starter.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author Max Liu
 * @since 2020/10/07
 */
@Data
@AllArgsConstructor
public class BizException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected String errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

}
