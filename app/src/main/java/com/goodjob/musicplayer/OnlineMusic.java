package com.goodjob.musicplayer;


import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class OnlineMusic extends AppCompatActivity implements View.OnClickListener{

    private DownloadService.DownloadBinder downloadBinder;
    private ServiceConnection connection=new ServiceConnection(){
        @Override
        public void onServiceDisconnected(ComponentName name){
        }
        @Override
        public void onServiceConnected(ComponentName name , IBinder service){
            downloadBinder=(DownloadService.DownloadBinder) service;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_music);
        Button startDownload1=(Button)findViewById(R.id.start_download1);
        Button startDownload2=(Button)findViewById(R.id.start_download2);
        Button startDownload3=(Button)findViewById(R.id.start_download3);
        Button startDownload4=(Button)findViewById(R.id.start_download4);
        Button startDownload5=(Button)findViewById(R.id.start_download5);
        Button pauseDownload=(Button)findViewById(R.id.pause_download);
        Button cancelDownload=(Button) findViewById(R.id.cancel_download);

        startDownload1.setOnClickListener(this);
        startDownload2.setOnClickListener(this);
        startDownload3.setOnClickListener(this);
        startDownload4.setOnClickListener(this);
        startDownload5.setOnClickListener(this);
        pauseDownload.setOnClickListener(this);
        cancelDownload.setOnClickListener(this);
        Intent intent=new Intent(this,DownloadService.class);
        startService(intent);//启动服务
        bindService(intent,connection, BIND_AUTO_CREATE);//绑定服务
        if (ContextCompat.checkSelfPermission(OnlineMusic.this, Manifest.
                permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(OnlineMusic.this,new
                    String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
    }
    @Override
    public void onClick(View v)  {
        if (downloadBinder ==null) {
            return;
        }
        switch (v.getId()){
            case R.id.start_download1:
                String url1="http://211.68.36.103:8080/music/mp3/1.mp3";
                downloadBinder.startDownload(url1);
                break;
            case R.id.start_download2:
                String url2="http://211.68.36.103:8080/music/mp3/2.mp3";
                downloadBinder.startDownload(url2);
                break;
            case R.id.start_download3:
                String url3="http://211.68.36.103:8080/music/mp3/3.mp3";
                downloadBinder.startDownload(url3);
                break;
            case R.id.start_download4:
                String url4="http://211.68.36.103:8080/music/mp3/4.mp3";
                downloadBinder.startDownload(url4);
                break;
            case R.id.start_download5:
                String url5="http://211.68.36.103:8080/music/mp3/5.mp3";
                downloadBinder.startDownload(url5);
                break;
            case R.id.pause_download:
                downloadBinder.pauseDownload();
                break;
            case R.id.cancel_download:
                downloadBinder.cancelDownload();
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[]grantResults){
        switch(requestCode){
            case 1:
                if (grantResults.length>0 && grantResults[0]!= PackageManager.
                        PERMISSION_GRANTED){
                    Toast.makeText(this,"拒绝权限将无法使用程序", Toast.LENGTH_SHORT).
                            show();
                    finish();
                }
                break;
            default:
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unbindService(connection);
    }
}






