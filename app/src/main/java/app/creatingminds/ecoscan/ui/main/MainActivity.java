package app.creatingminds.ecoscan.ui.main;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.tensorflow.demo.ClassifierActivity;

import java.util.ArrayList;
import java.util.List;

import app.creatingminds.ecoscan.EcoApp;
import app.creatingminds.ecoscan.R;
import app.creatingminds.ecoscan.data.DataManager;
import app.creatingminds.ecoscan.data.model.Food;
import app.creatingminds.ecoscan.ui.iteminfo.IteminfoActivity;
import app.creatingminds.ecoscan.ui.settings.SettingActivity;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ZXingScannerView scannerView;
    private ListView lvFood;

    private List<Food> foodList = new ArrayList<>();
    private DataManager dataManager;
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        dataManager = EcoApp.getDataManager();

        // Adapter 생성
        adapter = new ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        lvFood = findViewById(R.id.Foodlist);
        lvFood.setAdapter(adapter);

        foodList = dataManager.getCachedFoodList();
        if (foodList == null || foodList.isEmpty()) {
            // Load default data if empty

            // 첫 번째 아이템 추가.
            adapter.addItem(R.drawable.tengerine, "Orange", "2017/11/10");
            // 두 번째 아이템 추가.
            adapter.addItem(R.drawable.banana, "Banana", "2017/10/28");
            // 세 번째 아이템 추가.
            adapter.addItem(R.drawable.hotsix, "Hotsix", "2018/07/18");
        } else {
            adapter.setFood(foodList);
        }

        lvFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                FoodInfoItem item = (FoodInfoItem) parent.getItemAtPosition(position);

                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
                Drawable iconDrawable = ContextCompat.getDrawable(MainActivity.this, item.getIcon());
            }
        }) ;

        //Menu

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action

        } else if (id == R.id.nav_setting) {
            Intent setting = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(setting);
        } else if (id == R.id.nav_garbage) {
            Intent info = new Intent(MainActivity.this, IteminfoActivity.class);
            startActivity(info);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void AddbuttonClicked(View view){
        Intent opencamera = new Intent(this, ClassifierActivity.class);
        startActivity(opencamera);
    }
}
