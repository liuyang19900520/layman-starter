package com.liuyang19900520.layman.starter.common.exception;

import com.liuyang19900520.layman.starter.common.api.IResultCode;
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
public class AuthException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private IResultCode resultCode;

    private String message;

}
