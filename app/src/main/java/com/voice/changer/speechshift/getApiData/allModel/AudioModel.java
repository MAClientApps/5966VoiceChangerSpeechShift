package com.voice.changer.speechshift.getApiData.allModel;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import kotlin.jvm.internal.Intrinsics;

public final class AudioModel implements Parcelable {
    public static final Parcelable.Creator<AudioModel> CREATOR = new Creator();
    private long dateCreate;
    private long id;
    private String duration;
    private String fileName;
    private String path;
    private String size;
    private String type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static final class Creator implements Parcelable.Creator<AudioModel> {
        public AudioModel createFromParcel(Parcel parcel) {
            return new AudioModel(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readLong(), parcel.readString(), parcel.readString());
        }

        public AudioModel[] newArray(int i) {
            return new AudioModel[i];
        }
    }


    public final AudioModel copy(String str, String str2, String str3, long j, String str4, String str5) {
        Intrinsics.checkNotNullParameter(str, "path");
        Intrinsics.checkNotNullParameter(str2, "fileName");
        Intrinsics.checkNotNullParameter(str3, "duration");
        Intrinsics.checkNotNullParameter(str4, "size");
        String str6 = str5;
        Intrinsics.checkNotNullParameter(str6, "type");
        return new AudioModel(str, str2, str3, j, str4, str6);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AudioModel)) {
            return false;
        }
        AudioModel audioModel = (AudioModel) obj;
        return Intrinsics.areEqual((Object) this.path, (Object) audioModel.path) && Intrinsics.areEqual((Object) this.fileName, (Object) audioModel.fileName) && Intrinsics.areEqual((Object) this.duration, (Object) audioModel.duration) && this.dateCreate == audioModel.dateCreate && Intrinsics.areEqual((Object) this.size, (Object) audioModel.size) && Intrinsics.areEqual((Object) this.type, (Object) audioModel.type);
    }

    public int hashCode() {
        return (((((((((this.path.hashCode() * 31) + this.fileName.hashCode()) * 31) + this.duration.hashCode()) * 31) + ExternalSyntex.custSyntex(this.dateCreate)) * 31) + this.size.hashCode()) * 31) + this.type.hashCode();
    }

    @NonNull
    public String toString() {
        return "AudioModel(path=" + this.path + ", fileName=" + this.fileName + ", duration=" + this.duration + ", dateCreate=" + this.dateCreate + ", size=" + this.size + ", type=" + this.type + ')';
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.path);
        parcel.writeString(this.fileName);
        parcel.writeString(String.valueOf(this.duration));
        parcel.writeLong(this.dateCreate);
        parcel.writeString(this.size);
        parcel.writeString(this.type);
    }

    public AudioModel(String str, String str2, String str3, long j, String str4, String str5) {
        this.path = str;
        this.fileName = str2;
        this.duration = str3;
        this.dateCreate = j;
        this.size = str4;
        this.type = str5;
    }

    public AudioModel() {
    }

    public final String getPath() {
        return this.path;
    }

    public final void setPath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.path = str;
    }

    public final String getFileName() {
        return this.fileName;
    }

    public final void setFileName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.fileName = str;
    }

    public final String getDuration() {
        return this.duration;
    }

    public final void setDuration(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.duration = str;
    }

    public final long getDateCreate() {
        return this.dateCreate;
    }

    public final void setDateCreate(long j) {
        this.dateCreate = j;
    }

    public final String getSize() {
        return this.size;
    }

    public final void setSize(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.size = str;
    }

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.type = str;
    }
}
