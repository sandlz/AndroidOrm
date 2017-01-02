package me.sandlz.androidorm.app;

import android.app.Application;

import me.sandlz.androidorm.dao.DBHelper2;
import me.sandlz.dblite.DbLite;

/**
 * Created by liuzhu on 2016/12/23.
 * Description :
 * Usage :
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化数据库
        DbLite.init(this,false);
        // 创建数据库
        DBHelper2.init(this);
    }
}
