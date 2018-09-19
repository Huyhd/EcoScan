package app.creatingminds.ecoscan.ui.today;

/**
 * Created by Kimsoomin on 2017-10-28.
 */

import android.support.annotation.DrawableRes;

public class TodayFoodInfoItem {
    private @DrawableRes
    int iconDrawable;
    private String title;
    private String expireDate;
    private int quantity;

    public TodayFoodInfoItem(int icon, String title, String expireDate, int quantity) {
        this.iconDrawable = icon;
        this.title = title;
        this.expireDate = expireDate;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}