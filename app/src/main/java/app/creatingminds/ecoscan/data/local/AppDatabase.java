package app.creatingminds.ecoscan.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import app.creatingminds.ecoscan.data.model.Food;


/**
 * Created by tom on 9/14/18.
 */

@Database(entities = {Food.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FoodDao foodDao();
}