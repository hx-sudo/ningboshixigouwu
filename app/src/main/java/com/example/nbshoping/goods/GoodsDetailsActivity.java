package com.example.nbshoping.goods;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nbshoping.R;
import com.example.nbshoping.login.UserBean;
import com.example.nbshoping.utils.BaseActivity;
import com.example.nbshoping.utils.URLUtils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.xutils.http.RequestParams;
import org.xutils.x;

/*展示商品详情*/
public class GoodsDetailsActivity extends BaseActivity {
    private ProductiveBean.DataBean productiveInfo;//商品信息
    ImageView goodsIv,back,store,customer,buycar;
    TextView goodsNameTv, goodsCountTv, goodsPriceTv, goodsDetailinfpTv;
    TextView share,addcar,buy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        int goodsId = this.getIntent().getIntExtra("goodsId", -1);//-1异常
        if (goodsId==-1)
            Log.i("异常","开启商品详情异常");

        initView();

        showInfoInit(goodsId);//联网，获取商品详细信息,初始界面商品信息
//        getNetword(URLUtils.queryCommodityInfo_url+String.valueOf(goodsId));
        //设置点击事件
        setListener();


    }

    private void setListener() {
    }


    private void showInfoInit(int goodsId) {
        Log.i("货物id：" , String.valueOf(goodsId));
        RequestParams requestParams = new RequestParams(URLUtils.queryCommodityInfo_url+goodsId);
        //提交的键值对放到请求参数中
        //提交的键值对放到请求参数中
        requestParams.setAsJsonContent(true);//设置内容，形式
        requestParams.setBodyContentType("application/json;charset=utf-8");
        Log.i("url",requestParams.getUri().toString());
        x.http().get(requestParams, new CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //请求成功，数据解析，通过解析类，解析工具生成解析类UserBean
                ProductiveBean goodsBean = new Gson().fromJson(result, ProductiveBean.class);
                switch (goodsBean.getCode()) {
                    case 200:
                        productiveInfo = goodsBean.getData();//id查找商品信息数据//id查找商品信息数据
                        /*展示商品信息*/
                        goodsNameTv.setText(productiveInfo.getName());
                        goodsCountTv.setText("库存量:"+String.valueOf(productiveInfo.getCount()));
                        goodsPriceTv.setText("￥" + String.valueOf(productiveInfo.getPrice()) + "元");
                        goodsDetailinfpTv.setText(productiveInfo.getInfo());
                        String photo = productiveInfo.getPhoto();//设置网络图片,先获取路径
                        if (!TextUtils.isEmpty(photo)) {
                            String photourl = URLUtils.PUBLIC_URL + photo;
                            Picasso.with(getApplicationContext()).load(photourl).into(goodsIv);//加载图片到视图
                        }
                        break;
                    case 201:
                        Toast.makeText(getApplicationContext(), "查询失败！", Toast.LENGTH_SHORT).show();
                        break;
                    default: //其他失败
                        Toast.makeText(getApplicationContext(), "参数错误！", Toast.LENGTH_SHORT).show();
                        break;
                }
                Log.i("TAG","onSuccess:"+result);
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getApplicationContext(), "a未知问题，检查网络等！", Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });

    }



    private void initView() {

        goodsIv = findViewById(R.id.detail_goods_img_iv);//内容
        goodsNameTv = findViewById(R.id.detail_goods_name_tv);
        goodsCountTv = findViewById(R.id.detail_goods_count_tv);
        goodsPriceTv = findViewById(R.id.detail_goods_price_tv);
        goodsDetailinfpTv = findViewById(R.id.detail_goodsinfo_tv);

        back=findViewById(R.id.details_iv_back);//点击
        store=findViewById(R.id.detail_store);
        share=findViewById(R.id.details_tv_share);
        addcar=findViewById(R.id.detail_addcar);
        buy=findViewById(R.id.detail_buy);
        customer=findViewById(R.id.detail_customer);
        buycar=findViewById(R.id.detail_shopingcar);


        back.setOnClickListener(onClickListener);
        store.setOnClickListener(onClickListener);
        share.setOnClickListener(onClickListener);
        addcar.setOnClickListener(onClickListener);
        buy.setOnClickListener(onClickListener);
        customer.setOnClickListener(onClickListener);
        buycar.setOnClickListener(onClickListener);


    }

    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.details_iv_back:
                    finish();
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "该功能正在完善中！", Toast.LENGTH_SHORT).show();
                    break;


            }
        }
    };

//    @Override
//    public void onSuccess(String result) {
//        super.onSuccess(result);
//        ProductiveBean bean = new Gson().fromJson(result, ProductiveBean.class);
//        productiveInfo = bean.getData();//id查找商品信息数据
//        switch (bean.getCode()) {
//            case 200:
//                /*展示商品信息*/
////                goodsNameTv.setText(productiveInfo.getName());
////                goodsCountTv.setText("库存量:"+String.valueOf(productiveInfo.getCount()));
////                goodsPriceTv.setText("￥" + String.valueOf(productiveInfo.getPrice()) + "元");
////                goodsDetailinfpTv.setText(productiveInfo.getInfo());
////                String photo = productiveInfo.getPhoto();//设置网络图片,先获取路径
////                if (!TextUtils.isEmpty(photo)) {
////                    String photourl = URLUtils.PUBLIC_URL + photo;
////                    Picasso.with(getApplicationContext()).load(photourl).into(goodsIv);//加载图片到视图
////                }
//                Toast.makeText(getApplicationContext(), ".......！", Toast.LENGTH_SHORT).show();
//
//                break;
//            case 201: //
//                Toast.makeText(getApplicationContext(), "查询失败！", Toast.LENGTH_SHORT).show();
//                break;
//            default: //
//                Toast.makeText(getApplicationContext(), "参数错误！", Toast.LENGTH_SHORT).show();
//                break;
//        }
//
//
//    }





}