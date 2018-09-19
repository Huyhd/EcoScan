package app.creatingminds.ecoscan.ui.main;

/**
 * Created by Kimsoomin on 2017-10-28.
 */

import android.support.annotation.DrawableRes;

public class FoodInfoItem {
    private @DrawableRes
    int iconDrawable;
    private String title;
    private String expireDate;

    public FoodInfoItem(int icon, String title, String desc) {
        this.iconDrawable = icon;
        this.title = title;
        this.expireDate = desc;
    }

    public @DrawableRes
    int getIcon() {
        return this.iconDrawable;
    }

    public void setIcon(@DrawableRes int icon) {
        iconDrawable = icon;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExpireDate() {
        return this.expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
}