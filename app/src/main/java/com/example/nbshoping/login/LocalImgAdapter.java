package com.example.nbshoping.login;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.nbshoping.R;

import java.util.List;

/*展示本地照片Gridview适配器
* */
public class LocalImgAdapter extends BaseAdapter {
    Context context;
    List<String> datas;

    public LocalImgAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH vh;
        if (convertView == null) {
            convertView= LayoutInflater.from(context).inflate(R.layout.item_localimg,parent,false);
            vh=new VH(convertView);
            convertView.setTag(vh);
        }else {
            vh=(VH)convertView.getTag();
        }
        String path=datas.get(position);
        Bitmap bitmap= BitmapFactory.decodeFile(path);
        vh.iv.setImageBitmap(bitmap);
        return convertView;
    }

    class VH{
        ImageView iv;
        public VH(View v) {
            iv =v.findViewById(R.id.item_localimg_iv) ;
        }
    }


}
