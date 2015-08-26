package com.tuannt.qswitch.models;

import android.os.Parcel;

import com.orm.dsl.Column;
import com.orm.dsl.Table;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright Tuannt
 * Created by TuanNT on 8/17/2015.
 */
@Table(name = "t_app")
@AllArgsConstructor(suppressConstructorProperties = true)
@EqualsAndHashCode(callSuper = false)
@Data
@Builder
public class Application extends BaseModel {
    @Column(name = "name")
    private String name;
    @Column(name = "package")
    private String sPackage;
    @Column(name = "rank")
    private int rank;

    private static boolean isExist(String strPackage) {
        String args[] = {strPackage};
        return Application.count(Application.class, "package = ?", args) > 0;
    }

    public List<Tag> getTags() {
        List<Tag> tags = new ArrayList<>();
        for (AppTag appTag : AppTag.getAppTagByAppId(getId())) {
            tags.add(Tag.findById(Tag.class, appTag.getTagId()));
        }
        return tags;
    }

    public static List<Application> getApplications() {
        return Select.from(Application.class).list();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(getId());
        dest.writeString(this.name);
        dest.writeString(this.sPackage);
        dest.writeInt(this.rank);
    }

    protected Application(Parcel in) {
        this.setId(in.readLong());
        this.name = in.readString();
        this.sPackage = in.readString();
        this.rank = in.readInt();
    }

    public static final Creator<Application> CREATOR = new Creator<Application>() {
        public Application createFromParcel(Parcel source) {
            return new Application(source);
        }

        public Application[] newArray(int size) {
            return new Application[size];
        }
    };

}
