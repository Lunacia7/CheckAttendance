package com.example.checkattendance;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    private static String TAG = "InsertActivity";

    private EditText nameET;
    private EditText sidET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        //隐藏自带标题栏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        initUI();
    }

    private void initUI() {
        nameET = (EditText) findViewById(R.id.name);
        sidET = (EditText) findViewById(R.id.sid);
    }

    public void confirm(View view) {
        Log.d(TAG, "confirm");
        //获取用户输入的信息
        String name = nameET.getText().toString();
        String sid = sidET.getText().toString();
        //若用户输入信息不全，则提出警示信息
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(sid) ) {
            Toast.makeText(this, "请补全所有信息！", Toast.LENGTH_SHORT).show();
            return;
        }
        //获得的数据传入数据表Student中，
        Student student = new Student();
        student.setName(name);
        student.setSid(sid);
        student.setNoclass(0);
        student.setLeave(0);
        //判断新增信息情况
        if (student.save()) {
            Toast.makeText(this, "新增学生信息成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "新增学生信息失败", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancel(View view) {
        Log.d(TAG, "cancel");
        finish();
    }
}
