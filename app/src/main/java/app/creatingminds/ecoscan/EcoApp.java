package app.creatingminds.ecoscan;

import android.app.Application;
import android.arch.persistence.room.Room;

import java.app.creatingminds.ecoscan.utils.Const;

import app.creatingminds.ecoscan.data.AppDataManager;
import app.creatingminds.ecoscan.data.DataManager;
import app.creatingminds.ecoscan.data.local.AppDatabase;

/**
 * Created by tom on 9/14/18.
 */
public class EcoApp extends Application {

    private static DataManager dataManager;

    public static DataManager getDataManager() {
        return dataManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, Const.APP_DATABASE_NAME).build();

        if (dataManager == null)
            dataManager = new AppDataManager(db);
    }
}
