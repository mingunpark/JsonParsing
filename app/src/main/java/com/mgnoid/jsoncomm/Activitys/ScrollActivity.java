package com.mgnoid.jsoncomm.Activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mgnoid.jsoncomm.AboutJSON.JsonGetter;
import com.mgnoid.jsoncomm.AboutJSON.JsonResponse;
import com.mgnoid.jsoncomm.AboutJSON.JsonService;
import com.mgnoid.jsoncomm.Controller.DataAdapter;
import com.mgnoid.jsoncomm.Models.JsonData;
import com.mgnoid.jsoncomm.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScrollActivity extends AppCompatActivity {

    JsonGetter getter;
    Button btn_close;
    List<JsonData> listDatas;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager reLayoutManager;
    private JsonService service;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this,BaseActivity.class));
        setContentView(R.layout.activity_scoll);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true); // 크기 고정

        reLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(reLayoutManager);

        getter = new JsonGetter();

        service=JsonGetter.getService();


        btn_close=(Button)findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                exitDialog();

            }
        });

        callList();

        try{
            getter.join();
        }catch (InterruptedException e){

        } // 스레드가 종료 될 때까지 기다린다 ( 기다리지 않으면 파싱한 json 데이터들이 adapter 생성 시에 아직 어레이리스트에 들어가지 않아있게 됨)


    }

    public  void exitDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);     // 여기서 this는 Activity의 this

        // 여기서 부터는 알림창의 속성 설정
        builder.setTitle("종료 확인 대화 상자")        // 제목 설정
                .setMessage("앱을 종료 하시 겠습니까?")        // 메세지 설정
                .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                .setPositiveButton("확인", new DialogInterface.OnClickListener(){
                    // 확인 버튼 클릭시 설정
                    public void onClick(DialogInterface dialog, int whichButton){
                        finish();
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener(){
                    // 취소 버튼 클릭시 설정
                    public void onClick(DialogInterface dialog, int whichButton){
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기

    }//앱 종료를 위한 다이얼로그

    public void callList() {

        service.getItems().enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                listDatas = response.body().getItems();

                for(int i=0;i<listDatas.size();i++){

//                    System.out.println("==============check list==============");
//                    System.out.println("rank : "+listDatas.get(i).getRank());
//                    System.out.println("order : "+listDatas.get(i).getOrder());
//                    System.out.println("url : "+listDatas.get(i).getUrl());
//                    System.out.println("--------------------------------------");

                }
                DataAdapter adapter = new DataAdapter(listDatas);

                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {
                Log.i("ORDINARY RES FAIL", t.toString());
            }
        });
    }
}






