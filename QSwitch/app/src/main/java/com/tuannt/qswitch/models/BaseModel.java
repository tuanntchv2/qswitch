package com.tuannt.qswitch.models;

import android.os.Parcelable;

import com.orm.SugarRecord;

/**
 * Copyright Tuannt
 * Created by TuanNT on 8/17/2015.
 */
public abstract class BaseModel extends SugarRecord implements Parcelable {
    @Override
    public int describeContents() {
        return 0;
    }
}
