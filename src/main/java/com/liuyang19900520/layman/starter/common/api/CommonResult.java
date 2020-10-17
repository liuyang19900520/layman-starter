package com.liuyang19900520.layman.starter.common.api;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author Max Liu
 * @since 2020/09/15
 */
@ApiModel(value = "共通返回值", description = "共通返回值")
@Data
@AllArgsConstructor
public class CommonResult<T> {
    private String code;
    private String message;
    private T data;
    private T error;

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(ResultCode.OK.getCode(), ResultCode.OK.getMessage(), data, null);
    }


    /**
     * 失败返回结果
     *
     * @param error 获取的数据
     */
    public static <T> CommonResult<T> failed(IResultCode resultCode, T error) {
        return new CommonResult<>(resultCode.getCode(), resultCode.getMessage(), null, error);
    }

}

