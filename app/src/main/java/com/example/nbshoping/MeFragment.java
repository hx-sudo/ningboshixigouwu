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

import com.example.nbshoping.login.LoginRegActivity;
import com.example.nbshoping.login.PersonCenterActivity;
import com.example.nbshoping.login.UserBean;
import com.example.nbshoping.utils.SaveUserUtils;


public class MeFragment extends Fragment {
    private UserBean.DataBean userInfo;//之前登陆时保存的用户信息

    Button spcarbtn, sphistorybtn, personbtn, aboutbtn, setbtn;//购物车，历史采购，个人中心，关于，设置按钮
    ImageView headiv;//头像

//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//    private String mParam1;
//    private String mParam2;

//    public MeFragment() {
//        // Required empty public constructor
//    }
//
//
//    public static MeFragment newInstance(String param1, String param2) {
//        MeFragment fragment = new MeFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        userInfo = SaveUserUtils.getUserInfo(getContext());

        initView(view);
        return view;
    }

    private void initView(View view) {
        spcarbtn = view.findViewById(R.id.me_spcar_btn);
        sphistorybtn = view.findViewById(R.id.me_sphistory_btn);
        personbtn = view.findViewById(R.id.me_person_btn);
        aboutbtn = view.findViewById(R.id.me_about_btn);
        setbtn = view.findViewById(R.id.me_set_btn);
        headiv = view.findViewById(R.id.me_head_image);
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
                    break;
                case R.id.me_head_image:
                    break;

            }

        }
    };
}