package com.david.user.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import com.david.common.enums.ResultEnum;
import com.david.common.exception.WebMessageException;
import com.david.common.pojo.PageView;
import com.david.user.dao.UserDao;
import com.david.user.entity.User;
import com.david.user.service.UserService;
import com.david.user.vo.UserView;
import com.david.user.vo.mapper.UserMapper;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author lingjian
 * @since 2019-08-27 15:46:53
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    private UserMapper userMapper = new UserMapper();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserView getById(Integer id) {
        if (null == userDao.getUser(id)) {
            throw new WebMessageException(ResultEnum.FAILED.getCode(), "这个[ " + id + " ]id对应的查询结果不存在");
        }
        return userMapper.modelMapperConfig(true).map(userDao.getUser(id), UserView.class);
    }

    /**
     * 多条件分页查询所有数据
     *
     * @param pageable 分页对象
     * @param condition 查询条件
     * @return 对象列表
     */
    @Override
    public PageView<UserView> listByPage(String condition, Pageable pageable) {
        Page<User> page = userDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> temp = new ArrayList<>();
            if (condition != null) {
                temp.add(criteriaBuilder.and(
                        criteriaBuilder.like(root.get("id"), "%" + condition + "%")));
            }
            return criteriaBuilder.and(temp.toArray(new Predicate[0]));
        }, pageable);
        return PageView.of(page.getTotalElements(),userMapper.modelMapperConfig(true)
        .map(page.getContent(), new TypeToken<List<UserView>>() {
        }.getType()));
    }

    /**
     * 新增数据/编辑数据
     *
     * @param userView 实例对象
     */
    @Override
    public void save(UserView userView) {
        userDao.save(userMapper.modelMapperConfig(false).map(userView,User.class));
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public void deleteById(Integer id) {
        userDao.deleteById(id);
    }
}
