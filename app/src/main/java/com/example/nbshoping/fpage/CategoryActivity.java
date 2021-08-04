package com.example.nbshoping.fpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.nbshoping.R;
import com.example.nbshoping.goods.GoodsTypeFragment;

//二级分类页面
public class CategoryActivity extends AppCompatActivity {
    ImageView backiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        backiv = findViewById(R.id.category_iv_back);
        backiv.setOnClickListener(listener);
        int id = getIntent().getIntExtra("id", 1);//获取上一个界面的当前的产品分类
        loadFrag(id);
    }

    private void loadFrag(int id) {
        GoodsTypeFragment fragment = GoodsTypeFragment.newInstance(id + "", "");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();
        transaction.add(R.id.category_layout,fragment);
        transaction.commit();
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();//返回
        }
    };
}