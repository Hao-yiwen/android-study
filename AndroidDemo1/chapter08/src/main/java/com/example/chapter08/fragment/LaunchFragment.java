package com.example.chapter08.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.chapter08.R;
import com.example.chapter08.util.ToastUtil;

public class LaunchFragment extends Fragment {

    public static LaunchFragment newInstance(int position, int image_id) {
        LaunchFragment fragment = new LaunchFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putInt("image_id", image_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context = getContext();
        // Inflate the layout for this fragment
        Bundle arguments = getArguments();
        int position = arguments.getInt("position", 0);
        int imageId = arguments.getInt("image_id", 0);

        View view = LayoutInflater.from(context).inflate(R.layout.fragment_launch, container, false);
        ImageView iv_launch = view.findViewById(R.id.iv_launch);
        RadioGroup rg_indicate = view.findViewById(R.id.rg_indicate);
        Button btn_start = view.findViewById(R.id.btn_start);

        iv_launch.setImageResource(imageId);

        //每个页面度分配一组对应的单选按钮
        for (int j = 0; j < 4; j++) {
            RadioButton radio = new RadioButton(context);
            radio.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            radio.setPadding(10,10,10,10);
            rg_indicate.addView(radio);
        }

        ((RadioButton)rg_indicate.getChildAt(position)).setChecked(true);

        if(position == 3) { //最后一个页面才显示按钮
            btn_start.setVisibility(View.VISIBLE);
            btn_start.setOnClickListener(v->{
                ToastUtil.showMsg(context, "欢迎开启美好生活");
            });
        } else {
            btn_start.setVisibility(View.GONE);
        }


        return view;
    }
}