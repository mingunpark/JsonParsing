package com.mgnoid.jsoncomm.AboutJSON;

import android.util.Log;

import com.mgnoid.jsoncomm.Models.ListData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by MG_PARK on 2017-07-16.
 */

public class JsonGetter extends Thread {


    String json_url = "http://ec2-52-26-144-160.us-west-2.compute.amazonaws.com:3000/jiminzzang"; //json 형식으로 가져올 데이터들이 있는 url
    InputStream input;
    ByteArrayOutputStream baos;
    HttpURLConnection conn;
    int responseCode;
    String byteData;

    ArrayList<ListData> jsonParser = new ArrayList<ListData>();

    public JsonGetter(){

        input=null;
        baos=null;
        byteData=null;
        start();
    }

    @Override
    public void run() {
        super.run();

        try{
            URL url = new URL(json_url);

            conn = (HttpURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.connect();
            conn.setRequestMethod("GET"); //get형식으로 받자
            responseCode = conn.getResponseCode();
            input = conn.getInputStream();

            baos = new ByteArrayOutputStream();
            byte[] byteBuffer = new byte[1024];

            int nLength = 0;
            while((nLength = input.read(byteBuffer, 0, byteBuffer.length)) != -1) {
                baos.write(byteBuffer, 0, nLength);
            }
            byteData = new String(baos.toByteArray(),"UTF-8");

            // 잘 되는지 체크용
//            System.out.println("=============check 1============");
//            System.out.println("responseCode : "+responseCode);
//            System.out.println(byteData);

            jsonParser=this.insertData(); //파싱한 json 데이터들을 Listdata 어레이리스트에 담아 준다.

       }catch(IOException ex){

       }finally {
            try{
                input.close();
                baos.close(); //input, output 스트림 종료
            }catch (IOException exce){

            }

       }


    }

    public ArrayList<ListData> insertData(){

        ArrayList<ListData> temp = new ArrayList<ListData>(); //json을 파싱하고 넘겨주기 위해 임시로 저장해놓는 어레이리스트

        try{

            JSONObject responseJSON = new JSONObject(byteData);
            JSONObject jsonList = (JSONObject) responseJSON.get("testCase");
            JSONArray jsonArray = (JSONArray) jsonList.get("testList"); //json을 순서대로 파싱해 testList 안에 있는 정보들을 배열 형식으로 뽑아냄

            for(int i=0;i<jsonArray.length();i++) {

                ListData listData = new ListData();
                jsonList = jsonArray.getJSONObject(i);


                listData.setRank(jsonList.getInt("rank"));
                listData.setOrder(jsonList.getString("Nm"));
                listData.setUrl(jsonList.getString("url"));

                temp.add(listData);
                Log.i(TAG, "DATA response = " + byteData);
            }


        }catch (JSONException ex){

            ex.printStackTrace();

        }

        return temp;

    }

    public ArrayList<ListData> getJsonParser() {
        return jsonParser;
    } // 파싱 완료해서 데이터들을 저장해놓은 jsonParser 어레이리스트를 반환
}
