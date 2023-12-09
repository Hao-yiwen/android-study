package com.example.chapter08.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.chapter08.R;
import com.example.chapter08.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class LaunchSimpleAdapter extends PagerAdapter {
    private List<View> mViewList = new ArrayList<>();

    public LaunchSimpleAdapter(Context context, int[] imageArray) {
        for (int i = 0; i < imageArray.length; i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_launch, null);
            ImageView iv_launch = view.findViewById(R.id.iv_launch);
            RadioGroup rg_indicate = view.findViewById(R.id.rg_indicate);
            Button btn_start = view.findViewById(R.id.btn_start);
            iv_launch.setImageResource(imageArray[i]);

            //每个页面度分配一组对应的单选按钮
            for (int j = 0; j < imageArray.length; j++) {
                RadioButton radio = new RadioButton(context);
                radio.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                radio.setPadding(10,10,10,10);
                rg_indicate.addView(radio);
            }

            ((RadioButton)rg_indicate.getChildAt(i)).setChecked(true);

            if(i == imageArray.length - 1) { //最后一个页面才显示按钮
                btn_start.setVisibility(View.VISIBLE);
                btn_start.setOnClickListener(v->{
                    ToastUtil.showMsg(context, "欢迎开启美好生活");
                });
            } else {
                btn_start.setVisibility(View.GONE);
            }

            mViewList.add(view);
        }
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View item = mViewList.get(position);
        container.addView(item);
        return item;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mViewList.get(position));
    }
}
