package me.sandlz.dblibrary;

import android.app.Application;

import me.sandlz.dblibrary.db.DbManagerImpl;

/**
 * Created by liuzhu on 2016/12/23.
 * Description :
 * Usage :
 */
public class DbLite {

    private static Application app;
    private static boolean debug;

    private DbLite() {
    }

    public static Application app() {
        return app;
    }

    public static void init(Application application) {
        if (app == null) {
            app = application;
        }
    }

    public static void init(Application application, boolean isDebug) {
        if (app == null) {
            app = application;
        }
        debug = isDebug;
    }

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean isDebug) {
        debug = isDebug;
    }

    public static DbManager getDb(DbManager.DaoConfig daoConfig) {
        return DbManagerImpl.getInstance(daoConfig);
    }

}
