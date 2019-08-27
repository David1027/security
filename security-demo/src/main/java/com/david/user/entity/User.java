package com.david.user.entity;

import javax.persistence.*;
import lombok.Data;

/**
 * (User)表实体类
 *
 * @author lingjian
 * @since 2019-08-27 15:46:52
 */
@Entity
@Table(name = "user")
@Data
public class User {
        
    @Id
    @GeneratedValue
        /**用户主键 */    @Column(name="id")
    private Integer id;
        /**用户名称 */    @Column(name="username")
    private String username;
        /**用户密码 */    @Column(name="password")
    private String password;
        /** 用户性别 */    @Column(name="gender")
    private String gender;
        /** 用户年龄 */    @Column(name="age")
    private String age;
}