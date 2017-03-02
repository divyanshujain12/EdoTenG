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
