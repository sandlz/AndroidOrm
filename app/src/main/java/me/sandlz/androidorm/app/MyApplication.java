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
        DbLite.init(this,false);
        DBHelper2.init(this);
    }
}
