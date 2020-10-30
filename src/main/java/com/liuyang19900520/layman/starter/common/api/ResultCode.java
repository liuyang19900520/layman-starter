package com.liuyang19900520.layman.starter.common.api;

/**
 * <p>
 *
 * </p>
 *
 * @author Max Liu
 * @since 2020/09/15
 */
public enum ResultCode implements IResultCode {

    /**
     * 成功信息
     */
    OK("00000", "无错误");

    private final String code;
    private final String message;

    ResultCode(String code, String message) {
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
