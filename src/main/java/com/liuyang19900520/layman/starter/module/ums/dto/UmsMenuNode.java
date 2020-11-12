package com.liuyang19900520.layman.starter.module.ums.dto;


import com.liuyang19900520.layman.starter.module.ums.entity.UmsMenu;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 后台菜单节点封装
 *
 * @author macro
 * @date 2020/2/4
 */
@Getter
@Setter
public class UmsMenuNode extends UmsMenu {
    @ApiModelProperty(value = "子级菜单")
    private List<UmsMenuNode> children;
}
