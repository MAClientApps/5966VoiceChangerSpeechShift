package com.voice.changer.speechshift.allBaseAct;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.voice.changer.speechshift.custUi.constatnt.TapClick;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public abstract class BaseAdapter<DB extends ViewDataBinding, M> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<M> listData;
    protected RecyclerView recyclerView;


    public abstract RecyclerView.ViewHolder createVH(DB db);


    public abstract int getLayoutRes();

    public final List<M> getListData() {
        return this.listData;
    }

    public BaseAdapter(List<M> list) {
        Intrinsics.checkNotNullParameter(list, "listData");
        this.listData = list;
    }

    public final void setRecyclerView(RecyclerView recyclerView2) {
        Intrinsics.checkNotNullParameter(recyclerView2, "<set-?>");
        this.recyclerView = recyclerView2;
    }

    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView2) {
        Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView2);
        setRecyclerView(recyclerView2);
    }

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), getLayoutRes(), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f…layoutRes, parent, false)");
        return createVH((DB) inflate);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((BaseVH) viewHolder).bind(this.listData.get(i));
    }

    public int getItemCount() {
        return this.listData.size();
    }

    public final void addList(List<M> list) {
        this.listData.clear();
        this.listData.addAll(list);
        notifyDataSetChanged();
    }

    public abstract class BaseVH<T> extends RecyclerView.ViewHolder {
        private final DB binding;
        final BaseAdapter<DB, M> this$0;

        public void onItemClickListener(M m) {
        }

        public BaseVH(final BaseAdapter baseAdapter, DB db) {
            super(db.getRoot());
            Intrinsics.checkNotNullParameter(baseAdapter, "this$0");
            Intrinsics.checkNotNullParameter(db, "binding");
            this.this$0 = baseAdapter;
            this.binding = db;
            View root = db.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "binding.root");
            TapClick.tap(root, (Function1<View, Unit>) view -> {
                onItemClickListener((M) baseAdapter.getListData().get(getAdapterPosition()));
                return null;
            });
        }

        public final DB getBinding() {
            return this.binding;
        }

        public void bind(M m) {
            this.binding.setVariable(1, m);
            this.binding.executePendingBindings();
        }
    }
}
