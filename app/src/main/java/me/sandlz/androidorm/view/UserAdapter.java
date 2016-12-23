package me.sandlz.androidorm.view;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import me.sandlz.androidorm.R;
import me.sandlz.androidorm.entity.User;

/**
 * Created by liuzhu on 2016/12/23.
 * Description :
 * Usage :
 */
public class UserAdapter extends BaseQuickAdapter<User,BaseViewHolder>{

    public UserAdapter( List<User> data) {
        super(R.layout.item_user, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, User user) {
        if (null != user) {
            holder.setText(R.id.item_user_id,user.getUserId());
            holder.setText(R.id.item_user_age,user.getAge()+"");
            holder.setText(R.id.item_user_name,user.getName());
        }
    }
}
