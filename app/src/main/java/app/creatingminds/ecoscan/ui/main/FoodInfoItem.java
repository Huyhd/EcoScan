package app.creatingminds.ecoscan.ui.main;

/**
 * Created by Kimsoomin on 2017-10-28.
 */

import android.support.annotation.DrawableRes;

public class FoodInfoItem {
    private @DrawableRes
    int iconDrawable;
    private String titleStr ;
    private String descStr ;

    public FoodInfoItem(int icon, String title, String desc) {
        this.iconDrawable = icon;
        this.titleStr = title;
        this.descStr = desc;
    }

    public @DrawableRes
    int getIcon() {
        return this.iconDrawable;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }

    public void setIcon(@DrawableRes int icon) {
        iconDrawable = icon;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
}