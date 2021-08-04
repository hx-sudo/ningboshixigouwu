package com.example.nbshoping.me;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nbshoping.R;
import com.example.nbshoping.fpage.CategoryActivity;
import com.example.nbshoping.goods.GoodsBean;
import com.example.nbshoping.goods.GoodsTypeAdapter;
import com.example.nbshoping.utils.URLUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SphstAdapter extends BaseAdapter {
    Context context;
    List<SphstBean.DataBean> data;
    public SphstAdapter(Context context, List<SphstBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
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
        VH vh=null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_sphst,parent,false);
            vh=new VH(convertView);
            convertView.setTag(vh);

        }else {
            vh=(VH) convertView.getTag();
        }
        SphstBean.DataBean dataBean=data.get(position);
        vh.nameTv.setText(dataBean.getCommodityName());
        vh.infoTv.setText(dataBean.getCommodityInfo());
        vh.priceTv.setText("￥"+String.valueOf(dataBean.getUnivalence()));
        vh.numTv.setText("x"+dataBean.getCount());
        vh.moneyTv.setText("实付：￥"+dataBean.getTotalprices()+"（免运费）");

        String photo=dataBean.getCommodityPhoto();//设置网络图片,先获取路径
        if (!TextUtils.isEmpty(photo)) {
            String photourl= URLUtils.PUBLIC_URL+photo;
            Picasso.with(context).load(photourl).into(vh.iv);//加载图片到视图
        }

//        convertView.setOnClickListener(new View.OnClickListener() {//添加监听
//            @Override
//            public void onClick(View v) {
//                int id=dataBean.getId();
////                Intent intent=new Intent(context, CategoryActivity.class);
////                intent.putExtra("id",id);
////                context.startActivity(intent)
//                Toast.makeText(context.getApplicationContext(), "当前服务器不支持！", Toast.LENGTH_SHORT).show();
//
//
//            }
//        });


        return convertView;
    }

    class VH {
        TextView nameTv, infoTv, priceTv, numTv, moneyTv;
        ImageView iv;

        public VH(View v) {
            nameTv = v.findViewById(R.id.item_sphst_tv_name);
            priceTv = v.findViewById(R.id.item_sphst_tv_price);
            infoTv = v.findViewById(R.id.item_sphst_tv_info);
            numTv = v.findViewById(R.id.item_sphst_tv_num);
            moneyTv = v.findViewById(R.id.item_sphst_tv_money);
            iv = v.findViewById(R.id.item_sphst_iv);
        }
    }
}
