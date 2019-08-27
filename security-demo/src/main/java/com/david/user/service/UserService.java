package com.david.user.service;


import com.david.common.pojo.PageView;
import com.david.user.vo.UserView;

import org.springframework.data.domain.Pageable;

/**
 * (User)表服务接口
 *
 * @author lingjian
 * @since 2019-08-27 15:46:52
 */
public interface UserService {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserView getById(Integer id);

    /**
     * 多条件分页查询所有数据
     *
     * @param pageable 分页对象
     * @param condition 查询条件
     * @return 对象列表
     */
    PageView<UserView> listByPage(String condition, Pageable pageable);

    /**
     * 新增数据/编辑数据
     *
     * @param userView 实例对象
     */
    void save(UserView userView);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    void deleteById(Integer id);
}
