package com.mgnoid.jsoncomm.AboutJSON;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mgnoid.jsoncomm.Controller.DownloadImageTask;
import com.mgnoid.jsoncomm.R;


/**
 * Created by MG_PARK on 2017-07-16.
 */

public class JsonSetter extends LinearLayout {

    TextView txt_rank;
    TextView txt_order;
    ImageView img;


    public JsonSetter(Context context) {
        super(context);
        init(context);
    }

    public JsonSetter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.single_list, this, true); //single_list들을 실제 객체로 만들어 값을 부여할 수 있도록 해줌

        txt_rank=(TextView)findViewById(R.id.txt_rank);
        txt_order=(TextView)findViewById(R.id.txt_order);
        img=(ImageView)findViewById(R.id.img);
    }

    public void setRank(String string){

        txt_rank.setText(string);
    }

    public void setOrder(String string){

        txt_order.setText(string);
    }

    public void setImg(String img_url){

        final String getUrl=img_url;


        new DownloadImageTask(img).execute(getUrl); //ui 수정을 위한 내부클래스 생성

    }

} //ListView 하나 하나를 구성할 single_list에 대한 셋팅
