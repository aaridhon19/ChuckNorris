package com.example.chucknorris.models;

import android.os.Parcel;
import android.os.Parcelable;

public class JokeModel implements Parcelable {
    private String value;
    private String icon_url;
    private String id;

    protected JokeModel(Parcel in) {
        value = in.readString();
        icon_url = in.readString();
        id = in.readString();
    }

    public static final Creator<JokeModel> CREATOR = new Creator<JokeModel>() {
        @Override
        public JokeModel createFromParcel(Parcel in) {
            return new JokeModel(in);
        }

        @Override
        public JokeModel[] newArray(int size) {
            return new JokeModel[size];
        }
    };

    public String getValue() {
        return value;
    }

    public String getUrl() {
        return icon_url;
    }

    public String getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(value);
        parcel.writeString(icon_url);
        parcel.writeString(id);
    }
}