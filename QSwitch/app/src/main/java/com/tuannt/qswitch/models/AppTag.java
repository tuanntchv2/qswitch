package com.tuannt.qswitch.models;

import android.os.Parcel;

import com.orm.dsl.Column;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;

import lombok.Getter;

/**
 * Copyright Tuannt
 * Created by TuanNT on 8/23/2015.
 */
public class AppTag extends BaseModel {
    @Getter
    @Column(name = "tag_id")
    private int tagId;
    @Getter
    @Column(name = "app_id")
    private int appId;

    public static List<AppTag> getAppTagByTagId(long id){
        return Select.from(AppTag.class).
                where(Condition.prop("tag_id").eq(id)).list();
    }

    public static List<AppTag> getAppTagByAppId(long id){
        return Select.from(AppTag.class).
                where(Condition.prop("app_id").eq(id)).list();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(getId());
        dest.writeInt(this.tagId);
        dest.writeInt(this.appId);
    }

    public AppTag() {
    }

    protected AppTag(Parcel in) {
        this.setId(in.readLong());
        this.tagId = in.readInt();
        this.appId = in.readInt();
    }

    public static final Creator<AppTag> CREATOR = new Creator<AppTag>() {
        public AppTag createFromParcel(Parcel source) {
            return new AppTag(source);
        }

        public AppTag[] newArray(int size) {
            return new AppTag[size];
        }
    };
}
