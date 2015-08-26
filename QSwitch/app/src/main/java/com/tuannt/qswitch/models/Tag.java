package com.tuannt.qswitch.models;

import android.os.Parcel;

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
@Table(name = "t_tag")
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class Tag extends BaseModel {

    private String name;
    private int level;

    //function
    public static List<Tag> getTags() {
        return Select.from(Tag.class).list();
    }

    public List<Application> getApps() {
        List<Application> apps = new ArrayList<>();
        for (AppTag appTag : AppTag.getAppTagByTagId(getId())) {
            apps.add(Application.findById(Application.class, appTag.getAppId()));
        }
        return apps;
    }

    public static Tag checkAndInsert(String name, int tagLevel) {
        Tag tag = null;
        if (!isExistTagName(name)) {
            tag = Tag.builder()
                    .name(name)
                    .level(tagLevel).build();
            tag.save();
        }
        return tag;
    }

    public static boolean isExistTagName(String name) {
        String[] args = {name};
        return Tag.count(Tag.class, "name = ?", args) > 0;
    }

    public void deleteTag() {
        Tag.deleteAll(Tag.class, "ID = ?", String.valueOf(getId()));
        deleteAppTag();
    }

    private void deleteAppTag() {
        AppTag.deleteAll(AppTag.class, "tagId = ?", String.valueOf(getId()));
    }

    //init
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(getId());
        dest.writeString(this.name);
        dest.writeInt(this.level);
    }

    protected Tag(Parcel in) {
        this.setId(in.readLong());
        this.name = in.readString();
        this.level = in.readInt();
    }

    public static final Creator<Tag> CREATOR = new Creator<Tag>() {
        public Tag createFromParcel(Parcel source) {
            return new Tag(source);
        }

        public Tag[] newArray(int size) {
            return new Tag[size];
        }
    };

}
