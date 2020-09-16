package com.example.timer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static int timercnt;
TextView second,minute;
Button start,stop;
 static  int flag=0;
    Thread th;
TextView t;

   Handler handler=new Handler(new Handler.Callback() {
       @Override
       public boolean handleMessage(@NonNull Message msg) {
           return false;
       }
   });
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        second=findViewById(R.id.txtsecond);
        minute=findViewById(R.id.txtminute);
        start=findViewById(R.id.btnstart);
        stop=findViewById(R.id.btnstop);
        t=findViewById(R.id.textView);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("start","start");
                flag=1;
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=0;
            }
        });
        th=new Thread(new Runnable() {
            @Override
            public void run() {
                check();
                Log.d("bool", String.valueOf(flag));

            }
        });
th.start();
    }

    private void check() {
int i=1;
        while (true)
        {
            if(flag!=0){
            try {
                second.setText(String.valueOf(i));
                Thread.sleep(1000);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(second.getText().equals("59"))
            {
                timercnt+=1;
                i=0;
                minute.setText(String.valueOf(timercnt));
            }
            }
        }
    }

}