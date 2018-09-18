package app.creatingminds.ecoscan.ui.main;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import org.tensorflow.demo.ClassifierActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import app.creatingminds.ecoscan.EcoApp;
import app.creatingminds.ecoscan.R;
import app.creatingminds.ecoscan.data.DataManager;
import app.creatingminds.ecoscan.data.model.Food;
import app.creatingminds.ecoscan.ui.iteminfo.IteminfoActivity;
import app.creatingminds.ecoscan.ui.settings.SettingActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int CAMERA_STORAGE_PERMISSIONS_REQUEST = 1;

    private static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    private static final String PERMISSION_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private static final String TAG_INPUT_DIALOG = "input_dialog";

    private ListView lvFood;
    private FloatingActionButton fabCamera;
    private FloatingActionButton fabManual;
    private FloatingActionButton fabParent;
    private InputDialogFragment inputDialogFragment;

    private Animation showFab;
    private Animation hideFab;
    private Animation rotateShowFab;
    private Animation rotateHideFab;

    private List<Food> foodList = new ArrayList<>();
    private DataManager dataManager;
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        dataManager = EcoApp.getDataManager();

        // Adapter 생성
        adapter = new ListViewAdapter();

        showFab = AnimationUtils.loadAnimation(this, R.anim.fab_show);
        hideFab = AnimationUtils.loadAnimation(this, R.anim.fab_hide);
        rotateShowFab = AnimationUtils.loadAnimation(this, R.anim.fab_rotate_show);
        rotateHideFab = AnimationUtils.loadAnimation(this, R.anim.fab_rotate_hide);

        // 리스트뷰 참조 및 Adapter달기
        lvFood = findViewById(R.id.lv_food);
        fabCamera = findViewById(R.id.fab_add_camera);
        fabManual = findViewById(R.id.fab_add_manual);
        fabParent = findViewById(R.id.fab_add);
        inputDialogFragment = InputDialogFragment.newInstance();

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

        //Menu
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setupEventListeners();
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onRequestPermissionsResult(
            final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        if (requestCode == CAMERA_STORAGE_PERMISSIONS_REQUEST) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                startClassifierActivity();
            } else {
                requestPermission();
            }
        }
    }

    private boolean hasPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(PERMISSION_CAMERA) == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(PERMISSION_STORAGE) == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (shouldShowRequestPermissionRationale(PERMISSION_CAMERA) ||
                    shouldShowRequestPermissionRationale(PERMISSION_STORAGE)) {
                Toast.makeText(this,
                        "Camera & storage permission are required for this app to work", Toast.LENGTH_LONG).show();
            }
            requestPermissions(new String[]{PERMISSION_CAMERA, PERMISSION_STORAGE}, CAMERA_STORAGE_PERMISSIONS_REQUEST);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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

    private void setupEventListeners() {
        lvFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, final int position, long id) {
                final Food food = foodList.get(position);

                final Calendar c = Calendar.getInstance();
                c.setTimeInMillis(food.getExpireTimestamp());

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                c.set(Calendar.YEAR, year);
                                c.set(Calendar.MONTH, monthOfYear);
                                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                food.setExpireTimestamp(c.getTimeInMillis());

                                foodList.set(position, food);
                                adapter.updateFood(position, food);

                                // TODO: Optimize
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        dataManager.getDatabase().foodDao().updateAll(food);
                                        dataManager.setCachedFoodList(foodList);
                                    }
                                }).start();
                            }
                        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        lvFood.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int pos, long l) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert)
                                .setTitle("Delete food")
                                .setMessage("Are you sure to delete this food?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        adapter.removeItem(pos);

                                        // TODO: Optimize
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                // Update data
                                                Food food = foodList.get(pos);
                                                dataManager.getDatabase().foodDao().delete(food);
                                                foodList.remove(pos);
                                            }
                                        }).start();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });


                builder.create().show();

                return false; // Dispatch event to next layer
            }
        });

        inputDialogFragment.setOnPositiveClickListener(new InputDialogFragment.OnPositiveClickListener() {
            @Override
            public void onClick(DialogFragment dialog, String foodName, long timestamp) {
                final Food food = new Food(foodName, timestamp);
                foodList.add(food);
                adapter.addItem(food);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        dataManager.getDatabase().foodDao().insertAll(food);
                    }
                }).start();

                dialog.dismiss();
            }
        });
    }

    public void onAddCameraBtnClicked(View view) {
        startHideAnimation(fabParent);
        if (hasPermission()) {
            startClassifierActivity();
        } else {
            requestPermission();
        }
    }

    public void onAddManualBtnClicked(View view) {
        startHideAnimation(fabParent);
        inputDialogFragment.show(getFragmentManager(), TAG_INPUT_DIALOG);
    }

    public void onAddBtnClicked(View view) {
        if (fabCamera.isShown()) {
            startHideAnimation(view);
        } else {
            startShowAnimation(view);
        }
    }

    private void startShowAnimation(View parentButton) {
        parentButton.setAnimation(rotateShowFab);
        fabCamera.startAnimation(showFab);
        fabManual.startAnimation(showFab);
        fabCamera.setVisibility(View.VISIBLE);
        fabManual.setVisibility(View.VISIBLE);
    }

    private void startHideAnimation(View parentButton) {
        parentButton.setAnimation(rotateHideFab);
        fabCamera.startAnimation(hideFab);
        fabManual.startAnimation(hideFab);
        fabCamera.setVisibility(View.GONE);
        fabManual.setVisibility(View.GONE);
    }

    private void startClassifierActivity() {
        Intent opencamera = new Intent(this, ClassifierActivity.class);
        startActivity(opencamera);
    }

    public void onSortButtonClicked() {
        sortFoodByExpireDate();
        adapter.setFood(foodList);
    }

    private void sortFoodByExpireDate() {
        Collections.sort(foodList, new Comparator<Food>() {
            @Override
            public int compare(Food f1, Food f2) {
                return Long.compare(f1.getExpireTimestamp(), f2.getExpireTimestamp());
            }
        });
    }
}
