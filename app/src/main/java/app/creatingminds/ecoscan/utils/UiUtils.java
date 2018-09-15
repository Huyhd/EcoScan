package app.creatingminds.ecoscan.utils;

import android.support.annotation.DrawableRes;

import java.util.Random;

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
}
