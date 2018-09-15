package app.creatingminds.ecoscan.ui.edititem;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kimsoomin on 2017-10-28.
 */

public class FoodItem implements Parcelable {
    public static final Creator<FoodItem> CREATOR = new Creator<FoodItem>() {
        @Override
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }

        @Override
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };
    private String titleStr;
    private String time;

    public FoodItem() {

    }

    protected FoodItem(Parcel in) {
        titleStr = in.readString();
        time = in.readString();
    }

    public FoodItem(String name, String time) {
        this.titleStr = name;
        this.time = time;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titleStr);
        dest.writeString(time);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getTitle() {
        return this.titleStr;
    }

    public void setTitle(String title) {
        titleStr = title;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}