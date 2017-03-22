package com.example.divyanshujain.edoteng.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by divyanshu.jain on 2/28/2017.
 */

public class ProductModel  implements Parcelable{
    private String brand_title;
    private String id;
    private String item_type;
    private String cat_id;
    private String brand_id;
    private String product_name;
    private String product_file;
    private String short_description;
    private String physical_price;
    private String downloadable_price;
    private String mod_url;
    private String add_date;
    private String rating;
    private String can_physical_purchase;
    private String can_downloadable_purchase;

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

    public String getPhysical_price() {
        return physical_price;
    }

    public void setPhysical_price(String physical_price) {
        this.physical_price = physical_price;
    }

    public String getDownloadable_price() {
        return downloadable_price;
    }

    public void setDownloadable_price(String downloadable_price) {
        this.downloadable_price = downloadable_price;
    }

    public String getMod_url() {
        return mod_url;
    }

    public void setMod_url(String mod_url) {
        this.mod_url = mod_url;
    }

    public String getAdd_date() {
        return add_date;
    }

    public void setAdd_date(String add_date) {
        this.add_date = add_date;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCan_physical_purchase() {
        return can_physical_purchase;
    }

    public void setCan_physical_purchase(String can_physical_purchase) {
        this.can_physical_purchase = can_physical_purchase;
    }

    public String getCan_downloadable_purchase() {
        return can_downloadable_purchase;
    }

    public void setCan_downloadable_purchase(String can_downloadable_purchase) {
        this.can_downloadable_purchase = can_downloadable_purchase;
    }

    public ProductModel(){}

    protected ProductModel(Parcel in) {
        brand_title = in.readString();
        id = in.readString();
        item_type = in.readString();
        cat_id = in.readString();
        brand_id = in.readString();
        product_name = in.readString();
        product_file = in.readString();
        short_description = in.readString();
        physical_price = in.readString();
        downloadable_price = in.readString();
        mod_url = in.readString();
        add_date = in.readString();
        rating = in.readString();
        can_physical_purchase = in.readString();
        can_downloadable_purchase = in.readString();
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brand_title);
        dest.writeString(id);
        dest.writeString(item_type);
        dest.writeString(cat_id);
        dest.writeString(brand_id);
        dest.writeString(product_name);
        dest.writeString(product_file);
        dest.writeString(short_description);
        dest.writeString(physical_price);
        dest.writeString(downloadable_price);
        dest.writeString(mod_url);
        dest.writeString(add_date);
        dest.writeString(rating);
        dest.writeString(can_physical_purchase);
        dest.writeString(can_downloadable_purchase);
    }
}
