package com.example.divyanshujain.edoteng.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by divyanshuPC on 3/31/2017.
 */

public class ProductDetailModel implements Parcelable {
    private String id;
    private String item_type;
    private String cat_id;
    private String cat_level1;
    private String cat_level2;
    private String cat_level3;
    private String product_listing_type;
    private String brand_id;
    private String product_name;
    private String template_id;
    private String product_file;
    private String short_description;
    private String description;
    private String curriculum;
    private String about;
    private String physical_price;
    private String downloadable_price;
    private String shipping;
    private String views;
    private String language;
    private String duration;
    private String version;
    private String metaTitle;
    private String metaKeywords;
    private String metaDescription;
    private String mod_url;
    private String status;
    private String position;
    private String rating;
    private String can_physical_purchase;
    private String can_downloadable_purchase;
    private String last_modified;
    private String add_date;
    private String random;
    private String recommended_prod;
    private String suggestedKeywords;
    private String test_series_url;
    private String live_class_url;
    private String live_video_url;
    private String title;
    private String timeline_url;
    private int physicalPriceAdded = 0;
    private ArrayList<ReviewModel> rArray;
    private ArrayList<ProductReviewUserModel> productReview;

    public ProductDetailModel() {
    }

    protected ProductDetailModel(Parcel in) {
        id = in.readString();
        item_type = in.readString();
        cat_id = in.readString();
        cat_level1 = in.readString();
        cat_level2 = in.readString();
        cat_level3 = in.readString();
        product_listing_type = in.readString();
        brand_id = in.readString();
        product_name = in.readString();
        template_id = in.readString();
        product_file = in.readString();
        short_description = in.readString();
        description = in.readString();
        curriculum = in.readString();
        about = in.readString();
        physical_price = in.readString();
        downloadable_price = in.readString();
        shipping = in.readString();
        views = in.readString();
        language = in.readString();
        duration = in.readString();
        version = in.readString();
        metaTitle = in.readString();
        metaKeywords = in.readString();
        metaDescription = in.readString();
        mod_url = in.readString();
        status = in.readString();
        position = in.readString();
        rating = in.readString();
        can_physical_purchase = in.readString();
        can_downloadable_purchase = in.readString();
        last_modified = in.readString();
        add_date = in.readString();
        random = in.readString();
        recommended_prod = in.readString();
        suggestedKeywords = in.readString();
        test_series_url = in.readString();
        live_class_url = in.readString();
        live_video_url = in.readString();
        title = in.readString();
        timeline_url = in.readString();
        physicalPriceAdded = in.readInt();
        productReview = in.createTypedArrayList(ProductReviewUserModel.CREATOR);
        rArray = in.createTypedArrayList(ReviewModel.CREATOR);
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

    public String getCat_level1() {
        return cat_level1;
    }

    public void setCat_level1(String cat_level1) {
        this.cat_level1 = cat_level1;
    }

    public String getCat_level2() {
        return cat_level2;
    }

    public void setCat_level2(String cat_level2) {
        this.cat_level2 = cat_level2;
    }

    public String getCat_level3() {
        return cat_level3;
    }

    public void setCat_level3(String cat_level3) {
        this.cat_level3 = cat_level3;
    }

    public String getProduct_listing_type() {
        return product_listing_type;
    }

    public void setProduct_listing_type(String product_listing_type) {
        this.product_listing_type = product_listing_type;
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

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
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

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getMod_url() {
        return mod_url;
    }

    public void setMod_url(String mod_url) {
        this.mod_url = mod_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public String getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(String last_modified) {
        this.last_modified = last_modified;
    }

    public String getAdd_date() {
        return add_date;
    }

    public void setAdd_date(String add_date) {
        this.add_date = add_date;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public String getRecommended_prod() {
        return recommended_prod;
    }

    public void setRecommended_prod(String recommended_prod) {
        this.recommended_prod = recommended_prod;
    }

    public String getSuggestedKeywords() {
        return suggestedKeywords;
    }

    public void setSuggestedKeywords(String suggestedKeywords) {
        this.suggestedKeywords = suggestedKeywords;
    }

    public String getTest_series_url() {
        return test_series_url;
    }

    public void setTest_series_url(String test_series_url) {
        this.test_series_url = test_series_url;
    }

    public String getLive_class_url() {
        return live_class_url;
    }

    public void setLive_class_url(String live_class_url) {
        this.live_class_url = live_class_url;
    }

    public String getLive_video_url() {
        return live_video_url;
    }

    public void setLive_video_url(String live_video_url) {
        this.live_video_url = live_video_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimeline_url() {
        return timeline_url;
    }

    public void setTimeline_url(String timeline_url) {
        this.timeline_url = timeline_url;
    }

    public ArrayList<ReviewModel> getrArray() {
        return rArray;
    }

    public void setrArray(ArrayList<ReviewModel> rArray) {
        this.rArray = rArray;
    }

    public ArrayList<ProductReviewUserModel> getProductReview() {
        return productReview;
    }

    public void setProductReview(ArrayList<ProductReviewUserModel> productReview) {
        this.productReview = productReview;
    }

    public int getPhysicalPriceAdded() {
        return physicalPriceAdded;
    }

    public void setPhysicalPriceAdded(int physicalPriceAdded) {
        this.physicalPriceAdded = physicalPriceAdded;
    }

    public static final Creator<ProductDetailModel> CREATOR = new Creator<ProductDetailModel>() {
        @Override
        public ProductDetailModel createFromParcel(Parcel in) {
            return new ProductDetailModel(in);
        }

        @Override
        public ProductDetailModel[] newArray(int size) {
            return new ProductDetailModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(item_type);
        dest.writeString(cat_id);
        dest.writeString(cat_level1);
        dest.writeString(cat_level2);
        dest.writeString(cat_level3);
        dest.writeString(product_listing_type);
        dest.writeString(brand_id);
        dest.writeString(product_name);
        dest.writeString(template_id);
        dest.writeString(product_file);
        dest.writeString(short_description);
        dest.writeString(description);
        dest.writeString(curriculum);
        dest.writeString(about);
        dest.writeString(physical_price);
        dest.writeString(downloadable_price);
        dest.writeString(shipping);
        dest.writeString(views);
        dest.writeString(language);
        dest.writeString(duration);
        dest.writeString(version);
        dest.writeString(metaTitle);
        dest.writeString(metaKeywords);
        dest.writeString(metaDescription);
        dest.writeString(mod_url);
        dest.writeString(status);
        dest.writeString(position);
        dest.writeString(rating);
        dest.writeString(can_physical_purchase);
        dest.writeString(can_downloadable_purchase);
        dest.writeString(last_modified);
        dest.writeString(add_date);
        dest.writeString(random);
        dest.writeString(recommended_prod);
        dest.writeString(suggestedKeywords);
        dest.writeString(test_series_url);
        dest.writeString(live_class_url);
        dest.writeString(live_video_url);
        dest.writeString(title);
        dest.writeInt(physicalPriceAdded);
        dest.writeString(timeline_url);
        dest.writeTypedList(productReview);
    }
}