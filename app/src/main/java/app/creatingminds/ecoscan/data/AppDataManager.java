package app.creatingminds.ecoscan.data;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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

    @Override
    public List<Food> getRandomFoodList(int count) {
        if (foodList == null || foodList.isEmpty())
            return new ArrayList<>();

        if (foodList.size() <= count)
            return foodList;

        Random random = new Random();
        final Set<Integer> indexSet = new HashSet<>();
        while (indexSet.size() < count) {
            indexSet.add(random.nextInt(foodList.size()));
        }

        List<Food> randomList = new ArrayList<>();
        for (Integer index : indexSet) {
            randomList.add(foodList.get(index));
        }

        return randomList;
    }
}
