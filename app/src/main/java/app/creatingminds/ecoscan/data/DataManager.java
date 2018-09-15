package app.creatingminds.ecoscan.data;

import java.util.List;

import app.creatingminds.ecoscan.data.local.AppDatabase;
import app.creatingminds.ecoscan.data.model.Food;

/**
 * Created by tom on 9/14/18.
 */
public interface DataManager {
    AppDatabase getDatabase();

    List<Food> getCachedFoodList();

    void setCachedFoodList(List<Food> foodList);
}
