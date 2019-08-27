package com.david.user.dao.impl;


import com.david.common.dao.BaseRepository;
import com.david.user.entity.QUser;
import com.david.user.entity.User;


/**
 * (User)表数据库访问层实现类
 *
 * @author lingjian
 * @since 2019-08-27 15:46:53
 */
public class UserDaoImpl extends BaseRepository {

    /**
     * 根据id获取User实体类对象
     *
     * @param id id
     * @return User对象
     */
    public User getUser(Integer id) {
        QUser qUser = QUser.user;
        return queryFactory.selectFrom(qUser)
                .where(qUser.id.eq(id)).fetchOne();
    }

}
