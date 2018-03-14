package com.example.graduationproject.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;

import com.example.graduationproject.R;
import com.example.graduationproject.adapter.MyRecyclerAdapter;
import com.example.graduationproject.bean.ListItemBean;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    ScrollView scrollView;
    RecyclerView recyclerView;
    MyRecyclerAdapter myRecyclerAdapter;
    LinearLayoutManager linearLayoutManager;
    FloatingActionButton floatingActionButton;
    List<ListItemBean> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        scrollView = findViewById(R.id.scroll_view);
        recyclerView = findViewById(R.id.recycler_view);
        floatingActionButton = findViewById(R.id.floating_add_button);
        list.add(new ListItemBean("好好读几年书，高考去，考个正经学校。再嫌不系统就考出国去，考个斯坦福伯克利什么的，再系统不过了。\n" +
                "趁年轻，还有时间走正路，不要急着抄捷径。目前编程对你来说就当个爱好玩一玩挺好。"));
        list.add(new ListItemBean("最近一直在思考怎么样快速掌握一门编程语言基础，究竟哪些是入门编程语言的最少必要知识。\n" +
                "\n" +
                "在重新学习了python, javascript之后，我发现掌握基础编程概念后，使用如下套路可快速入门新的编程语言"));
        list.add(new ListItemBean("学英语\n" +
                "考托福\n" +
                "考 SAT\n" +
                "申请美国计算机前 50 的学校 / 加拿大前 10 的学校\n" +
                "好好听课\n" +
                "没了"));
        list.add(new ListItemBean("到现在题主应该还没进入大学吧。没有基础？当然啦，谁一开始都是没有基础的。初中时候，家里没电脑，学校里上微机课我就写QB。当然内容也不是什么高大上的，比如写个剪刀石头布啦、模拟一下游戏里5-20的攻击力是怎么弄出来的、自己再捣鼓一个加幸运的算法进去。高中时期比较忙，泛泛地了解了一下C，只停留在能打印个菱形的水平。\n" +
                "\n" +
                "作者：[已重置]\n" +
                "链接：https://www.zhihu.com/question/28611887/answer/141188181\n" +
                "来源：知乎\n" +
                "著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。"));
        list.add(new ListItemBean("不管做什么事情，兴趣是最重要的，没有兴趣是坚持不下去的。\n" +
                "根据自己的兴趣，选择适合自己的学习路径，掌握一定的学习方法。\n" +
                "最后，坚持+坚持+坚持。"));
        myRecyclerAdapter = new MyRecyclerAdapter(list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myRecyclerAdapter);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, EditActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 0: break;
            case 1:
                list.add(0, (ListItemBean) data.getSerializableExtra("listItemBean"));
                Log.e("123123", String.valueOf(list.size()));
                myRecyclerAdapter.notifyItemInserted(0);
                scrollView.scrollTo(0, 0);
                break;
            default:
                break;
        }
    }
}