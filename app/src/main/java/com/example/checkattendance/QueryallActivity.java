package com.example.checkattendance;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryallActivity extends AppCompatActivity {
    //Queryall通过student表
    private static String TAG = "QueryallActivity";

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queryall);
        listView = (ListView) findViewById(R.id.listAll);
        initData();
    }

    private void initData() {
        //查询所有数据
            List<Student> students = LitePal.findAll(Student.class);

        if (students == null) {
            Toast.makeText(this, "获取学生信息失败，请稍后再试！", Toast.LENGTH_SHORT).show();
            finish();
        }
        List<Map<String, String>> maps = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            Map<String, String> map = new HashMap<>();
            map.put("name", students.get(i).getName());
            map.put("sid", students.get(i).getSid());
            map.put("absent", students.get(i).getNoclass() + "次");
            map.put("leave", students.get(i).getLeave()+ "次");
            maps.add(map);
        }
        String[] strings = {"name", "sid", "absent","leave"};
        int[] ids = {R.id.name, R.id.sid, R.id.absent,R.id.leave};

        SimpleAdapter adapter = new SimpleAdapter(this, maps, R.layout.item_queryall, strings, ids);  //ListView适配item_queyall.xml
        listView.setAdapter(adapter);

    }
}
