package com.example.nbshoping.me;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nbshoping.R;
import com.example.nbshoping.goods.GoodsBean;
import com.example.nbshoping.goods.GoodsDetailsActivity;
import com.example.nbshoping.goods.GoodsTypeAdapter;
import com.example.nbshoping.login.LoginRegActivity;
import com.example.nbshoping.login.UserBean;
import com.example.nbshoping.utils.BaseActivity;
import com.example.nbshoping.utils.SaveUserUtils;
import com.example.nbshoping.utils.URLUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.security.AccessController.getContext;

public class ShoppingHstActivity extends BaseActivity {
    List<SphstBean.DataBean> data;
    SphstAdapter sphstAdapter;//适配器
    ListView sphstLv;
    ImageView backiv;

    private UserBean.DataBean userInfo = null;//之前登陆时保存的用户信息


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppinghst);
        userInfo = SaveUserUtils.getUserInfo(this);
        initView();
        //当前右侧商品信息fragmment中的list
        sphstLv = findViewById(R.id.sphst_listv);//listview
        //数据源
        data=new ArrayList<>();
        //适配器
        sphstAdapter = new SphstAdapter(getApplicationContext(), data);
        sphstLv.setAdapter(sphstAdapter);
        //联网
        Log.i("url", URLUtils.queryBougth_url+userInfo.getId() );
        getNetword(URLUtils.queryBougth_url+userInfo.getId());
        //设置点击事件
        setListener();
    }

    @Override
    public void onSuccess(String result) {
        super.onSuccess(result);
        SphstBean goodsBean =new Gson().fromJson(result,SphstBean.class);

        List<SphstBean.DataBean> list=goodsBean.getData();
        Collections.reverse(list);     //实现list集合逆序排列
        data.addAll(list);
        sphstAdapter.notifyDataSetChanged();
    }



    /*给listview每一项设置点击事件*/
    private void setListener() {
        sphstLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("点历史记录：" , String.valueOf(data.get(position).getId()));//商品id
                new AlertDialog.Builder(parent.getContext(), R.style.UpdateDialogStyle)
                        .setTitle("提示信息")
                        .setIcon(R.mipmap.icon)
                        .setMessage("是删除该历史记录？")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Toast.makeText(getApplicationContext(), "当前服务器不支持！", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .create()
                        .show();
            }
        });
    }

    private void initView() {
        backiv = findViewById(R.id.sphst_iv_back);
        backiv.setOnClickListener(listener);
    }
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();//返回
        }
    };
}