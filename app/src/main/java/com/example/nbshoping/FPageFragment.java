package com.example.nbshoping;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 首页碎片
 */
public class FPageFragment extends Fragment {

    private boolean ischange = false;//刷新fragment标志

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    EditText searchEt;//todo


    public FPageFragment() {
        // Required empty public constructor
    }
    public static FPageFragment newInstance(String param1, String param2) {
        FPageFragment fragment = new FPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_fpage, container, false);
        initView(view);
        searchEt.setText("");

        return view;
    }

    @Override
    public void onResume() {

        super.onResume();

    }
    @Override
    public void onPause() {
        super.onPause();
    }

    /*切换刷新*/
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
            if (hidden) {
                //隐藏时所作的事情
                searchEt.setText("");//搜索框恢复
            } else {
                //显示时所作的事情

            }


    }

    private void initView(View view) {
        searchEt = view.findViewById(R.id.fpage_sc_et);

        searchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if ((actionId == EditorInfo.IME_ACTION_UNSPECIFIED || actionId == EditorInfo.IME_ACTION_SEARCH) && keyEvent != null) {
                    //点击搜索要做的操作
                    search();
                    return true;
                }
                return false;
            }
        });

    }


    //点击搜索要做的操作
    private void search() {
        Toast.makeText(getContext(),"开始搜索",Toast.LENGTH_SHORT).show();

    }
}