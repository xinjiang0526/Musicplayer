package com.goodjob.musicplayer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import com.goodjob.musicplayer.OnlineMusic;
import com.goodjob.musicplayer.R;

public class MainActivity extends AppCompatActivity {

    private Button zaixian;
    private Button bendi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zaixian = (Button) findViewById(R.id.zaixian);
        bendi = (Button) findViewById(R.id.bendi);
        zaixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,OnlineMusic.class);
                startActivity(intent);
            }
        });
        bendi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ListActivity.class);
                startActivity(intent);
            }
        });
    }
}
