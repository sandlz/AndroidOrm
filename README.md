# AndroidOrm
[![Chrome Web Store](https://img.shields.io/chrome-web-store/stars/nimelepbpejjlbmoobocpfnjhihnpked.svg)]()
[![License](http://img.shields.io/badge/License-Apache%202.0-blue.svg?style=flat-square)](http://www.apache.org/licenses/LICENSE-2.0)
[![API](https://img.shields.io/badge/API-15%2B-blue.svg?style=flat-square)](https://developer.android.com/about/versions/android-4.2.html)

sqlite orm.
It'refer to swyouflf's xutils, thank swyouflf's offertory.


## ScreenShot

<div>    
<img align="left" src="http://7xsap2.com1.z0.glb.clouddn.com/dblite_add.png" width = "423" height = "702" />
<img align="right" src="http://7xsap2.com1.z0.glb.clouddn.com/dblite_delete.png" width = "423" height = "702" />
</div>
<div>    
<img align="left" src="http://7xsap2.com1.z0.glb.clouddn.com/dblite_update.png" width = "423" height = "702"  />
<img align="right" src="http://7xsap2.com1.z0.glb.clouddn.com/dblite_query.png" width = "423" height = "702" />
</div>

## 配置

### Gradle

```
compile 'me.sandlz:dblite:1.0.2'
```

### Application
在自定义Application里调用

```
// 初始化数据库
DbLite.init(this,false);
```

### 创建DBHelper类

```
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
```


在合适的地方调用

```
// this这里是在Application
DBHelper2.init(this);
```

## 使用

新增Dao操作类，对外提供增删改查方法，具体可根据需求修改，选择效率最高的方式(请在子线程调用dao方法，防止阻塞主线程，影响用户体验！！！)

最好设置主键，提高操作效率.

### 增

```
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
    
```

### 删

```
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
    
```
### 改

```
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
    
```
### 查

```
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
    
```


