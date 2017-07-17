package com.mgnoid.jsoncomm.Activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.mgnoid.jsoncomm.AboutJSON.JsonGetter;
import com.mgnoid.jsoncomm.Controller.DataAdapter;
import com.mgnoid.jsoncomm.R;

public class ScrollActivity extends AppCompatActivity {

    JsonGetter getter;
    Button btn_close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this,BaseActivity.class));
        setContentView(R.layout.activity_scoll);

        ListView listView = (ListView) findViewById(R.id.listView);
        getter = new JsonGetter();

        btn_close=(Button)findViewById(R.id.btn_close);


        btn_close.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                exitDialog();

            }
        });

        try{
            getter.join();
        }catch (InterruptedException inE){

        } // 스레드가 종료 될 때까지 기다린다 ( 기다리지 않으면 파싱한 json 데이터들이 adapter 생성 시에 아직 어레이리스트에 들어가지 않아있게 됨)

        DataAdapter adapter = new DataAdapter(getApplicationContext(), getter);


        listView.setAdapter(adapter);

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





}
