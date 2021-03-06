package com.example.graduationproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.graduationproject.R;
import com.example.graduationproject.activity.ContentActivity;
import com.example.graduationproject.bean.ListItemBean;
import com.example.graduationproject.bean.UserCreditBean;


import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by csn on 2018/3/14.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> { //每项文字资源内容适配器

    private List<ListItemBean> list;
    private Context context;

    public MyRecyclerAdapter(List<ListItemBean> list, Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //Log.e("onBindViewHolder: ", String.valueOf(position)); 证明RecyclerView嵌套在NestedScrollView里时会一次性渲染全部的item
        holder.userImageView.setImageResource(list.get(position).getUserPhotoId());
        holder.userIdView.setText(list.get(position).getUserName());
        //信用值结果由数据库里查询得到
        holder.userCreditView.setText(String.valueOf(DataSupport.where("userName = ?", list.get(position).getUserName()).findFirst(UserCreditBean.class).getUserCredit()));
        holder.textView.setText(list.get(position).getContent());
        holder.agreeView.setText(String.valueOf(list.get(position).getItemAgree()));
        holder.disagreeView.setText(String.valueOf(list.get(position).getItemDisagree()));
        holder.commentView.setText(String.valueOf(list.get(position).getItemComment()));
        holder.timeView.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(list.get(position).getSendTime()));
        holder.cardView.setOnClickListener(new View.OnClickListener() { //启动具体内容界面
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ContentActivity.class);
                intent.putExtra("content", list.get(holder.getAdapterPosition())); //不用list.get(position)，处理点击项显示的和点击位置不一样的问题
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private CircleImageView userImageView;
        private TextView userIdView;
        private TextView userCreditView;
        private TextView textView;
        private TextView agreeView;
        private TextView disagreeView;
        private TextView commentView;
        private TextView timeView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            userImageView = itemView.findViewById(R.id.user_image);
            userIdView = itemView.findViewById(R.id.user_name);
            userCreditView = itemView.findViewById(R.id.user_credit);
            textView = itemView.findViewById(R.id.text_view);
            agreeView = itemView.findViewById(R.id.num_of_agree);
            disagreeView = itemView.findViewById(R.id.num_of_disagree);
            commentView = itemView.findViewById(R.id.num_of_comment);
            timeView = itemView.findViewById(R.id.send_time);
        }
    }
}
