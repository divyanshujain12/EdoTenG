package com.example.divyanshujain.edoteng.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by divyanshuPC on 4/19/2017.
 */

public class AutoSearchKeywordModel implements Parcelable{
    private String keyword_title;

    public AutoSearchKeywordModel(){}

    protected AutoSearchKeywordModel(Parcel in) {
        keyword_title = in.readString();
    }

    public static final Creator<AutoSearchKeywordModel> CREATOR = new Creator<AutoSearchKeywordModel>() {
        @Override
        public AutoSearchKeywordModel createFromParcel(Parcel in) {
            return new AutoSearchKeywordModel(in);
        }

        @Override
        public AutoSearchKeywordModel[] newArray(int size) {
            return new AutoSearchKeywordModel[size];
        }
    };

    public String getKeyword_title() {
        return keyword_title;
    }

    public void setKeyword_title(String keyword_title) {
        this.keyword_title = keyword_title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(keyword_title);
    }
}
