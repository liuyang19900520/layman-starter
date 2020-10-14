package com.liuyang19900520.layman.starter.common.api;

/**
 * <p>
 * 用户端错误
 * </p>
 *
 * @author Max Liu
 * @since 2020/09/15
 */
public enum AResultCode implements IResultCode {


    COMMON_A_ERROR("A0001", "用户端错误");

    private final String code;
    private final String message;

    private AResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
