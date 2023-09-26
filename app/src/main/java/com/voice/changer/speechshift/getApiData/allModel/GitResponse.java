package com.voice.changer.speechshift.getApiData.allModel;

import androidx.annotation.NonNull;

import java.util.List;

import kotlin.jvm.internal.Intrinsics;

public final class GitResponse {
    private final boolean incomplete_results;
    private final List<RepoModel> items;
    private final int total_count;

    public final GitResponse copy(int i, boolean z, List<RepoModel> list) {
        Intrinsics.checkNotNullParameter(list, "items");
        return new GitResponse(i, z, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GitResponse)) {
            return false;
        }
        GitResponse gitResponse = (GitResponse) obj;
        return this.total_count == gitResponse.total_count && this.incomplete_results == gitResponse.incomplete_results && Intrinsics.areEqual((Object) this.items, (Object) gitResponse.items);
    }

    public int hashCode() {
        int i = this.total_count * 31;
        boolean z = this.incomplete_results;
        if (z) {
            z = true;
        }
        return ((i + (z ? 1 : 0)) * 31) + this.items.hashCode();
    }

    @NonNull
    public String toString() {
        return "GitResponse(total_count=" + this.total_count + ", incomplete_results=" + this.incomplete_results + ", items=" + this.items + ')';
    }

    public GitResponse(int i, boolean z, List<RepoModel> list) {
        Intrinsics.checkNotNullParameter(list, "items");
        this.total_count = i;
        this.incomplete_results = z;
        this.items = list;
    }

}
