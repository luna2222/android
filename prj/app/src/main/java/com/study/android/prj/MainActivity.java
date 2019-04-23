package com.study.android.prj;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //drawer toggle button(상단 작대기 3개 아이콘)
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //앱 시작되면 보여줄 첫번째 프래그먼트 설정
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new MainFragment()).commit();
        navigationView.setCheckedItem(R.id.menu1);//선택됨을 표시
    }

    //폰 자체 백버튼
    @Override
    public void onBackPressed(){

        // 메뉴가 펼쳐져 있으 경우에는 종료
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //메뉴가 닫힌 상테에서 뒤로가기 두번 누르면 앱 종료
            if(System.currentTimeMillis()-time>=2000){
                time=System.currentTimeMillis();
                Toast.makeText(getApplicationContext(),"뒤로가기 버튼을 한번 더 누르면 앱이 종료됩니다.",Toast.LENGTH_SHORT).show();
            }else if(System.currentTimeMillis()-time<2000){
                finish();
            }
        }
    }

    //메뉴 선택 이벤트
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            //drawer_menu.xml에 설정된 id
            //프래그먼트를 교체해 준다.

            case R.id.mymenu:
                Toast.makeText(this, "준비중입니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu1:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MainFragment()).commit();
                break;
            case R.id.menu2:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MovieFragment()).commit();
                break;
            case R.id.menu3:
                Toast.makeText(this, "준비중입니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu4:
                Toast.makeText(this, "준비중입니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu5:
                Toast.makeText(this, "준비중입니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu6:
                Toast.makeText(this, "준비중입니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu7:
                Toast.makeText(this, "준비중입니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.boarder:
                Toast.makeText(this, "준비중입니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.callinder:
                Toast.makeText(this, "준비중입니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.locatian:
                Toast.makeText(this, "준비중입니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.chat:
                Toast.makeText(this, "준비중입니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.share:
                Toast.makeText(this, "준비중입니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.translation:
                Toast.makeText(this, "준비중입니다.", Toast.LENGTH_LONG).show();
                break;
        }
        //drawer close
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private long time= 0;
}
