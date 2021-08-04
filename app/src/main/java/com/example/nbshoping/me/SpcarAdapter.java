package com.example.nbshoping.me;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nbshoping.R;
import com.example.nbshoping.utils.URLUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpcarAdapter extends BaseAdapter {
    Context context;
    List<SphstBean.DataBean> data;
    // 用来控制CheckBox的选中状况
    private  HashMap<Integer, Boolean> isSelected;

    public SpcarAdapter(Context context, List<SphstBean.DataBean> data,HashMap<Integer, Boolean> isSelected) {
        this.context = context;
        this.data = data;
        this.isSelected = isSelected;
    }

    // 初始化isSelected的数据,初始都未选
    private void initSecDate() {
        for (int i = 0; i < data.size(); i++) {
            isSelected.put(i, false);
        }
    }


    @Override
    public int getCount() {
        Log.i("data.size", String.valueOf(data.size()));
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH vh = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_spcar, parent, false);
            vh = new VH(convertView);
            convertView.setTag(vh);
        } else {
            vh = (VH) convertView.getTag();
        }
        //fixme
//        Log.i("hash.size", String.valueOf(isSelected.size()));
        SphstBean.DataBean dataBean = data.get(position);
        vh.nameTv.setText(dataBean.getCommodityName());
        vh.infoTv.setText(dataBean.getCommodityInfo());
        vh.priceTv.setText("￥" + String.valueOf(dataBean.getUnivalence()));
        vh.numTv.setText("x" + dataBean.getCount());
        // 根据isSelected来设置checkbox的选中状况 todo
        vh.checkBox.setChecked(isSelected.get(position));


        String photo = dataBean.getCommodityPhoto();//设置网络图片,先获取路径
        if (!TextUtils.isEmpty(photo)) {
            String photourl = URLUtils.PUBLIC_URL + photo;
            Picasso.with(context).load(photourl).into(vh.iv);//加载图片到视图
        }
        return convertView;
    }


//    public  HashMap<Integer, Boolean> getIsSelected() {
//        return isSelected;
//    }

    public  void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        this.isSelected = isSelected;
    }


    class VH {

        TextView nameTv, infoTv, priceTv, numTv;
        ImageView iv;
        CheckBox checkBox;

        public VH(View v) {
            nameTv = v.findViewById(R.id.item_spcar_tv_name);
            priceTv = v.findViewById(R.id.item_spcar_tv_price);
            infoTv = v.findViewById(R.id.item_spcar_tv_info);
            numTv = v.findViewById(R.id.item_spcar_tv_num);
            iv = v.findViewById(R.id.item_spcar_iv);
            checkBox = v.findViewById(R.id.item_spcar_select);
        }
    }
}
