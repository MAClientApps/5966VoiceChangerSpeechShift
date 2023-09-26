package com.voice.changer.speechshift.getApiData.allModel;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import kotlin.jvm.internal.Intrinsics;

public final class License implements Parcelable {
    public static final Parcelable.Creator<License> CREATOR = new Creator();
    private final String key;
    private final String name;
    private final String node_id;
    private final String spdx_id;
    private final String url;

    public static final class Creator implements Parcelable.Creator<License> {
        public final License createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new License(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
        }

        public final License[] newArray(int i) {
            return new License[i];
        }
    }

    public final License copy(String str, String str2, String str3, String str4, String str5) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(str3, "spdx_id");
        Intrinsics.checkNotNullParameter(str4, "url");
        Intrinsics.checkNotNullParameter(str5, "node_id");
        return new License(str, str2, str3, str4, str5);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof License)) {
            return false;
        }
        License license = (License) obj;
        return Intrinsics.areEqual((Object) this.key, (Object) license.key) && Intrinsics.areEqual((Object) this.name, (Object) license.name) && Intrinsics.areEqual((Object) this.spdx_id, (Object) license.spdx_id) && Intrinsics.areEqual((Object) this.url, (Object) license.url) && Intrinsics.areEqual((Object) this.node_id, (Object) license.node_id);
    }

    public int hashCode() {
        return (((((((this.key.hashCode() * 31) + this.name.hashCode()) * 31) + this.spdx_id.hashCode()) * 31) + this.url.hashCode()) * 31) + this.node_id.hashCode();
    }

    @NonNull
    public String toString() {
        return "License(key=" + this.key + ", name=" + this.name + ", spdx_id=" + this.spdx_id + ", url=" + this.url + ", node_id=" + this.node_id + ')';
    }

    public void writeToParcel(@NonNull Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.key);
        parcel.writeString(this.name);
        parcel.writeString(this.spdx_id);
        parcel.writeString(this.url);
        parcel.writeString(this.node_id);
    }

    public License(String str, String str2, String str3, String str4, String str5) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(str3, "spdx_id");
        Intrinsics.checkNotNullParameter(str4, "url");
        Intrinsics.checkNotNullParameter(str5, "node_id");
        this.key = str;
        this.name = str2;
        this.spdx_id = str3;
        this.url = str4;
        this.node_id = str5;
    }

    public final String getKey() {
        return this.key;
    }

    public final String getName() {
        return this.name;
    }


    public final String getUrl() {
        return this.url;
    }

}
