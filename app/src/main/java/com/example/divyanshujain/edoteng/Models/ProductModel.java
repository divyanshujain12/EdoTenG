package com.example.divyanshujain.edoteng.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by divyanshu.jain on 2/28/2017.
 */

public class ProductModel implements Parcelable {
    private String brand_title;
    private String id;
    private String item_type;
    private String cat_id;
    private String brand_id;
    private String product_name;
    private String product_file;
    private String short_description;

    public ProductModel() {
    }

    protected ProductModel(Parcel in) {
        brand_title = in.readString();
        id = in.readString();
        item_type = in.readString();
        cat_id = in.readString();
        brand_id = in.readString();
        product_name = in.readString();
        product_file = in.readString();
        short_description = in.readString();
    }

    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel in) {
            return new ProductModel(in);
        }

        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

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

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_file() {
        return product_file;
    }

    public void setProduct_file(String product_file) {
        this.product_file = product_file;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(brand_title);
        parcel.writeString(id);
        parcel.writeString(item_type);
        parcel.writeString(cat_id);
        parcel.writeString(brand_id);
        parcel.writeString(product_name);
        parcel.writeString(product_file);
        parcel.writeString(short_description);
    }
}
