package me.sandlz.androidorm.dao;

import java.util.List;

import me.sandlz.androidorm.entity.User;
import me.sandlz.dblite.db.ex.DbException;

/**
 * Created by liuzhu on 2016/12/23.
 * Description :
 * Usage :
 */
public class UserDao {

    public UserDao() {
    }

    /*########################## 增 ##########################*/

    public void addUser(User user) {
        if (null == user) {
            return;
        }
        try {
            DBHelper2.getInstance().getDb().saveOrUpdate(user);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public void addUser(List<User> users) {
        if (null == users) {
            return;
        }
        try {
            DBHelper2.getInstance().getDb().saveOrUpdate(users);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /*########################## 删 ##########################*/

    public void deleteUser(User user) {
        if (null == user) {
            return;
        }
        try {
            DBHelper2.getInstance().getDb().delete(user);
        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    public void deleteUser(List<User> users) {
        if (null == users) {
            return;
        }
        try {
            DBHelper2.getInstance().getDb().delete(users);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllUser() {
        try {
            DBHelper2.getInstance().getDb().delete(User.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /*########################## 改 ##########################*/

    public void updateUser(User user) {
        if (null == user) {
            return;
        }
        try {
            DBHelper2.getInstance().getDb().update(user);
        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    public void updateUser(List<User> users) {
        if (null == users) {
            return;
        }
        try {
            DBHelper2.getInstance().getDb().update(users);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    /*########################## 查 ##########################*/

    public User queryUser(String userId) {
        if (null == userId) {
            return null;
        }
        User user = null;
        try {
            List<User> users = DBHelper2.getInstance().getDb()
                    .selector(User.class)
                    .where("userId","=",userId)
                    .findAll();
            if (null != users && users.size() > 0) {
                user = users.get(0);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> queryAllUsers() {
        List<User> users = null;
        try {
            users = DBHelper2.getInstance().getDb().findAll(User.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return users;
    }



}
