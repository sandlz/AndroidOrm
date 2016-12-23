package me.sandlz.androidorm.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.sandlz.androidorm.R;
import me.sandlz.androidorm.dao.UserDao;
import me.sandlz.androidorm.entity.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_add;
    private Button btn_delete;
    private Button btn_update;
    private Button btn_query;
    private TextView tv_spend;

    private RecyclerView recyclerView;

    private UserDao userDao = new UserDao();

    private List<User> dataList;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        initView();
        initListener();
        initRecyclerView();
    }

    private void initView() {
        tv_spend    = (TextView)findViewById(R.id.db_spend);
        btn_add     = (Button) findViewById(R.id.db_add);
        btn_delete  = (Button) findViewById(R.id.db_delete);
        btn_update  = (Button) findViewById(R.id.db_update);
        btn_query   = (Button) findViewById(R.id.db_query);
        recyclerView= (RecyclerView) findViewById(R.id.db_recycler);
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        if (null == dataList) {
            dataList = new ArrayList<>();
        }
        userAdapter = new UserAdapter(dataList);
        recyclerView.setAdapter(userAdapter);
    }

    private void initListener() {
        btn_add.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_query.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.db_add:
                add();
                break;
            case R.id.db_delete:
                delete();
                break;
            case R.id.db_update:
                update();
                break;
            case R.id.db_query:
                query();
                break;
        }
    }

    private void add() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User user = new User((1+i)+"",i+1,"sandlz"+(i+1));
            users.add(user);
        }
        long startTime = System.currentTimeMillis();
        userDao.addUser(users);
        userAdapter.setNewData(users);
        recyclerView.scrollToPosition(0);
        tv_spend.setText("新增100条数据耗时："+(System.currentTimeMillis()-startTime)+" ms");
    }

    private void delete() {
        long startTime = System.currentTimeMillis();
        userDao.deleteAllUser();
        userAdapter.setNewData(new ArrayList<User>());
        tv_spend.setText("删除100条数据耗时："+(System.currentTimeMillis()-startTime)+" ms");
    }

    private void update() {
        User user = new User("1",1,"SandLZ");
        long startTime = System.currentTimeMillis();
        userDao.updateUser(user);
        List<User> users = userDao.queryAllUsers();
        if (null != users) {
            userAdapter.setNewData(users);
            recyclerView.scrollToPosition(0);
        }else {
            userAdapter.setNewData(new ArrayList<User>());

        }        tv_spend.setText("更新1条数据耗时："+(System.currentTimeMillis()-startTime)+" ms");
    }

    private void query() {
        long startTime = System.currentTimeMillis();
        List<User> users = userDao.queryAllUsers();
        if (null != users) {
            userAdapter.setNewData(users);
            recyclerView.scrollToPosition(0);
        }else {
            userAdapter.setNewData(new ArrayList<User>());
        }
        tv_spend.setText("查询100条数据耗时："+(System.currentTimeMillis()-startTime)+" ms");
    }


}
