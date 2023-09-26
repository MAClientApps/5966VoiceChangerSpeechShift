package com.voice.changer.speechshift.getApiData.allModel;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class EffectModel implements Parcelable {
    public static final Parcelable.Creator<EffectModel> CREATOR = new Creator();
    private int iconSelected;
    private int iconUnSelected;
    private int id;
    private boolean isActive;
    private String name;
    private String nameOrigin;
    private int thumb;

    public static final class Creator implements Parcelable.Creator<EffectModel> {
        public final EffectModel createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new EffectModel(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0);
        }

        public final EffectModel[] newArray(int i) {
            return new EffectModel[i];
        }
    }


    public final EffectModel copy(int i, String str, String str2, int i2, int i4, int i3, boolean z) {
        Intrinsics.checkNotNullParameter(str, "name");
        return new EffectModel(i, str, str2, i2, i4, i3, z);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EffectModel)) {
            return false;
        }
        EffectModel effectModel = (EffectModel) obj;
        return this.id == effectModel.id && Intrinsics.areEqual((Object) this.name, (Object) effectModel.name) && Intrinsics.areEqual((Object) this.nameOrigin, (Object) effectModel.nameOrigin) && this.iconSelected == effectModel.iconSelected && this.thumb == effectModel.thumb && this.isActive == effectModel.isActive;
    }

    public int hashCode() {
        int hashCode = ((this.id * 31) + this.name.hashCode()) * 31;
        String str = this.nameOrigin;
        int hashCode2 = (((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.iconSelected) * 31) + this.thumb) * 31;
        boolean z = this.isActive;
        if (z) {
            z = true;
        }
        return hashCode2 + (z ? 1 : 0);
    }

    @NonNull
    public String toString() {
        return "EffectModel(id=" + this.id + ", name=" + this.name + ", nameOrigin=" + this.nameOrigin + ", icon=" + this.iconSelected + ", thumb=" + this.thumb + ", isActive=" + this.isActive + ')';
    }

    public void writeToParcel(@NonNull Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.nameOrigin);
        parcel.writeInt(this.iconSelected);
        parcel.writeInt(this.thumb);
        parcel.writeInt(this.isActive ? 1 : 0);
    }

    public EffectModel(int i, String str, String str2, int i2, int i4, int i3, boolean z) {
        this.id = i;
        this.name = str;
        this.nameOrigin = str2;
        this.iconSelected = i2;
        this.iconUnSelected = i4;
        this.thumb = i3;
        this.isActive = z;
    }

    public EffectModel(int i, String str, String str2, int i2,int i5, int i3, boolean z, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, (i4 & 4) != 0 ? null : str2, i2, i5, i3, (i4 & 32) != 0 ? false : z);
    }

    public final int getId() {
        return this.id;
    }

    public final void setId(int i) {
        this.id = i;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final String getNameOrigin() {
        return this.nameOrigin;
    }

    public final void setNameOrigin(String str) {
        this.nameOrigin = str;
    }

    public final int getIconSelected() {
        return this.iconSelected;
    }

    public int getIconUnSelected() {
        return iconUnSelected;
    }

    public void setIconUnSelected(int iconUnSelected) {
        this.iconUnSelected = iconUnSelected;
    }

    public final void setIconSelected(int i) {
        this.iconSelected = i;
    }

    public final int getThumb() {
        return this.thumb;
    }

    public final void setThumb(int i) {
        this.thumb = i;
    }

    public final boolean isActive() {
        return this.isActive;
    }

    public final void setActive(boolean z) {
        this.isActive = z;
    }

    public EffectModel(String str) {
        this(0, str, (String) null, 0, 0, 0, false);
        Intrinsics.checkNotNullParameter(str, "name");
    }
}
