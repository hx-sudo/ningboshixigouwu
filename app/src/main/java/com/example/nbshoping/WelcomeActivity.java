package com.example.nbshoping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;

import java.util.ArrayList;
import java.util.List;

/*应用欢迎界面
 * 动态权限的申请
 * 1.清单文件申明
 * 2.检查手机是否有权限，6.8版本不需申请，23以上需要
 *
 * */
public class WelcomeActivity extends AppCompatActivity {
    String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};

    List<String> mpermissionList;//将用户没有给的权限放到这个集合中
    int requestcode = 100;//申请权限请求码

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        //定时

        @Override
        public void handleMessage(@NonNull Message msg) {

            if (msg.what == 1) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        if (Build.VERSION.SDK_INT >= 23) {
            //判断手机版本号码
            initpermission();
        }
    }

    //初始化权限
    private void initpermission() {
        mpermissionList = new ArrayList<>();
        //核对用户之前是否打开动态权限
        for (int i = 0; i < permissions.length; i++) {
            String permission = permissions[i];
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {

                //如果没有给权限，将权限添加到集合
                mpermissionList.add(permission);
            }
        }
        if (mpermissionList.size() > 0) {
            //有权限用户没给，需申请
            ActivityCompat.requestPermissions(this, permissions, requestcode);
        } else {
            //需要的权限都给了，跳转到下一个界面
            handler.sendEmptyMessageDelayed(1, 1000);
        }


    }

    //查看用户给权限的结果
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        boolean flag = false;//查看是否所有权限都给了的标志位
        for (int i = 0; i < grantResults.length; i++) {
            int grantResult = grantResults[i];
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                flag = true;
            }
        }
        if (flag) {
            //有权限没有申请到
            showSettingDialog();

        } else {
            //权限都给了
            handler.sendEmptyMessageDelayed(1, 1000);

        }
    }

    private void showSettingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示信息")
                .setMessage("你有权限未打开，是否打开设置界面？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //跳转设置界面
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    }
                })
                .create()
                .show();

    }
}