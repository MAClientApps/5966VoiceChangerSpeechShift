package com.voice.changer.speechshift.getApiData.allModel;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import kotlin.jvm.internal.Intrinsics;

public final class TypeEffectModel implements Parcelable {
    public static final Parcelable.Creator<TypeEffectModel> CREATOR = new Creator();
    private boolean isActive;
    private String type;

    public static final class Creator implements Parcelable.Creator<TypeEffectModel> {
        public TypeEffectModel createFromParcel(Parcel parcel) {
            return new TypeEffectModel(parcel.readString(), parcel.readInt() != 0);
        }

        public TypeEffectModel[] newArray(int i) {
            return new TypeEffectModel[i];
        }
    }

    public TypeEffectModel copy(String str, boolean z) {
        return new TypeEffectModel(str, z);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TypeEffectModel)) {
            return false;
        }
        TypeEffectModel typeEffectModel = (TypeEffectModel) obj;
        return Intrinsics.areEqual((Object) this.type, (Object) typeEffectModel.type) && this.isActive == typeEffectModel.isActive;
    }

    public int hashCode() {
        int hashCode = this.type.hashCode() * 31;
        boolean z = this.isActive;
        return hashCode + (z ? 1 : 0);
    }

    @NonNull
    public String toString() {
        return "TypeEffectModel(type=" + this.type + ", isActive=" + this.isActive + ')';
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.type);
        parcel.writeInt(this.isActive ? 1 : 0);
    }

    public TypeEffectModel(String str, boolean z) {
        this.type = str;
        this.isActive = z;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean z) {
        this.isActive = z;
    }
}
