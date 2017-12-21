package com.zhaldak.androidexam.data;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Planet implements Parcelable{
    @SerializedName("name")
    private String title;
    @SerializedName("description")
    private String details;
    @SerializedName("image_url")
    private String pictureUrl;

    protected Planet(Parcel in) {
        title = in.readString();
        details = in.readString();
        pictureUrl = in.readString();
    }

    public static final Creator<Planet> CREATOR = new Creator<Planet>() {
        @Override
        public Planet createFromParcel(Parcel in) {
            return new Planet(in);
        }

        @Override
        public Planet[] newArray(int size) {
            return new Planet[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    @Override
    public int describeContents() {
       return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(details);
        parcel.writeString(pictureUrl);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Planet planet = (Planet) o;

        if (title != null ? !title.equals(planet.title) : planet.title != null) return false;
        if (details != null ? !details.equals(planet.details) : planet.details != null)
            return false;
        return pictureUrl != null ? pictureUrl.equals(planet.pictureUrl) : planet.pictureUrl == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (pictureUrl != null ? pictureUrl.hashCode() : 0);
        return result;
    }
}
