package com.example.nbshoping.fpage;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nbshoping.R;
import com.example.nbshoping.goods.GoodsBean;
import com.example.nbshoping.goods.TypeBean;
import com.example.nbshoping.utils.URLUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/*分类中的商品 适配器
 *编写listview和dridview适配器
 * 继承BaseAdapter
 * 重写4个方法
 * 定义两个变量：context，集合
 * 创建构造方法，变量赋值
 * 前三个方法按套路写，最后一个
 * 创建内部内，item中控件，构造方法传入View，findvi控件
 * */
//首页分类适配器
public class FPtypeAdapter extends BaseAdapter{
    Context context;
    List<TypeBean.DataBean> data;

    public FPtypeAdapter(Context context, List<TypeBean.DataBean> alllist,int page,int pagesize) {

        this.context = context;
        this.data = new ArrayList<>();

        //第0页 0-7
        //第1页 8-15
        int start =page*pagesize;
        int end=(page+1)*pagesize-1;
        if (end>alllist.size()-1) {
            end=alllist.size()-1;
        }
        for (int i = start; i <=end ; i++) {
            TypeBean.DataBean bean=alllist.get(i);
            data.add(bean);

        }
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

        VH vh = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fpage_type, parent, false);
            vh = new VH(convertView);
            convertView.setTag(vh);

        } else {
            vh = (VH) convertView.getTag();
        }
        TypeBean.DataBean dataBean = data.get(position);
        vh.titleTv.setText(dataBean.getName());
        String icon=dataBean.getIcon();
        if (!TextUtils.isEmpty(icon) ) {
            String url=URLUtils.PUBLIC_URL+icon;
            Picasso.with(context).load(url).into(vh.iv);
        }
        convertView.setOnClickListener(new View.OnClickListener() {//添加监听
            @Override
            public void onClick(View v) {
                int id=dataBean.getId();
                Intent intent=new Intent(context,CategoryActivity.class);
                intent.putExtra("id",id);
                context.startActivity(intent);

            }
        });
        return convertView;
    }

    class VH {
        TextView titleTv;
        ImageView iv;

        public VH(View v) {
            titleTv = v.findViewById(R.id.item_type_tv);
            iv = v.findViewById(R.id.item_type_iv);
        }
    }

}
