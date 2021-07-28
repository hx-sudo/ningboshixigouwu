package com.example.nbshoping.utils;

import android.util.Log;

import androidx.fragment.app.Fragment;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


import java.util.Map;
import java.util.Set;
/*
 * 封装联网请求
 * 获取网络数据的工具Fragment类，做父类
 *
 */

public class BaseFragment extends Fragment implements Callback.CommonCallback<String> {
    private String TAG="huxin123";

    //请求
    public void postNetwork(String url, Map<String, String> map) {
        //将url封装到请求参数中
        RequestParams requestParams = new RequestParams(url);
        //提交的键值对放到请求参数中
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            String val = map.get(key);
            requestParams.addParameter(key, val);

        }
        requestParams.setAsJsonContent(true);//设置内容，形式
        requestParams.setBodyContentType("application/json;charset=utf-8");
        x.http().post(requestParams, this);//传参请求
    }

    //获取
    public void getNetword(String url)
    {
        //将url封装到请求参数中
        RequestParams requestParams = new RequestParams(url);
        x.http().get(requestParams,this);
    }

    @Override
    public void onSuccess(String result) {
        //获取数据成功
        Log.i(TAG,"onSuccess:"+result);
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        //获取数据失败
        Log.i(TAG,"onError:"+ex.getMessage());

    }

    @Override
    public void onCancelled(CancelledException cex) {
        //获取数据取消
        Log.i(TAG,"onCancelled:"+cex.getMessage());

    }

    @Override
    public void onFinished() {
        //获取数据完成
    }
}
