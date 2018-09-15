package app.creatingminds.ecoscan.data;


import java.util.ArrayList;
import java.util.List;

import app.creatingminds.ecoscan.data.local.AppDatabase;
import app.creatingminds.ecoscan.data.model.Food;

/**
 * Created by tom on 9/14/18.
 */
public class AppDataManager implements DataManager {

    private AppDatabase appDatabase;

    // TODO: save in local db class
    private List<Food> foodList = new ArrayList<>();

    public AppDataManager(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    public AppDatabase getDatabase() {
        return appDatabase;
    }

    @Override
    public List<Food> getCachedFoodList() {
        return foodList;
    }

    @Override
    public void setCachedFoodList(List<Food> foodList) {
        if (foodList == null)
            foodList = new ArrayList<>();

        this.foodList = foodList;
    }
}
