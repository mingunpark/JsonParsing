package com.mgnoid.jsoncomm.Controller;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mgnoid.jsoncomm.AboutJSON.JsonGetter;
import com.mgnoid.jsoncomm.AboutJSON.JsonSetter;
import com.mgnoid.jsoncomm.Models.ListData;
import com.mgnoid.jsoncomm.Activitys.ScrollActivity;

import java.util.ArrayList;

/**
 * Created by MG_PARK on 2017-07-16.
 */

public class DataAdapter extends BaseAdapter {

    private ArrayList<ListData> list = new ArrayList<ListData>();
    private ScrollActivity main;
    private Context context;
    //JsonGetter getter;

    public DataAdapter(Context context,JsonGetter getter){

        this.context=context;
        list=getter.getJsonParser();

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) { //어레이리스트의 객체 하나하나를 single_list를 통해 리스트뷰에 넣어준다.

        JsonSetter setting = new JsonSetter(context);

        ListData data = list.get(i);
        setting.setRank(data.getRank() + "");
        setting.setOrder(data.getOrder());
        setting.setImg(data.getUrl());

        return setting;
    }// 각각의 single_list의 내용을 삽입
}
