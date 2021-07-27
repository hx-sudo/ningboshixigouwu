package com.example.nbshoping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RadioGroup rg;//首页，分类，我的
    Button fpagebtn, typebtn, mainbtn, loginbtn, regbtn, canclebtn;//首页，分类，我的，登录，注册，注销
    TextView loginntv;//欢迎登录语句
    DrawerLayout drawerLayout;//首页
    List<Fragment> fragmentList;//将当前acticity包含的fragment放到集合里
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
        initFragment();





    }

    //初始化activity中包含的fragment
    private void initFragment() {
        fragmentList =new ArrayList<>();
        fragmentList.add(new FPageFragment());
        fragmentList.add(new TypeFragment());
        fragmentList.add(new MeFragment());
        //将碎片放到activity中
        fm=getSupportFragmentManager();//管理对象
        FragmentTransaction transaction=fm.beginTransaction();//获取碎片事件对象
        for (int i=0;i<fragmentList.size();i++)
        {
            transaction.add(R.id.main_layout,fragmentList.get(i));//仅需添加一次
            transaction.hide(fragmentList.get(i));//添加后隐藏
        }
        transaction.show(fragmentList.get(0));//默认展示第一个
        transaction.commit();//提交保存
    }

    //切换fragment,首页，分类，我的
    private  void changgeFragment(int pos){
        FragmentTransaction transaction=fm.beginTransaction();//获取碎片事件对象
        for (int i=0;i<fragmentList.size();i++)
        {
            transaction.hide(fragmentList.get(i));//全部隐藏
        }
        transaction.show(fragmentList.get(pos));//展示pos
        transaction.commit();//提交保存

    }

    //设置监听方法
    private void setListener() {
        fpagebtn.setOnClickListener(onClickListener);
        typebtn.setOnClickListener(onClickListener);
        mainbtn.setOnClickListener(onClickListener);
        loginbtn.setOnClickListener(onClickListener);
        regbtn.setOnClickListener(onClickListener);
        canclebtn.setOnClickListener(onClickListener);
        rg.setOnCheckedChangeListener(onCheckedChangeListener);

    }

    //设置监听对象，左侧抽屉按钮
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();

            switch (id){
                case R.id.drawer_btn_fpage:
                    rg.check(R.id.main_rb1);//点击抽屉与首页button对应
                    changgeFragment(0);
                    break;
                case R.id.drawer_btn_type:
                    rg.check(R.id.main_rb2);
                    changgeFragment(1);
                    break;
                case R.id.drawer_btn_main:
                    rg.check(R.id.main_rb3);
                    changgeFragment(2);
                    break;
                case R.id.drawer_btn_login:
                    /*判断是否登录，是跳到个人中心，否跳到登录界面*/
                    // TODO: 2021/7/27
                    //跳转页面
                    Intent intent =new Intent(MainActivity.this,LoginRegActivity.class);
                    intent.putExtra("flag",0);
                    startActivity(intent);

                    break;
                case R.id.drawer_btn_reg:
                    intent =new Intent(MainActivity.this,LoginRegActivity.class);
                    intent.putExtra("flag",1);
                    startActivity(intent);

                    break;
                case R.id.drawer_btn_cancle:
                    break;

            }
            drawerLayout.closeDrawers();//关闭左侧抽屉


        }
    };
    //主页3个button
    RadioGroup.OnCheckedChangeListener onCheckedChangeListener=new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId)//切换主题内容部分
        {
            case R.id.main_rb1:
                changgeFragment(0);
                break;
            case R.id.main_rb2:
                changgeFragment(1);
                break;
            case R.id.main_rb3:
                changgeFragment(2);
                break;

        }
        }
    };



    //查找控件
    private void initView() {
        rg = findViewById(R.id.main_rg);
        fpagebtn = findViewById(R.id.drawer_btn_fpage);
        typebtn = findViewById(R.id.drawer_btn_type);
        mainbtn = findViewById(R.id.drawer_btn_main);
        loginbtn = findViewById(R.id.drawer_btn_login);
        regbtn = findViewById(R.id.drawer_btn_reg);
        canclebtn = findViewById(R.id.drawer_btn_cancle);
        loginntv=findViewById(R.id.drwer_tv_name);
        drawerLayout=findViewById(R.id.main_drawer);
    }





}