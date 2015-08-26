package com.tuannt.qswitch.models;

import android.os.Parcel;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright Tuannt
 * Created by TuanNT on 8/17/2015.
 */
@Table(name = "t_contact")
@AllArgsConstructor(suppressConstructorProperties = true)
@EqualsAndHashCode(callSuper = false)
@Data
@Builder
public class Contact extends BaseModel {
    @Column(name = "name")
    private String name;
    @Column(name = "tag_id")
    private int tagId;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "rank")
    private int rank;

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(getId());
        dest.writeString(this.name);
        dest.writeInt(this.tagId);
        dest.writeString(this.phone);
        dest.writeString(this.email);
        dest.writeString(this.address);
        dest.writeInt(this.rank);
    }

    protected Contact(Parcel in) {
        this.setId(in.readLong());
        this.name = in.readString();
        this.tagId = in.readInt();
        this.phone = in.readString();
        this.email = in.readString();
        this.address = in.readString();
        this.rank = in.readInt();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

}
