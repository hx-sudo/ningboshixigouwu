package com.example.nbshoping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.nbshoping.login.UpPwdVaActivity;
import com.example.nbshoping.utils.SaveUserUtils;
import com.example.nbshoping.utils.URLUtils;

public class SetActivity extends AppCompatActivity {

    ImageView setbackiv;
    LinearLayout pwdlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setmain);
        initView();
    }

    private void initView() {
        setbackiv = findViewById(R.id.set_iv_back);
        pwdlayout = findViewById(R.id.set1_pwd_layuot);

        setbackiv.setOnClickListener(onClickListener);
        pwdlayout.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.set_iv_back:
                    finish();
                    break;
                case R.id.set1_pwd_layuot:
                    //密码设置
                    if (SaveUserUtils.getUserInfo(getApplicationContext()) == null) {
                        Toast.makeText(getApplicationContext(), "没有获取到账户信息,请先登录!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    Intent intent = new Intent(SetActivity.this, UpPwdVaActivity.class);
                    startActivity(intent);
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "当前功能还在完善中！", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };


}