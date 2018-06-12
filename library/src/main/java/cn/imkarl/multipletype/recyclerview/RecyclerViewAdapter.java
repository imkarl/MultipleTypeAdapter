package cn.imkarl.multipletype.recyclerview;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import cn.imkarl.multipletype.AdapterData;
import cn.imkarl.multipletype.AdapterRegister;
import cn.imkarl.multipletype.MultipleTypeAdapter;

public final class RecyclerViewAdapter extends RecyclerView.Adapter<BasicViewHolder> implements MultipleTypeAdapter {

    @NonNull
    private final AdapterData data;
    @NonNull
    private final AdapterRegister register;

    public RecyclerViewAdapter(@NonNull AdapterData data, @NonNull AdapterRegister register) {
        this.data = data;
        this.register = register;
    }

    @Override
    public int getItemViewType(int position) {
        final Object item = getItemData(position);
        return register.getItemViewType(item);
    }

    @NonNull
    @Override
    public BasicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = register.onCreateView(parent, viewType);
        return new BasicViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BasicViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Object item = getItemData(position);
        register.onBindView(holder.helper, position, item);
    }


    @NonNull
    @Override
    public AdapterData getAllData() {
        return data;
    }

    @NonNull
    @Override
    public AdapterData.DataList getHeaders() {
        return data.getHeaders();
    }

    @NonNull
    @Override
    public AdapterData.DataList getContents() {
        return data.getContents();
    }

    @NonNull
    @Override
    public AdapterData.DataList getFooters() {
        return data.getFooters();
    }

    @Override
    public int getItemCount() {
        return data.getItemCount();
    }

    @Override
    @NonNull
    public Object getItemData(int position) {
        return data.getItemData(position);
    }

}
