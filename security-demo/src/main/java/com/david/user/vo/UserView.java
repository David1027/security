package com.david.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * (User)表前端展示类
 *
 * @author lingjian
 * @since 2019-08-27 15:46:53
 */
@Data
@ApiModel("User前端展示类")
public class UserView {

    @ApiModelProperty(value="用户主键")
    private Integer id;

    @ApiModelProperty(value="用户名称")
    private String username;

    @ApiModelProperty(value="用户密码")
    private String password;

    @ApiModelProperty(value=" 用户性别")
    private String gender;

    @ApiModelProperty(value=" 用户年龄")
    private String age;
}