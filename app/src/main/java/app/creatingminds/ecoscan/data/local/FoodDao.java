package app.creatingminds.ecoscan.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import app.creatingminds.ecoscan.data.model.Food;

/**
 * Created by tom on 9/14/18.
 */

@Dao
public interface FoodDao {
    @Query("SELECT * FROM food")
    List<Food> getAll();

    @Query("SELECT * FROM food WHERE id IN (:foodIds)")
    List<Food> loadAllByIds(int[] foodIds);

    @Query("SELECT * FROM food WHERE name LIKE :name LIMIT 1")
    Food findByName(String name);

    @Insert
    void insertAll(Food... foods);

    @Update
    void updateAll(Food... food);

    @Delete
    void delete(Food food);
}