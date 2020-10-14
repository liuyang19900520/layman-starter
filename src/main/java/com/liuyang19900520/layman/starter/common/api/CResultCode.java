package com.liuyang19900520.layman.starter.common.api;

/**
 * <p>
 * 第三方调用错误
 * </p>
 *
 * @author Max Liu
 * @since 2020/09/15
 */
public enum CResultCode implements IResultCode {


    COMMON_C_ERROR("C0001", "调用第三方服务出错");

    private final String code;
    private final String message;

    private CResultCode(String code, String message) {
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
