package app.creatingminds.ecoscan.data;


import app.creatingminds.ecoscan.data.local.AppDatabase;

/**
 * Created by tom on 9/14/18.
 */
public class AppDataManager implements DataManager {

    private AppDatabase appDatabase;

    public AppDataManager(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    public AppDatabase getDatabase() {
        return appDatabase;
    }
}
