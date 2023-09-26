package com.voice.changer.speechshift.getApiData.allModel;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import kotlin.jvm.internal.Intrinsics;

public final class RecordingModel implements Parcelable {
    public static final Parcelable.Creator<RecordingModel> CREATOR = new Creator();
    int fav;
    private long length;
    private String name;
    private String path;
    long timeAdded;

    public static final class Creator implements Parcelable.Creator<RecordingModel> {
        public final RecordingModel createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new RecordingModel(parcel.readString(), parcel.readString(), parcel.readLong(), parcel.readLong(), parcel.readInt());
        }

        public final RecordingModel[] newArray(int i) {
            return new RecordingModel[i];
        }
    }

    public final RecordingModel copy(String str, String str2, long j, long j2, int i) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, "path");
        return new RecordingModel(str, str2, j, j2, i);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RecordingModel)) {
            return false;
        }
        RecordingModel recordingModel = (RecordingModel) obj;
        return Intrinsics.areEqual((Object) this.name, (Object) recordingModel.name) && Intrinsics.areEqual((Object) this.path, (Object) recordingModel.path) && this.length == recordingModel.length && this.timeAdded == recordingModel.timeAdded && this.fav == recordingModel.fav;
    }

    public int hashCode() {
        return (((((((this.name.hashCode() * 31) + this.path.hashCode()) * 31) + ExternalSyntex.custSyntex(this.length)) * 31) + ExternalSyntex.custSyntex(this.timeAdded)) * 31) + this.fav;
    }

    @NonNull
    public String toString() {
        return "RecordingModel(name=" + this.name + ", path=" + this.path + ", length=" + this.length + ", timeAdded=" + this.timeAdded + ", fav=" + this.fav + ')';
    }

    public void writeToParcel(@NonNull Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.name);
        parcel.writeString(this.path);
        parcel.writeLong(this.length);
        parcel.writeLong(this.timeAdded);
        parcel.writeInt(this.fav);
    }

    public RecordingModel(String str, String str2, long j, long j2, int i) {
        this.name = str;
        this.path = str2;
        this.length = j;
        this.timeAdded = j2;
        this.fav = i;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final String getPath() {
        return this.path;
    }

    public final void setPath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.path = str;
    }

    public final long getLength() {
        return this.length;
    }

    public final void setLength(long j) {
        this.length = j;
    }

}
