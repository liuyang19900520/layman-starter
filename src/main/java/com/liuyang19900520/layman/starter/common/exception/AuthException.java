package com.liuyang19900520.layman.starter.common.exception;

import com.liuyang19900520.layman.starter.common.api.AResultCode;
import com.liuyang19900520.layman.starter.common.api.IResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.springframework.security.core.AuthenticationException;

/**
 * <p>
 *
 * </p>
 *
 * @author Max Liu
 * @since 2020/10/07
 */
@Getter
public class AuthException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    private IResultCode resultCode;

    private String message;

    public AuthException(String message) {
        super(message);
        this.resultCode = AResultCode.COMMON_401_ERROR;
        this.message = message;
    }

}
