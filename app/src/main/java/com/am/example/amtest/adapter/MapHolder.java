package com.am.example.amtest.adapter;

import com.am.example.amtest.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapHolder {

    private Map<String, Integer> imageMap = new HashMap<>();

    private static MapHolder mapHolder = new MapHolder();

    public static MapHolder getHolder(){
        return mapHolder;
    }

    private MapHolder(){

        /* 尽调 */
        imageMap.put("客户信息", R.drawable.customer_info);
        imageMap.put("生产经营", R.drawable.producer_run);
        imageMap.put("客户财务", R.drawable.customer_finance);
        imageMap.put("银企合作", R.drawable.yinqi_cooperate);
        imageMap.put("项目信息", R.drawable.project_info);
        imageMap.put("融资用途", R.drawable.rongzi_use);
        imageMap.put("还款来源", R.drawable.payback_origin);
        imageMap.put("风险缓解", R.drawable.risk_relieve);

        /* 跟踪 */
        imageMap.put("客户新闻", R.drawable.customer_news);
        imageMap.put("项目进展", R.drawable.project_progress);
        imageMap.put("上下游企业新闻", R.drawable.enterprise_news);
        imageMap.put("财务状况", R.drawable.finace_situation);

        /* 我的 */
        imageMap.put("我的客户", R.drawable.my_customer);
        imageMap.put("消息", R.drawable.message);
        imageMap.put("我的尽调清单", R.drawable.my_jindiao_list);
        imageMap.put("我的尽调进度", R.drawable.my_jindiao_progress);
        imageMap.put("我的发布", R.drawable.my_published);
        imageMap.put("推荐给好友", R.drawable.recommend_to_friend);
        imageMap.put("帮助和反馈", R.drawable.help_and_reback);
    }

    public void addItem(String key, int value){
        imageMap.put(key, value);
    }

    public void addItems(List<String> keys, List<Integer> values){
        if(keys.size() != values.size()) return;
        for(int i=0; i<keys.size(); ++i){
            imageMap.put(keys.get(i), values.get(i));
        }
    }

    public int getValue(String key){
        return imageMap.get(key);
    }
}
