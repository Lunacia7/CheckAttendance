package com.example.checkattendance;

import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.List;

public class SupplyActivity extends AppCompatActivity {
    private EditText nameET;
    private EditText supplyDateET;
    private double datedb;//double类型的date

    private List<Student> students;
    Student supplyStudent;

    private List<StudentLog> studentLogs;
    StudentLog supplyStudentLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply);
        //隐藏自带标题栏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

    }

    public void confirm(View view) {
        nameET = (EditText) findViewById(R.id.nameET);
        supplyDateET = (EditText) findViewById(R.id.supplyDate);

        //获取用户输入的信息
        String name = nameET.getText().toString();
        String date = supplyDateET.getText().toString();
        datedb = Double.parseDouble(supplyDateET.getText().toString());
        double mymin = datedb - 0.0001;
        double mymax = datedb + 0.0001;

        //找到Student表中的待补签学生
        students = LitePal.where("name = ?",name).find(Student.class);
        supplyStudent = students.get(0);
        //找到StudentLog表中的待补签学生
        studentLogs = LitePal.where("name == ? and date > ? and date < ?",name,Double.toString(mymin),Double.toString(mymax)).find(StudentLog.class);
        supplyStudentLog = studentLogs.get(0);

        //若用户输入信息不全，则提出警示信息
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(date) ) {
            Toast.makeText(this, "请补全所有信息！", Toast.LENGTH_SHORT).show();
            return;
        }
        //修改supplyStudent信息
        //更新 noclass 为 0，直接调用.setNoclass(0)是无效的，因为在 java 中 int 的默认值就是 0 ，所以此时LitePal将不会对此更新，对于要更新的数据，LitePal为我们提供了一个setToDefault()方法
        if(supplyStudent.getNoclass()==1) {
            supplyStudent.setToDefault("noclass");
            supplyStudent.update(supplyStudent.getId());
        } else {
            supplyStudent.setNoclass(supplyStudent.getNoclass() - 1);
            supplyStudent.update(supplyStudent.getId());
        }

        //修改supplyStudentLog信息
        supplyStudentLog.setToDefault("isnoclass");
        supplyStudentLog.update(supplyStudentLog.getId());

        Toast.makeText(this, "补签成功", Toast.LENGTH_SHORT).show();

    }

    public void cancel(View view) {
        finish();
    }
}
