package com.example.shakecolorpictest;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.IBinder;
import androidx.annotation.Nullable;
import java.util.Random;

public class ShakePicService extends Service implements SensorEventListener{

    //宣告SensorManager與Sensor
    private SensorManager accelerationSensorManager0;
    private Sensor accelerationSensor0;

    //加速度的相關變數
    private float acceleration0;//
    private float accelerationCurrent0;//當前量測到的加速度
    private float accelerationLast0;//上一次量測到的加速度

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //讓感應器管理員取得系統服務
        accelerationSensorManager0 = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        //設定加速度感應器為感應器管理員的預設感應器
        accelerationSensor0 = accelerationSensorManager0.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //註冊感應器管理員，使用延遲UI並生成一個Handler物件
        accelerationSensorManager0.registerListener(this,accelerationSensor0,SensorManager.SENSOR_DELAY_UI,new Handler());
        //回傳
        return START_STICKY;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        //取得x、y、z方向的位移量(delta x、delta y、delta z)
        float deltaX = event.values[0];
        float deltaY = event.values[1];
        float deltaZ = event.values[2];
        //如果是第一次啟動，先把「上次量測的加速度」套用目前量測的結果
        accelerationLast0 = accelerationCurrent0;
        //取得當前的加速度(如感應器取樣間隔很短時，可約略視為等加速度運動，因此總位移距離1/2 a t^2 = (delta x)^2+(delta y)^2+(delta z)^2
        //移項後為1/2 a = (deltaX/t)^2+(deltaY/t)^2+(deltaZ/t)^2->Vx^2+Vy^2+Vz^2
        accelerationCurrent0 = (float) Math.sqrt((double) (Math.pow(deltaX,2) + Math.pow(deltaY,2) + Math.pow(deltaZ,2)));
        //取得本次與前一次量測結果的差值
        float delta = accelerationCurrent0 - accelerationLast0;
        //高通濾波器
        acceleration0 = acceleration0 * 0.9f + delta;
        //利用TypedArray來讀取pic_array.xml裡的圖檔資訊
        TypedArray typedArray0 = getResources().obtainTypedArray(R.array.random_pics);
        //確認圖檔陣列的長度len
        int len = typedArray0.length();
        //產生用來儲存圖檔來源的整數陣列picResourceIds[]，長度亦為len
        int[] picResourceIds = new int[len];
        //將圖片的資訊填入整數陣列picResourceIds[]
        for (int i = 0; i < len; i++)
            picResourceIds[i] = typedArray0.getResourceId(i, 0);//預設會顯示index 0的那張圖
        //觸發的條件
        if (acceleration0 > 10) {//地表附近重力加速度g約為9.8 m/(s^2)
            //生成亂數物件
            Random random0 = new Random();
            //利用亂數來決定要顯示哪張圖片(將為該陣列的index，範圍為0～len-1)
            int pic_index = random0.nextInt(len);
            //執行回收
            typedArray0.recycle();
            //套用進圖片設定
            Main2Activity.pic.setImageResource(picResourceIds[pic_index]);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
