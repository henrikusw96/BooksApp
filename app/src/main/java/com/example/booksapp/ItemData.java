package com.example.booksapp;

import android.os.Parcelable;
import android.os.Parcel;

public class ItemData implements Parcelable {
    public String itemTitle;
    public String itemAuthor;
    public String itemImage;
    public String itemDescription;

    public ItemData() {
    }

    protected ItemData(Parcel in) {
        itemTitle = in.readString();
        itemAuthor = in.readString();
        itemImage = in.readString();
        itemDescription = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(itemTitle);
        dest.writeString(itemAuthor);
        dest.writeString(itemImage);
        dest.writeString(itemDescription);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemData> CREATOR = new Creator<ItemData>() {
        @Override
        public ItemData createFromParcel(Parcel in) {
            return new ItemData(in);
        }

        @Override
        public ItemData[] newArray(int size) {
            return new ItemData[size];
        }
    };
}
