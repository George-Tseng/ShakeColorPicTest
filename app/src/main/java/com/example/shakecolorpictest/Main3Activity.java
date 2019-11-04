package com.example.shakecolorpictest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Main3Activity extends AppCompatActivity {

    //宣告要被改背景的linearlayout為public，這樣才能被ShakeService.java給讀取
    public static LinearLayout square;
    //宣告要被改背景的ImageView為public，這樣才能被ShakeService.java給讀取
    public static ImageView pic;
    //宣告Button物件
    Button button_home3;
    //宣告Intent物件
    Intent intent0,intent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //findViewById方法
        square = findViewById(R.id.square);
        pic = findViewById(R.id.pic);
        button_home3 = findViewById(R.id.button_home3);
        //建立Intent物件
        intent0 = new Intent();

        //生成所需Intent物件，並設定其執行特定的服務
        intent1 = new Intent(this, ShakeService.class);
        //開始執行服務
        startService(intent1);

        //在Button物件上設置監聽器
        button_home3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //設定傳頁
                intent0.setClass(v.getContext(),StartActivity.class);
                //開始執行傳頁
                startActivity(intent0);
                //停止執行服務ShakeService
                stopService(intent1);
                //結束剩餘活動
                finish();
            }
        });
    }
}
