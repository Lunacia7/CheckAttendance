package com.example.checkattendance;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.List;

public class CallActivity extends AppCompatActivity {
    private static String TAG = "CallActivity";

    private TextView nameTV;
    private TextView sidTV;

    public double datadb;
    public String banji_out;

    private List<Student> students;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        //隐藏自带标题栏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        //banji获取上个活动传入的所输入待考勤班级
        Intent intent = getIntent();
        banji_out = intent.getStringExtra("banji_data");

        initUI();
        initData();
    }

    public void admit(View view) {
        //获取EditText中的当日日期，并转为double型
        EditText dataTV = (EditText) findViewById(R.id.dataET);
        datadb = Double.parseDouble(dataTV.getText().toString());
        Toast.makeText(this, "更改日期成功", Toast.LENGTH_SHORT).show();
    }

    private void initUI() {
        nameTV = (TextView) findViewById(R.id.name);
        sidTV = (TextView) findViewById(R.id.sid);
    }
    //数据库
    private void initData() {
        Log.d(TAG, "initData");
        //模糊查询sid为banji的学生
        students = LitePal.where("sid like ?",banji_out+"%").find(Student.class);

        Log.d(TAG, "students:" + students);
        index = 0;
        show();
    }
    //在页面显示
    private void show() {
        Log.d(TAG, "show：" + index);

        if (students == null || students.size() < 1) {
            //若数据库无学生，则弹出提示框，提示先添加学生
            Toast.makeText(this, "请先添加学生信息！", Toast.LENGTH_SHORT).show();
            finish();
            return  ;
        }
        if (index < 0) {
            return;
        }
        if (index >= students.size()) {
            Toast.makeText(this, "点名完毕~", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        //显示学生姓名
        nameTV.setText(students.get(index).getName());
        //显示学生学号
        sidTV.setText(students.get(index).getSid());

    }


    //点击call已到，index+1
    public void attend(View view) {
        Log.d(TAG, "attend");

        //在StudentLog中插入当前学生当日信息(0,0)
        Student update = students.get(index);
        StudentLog studentLog = new StudentLog();
        studentLog.setName(update.getName());
        studentLog.setSid(update.getSid());
        studentLog.setIsnoclass(0);
        studentLog.setIsleave(0);
        studentLog.setDate(datadb);
        studentLog.save();

        index++;
        show();
    }

    public void absent(View view) {
        Log.d(TAG, "absent");

        Student updateNoclass = students.get(index);
        //点击call里面的缺席，缺席+1，更新到数据库，index+1
        updateNoclass.setNoclass(updateNoclass.getNoclass() + 1);
        updateNoclass.update(updateNoclass.getId());

        //在StudentLog中插入当前学生当日信息(1,0)
        StudentLog studentLog = new StudentLog();
        studentLog.setName(updateNoclass.getName());
        studentLog.setSid(updateNoclass.getSid());
        studentLog.setIsnoclass(1);
        studentLog.setIsleave(0);
        studentLog.setDate(datadb);
        studentLog.save();

        index++;
        show();
    }

    public void leave(View view) {
        Log.d(TAG, "leave");

        Student updateLeave = students.get(index);
        //点击call里面的请假，请假+1，更新到数据库，index+1
        updateLeave.setLeave(updateLeave.getLeave() + 1);
        updateLeave.update(updateLeave.getId());

        //在StudentLog中插入当前学生当日信息(0,1)
        StudentLog studentLog = new StudentLog();
        studentLog.setName(updateLeave.getName());
        studentLog.setSid(updateLeave.getSid());
        studentLog.setIsnoclass(0);
        studentLog.setIsleave(1);
        studentLog.setDate(datadb);
        studentLog.save();

        index++;
        show();
    }
}
