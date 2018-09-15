package app.creatingminds.ecoscan.utils;

import android.content.Context;
import android.support.annotation.DrawableRes;

import java.util.Random;

import app.creatingminds.ecoscan.EcoApp;
import app.creatingminds.ecoscan.R;

/**
 * Created by tom on 9/15/18.
 */
public final class UiUtils {
    private static final @DrawableRes
    int[] FOOD_ICONS = new int[]{R.drawable.ic_food, R.drawable.ic_fast_food, R.drawable.ic_fruit};

    public static @DrawableRes
    int getRandomFoodIcon() {
        Random random = new Random();

        return FOOD_ICONS[random.nextInt(2)];
    }

    /**
     * Stringify food name and get corresponding img if exists. If not get default random img
     *
     * @param foodName
     * @return
     */
    public static @DrawableRes
    int getFoodIcon(String foodName) {
        Context context = EcoApp.getAppContext();

        foodName = FormatUtils.stringify(foodName);

        int id = context.getResources().getIdentifier(foodName, "drawable", context.getPackageName());

        if (id == 0)
            id = getRandomFoodIcon();

        return id;
    }
}
