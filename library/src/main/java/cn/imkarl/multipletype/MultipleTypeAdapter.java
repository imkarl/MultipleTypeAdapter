package cn.imkarl.multipletype;

import android.support.annotation.NonNull;

public interface MultipleTypeAdapter {

    void notifyDataSetChanged();

    @NonNull
    AdapterData getAllData();

    @NonNull
    AdapterData.DataList getHeaders();
    @NonNull
    AdapterData.DataList getContents();
    @NonNull
    AdapterData.DataList getFooters();

    int getItemCount();

    @NonNull
    Object getItemData(int position);

}
