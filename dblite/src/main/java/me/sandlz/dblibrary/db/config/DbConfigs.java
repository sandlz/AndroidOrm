package me.sandlz.dblibrary.db.config;


import me.sandlz.dblibrary.DbManager;
import me.sandlz.dblibrary.db.ex.DbException;
import me.sandlz.dblibrary.db.util.LogUtil;

/**
 * 全局db配置
 */
public enum DbConfigs {
    HTTP(new DbManager.DaoConfig()
            .setDbName("dbLite_http_cache.db")
            .setDbVersion(1)
            .setDbOpenListener(new DbManager.DbOpenListener() {
                @Override
                public void onDbOpened(DbManager db) {
                    db.getDatabase().enableWriteAheadLogging();
                }
            })
            .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                @Override
                public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                    try {
                        // 默认删除所有表
                        db.dropDb();
                    } catch (DbException ex) {
                        LogUtil.e(ex.getMessage(), ex);
                    }
                }
            })),

    COOKIE(new DbManager.DaoConfig()
            .setDbName("dbLite_http_cookie.db")
            .setDbVersion(1)
            .setDbOpenListener(new DbManager.DbOpenListener() {
                @Override
                public void onDbOpened(DbManager db) {
                    db.getDatabase().enableWriteAheadLogging();
                }
            })
            .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                @Override
                public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                    try {
                        // 默认删除所有表
                        db.dropDb();
                    } catch (DbException ex) {
                        LogUtil.e(ex.getMessage(), ex);
                    }
                }
            }));

    private DbManager.DaoConfig config;

    DbConfigs(DbManager.DaoConfig config) {
        this.config = config;
    }

    public DbManager.DaoConfig getConfig() {
        return config;
    }
}
