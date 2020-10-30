package com.liuyang19900520.layman.starter.common.api;

/**
 * <p>
 * 系统端错误
 * </p>
 *
 * @author Max Liu
 * @since 2020/09/15
 */
public enum BResultCode implements IResultCode {

    /**
     * 服务端共同错误
     */
    COMMON_B_ERROR("B0001", "系统内部出错");

    private final String code;
    private final String message;

    BResultCode(String code, String message) {
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
