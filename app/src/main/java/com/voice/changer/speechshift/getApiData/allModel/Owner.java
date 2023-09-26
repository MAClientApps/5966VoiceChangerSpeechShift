package com.voice.changer.speechshift.getApiData.allModel;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

public final class Owner implements Parcelable {
    public static final Parcelable.Creator<Owner> CREATOR = new Creator();
    private final String avatar_url;
    private final String events_url;
    private final String followers_url;
    private final String following_url;
    private final String gists_url;
    private final String gravatar_id;
    private final String html_url;

    private final int f16583id;
    private final String login;
    private final String node_id;
    private final String organizations_url;
    private final String received_events_url;
    private final String repos_url;
    private final boolean site_admin;
    private final String starred_url;
    private final String subscriptions_url;
    private final String type;
    private final String url;

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)

    public static final class Creator implements Parcelable.Creator<Owner> {
        public final Owner createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new Owner(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0);
        }

        public final Owner[] newArray(int i) {
            return new Owner[i];
        }
    }

    public static  Owner copy$default(Owner owner, String str, int i, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, boolean z, int i2, Object obj) {
        Owner owner2 = owner;
        int i3 = i2;
        return owner.copy((i3 & 1) != 0 ? owner2.login : str, (i3 & 2) != 0 ? owner2.f16583id : i, (i3 & 4) != 0 ? owner2.node_id : str2, (i3 & 8) != 0 ? owner2.avatar_url : str3, (i3 & 16) != 0 ? owner2.gravatar_id : str4, (i3 & 32) != 0 ? owner2.url : str5, (i3 & 64) != 0 ? owner2.html_url : str6, (i3 & 128) != 0 ? owner2.followers_url : str7, (i3 & 256) != 0 ? owner2.following_url : str8, (i3 & 512) != 0 ? owner2.gists_url : str9, (i3 & 1024) != 0 ? owner2.starred_url : str10, (i3 & 2048) != 0 ? owner2.subscriptions_url : str11, (i3 & 4096) != 0 ? owner2.organizations_url : str12, (i3 & 8192) != 0 ? owner2.repos_url : str13, (i3 & 16384) != 0 ? owner2.events_url : str14, (i3 & 32768) != 0 ? owner2.received_events_url : str15, (i3 & 65536) != 0 ? owner2.type : str16, (i3 & 131072) != 0 ? owner2.site_admin : z);
    }


    public final Owner copy(String str, int i, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, boolean z) {
        String str17 = str;
        Intrinsics.checkNotNullParameter(str17, "login");
        Intrinsics.checkNotNullParameter(str2, "node_id");
        Intrinsics.checkNotNullParameter(str3, "avatar_url");
        Intrinsics.checkNotNullParameter(str4, "gravatar_id");
        Intrinsics.checkNotNullParameter(str5, "url");
        Intrinsics.checkNotNullParameter(str6, "html_url");
        Intrinsics.checkNotNullParameter(str7, "followers_url");
        Intrinsics.checkNotNullParameter(str8, "following_url");
        Intrinsics.checkNotNullParameter(str9, "gists_url");
        Intrinsics.checkNotNullParameter(str10, "starred_url");
        Intrinsics.checkNotNullParameter(str11, "subscriptions_url");
        Intrinsics.checkNotNullParameter(str12, "organizations_url");
        Intrinsics.checkNotNullParameter(str13, "repos_url");
        Intrinsics.checkNotNullParameter(str14, "events_url");
        Intrinsics.checkNotNullParameter(str15, "received_events_url");
        Intrinsics.checkNotNullParameter(str16, "type");
        return new Owner(str17, i, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14, str15, str16, z);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Owner)) {
            return false;
        }
        Owner owner = (Owner) obj;
        return Intrinsics.areEqual((Object) this.login, (Object) owner.login) && this.f16583id == owner.f16583id && Intrinsics.areEqual((Object) this.node_id, (Object) owner.node_id) && Intrinsics.areEqual((Object) this.avatar_url, (Object) owner.avatar_url) && Intrinsics.areEqual((Object) this.gravatar_id, (Object) owner.gravatar_id) && Intrinsics.areEqual((Object) this.url, (Object) owner.url) && Intrinsics.areEqual((Object) this.html_url, (Object) owner.html_url) && Intrinsics.areEqual((Object) this.followers_url, (Object) owner.followers_url) && Intrinsics.areEqual((Object) this.following_url, (Object) owner.following_url) && Intrinsics.areEqual((Object) this.gists_url, (Object) owner.gists_url) && Intrinsics.areEqual((Object) this.starred_url, (Object) owner.starred_url) && Intrinsics.areEqual((Object) this.subscriptions_url, (Object) owner.subscriptions_url) && Intrinsics.areEqual((Object) this.organizations_url, (Object) owner.organizations_url) && Intrinsics.areEqual((Object) this.repos_url, (Object) owner.repos_url) && Intrinsics.areEqual((Object) this.events_url, (Object) owner.events_url) && Intrinsics.areEqual((Object) this.received_events_url, (Object) owner.received_events_url) && Intrinsics.areEqual((Object) this.type, (Object) owner.type) && this.site_admin == owner.site_admin;
    }

    public int hashCode() {
        int hashCode = ((((((((((((((((((((((((((((((((this.login.hashCode() * 31) + this.f16583id) * 31) + this.node_id.hashCode()) * 31) + this.avatar_url.hashCode()) * 31) + this.gravatar_id.hashCode()) * 31) + this.url.hashCode()) * 31) + this.html_url.hashCode()) * 31) + this.followers_url.hashCode()) * 31) + this.following_url.hashCode()) * 31) + this.gists_url.hashCode()) * 31) + this.starred_url.hashCode()) * 31) + this.subscriptions_url.hashCode()) * 31) + this.organizations_url.hashCode()) * 31) + this.repos_url.hashCode()) * 31) + this.events_url.hashCode()) * 31) + this.received_events_url.hashCode()) * 31) + this.type.hashCode()) * 31;
        boolean z = this.site_admin;
        if (z) {
            z = true;
        }
        return hashCode + (z ? 1 : 0);
    }

    @NonNull
    public String toString() {
        return "Owner(login=" + this.login + ", id=" + this.f16583id + ", node_id=" + this.node_id + ", avatar_url=" + this.avatar_url + ", gravatar_id=" + this.gravatar_id + ", url=" + this.url + ", html_url=" + this.html_url + ", followers_url=" + this.followers_url + ", following_url=" + this.following_url + ", gists_url=" + this.gists_url + ", starred_url=" + this.starred_url + ", subscriptions_url=" + this.subscriptions_url + ", organizations_url=" + this.organizations_url + ", repos_url=" + this.repos_url + ", events_url=" + this.events_url + ", received_events_url=" + this.received_events_url + ", type=" + this.type + ", site_admin=" + this.site_admin + ')';
    }

    public void writeToParcel(@NonNull Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.login);
        parcel.writeInt(this.f16583id);
        parcel.writeString(this.node_id);
        parcel.writeString(this.avatar_url);
        parcel.writeString(this.gravatar_id);
        parcel.writeString(this.url);
        parcel.writeString(this.html_url);
        parcel.writeString(this.followers_url);
        parcel.writeString(this.following_url);
        parcel.writeString(this.gists_url);
        parcel.writeString(this.starred_url);
        parcel.writeString(this.subscriptions_url);
        parcel.writeString(this.organizations_url);
        parcel.writeString(this.repos_url);
        parcel.writeString(this.events_url);
        parcel.writeString(this.received_events_url);
        parcel.writeString(this.type);
        parcel.writeInt(this.site_admin ? 1 : 0);
    }

    public Owner(String str, int i, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, boolean z) {
        String str17 = str;
        String str18 = str2;
        String str19 = str3;
        String str20 = str4;
        String str21 = str5;
        String str22 = str6;
        String str23 = str7;
        String str24 = str8;
        String str25 = str9;
        String str26 = str10;
        String str27 = str11;
        String str28 = str12;
        String str29 = str13;
        String str30 = str14;
        String str31 = str16;
        String str32 = str16;
        this.login = str17;
        this.f16583id = i;
        this.node_id = str18;
        this.avatar_url = str19;
        this.gravatar_id = str20;
        this.url = str21;
        this.html_url = str22;
        this.followers_url = str23;
        this.following_url = str24;
        this.gists_url = str25;
        this.starred_url = str26;
        this.subscriptions_url = str27;
        this.organizations_url = str28;
        this.repos_url = str29;
        this.events_url = str30;
        this.received_events_url = str15;
        this.type = str32;
        this.site_admin = z;
    }

    public final int getId() {
        return this.f16583id;
    }


    public final String getUrl() {
        return this.url;
    }

    public final String getType() {
        return this.type;
    }

}
