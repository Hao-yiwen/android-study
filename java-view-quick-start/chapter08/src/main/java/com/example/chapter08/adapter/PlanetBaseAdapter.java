package com.example.chapter08.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chapter08.R;
import com.example.chapter08.enity.Planet;

import java.util.List;

public class PlanetBaseAdapter extends BaseAdapter {
    private Context context;
    private List<Planet> mPlanetList;

    public PlanetBaseAdapter(Context context, List<Planet> planetList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list, null);
            helper = new ViewHelper();
            helper.iv_icon = convertView.findViewById(R.id.iv_icon);
            helper.tv_name = convertView.findViewById(R.id.tv_name);
            helper.tv_desc = convertView.findViewById(R.id.tv_desc);
            convertView.setTag(helper);
        } else {
            helper = (ViewHelper) convertView.getTag();
        }


        Planet planet = mPlanetList.get(position);
        helper.iv_icon.setImageResource(planet.image);
        helper.tv_name.setText(planet.name);
        helper.tv_desc.setText(planet.desc);
        return convertView;
    }

    public final class ViewHelper {
        public ImageView iv_icon;
        public TextView tv_name;
        public TextView tv_desc;
    }
}
