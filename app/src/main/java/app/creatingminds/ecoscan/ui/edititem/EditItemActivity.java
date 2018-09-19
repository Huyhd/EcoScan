package app.creatingminds.ecoscan.ui.edititem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.creatingminds.ecoscan.EcoApp;
import app.creatingminds.ecoscan.R;
import app.creatingminds.ecoscan.data.model.Food;
import app.creatingminds.ecoscan.utils.FormatUtils;

/**
 * Created by Kimsoomin on 2017-10-29.
 */

public class EditItemActivity extends AppCompatActivity {

    public static final String EXTRA_KEY_LIST_FOOD = "extra_key_list_food";

    private List<FoodItem> foodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newitemslist);

        final FoodItemAdapter adapter;

        // Adapter 생성
        adapter = new FoodItemAdapter();

        // 리스트뷰 참조 및 Adapter달기
        final ListView newlv = (ListView) findViewById(R.id.NewFood);
        newlv.setAdapter(adapter);

        foodList = getIntent().getParcelableArrayListExtra(EXTRA_KEY_LIST_FOOD);

        adapter.setFoodList(foodList);
    }

    public void onDoneClick(View view) {
        // Write to db
        // TODO: Optimize
        new Thread(new Runnable() {
            @Override
            public void run() {
                Food[] food = buildFoodList(foodList);
                EcoApp.getDataManager().getDatabase().foodDao().insertAll(food);
                List<Food> foodList = new ArrayList<>(Arrays.asList(food));
                foodList.addAll(EcoApp.getDataManager().getCachedFoodList());
                EcoApp.getDataManager().setCachedFoodList(foodList);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                });
            }
        }).start();
    }

    private Food[] buildFoodList(List<FoodItem> foodList) {
        Food[] food = new Food[foodList.size()];

        for (int i = 0; i < foodList.size(); i++) {
            FoodItem item = foodList.get(i);
            food[i] = new Food(item.getTitle(), FormatUtils.convertDateToTimestamp(item.getTime()));
        }

        return food;
    }
}
