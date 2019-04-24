package com.example.checkattendance;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SelectClassBeforeCallActivity extends AppCompatActivity {

    public String banji_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_class_before_call);
        //隐藏自带标题栏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
    }

    public void admit(View view) {
        //获取EditText中班级，并转为string型
        EditText classTV = (EditText) findViewById(R.id.classET);
        banji_in = classTV.getText().toString();
        Toast.makeText(this, "获取班级成功", Toast.LENGTH_SHORT).show();

        //string型的班级数据传到下个CallActivity
        Intent intent = new Intent(SelectClassBeforeCallActivity.this,CallActivity.class);
        intent.putExtra("banji_data",banji_in);
        startActivity(intent);
        finish();
    }
}
