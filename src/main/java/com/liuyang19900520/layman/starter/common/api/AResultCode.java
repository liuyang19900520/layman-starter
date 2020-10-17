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


    COMMON_A_ERROR("A0001", "用户端错误"),
    COMMON_400_ERROR("A0002", "参数不正确"),
    COMMON_401_ERROR("A0003", "认证失败"),
    COMMON_403_ERROR("A0004", "未有权限"),
    COMMON_404_ERROR("A0005", "未找到资源");

    private final String code;
    private final String message;

    AResultCode(String code, String message) {
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
