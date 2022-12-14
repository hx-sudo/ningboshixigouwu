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
    /*????????????*/
    //??????????????????????????????????????????
    public void setDialogWidth() {
        Window window = getWindow();   //????????????????????????
        WindowManager.LayoutParams wlp = window.getAttributes();  //????????????????????????
        //??????????????????
        Display d = window.getWindowManager().getDefaultDisplay();
        wlp.width = (int) (d.getWidth());   //??????????????????????????????????????????
        wlp.gravity = Gravity.BOTTOM;    //????????????????????????
        window.setBackgroundDrawableResource(android.R.color.transparent);   //????????????????????????
        window.setAttributes(wlp);

    }
}
