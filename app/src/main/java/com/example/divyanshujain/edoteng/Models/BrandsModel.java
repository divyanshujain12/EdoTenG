package com.example.divyanshujain.edoteng.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by divyanshu.jain on 2/28/2017.
 */

public class BrandsModel implements Parcelable{
    private String brand_title;
    private String id;

    public BrandsModel(){}

    protected BrandsModel(Parcel in) {
        brand_title = in.readString();
        id = in.readString();
    }

    public static final Creator<BrandsModel> CREATOR = new Creator<BrandsModel>() {
        @Override
        public BrandsModel createFromParcel(Parcel in) {
            return new BrandsModel(in);
        }

        @Override
        public BrandsModel[] newArray(int size) {
            return new BrandsModel[size];
        }
    };

    public String getBrand_title() {
        return brand_title;
    }

    public void setBrand_title(String brand_title) {
        this.brand_title = brand_title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(brand_title);
        parcel.writeString(id);
    }
}
