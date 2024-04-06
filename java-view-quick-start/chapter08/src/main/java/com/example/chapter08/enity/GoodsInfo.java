package com.example.chapter08.enity;

import com.example.chapter08.R;

import java.util.ArrayList;

public class GoodsInfo {
    public int id;
    public String name;
    public String description;
    public double price;
    public String pic_path;
    public int pic;

    // 声明一个手机商品的名称组
    private static String[] mNameArray = {"iPhone 12", "Mate 40", "小米10", "OPPO Find X2", "vivo X60", "魅族 17",};

    // 声明一个手机商品的描述数组
    private static String[] mDescArray = {"Apple iPhone 12 128GB 白色 支持移动联通电信5G 双卡双待手机", "华为 HUAWEI Mate 40 Pro 5G手机 麒麟9000旗舰芯片 5000万超感知徕卡电影影像 8GB+256GB亮黑色", "小米10至尊纪念版 120X 变焦 120W秒充 120Hz屏幕 120亿像素相机 120倍长焦镜头 16GB+512GB 透明版", "OPPO Find X2 120Hz超感屏 120倍数码变焦 4800万超清三摄 65W超级闪充 8GB+128GB 亮黑色", "vivo X60 120Hz柔性屏 三摄影像系统 33W闪充 8GB+128GB 虚幻色", "魅族 17 6400万四摄 4500mAh大电池 30W快充 骁龙865 8GB+128GB 绿野仙踪",};
    // 声明一个手机商品的价格数组
    private static double[] mPriceArray = {5499, 6499, 5299, 4999, 3498, 3699};

    // 声明一个手机商品的大图数组
    private static int[] mPicArray = {R.drawable.iphone, R.drawable.huawei, R.drawable.xiaomi, R.drawable.oppo, R.drawable.vivo, R.drawable.rongyao,};

    // 获取默认的手机信息列表
    public static ArrayList<GoodsInfo> getDefaultList() {
        ArrayList<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
        for (int i = 0; i < mNameArray.length; i++) {
            GoodsInfo info = new GoodsInfo();
            info.id = i;
            info.name = mNameArray[i];
            info.description = mDescArray[i];
            info.price = mPriceArray[i];
            info.pic = mPicArray[i];
            goodsList.add(info);
        }
        return goodsList;
    }
}
