package com.liuyang19900520.layman.starter.common.logger.database.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 错误日志
 * </p>
 *
 * @author Max Liu
 * @since 2020-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_error_log")
@ApiModel(value="SysErrorLog对象", description="错误日志")
public class SysErrorLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long errorLogId;

    @ApiModelProperty(value = "日志类型(字典)")
    private String errorCode;

    @ApiModelProperty(value = "日志名称")
    private String errorMsg;

    @ApiModelProperty(value = "类名称")
    private String className;

    @ApiModelProperty(value = "方法名称")
    private String method;

    @ApiModelProperty(value = "路由")
    private String path;

    @ApiModelProperty(value = "详情")
    private String detail;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建用户")
    private Long createUser;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "更新用户")
    private Long updateUser;

    @ApiModelProperty(value = "数据状态")
    private String deleteFlg;

    @ApiModelProperty(value = "备注")
    private String remark;


}
