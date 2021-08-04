package com.example.nbshoping.me;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nbshoping.R;
import com.example.nbshoping.login.UserBean;
import com.example.nbshoping.utils.BaseActivity;
import com.example.nbshoping.utils.SaveUserUtils;
import com.example.nbshoping.utils.URLUtils;
import com.google.gson.Gson;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ShoppingCarActivity extends BaseActivity {
    List<SphstBean.DataBean> data;
    SpcarAdapter spcarAdapter;//适配器
    ListView spcarLv;
    ImageView backiv;
    private UserBean.DataBean userInfo = null;//之前登陆时保存的用户信息

    CheckBox allCB;//全选
    TextView ensureTv, moneyTv;//购买总金额
    private boolean ismChecked = false;//全选
    // 用来控制CheckBox的选中状况
    private HashMap<Integer, Boolean> isSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);
        userInfo = SaveUserUtils.getUserInfo(this);
        initView();
        //当前右侧商品信息fragmment中的list
        spcarLv = findViewById(R.id.spcar_listv);//listview
        //数据源
        data = new ArrayList<>();
        isSelected = new HashMap<Integer, Boolean>();
        //适配器
        spcarAdapter = new SpcarAdapter(getApplicationContext(), data, isSelected);
        spcarLv.setAdapter(spcarAdapter);
        //联网
        Log.i("url", URLUtils.queryShoppingCar_url + userInfo.getId());
        getNetword(URLUtils.queryShoppingCar_url + userInfo.getId());

        //设置点击事件listview
        setListener();
        myClick();

    }


    @Override
    public void onSuccess(String result) {
        super.onSuccess(result);
        SphstBean goodsBean = new Gson().fromJson(result, SphstBean.class);
        List<SphstBean.DataBean> list = goodsBean.getData();
        data.addAll(list);
        for (int i = 0; i < data.size(); i++) {
            isSelected.put(i, false);
        }
        spcarAdapter.notifyDataSetChanged();
    }

    /*给listview每一项设置点击事件*/
    private void setListener() {


        //todo 购物车单项点击，checkbox 修改isSelect
        spcarLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

    private void initView() {
        backiv = findViewById(R.id.spcar_iv_back);
        allCB = findViewById(R.id.spcar_allselect);
        ensureTv = findViewById(R.id.spcar_buy);
        moneyTv = findViewById(R.id.spcar_totalmoney);


        ensureTv.setOnClickListener(listener);
        backiv.setOnClickListener(listener);


    }

    private void myClick() {
//      外面的全选
        allCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ismChecked = isChecked;
//              全选是选中状态
                if (isChecked) {
                    for (int i = 0; i < data.size(); i++) {
                        isSelected.put(i, true);
                    }
                    spcarAdapter.notifyDataSetChanged();

                } else {
                    for (int i = 0; i < data.size(); i++) {
                        isSelected.put(i, false);
                    }
                    spcarAdapter.notifyDataSetChanged();

                }
            }
        });

    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.spcar_iv_back:
                    finish();
                    break;
                case R.id.spcar_buy:
                    carBuy();
                    break;

                default:
                    Toast.makeText(getApplicationContext(), "当前功能正在完善中！", Toast.LENGTH_SHORT).show();

            }
        }
    };

    /*购物车点击购买*/
    private void carBuy() {
        if (!isSelected.containsValue(true)) {
            Toast.makeText(getApplicationContext(), "请选择商品！", Toast.LENGTH_SHORT).show();
            return;
        }
        //网络参数
        List<Pa> list = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            if (isSelected.get(i) == true) {
                SphstBean.DataBean dataBean = data.get(i);
                Pa p = new Pa(String.valueOf(dataBean.getId()), String.valueOf(dataBean.getUserId()),
                        String.valueOf(dataBean.getCommodityId()), String.valueOf(dataBean.getCount()));

                data.remove(i);
                list.add(p);
            }
        }
        String s = new Gson().toJson(list);

        RequestParams requestParams = new RequestParams(URLUtils.orderShopping_url);
        requestParams.setBodyContent(s);
        requestParams.setAsJsonContent(true);//设置内容，形式
        requestParams.setBodyContentType("application/json;charset=utf-8");
        x.http().post(requestParams, new CommonCallback<Object>() {
            @Override
            public void onSuccess(Object result) {
                Toast.makeText(getApplicationContext(), "结算购买成功！", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < data.size(); i++) {
                    isSelected.put(i, false);
                }
                spcarAdapter.notifyDataSetChanged();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });//传参请求


    }

    class Pa {
        String id, userId, commodityId, count;

        public Pa(String id, String userId, String commodityId, String count) {
            this.id = id;
            this.userId = userId;
            this.commodityId = commodityId;
            this.count = count;
        }
    }
}