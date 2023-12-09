package com.example.chapter08.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chapter08.R;
import com.example.chapter08.enity.Planet;
import com.example.chapter08.util.ToastUtil;

import java.util.List;

public class PlanetListWithButtonAdapter extends BaseAdapter {
    private Context context;
    private List<Planet> mPlanetList;

    public PlanetListWithButtonAdapter(Context context, List<Planet> planetList) {
        this.context = context;
        this.mPlanetList = planetList;
    }

    @Override
    public int getCount() {
        return mPlanetList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPlanetList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHelper helper;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_with_button, null);
            helper = new ViewHelper();
            helper.ll_item = convertView.findViewById(R.id.ll_item);
            helper.iv_icon = convertView.findViewById(R.id.iv_icon);
            helper.tv_name = convertView.findViewById(R.id.tv_name);
            helper.tv_desc = convertView.findViewById(R.id.tv_desc);
            helper.btn_oper = convertView.findViewById(R.id.btn_oper);
            convertView.setTag(helper);
        } else {
            helper = (ViewHelper) convertView.getTag();
        }


        Planet planet = mPlanetList.get(position);
        helper.ll_item.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        helper.iv_icon.setImageResource(planet.image);
        helper.tv_name.setText(planet.name);
        helper.tv_desc.setText(planet.desc);
        helper.btn_oper.setOnClickListener(v -> {
            ToastUtil.showMsg(context, "你点击了" + planet.name);
        });
        return convertView;
    }

    public final class ViewHelper {
        LinearLayout ll_item;
        public ImageView iv_icon;
        public TextView tv_name;
        public TextView tv_desc;

        public Button btn_oper;
    }
}
