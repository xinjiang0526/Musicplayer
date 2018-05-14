package com.goodjob.musicplayer.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.goodjob.musicplayer.DBHelper;
import com.goodjob.musicplayer.R;

public class RegisterActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private EditText newaccount;
    private EditText newpassword;
    private EditText againpassword;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        newaccount = (EditText) findViewById(R.id.newaccount);
        newpassword = (EditText) findViewById(R.id.newpassword);
        againpassword = (EditText) findViewById(R.id.againpassword);
        submit = (Button) findViewById(R.id.submit);


        dbHelper = new DBHelper(this, "User.db", null, 1);

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                String newac = newaccount.getText().toString();
                String newpwd = newpassword.getText().toString();
                String again = againpassword.getText().toString();
                String Query = "Select * from user where account =?";
                Cursor cursor = db.rawQuery(Query, new String[]{newac});

                if (newac.equals("")) {
                    Toast.makeText(RegisterActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
                } else {

                if (cursor.getCount() > 0) {
                    Toast.makeText(RegisterActivity.this, "该账号已存在", Toast.LENGTH_SHORT).show();


                } else {
                    if (newpwd.equals("")) {
                        Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    } else {
                        if (again.equals("")) {
                            Toast.makeText(RegisterActivity.this, "请确认密码", Toast.LENGTH_SHORT).show();
                        } else {
                        if (newpwd.equals(again)) {
                            values.put("account", newac);
                            values.put("password", newpwd);
                            db.insert("User", null, values);
                            //  db.close();
                            AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this);
                            dialog.setTitle("提醒");
                            dialog.setMessage("注册成功");
                            dialog.setCancelable(false);
                            dialog.setPositiveButton("登录",new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                    startActivity(intent);
                                }
                            });
                            dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            dialog.show();
                          //  Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


                }
            }
            }




        });
    }

}

