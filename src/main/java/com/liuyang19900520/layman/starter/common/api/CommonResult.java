package com.liuyang19900520.layman.starter.common.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.annotations.ConstructorArgs;

import java.util.List;

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
    private T errors;

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.OK.getCode(), ResultCode.OK.getMessage(), data, null);
    }


    /**
     * 失败返回结果
     *
     * @param errors 获取的数据
     */
    public static <T> CommonResult<T> failed(IResultCode resultCode, T errors) {
        return new CommonResult<T>(resultCode.getCode(), resultCode.getMessage(), null, errors);
    }

}

