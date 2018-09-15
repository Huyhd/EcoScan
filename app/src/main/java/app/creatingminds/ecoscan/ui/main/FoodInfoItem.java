package app.creatingminds.ecoscan.ui.main;

/**
 * Created by Kimsoomin on 2017-10-28.
 */

import android.graphics.drawable.Drawable;

public class FoodInfoItem {
    private Drawable iconDrawable ;
    private String titleStr ;
    private String descStr ;

    public FoodInfoItem(Drawable icon, String title, String desc) {
        this.iconDrawable = icon;
        this.titleStr = title;
        this.descStr = desc;
    }

    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }

    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
}