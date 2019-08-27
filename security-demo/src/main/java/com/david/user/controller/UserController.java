package com.david.user.controller;


import com.david.common.pojo.Result;
import com.david.common.utils.ResultUtil;
import com.david.user.service.UserService;
import com.david.user.vo.UserView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * (User)表控制层
 *
 * @author lingjian
 * @since 2019-08-27 15:46:53
 */
@RestController
@RequestMapping("/user/")
@Api(tags = "UserController相关api")
public class UserController {
    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("getbyid")
    @ApiOperation("通过主键查询单条数据")
    public Result getById(@RequestParam Integer id) {
        return ResultUtil.success(userService.getById(id));
    }

    /**
     * 多条件分页查询所有数据
     *
     * @param page 分页对象
     * @param size 分页对象
     * @param condition 查询条件
     * @return 所有数据
     */
    @GetMapping("list")
    @ApiOperation("多条件分页查询所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "开始页码", required = true, defaultValue = "0"),
            @ApiImplicitParam(name = "size", value = "显示条数", required = true, defaultValue = "20")
    })
    public Result selectAll(String condition, Integer page, Integer size) {
        return ResultUtil.success(userService.listByPage(condition, PageRequest.of(page, size)));
    }

    /**
     * 新增数据/编辑数据
     *
     * @param userView 实体对象
     * @return 新增结果
     */
    @PostMapping("save")
    @ApiOperation("新增/编辑数据")
    public Result save(@RequestBody UserView userView) {
        userService.save(userView);
        return ResultUtil.success();
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping
    @ApiOperation("删除数据")
    public Result delete(@RequestParam Integer id) {
        userService.deleteById(id);
        return ResultUtil.success();
    }
}
