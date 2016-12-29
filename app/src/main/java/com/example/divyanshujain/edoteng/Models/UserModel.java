package com.example.divyanshujain.edoteng.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by divyanshu on 8/26/2016.
 */
public class UserModel implements Parcelable {
    String user_id;
    String name;
    String date_of_birth;
    String email;
    String phone;



    public UserModel() {
    }

    protected UserModel(Parcel in) {
        user_id = in.readString();
        email = in.readString();
        name = in.readString();
        date_of_birth = in.readString();
        phone = in.readString();

    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(user_id);
        dest.writeString(email);
        dest.writeString(name);
        dest.writeString(date_of_birth);
        dest.writeString(phone);
    }
}
