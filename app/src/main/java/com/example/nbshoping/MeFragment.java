package com.example.nbshoping;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nbshoping.login.LoginRegActivity;
import com.example.nbshoping.login.PersonCenterActivity;
import com.example.nbshoping.login.UserBean;
import com.example.nbshoping.utils.SaveUserUtils;


public class MeFragment extends Fragment {
    private UserBean.DataBean userInfo;//之前登陆时保存的用户信息

    Button spcarbtn, sphistorybtn, personbtn, aboutbtn, setbtn;//购物车，历史采购，个人中心，关于，设置按钮
    ImageView headiv;//头像
    TextView nickname;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        userInfo = SaveUserUtils.getUserInfo(getContext());
        initView(view);

        return view;
    }

    //我的-昵称显示
    @Override
    public void onResume() {
        super.onResume();
        setWelcomeText();

    }
    private void setWelcomeText() {
        userInfo = SaveUserUtils.getUserInfo(getContext());
        if (userInfo == null) {
            // 当前没有用户信息
            nickname.setText("未登录，请先登录！");
        } else {
            nickname.setText(userInfo.getNickname());
        }
    }



    private void initView(View view) {
        spcarbtn = view.findViewById(R.id.me_spcar_btn);
        sphistorybtn = view.findViewById(R.id.me_sphistory_btn);
        personbtn = view.findViewById(R.id.me_person_btn);
        aboutbtn = view.findViewById(R.id.me_about_btn);
        setbtn = view.findViewById(R.id.me_set_btn);
        headiv = view.findViewById(R.id.me_head_image);
        nickname=view.findViewById(R.id.me_nickname);

        spcarbtn.setOnClickListener(onClickListener);
        sphistorybtn.setOnClickListener(onClickListener);
        personbtn.setOnClickListener(onClickListener);

        aboutbtn.setOnClickListener(onClickListener);
        setbtn.setOnClickListener(onClickListener);
        headiv.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.me_spcar_btn:
                    break;
                case R.id.me_sphistory_btn:
                    break;
                case R.id.me_person_btn:
                    userInfo = SaveUserUtils.getUserInfo(getContext());

                    if (userInfo==null)
                    {
                        //登录界面
                        Intent intent=new Intent(getActivity(), LoginRegActivity.class);
                        intent.putExtra("flag",0);
                        startActivity(intent);
                    }else{
                        //个人中心
                        Intent intent=new Intent(getActivity(), PersonCenterActivity.class);
                        startActivity(intent);
                    }
                    break;
                case R.id.me_about_btn:
                    Intent intent=new Intent(getActivity(), AboutActivity.class);
                    startActivity(intent);
                    break;
                case R.id.me_set_btn:
                    intent=new Intent(getActivity(), SetActivity.class);
                    startActivity(intent);
                    break;
                case R.id.me_head_image:
                    break;

            }

        }
    };
}