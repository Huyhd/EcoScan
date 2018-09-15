package app.creatingminds.ecoscan.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by tom on 9/14/18.
 */

@Entity
public class Food implements Parcelable {
    public static final int TYPE_VEGETABLE = 0;
    public static final int TYPE_MEAT = 1;
    public static final int TYPE_FRUIT = 2;
    public static final int TYPE_PACKED = 3;
    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "expire_date")
    private long expireTimestamp;
    @ColumnInfo(name = "nutrition")
    private String nutrition;
    @ColumnInfo(name = "price")
    private double price;
    @ColumnInfo(name = "type")
    private int type;

    public Food(String name, long expireTimestamp, String nutrition, double price, int type) {
        this.name = name;
        this.expireTimestamp = expireTimestamp;
        this.nutrition = nutrition;
        this.price = price;
        this.type = type;
    }

    @Ignore
    public Food(String name, long expireTimestamp) {
        this(name, expireTimestamp, null, 0, TYPE_VEGETABLE);
    }

    @Ignore
    protected Food(Parcel in) {
        id = in.readInt();
        name = in.readString();
        expireTimestamp = in.readLong();
        nutrition = in.readString();
        price = in.readDouble();
        type = in.readInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getExpireTimestamp() {
        return expireTimestamp;
    }

    public void setExpireTimestamp(long expireTimestamp) {
        this.expireTimestamp = expireTimestamp;
    }

    public String getNutrition() {
        return nutrition;
    }

    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeLong(expireTimestamp);
        parcel.writeString(nutrition);
        parcel.writeDouble(price);
        parcel.writeInt(type);
    }
}
