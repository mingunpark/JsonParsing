package com.mgnoid.jsoncomm.Controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by MG_PARK on 2017-07-17.
 */

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {  //String : doInBackgroun 파라미터 타입, excute 인자값  void : doInBackground의 작업 시 진행 단위 타입 Bitmap : onPostExecute의 인자값
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        bmImage.setImageBitmap(result);

    }
}// image view에 사진을 넣기 위해 스레드나 핸들러를 사용하지 않고 AsyncTask를 상속받은 내부 클래스를 이용