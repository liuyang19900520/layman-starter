package com.liuyang19900520.layman.starter.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author Max Liu
 * @since 2020/10/14
 */
@Data
@AllArgsConstructor
public class Error {
    private Integer statusCode;
    private String path;
    private String detail;
}
