package com.liuyang19900520.layman.starter.exception;

import com.liuyang19900520.layman.starter.common.api.IResultCode;
import com.liuyang19900520.layman.starter.common.api.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

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
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private IResultCode resultCode;

    private Error error;

}
