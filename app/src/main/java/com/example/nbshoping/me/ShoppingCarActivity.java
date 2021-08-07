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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ShoppingCarActivity extends BaseActivity implements SpcarAdapter.InnerItemOnclickListener,
        AdapterView.OnItemClickListener {
    List<SphstBean.DataBean> data;//数据源
    SpcarAdapter spcarAdapter;//适配器
    ListView spcarLv;
    ImageView backiv;
    TextView deleteTv;
    private UserBean.DataBean userInfo = null;//之前登陆时保存的用户信息

    CheckBox allCB;//全选
    TextView ensureTv, moneyTv;//购买总金额
    double money = 0.0;//购买总金额
    private boolean ismChecked = false;//全选
    // 用来控制CheckBox的选中状况
    private HashMap<Integer, Boolean> isSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);
        userInfo = SaveUserUtils.getUserInfo(this);
        initView();
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

        //设置点击事件listview fix
//        setListener();
        myClick();
        spcarLv.setOnItemClickListener(this);
        spcarAdapter.setOnInnerItemOnClickListener(this);


    }

    //显示总金额
    private void showMoney(Double money) {
        moneyTv.setText("￥" + money);
    }


    @Override
    public void onSuccess(String result) {
        super.onSuccess(result);
        SphstBean goodsBean = new Gson().fromJson(result, SphstBean.class);
        List<SphstBean.DataBean> list = goodsBean.getData();
        if (goodsBean.getCode()==201) {
            Toast.makeText(getApplicationContext(), "购物车空了！", Toast.LENGTH_SHORT).show();
            return;
        }
        data.addAll(list);
        for (int i = 0; i < data.size(); i++) {
            isSelected.put(i, false);
        }
        money = 0.0;
        spcarAdapter.notifyDataSetChanged();
    }


    private void initView() {
        backiv = findViewById(R.id.spcar_iv_back);
        allCB = findViewById(R.id.spcar_allselect);
        ensureTv = findViewById(R.id.spcar_buy);
        moneyTv = findViewById(R.id.spcar_totalmoney);
        deleteTv = findViewById(R.id.spcar_tv_delete);


        ensureTv.setOnClickListener(listener);
        backiv.setOnClickListener(listener);
        deleteTv.setOnClickListener(listener);

    }


    /**
     * java
     * <p>
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    public double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }


    //购买和全选点击事件
    private void myClick() {
//      外面的全选
        allCB.setOnClickListener(new View.OnClickListener() {
            //setChecked 无监听
            @Override
            public void onClick(View v) {
                //通过checkbox的isChecked()方法判断是否选中
                CheckBox checkBox = v.findViewById(R.id.spcar_allselect);
                boolean isChecked = checkBox.isChecked();
                ismChecked = isChecked;
//              全选是选中状态
                if (isChecked) {
                    money = 0.0;
                    for (int i = 0; i < data.size(); i++) {
                        isSelected.put(i, true);
                        money = add(money, data.get(i).getTotalprices());
                        Log.i("monet", "" + money);
                    }
                    showMoney(money);
                    spcarAdapter.notifyDataSetChanged();

                } else {
                    for (int i = 0; i < data.size(); i++) {
                        isSelected.put(i, false);
                    }
                    money = 0.0;
                    Log.i("monet", "" + money);
                    showMoney(money);
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
                //网络请求数据格式
                Pa p = new Pa(String.valueOf(dataBean.getId()), String.valueOf(userInfo.getId()),
                        String.valueOf(dataBean.getCommodityId()), String.valueOf(dataBean.getCount()));
                list.add(p);
            }
        }
        String s = new Gson().toJson(list);
        Log.i("root", "gson: s===" + s);
        Log.i("root", "list: s===" + list);

        RequestParams requestParams = new RequestParams(URLUtils.orderShopping_url);
        requestParams.setBodyContent(s);
        requestParams.setAsJsonContent(true);//设置内容，形式
        Log.i("url", requestParams.getUri());
        requestParams.setBodyContentType("application/json;charset=utf-8");
        x.http().post(requestParams, new CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("result",result);

                Toast.makeText(getApplicationContext(), "结算购买成功！", Toast.LENGTH_SHORT).show();

                //整合购物车剩余内存数据，显示
                for (int i = data.size() - 1; i >= 0; i--) {
                    if (isSelected.get(i) == true) {
                        data.remove(i);
                    }
                }

                isSelected.clear();
                for (int i = 0; i < data.size(); i++) {
                    isSelected.put(i, false);
                }
                spcarAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("eror", ex.getMessage());
                Toast.makeText(getApplicationContext(), "结算购买失败！", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });//传参请求


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e("整体item----->", position + "");

    }

    @Override
    public void itemClick(View v) {
        int position;
        position = (Integer) v.getTag();
        switch (v.getId()) {
            case R.id.item_spcar_select:
                if (ismChecked)
                    allCB.setChecked(false);
                CheckBox checkBox = v.findViewById(R.id.item_spcar_select);
                if (checkBox.isChecked()) {
                    isSelected.put(position, true);
                    money = add(money, data.get(position).getTotalprices());
                    Log.i("monet", "" + money);

                    showMoney(money);
                } else {
                    isSelected.put(position, false);
                    money = sub(money, data.get(position).getTotalprices());
                    Log.i("monet", "" + money);

                    showMoney(money);
                }

                Log.e("内部item--checkbox-->", position + "");
                Log.e("内部item--checkbox-->", position + " " + checkBox.isChecked());

                break;
            case R.id.item_spcar_goodslayout:
                Log.e("内部item--goods-->", position + "");
                break;
            default:
                break;
        }

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