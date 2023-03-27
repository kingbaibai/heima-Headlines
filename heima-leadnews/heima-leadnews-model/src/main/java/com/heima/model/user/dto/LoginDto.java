package com.heima.model.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangqin
 * @Date: 2023/3/25 - 03 - 25 - 20:37
 * @Description: com.heima.model.user.dto
 * @version: 1.0
 */
@Data
@ApiModel(description = "登入dto")
public class LoginDto {

    @ApiModelProperty(name = "电话")
    private String phone;
    @ApiModelProperty("密码")
    private String password;
}
