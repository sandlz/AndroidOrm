package me.sandlz.androidorm.entity;

import me.sandlz.dblibrary.db.annotation.Column;
import me.sandlz.dblibrary.db.annotation.Table;

/**
 * Created by liuzhu on 2016/12/23.
 * Description :
 * Usage :
 */
@Table(name = "User")
public class User {

    @Column(name = "userId" ,isId = true, autoGen = false)
    private String userId;

    @Column(name = "age")
    private int age;

    @Column(name = "name")
    private String name;

    public User() {

    }

    public User(String userId, int age, String name) {
        this.userId = userId;
        this.age = age;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
