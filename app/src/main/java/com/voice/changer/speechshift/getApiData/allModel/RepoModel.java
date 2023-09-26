package com.voice.changer.speechshift.getApiData.allModel;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import kotlin.jvm.internal.Intrinsics;

public final class RepoModel implements Parcelable {
    public static final Parcelable.Creator<RepoModel> CREATOR = new Creator();
    private final String full_name;

    private final int id;
    private final String name;
    private final String node_id;
    private final Owner owner;
    private int position;

    public static final class Creator implements Parcelable.Creator<RepoModel> {
        public final RepoModel createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new RepoModel(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), Owner.CREATOR.createFromParcel(parcel), parcel.readInt());
        }

        public final RepoModel[] newArray(int i) {
            return new RepoModel[i];
        }
    }

    public final RepoModel copy(int i, String str, String str2, String str3, Owner owner2, int i2) {
        Intrinsics.checkNotNullParameter(str, "node_id");
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(str3, "full_name");
        Intrinsics.checkNotNullParameter(owner2, "owner");
        return new RepoModel(i, str, str2, str3, owner2, i2);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RepoModel)) {
            return false;
        }
        RepoModel repoModel = (RepoModel) obj;
        return this.id == repoModel.id && Intrinsics.areEqual((Object) this.node_id, (Object) repoModel.node_id) && Intrinsics.areEqual((Object) this.name, (Object) repoModel.name) && Intrinsics.areEqual((Object) this.full_name, (Object) repoModel.full_name) && Intrinsics.areEqual((Object) this.owner, (Object) repoModel.owner) && this.position == repoModel.position;
    }

    public int hashCode() {
        return (((((((((this.id * 31) + this.node_id.hashCode()) * 31) + this.name.hashCode()) * 31) + this.full_name.hashCode()) * 31) + this.owner.hashCode()) * 31) + this.position;
    }

    @NonNull
    public String toString() {
        return "RepoModel(id=" + this.id + ", node_id=" + this.node_id + ", name=" + this.name + ", full_name=" + this.full_name + ", owner=" + this.owner + ", position=" + this.position + ')';
    }

    public void writeToParcel(@NonNull Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeInt(this.id);
        parcel.writeString(this.node_id);
        parcel.writeString(this.name);
        parcel.writeString(this.full_name);
        this.owner.writeToParcel(parcel, i);
        parcel.writeInt(this.position);
    }

    public RepoModel(int i, String str, String str2, String str3, Owner owner2, int i2) {
        Intrinsics.checkNotNullParameter(str, "node_id");
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(str3, "full_name");
        Intrinsics.checkNotNullParameter(owner2, "owner");
        this.id = i;
        this.node_id = str;
        this.name = str2;
        this.full_name = str3;
        this.owner = owner2;
        this.position = i2;
    }

    public final int getId() {
        return this.id;
    }

    public final String getNode_id() {
        return this.node_id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getFull_name() {
        return this.full_name;
    }

    public final Owner getOwner() {
        return this.owner;
    }

    public final int getPosition() {
        return this.position;
    }

    public final void setPosition(int i) {
        this.position = i;
    }
}
