package com.example.nbshoping.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.nbshoping.R;

/*
 * 自定义Dialog:修改个人信息密码的对话框
 * */
public class EditUserDialog extends Dialog implements View.OnClickListener {
    EditText oldEt, newEt, renewEt;//密码框
    Button ensureBtn, cancelBtn;

    //创建被点击确定执行的新的接口
    public interface OnEnsureListener {
        public void onEnsure(String oldpwd,String newpwd,String renewpwd);   //填进去的数据
    }

    OnEnsureListener onEnsureListener;    //声明接口的变量
    public void setOnEnsureListener(OnEnsureListener onEnsureListener) {
        this.onEnsureListener = onEnsureListener;
    }

    public EditUserDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edit_userpwd);//绑定布局

        oldEt = findViewById(R.id.dialog_et_oldpwd);
        newEt = findViewById(R.id.dialog_et_newpwd);
        renewEt = findViewById(R.id.dialog_et_renewpwd);
        ensureBtn = findViewById(R.id.dialog_btn_ensure);
        cancelBtn = findViewById(R.id.dialog_btn_cancel);

        ensureBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_btn_cancel:
                cancel();
                break;
            case R.id.dialog_btn_ensure:
                if (onEnsureListener != null) {
                    String oldpwd = oldEt.getText().toString().trim();
                    String newpwd = newEt.getText().toString().trim();
                    String renewpwd = renewEt.getText().toString().trim();

                    onEnsureListener.onEnsure(oldpwd,newpwd,renewpwd);
                }
                break;
        }
    }

    /* 可以修改edittext的提示的方法*/
    public void setTipHint(String tipHint) {
        oldEt.setHint(tipHint);
        newEt.setHint(tipHint);
        renewEt.setHint(tipHint);
    }

    //    设置对话框宽度和屏幕宽度一致
//    public void setDialogWidth() {
//        Window window = getWindow();   //当前屏幕窗口对象
//        WindowManager.LayoutParams wlp = window.getAttributes();  //获取窗口信息参数
//        //获取屏幕宽度
//        Display d = window.getWindowManager().getDefaultDisplay();
//        wlp.width = (int) (d.getWidth());   //对话框窗口宽度为屏幕窗口宽度
//        wlp.gravity = Gravity.BOTTOM;    //从底部弹出对话框
//        window.setBackgroundDrawableResource(android.R.color.transparent);   //设置窗口背景透明
//        window.setAttributes(wlp);
//        //自动弹出软键盘
//        handler.sendEmptyMessageDelayed(1, 100);
//    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            InputMethodManager manager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    };

}
