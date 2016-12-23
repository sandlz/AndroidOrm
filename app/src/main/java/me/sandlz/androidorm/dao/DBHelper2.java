package me.sandlz.androidorm.dao;

import android.content.Context;

import java.io.File;

import me.sandlz.dblite.DbLite;
import me.sandlz.dblite.DbManager;

/**
 * Created by liuzhu on 2016/11/16.
 * Description :
 * Usage :
 */
public class DBHelper2 {
    private static final String TAG = DBHelper2.class.getSimpleName();

    private static DBHelper2 instance;

    private static Context appContext;
    private static boolean DebugModel = true;

    private DbManager db;

    private DBHelper2() {
    }

    /**
     * 初始化数据库 appContetx
     * @param context
     */
    public  static  void init(Context context){
        appContext=context;
    }

    /**
     * 获取db的实例
     * @return
     */
    public static DBHelper2 getInstance() {
        if(appContext==null){
            throw new RuntimeException("请先在程序入口处初始化，调用init(ApplicationContext)");
        }
        if (instance == null) {
            instance = new DBHelper2();
            File dbFile=new File("/sdcard/sandlz");
            if(!dbFile.exists()){
                dbFile.mkdirs();
            }

            DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                    .setDbName("test.sqlite.db")
                    // 不设置dbDir时, 默认存储在app的私有目录.
                    .setDbDir(new File("/sdcard/sandlz"))
                    .setDbVersion(1)
                    .setDbOpenListener(new DbManager.DbOpenListener() {
                        @Override
                        public void onDbOpened(DbManager db) {
                            // 开启WAL, 对写入加速提升巨大
                            db.getDatabase().enableWriteAheadLogging();
                        }
                    })
                    .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                        @Override
                        public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                            // TODO: 可执行一些操作 如删除、更新表等
                            // db.addColumn(...);
                            // db.dropTable(...);
                            // ...
                            // or
                            // db.dropDb();
                        }
                    });
            try {
                instance.db = DbLite.getDb(daoConfig);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return instance;
    }

    public DbManager getDb() {
        return db;
    }

    public void setDb(DbManager db) {
        this.db = db;
    }

}
