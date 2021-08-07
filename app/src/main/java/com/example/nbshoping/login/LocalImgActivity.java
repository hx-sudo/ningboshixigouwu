package com.example.nbshoping.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.nbshoping.R;

import java.util.ArrayList;
import java.util.List;


/*头像选取本地照片*/
public class LocalImgActivity extends AppCompatActivity {
    GridView gv;
    List<String> datas;
    LocalImgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_img);
        gv=findViewById(R.id.localimg_gv);
        //数据源
        datas=new ArrayList<>();
        //适配器
        adapter=new LocalImgAdapter(this,datas);
        gv.setAdapter(adapter);
        //获取本地数据，存放到数据源中
        loadImgData();
        //为每个item设置点击事件
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取图片路径，设置返回数据
                String path=datas.get(position);
                Intent intent =new Intent();
                intent.putExtra("fpath",path);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }

    /* 获取本地数据：sd卡当中的图片信息*/
    private void loadImgData() {
        ContentResolver resolver=getContentResolver();//内容接收者
        Uri uri= MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor=resolver.query(uri,null,null,
        null,null);

        while (cursor.moveToNext()){
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            //图片路径
            Log.i("图片路径","localImgData:path=="+path);
            datas.add(path);
        }
        adapter.notifyDataSetChanged();

    }
}