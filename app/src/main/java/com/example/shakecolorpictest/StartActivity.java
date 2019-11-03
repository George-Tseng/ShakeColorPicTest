package com.example.shakecolorpictest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    //宣告Intent物件
    Intent intent0;
    //宣告Button物件
    Button button_option1,button_option2,button_option3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //生成Intent物件
        intent0 = new Intent();

        //findViewById方法
        button_option1 = findViewById(R.id.button_option1);
        button_option2 = findViewById(R.id.button_option2);
        button_option3 = findViewById(R.id.button_option3);

        //在Button物件上設置監聽器
        button_option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //設定傳頁
                intent0.setClass(v.getContext(),MainActivity.class);
                //開始執行傳頁
                startActivity(intent0);
                //結束剩餘活動
                finish();
            }
        });
        button_option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //設定傳頁
                intent0.setClass(v.getContext(),Main2Activity.class);
                //開始執行傳頁
                startActivity(intent0);
                //結束剩餘活動
                finish();
            }
        });
        button_option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //設定傳頁
                intent0.setClass(v.getContext(),Main3Activity.class);
                //開始執行傳頁
                startActivity(intent0);
                //結束剩餘活動
                finish();
            }
        });
    }

    //生成Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //偵測到哪個Menu被選擇了
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_option1) {
            //設定傳頁
            intent0.setClass(this,MainActivity.class);
            //開始執行傳頁
            startActivity(intent0);
            //結束剩餘活動
            finish();
            //方法指定的回傳
            return true;
        }
        if (id == R.id.menu_option2) {
            //生成Intent物件
            intent0 = new Intent();
            //設定傳頁
            intent0.setClass(this,Main2Activity.class);
            //開始執行傳頁
            startActivity(intent0);
            //結束剩餘活動
            finish();
            //方法指定的回傳
            return true;
        }
        if (id == R.id.menu_option3) {
            //生成Intent物件
            intent0 = new Intent();
            //設定傳頁
            intent0.setClass(this,Main3Activity.class);
            //開始執行傳頁
            startActivity(intent0);
            //結束剩餘活動
            finish();
            //方法指定的回傳
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
