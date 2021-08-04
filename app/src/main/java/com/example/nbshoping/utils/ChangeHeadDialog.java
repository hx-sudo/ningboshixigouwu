package com.example.nbshoping.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.nbshoping.R;

public class ChangeHeadDialog extends Dialog {
    Button camerbtn,localbtn,cancelbtn;
    public ChangeHeadDialog(@NonNull Context context) {
        super(context);
    }
    public interface OnChangeheadListener{
        public void clickCamear();
        public void clickLocal();
    }
    OnChangeheadListener onChangeheadListener;

    public void setOnChangeheadListener(OnChangeheadListener onChangeheadListener) {
        this.onChangeheadListener = onChangeheadListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_changehead);
        initView();
    }

    private void initView() {
        camerbtn=findViewById(R.id.dialog_chead_btn1);
        localbtn=findViewById(R.id.dialog_chead_btn2);
        cancelbtn=findViewById(R.id.dialog_chead_btn3);
        camerbtn.setOnClickListener(listener);
        localbtn.setOnClickListener(listener);
        cancelbtn.setOnClickListener(listener);

    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.dialog_chead_btn1:
                    if (onChangeheadListener!=null) {
                        onChangeheadListener.clickCamear();
                        cancel();
                    }
                    break;
                case R.id.dialog_chead_btn2:
                    if (onChangeheadListener!=null) {
                        onChangeheadListener.clickLocal();
                    }
                    break;
                case  R.id.dialog_chead_btn3:
                    cancel();
                    break;

            }

        }
    };
    /*底部弹出*/
    //设置对话框宽度和屏幕宽度一致
    public void setDialogWidth() {
        Window window = getWindow();   //当前屏幕窗口对象
        WindowManager.LayoutParams wlp = window.getAttributes();  //获取窗口信息参数
        //获取屏幕宽度
        Display d = window.getWindowManager().getDefaultDisplay();
        wlp.width = (int) (d.getWidth());   //对话框窗口宽度为屏幕窗口宽度
        wlp.gravity = Gravity.BOTTOM;    //从底部弹出对话框
        window.setBackgroundDrawableResource(android.R.color.transparent);   //设置窗口背景透明
        window.setAttributes(wlp);

    }
}
