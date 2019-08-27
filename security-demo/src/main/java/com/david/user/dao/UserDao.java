package com.david.user.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.david.user.entity.User;

/**
 * (User)表数据库访问层
 *
 * @author lingjian
 * @since 2019-08-27 15:46:52
 */
public interface UserDao extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User>, PagingAndSortingRepository<User,Integer> {
    /**
     * 通过id查询User单个对象
     * @param id id
     * @return User实体类
     */
    User getUser(Integer id);
}