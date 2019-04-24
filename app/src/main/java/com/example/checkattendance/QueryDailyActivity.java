package com.example.checkattendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryDailyActivity extends AppCompatActivity {
    private static String TAG = "QueryDailyActivity";

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //QueryDaily通过StudentLog表
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_daily);
        listView = (ListView) findViewById(R.id.listAllDaily);
        initData();
    }

    private void initData() {
        //查询所有数据
        List<StudentLog> studentsLog = LitePal.findAll(StudentLog.class);

        if (studentsLog == null) {
            Toast.makeText(this, "获取学生信息失败，请稍后再试！", Toast.LENGTH_SHORT).show();
            finish();
        }
        List<Map<String, String>> maps = new ArrayList<>();
        for (int i = 0; i < studentsLog.size(); i++) {
            Map<String, String> map = new HashMap<>();
            map.put("name", studentsLog.get(i).getName());
            map.put("sid", studentsLog.get(i).getSid());
            map.put("absent", String.valueOf(studentsLog.get(i).getIsnoclass()));
            map.put("leave", String.valueOf(studentsLog.get(i).getIsleave()));
            map.put("date",String.valueOf(studentsLog.get(i).getDate()));
            maps.add(map);
        }
        String[] strings = {"name", "sid", "absent","leave","date"};
        int[] ids = {R.id.name, R.id.sid, R.id.absent,R.id.leave,R.id.data};

        SimpleAdapter adapter = new SimpleAdapter(this, maps, R.layout.item_queryalldaily, strings, ids);  //ListView适配item_queryAllDaily.xml
        listView.setAdapter(adapter);

    }
}
