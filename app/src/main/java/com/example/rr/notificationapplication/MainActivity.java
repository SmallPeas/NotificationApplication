package com.example.rr.notificationapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
  private Button mbutton1;
    private Button mbutton2;
    private Button mbutton3;
   private NotificationManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbutton1= (Button) findViewById(R.id.button1);
        mbutton1.setOnClickListener(this);
        mbutton2= (Button) findViewById(R.id.button2);
        mbutton2.setOnClickListener(this);
        mbutton3= (Button) findViewById(R.id.button3);
        mbutton3.setOnClickListener(this);
        manager= (NotificationManager) getSystemService
                (NOTIFICATION_SERVICE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1://默认
                Notification.Builder builder=new Notification.Builder(this);
                builder.setTicker("有新消息");
                builder.setSmallIcon(R.mipmap.ic_launcher);//设置图标
                builder.setContentTitle(" baidu");//消息标题
                builder.setContentText("发生一个消息");//消息内容
                PendingIntent pendingIntent=PendingIntent.getActivity
                        //通过Notification来创建通知
                        (this,1,new Intent(MainActivity.this,MainActivity.class),0);
                builder.setContentIntent(pendingIntent);
                manager.notify(1,builder.build());//通过管理器发起通知
                break;
            case R.id.button2://自定义 区别于填充器
                Notification notification=new Notification();
                notification.icon=R.mipmap.ic_launcher;
                notification.tickerText="自定义";
                notification.flags=Notification.FLAG_NO_CLEAR;//不能知道清除
                RemoteViews remoteViews=new RemoteViews
                        (getPackageName(), R.layout.activity_notification);
                remoteViews.setTextViewText(R.id.textview,"消息");
                notification.contentView=remoteViews;
                manager.notify(2,notification);  break;
            case R.id.button3:
              manager.cancelAll();  break;//清除内容


        }

    }
}
