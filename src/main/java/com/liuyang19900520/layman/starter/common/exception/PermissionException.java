package com.liuyang19900520.layman.starter.common.exception;

import com.liuyang19900520.layman.starter.common.api.AResultCode;
import com.liuyang19900520.layman.starter.common.api.IResultCode;
import lombok.Getter;
import org.springframework.security.access.AccessDeniedException;

/**
 * <p>
 *
 * </p>
 *
 * @author Max Liu
 * @since 2020/10/07
 */
@Getter
public class PermissionException extends AccessDeniedException {
    private static final long serialVersionUID = 1L;

    private IResultCode resultCode;

    private String message;

    public PermissionException(String message) {
        super(message);
        this.resultCode = AResultCode.COMMON_401_ERROR;
        this.message = message;
    }
}
