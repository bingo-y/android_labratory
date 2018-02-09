package com.bingo.laboratory.ui.parcel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tuyx on 2017/9/27.
 * Description :
 */

public class House implements Parcelable{

    private String name;
    private String address;
    private int size;

    public House() {
    }

    protected House(Parcel in) {
        name = in.readString();
        address = in.readString();
        size = in.readInt();
    }

    public static final Creator<House> CREATOR = new Creator<House>() {
        @Override
        public House createFromParcel(Parcel in) {
            return new House(in);
        }

        @Override
        public House[] newArray(int size) {
            return new House[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeInt(size);
    }


}
