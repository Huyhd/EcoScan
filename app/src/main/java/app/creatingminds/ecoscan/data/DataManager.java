package app.creatingminds.ecoscan.data;

import app.creatingminds.ecoscan.data.local.AppDatabase;

/**
 * Created by tom on 9/14/18.
 */
public interface DataManager {
    AppDatabase getDatabase();
}
