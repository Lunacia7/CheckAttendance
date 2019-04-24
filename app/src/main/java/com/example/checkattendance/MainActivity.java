package com.example.checkattendance;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //开关侧导航栏
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //设定NavigationView菜单的选择事件
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override    //通过onBackPressed方法,当点击返回按钮的时候,如果DrawerLayout是打开状态则关闭
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);        //每次点击一个Menu关闭DrawerLayout
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //右上角菜单选项
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //创建和删除数据库
        if (id == R.id.create_database) {
            Connector.getDatabase();
            Toast.makeText(MainActivity.this," DataBase successfully created",Toast.LENGTH_SHORT).show();
        }else if(id == R.id.clear_database) {
            LitePal.deleteAll(Student.class);
            LitePal.deleteAll(StudentLog.class);
            Toast.makeText(MainActivity.this," DataBase successfully cleared",Toast.LENGTH_SHORT).show();
        }else if(id == R.id.clear_data) {
            LitePal.deleteAll(StudentLog.class);
            Student clear = new Student();
            clear.setToDefault("noclass");
            clear.setToDefault("leave");
            clear.updateAll();
            Toast.makeText(MainActivity.this," Data successfully cleared",Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    //导航栏选项
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_call) {
            Intent intent = new Intent(MainActivity.this,SelectClassBeforeCallActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_QueryAttendanceDaily) {
            Intent intent = new Intent(MainActivity.this,QueryDailyActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_QueryAttendance) {
            Intent intent = new Intent(MainActivity.this,QueryallActivity.class);
            startActivity(intent);

        } else if (id == R.id.supply) {
            Intent intent = new Intent(MainActivity.this,SupplyActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_InformationEntry) {
            Intent intent = new Intent(MainActivity.this,InsertActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Exit) {
            AlertDialog alert = new AlertDialog.Builder(MainActivity.this).create();
            alert.setMessage("真的要退出吗？");
            alert.setButton(DialogInterface.BUTTON_NEGATIVE, "点错了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            alert.setButton(DialogInterface.BUTTON_POSITIVE, "是的", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alert.show();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
