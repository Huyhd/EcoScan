package app.creatingminds.ecoscan.ui.today;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.creatingminds.ecoscan.EcoApp;
import app.creatingminds.ecoscan.R;
import app.creatingminds.ecoscan.data.DataManager;
import app.creatingminds.ecoscan.data.model.Food;
import app.creatingminds.ecoscan.utils.Const;

public class TodayActivity extends AppCompatActivity {

    private ListView lvFood;
    private FloatingActionButton fabRefresh;
    private LinearLayout layoutPlaceHolder;

    private DataManager dataManager;
    private TodayListAdapter adapter;

    private List<Food> randomFoodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

        dataManager = EcoApp.getDataManager();
        adapter = new TodayListAdapter();

        lvFood = findViewById(R.id.lv_food);
        fabRefresh = findViewById(R.id.fab_refresh);
        layoutPlaceHolder = findViewById(R.id.layout_placeholder);

        lvFood.setAdapter(adapter);

        randomFoodList = dataManager.getRandomFoodList(Const.DEFAULT_FOOD_ITEM_TODAY);
        if (randomFoodList != null && !randomFoodList.isEmpty()) {
            layoutPlaceHolder.setVisibility(View.GONE);
            adapter.setFood(randomFoodList);
        }

        setupEventListeners();
    }

    private void setupEventListeners() {

    }

    public void onRandomBtnClicked(View view) {
        randomFoodList = dataManager.getRandomFoodList(Const.DEFAULT_FOOD_ITEM_TODAY);
        adapter.setFood(randomFoodList);
    }
}
