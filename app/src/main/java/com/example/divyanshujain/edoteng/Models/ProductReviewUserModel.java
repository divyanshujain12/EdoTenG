package com.example.divyanshujain.edoteng.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by divyanshuPC on 4/1/2017.
 */

public class ProductReviewUserModel implements Parcelable {

    private String user_id;
    private String name;
    private String gender;
    private String birth_year;
    private String email;
    private String password;
    private String street_address;
    private String suburb;
    private String pin;
    private String city;
    private String state;
    private String country;
    private String location_id;
    private String phone;
    private String newsletter;
    private String delevered;
    private String status;
    private String fb_user_id;
    private String gp_user_id;
    private String mod_url;
    private String date_added;
    private String creater_user_id;
    private String review;
    private String review_date;


    public ProductReviewUserModel() {
    }

    protected ProductReviewUserModel(Parcel in) {
        user_id = in.readString();
        name = in.readString();
        gender = in.readString();
        birth_year = in.readString();
        email = in.readString();
        password = in.readString();
        street_address = in.readString();
        suburb = in.readString();
        pin = in.readString();
        city = in.readString();
        state = in.readString();
        country = in.readString();
        location_id = in.readString();
        phone = in.readString();
        newsletter = in.readString();
        delevered = in.readString();
        status = in.readString();
        fb_user_id = in.readString();
        gp_user_id = in.readString();
        mod_url = in.readString();
        date_added = in.readString();
        creater_user_id = in.readString();
        review = in.readString();
        review_date = in.readString();
    }

    public static final Creator<ProductReviewUserModel> CREATOR = new Creator<ProductReviewUserModel>() {
        @Override
        public ProductReviewUserModel createFromParcel(Parcel in) {
            return new ProductReviewUserModel(in);
        }

        @Override
        public ProductReviewUserModel[] newArray(int size) {
            return new ProductReviewUserModel[size];
        }
    };

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(String birth_year) {
        this.birth_year = birth_year;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(String newsletter) {
        this.newsletter = newsletter;
    }

    public String getDelevered() {
        return delevered;
    }

    public void setDelevered(String delevered) {
        this.delevered = delevered;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFb_user_id() {
        return fb_user_id;
    }

    public void setFb_user_id(String fb_user_id) {
        this.fb_user_id = fb_user_id;
    }

    public String getGp_user_id() {
        return gp_user_id;
    }

    public void setGp_user_id(String gp_user_id) {
        this.gp_user_id = gp_user_id;
    }

    public String getMod_url() {
        return mod_url;
    }

    public void setMod_url(String mod_url) {
        this.mod_url = mod_url;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public String getCreater_user_id() {
        return creater_user_id;
    }

    public void setCreater_user_id(String creater_user_id) {
        this.creater_user_id = creater_user_id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getReview_date() {
        return review_date;
    }

    public void setReview_date(String review_date) {
        this.review_date = review_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(user_id);
        dest.writeString(name);
        dest.writeString(gender);
        dest.writeString(birth_year);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(street_address);
        dest.writeString(suburb);
        dest.writeString(pin);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(country);
        dest.writeString(location_id);
        dest.writeString(phone);
        dest.writeString(newsletter);
        dest.writeString(delevered);
        dest.writeString(status);
        dest.writeString(fb_user_id);
        dest.writeString(gp_user_id);
        dest.writeString(mod_url);
        dest.writeString(date_added);
        dest.writeString(creater_user_id);
        dest.writeString(review);
        dest.writeString(review_date);
    }
}
