package com.tuannt.qswitch.models;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;
import lombok.Setter;

/**
 * Copyright Tuannt
 * Created by TuanNT on 8/17/2015.
 */
public enum Favorite implements Parcelable {

    ON(1), OFF(2);

    @Setter
    @Getter
    private int mValue;

    Favorite(int value) {
        mValue = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

}
