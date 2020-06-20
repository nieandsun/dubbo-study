package com.nrsc.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("用户地址信息")
@Data
public class UserAddress implements Serializable {

    private static final long serialVersionUID = -6473376930137251405L;
    private Integer id;
    @ApiModelProperty("用户地址")
    private String userAddress;
    @ApiModelProperty("用户id")
    private String userId;
    @ApiModelProperty("收货人")
    private String consignee;
    @ApiModelProperty("电话号码")
    private String phoneNum;
    @ApiModelProperty("是否为默认地址  Y-是   N-否")
    private String isDefault;
}
