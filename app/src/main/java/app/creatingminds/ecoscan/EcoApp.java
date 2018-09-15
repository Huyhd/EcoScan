package app.creatingminds.ecoscan;

import android.app.Application;
import android.arch.persistence.room.Room;

import app.creatingminds.ecoscan.data.AppDataManager;
import app.creatingminds.ecoscan.data.DataManager;
import app.creatingminds.ecoscan.data.local.AppDatabase;
import app.creatingminds.ecoscan.utils.Const;

/**
 * Created by tom on 9/14/18.
 */
public class EcoApp extends Application {

    private static DataManager dataManager;
    private static EcoApp ecoApp;

    public static DataManager getDataManager() {
        return dataManager;
    }

    public static EcoApp getAppContext() {
        return ecoApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        ecoApp = this;

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, Const.APP_DATABASE_NAME).build();

        if (dataManager == null)
            dataManager = new AppDataManager(db);
    }
}
