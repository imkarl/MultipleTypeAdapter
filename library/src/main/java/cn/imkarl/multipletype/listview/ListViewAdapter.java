package cn.imkarl.multipletype.listview;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import cn.imkarl.multipletype.AdapterData;
import cn.imkarl.multipletype.AdapterRegister;
import cn.imkarl.multipletype.BasicViewHelper;
import cn.imkarl.multipletype.MultipleTypeAdapter;

public class ListViewAdapter extends BaseAdapter implements MultipleTypeAdapter {

    public interface OnViewTypeCountChangedListener {
        void onViewTypeCountChanged(int curViewTypeCount);
    }

    @NonNull
    private final AdapterData data;
    @NonNull
    private final AdapterRegister register;

    private int viewTypeCount;
    private OnViewTypeCountChangedListener onViewTypeCountChangedListener;

    public ListViewAdapter(@NonNull AdapterData data, @NonNull AdapterRegister register) {
        this.data = data;
        this.register = register;
    }

    public void setViewTypeCount(int viewTypeCount) {
        this.viewTypeCount = viewTypeCount;
    }

    public void setOnViewTypeCountChangedListener(OnViewTypeCountChangedListener onViewTypeCountChangedListener) {
        this.onViewTypeCountChangedListener = onViewTypeCountChangedListener;
    }

    @Override
    public int getItemViewType(int position) {
        final Object item = getItemData(position);
        final int viewType = register.getItemViewType(item);
        if (viewType >= viewTypeCount) {
            if (onViewTypeCountChangedListener != null) {
                onViewTypeCountChangedListener.onViewTypeCountChanged(viewTypeCount);
            }
        }
        return viewType;
    }

    @Override
    public int getViewTypeCount() {
        return Math.max(viewTypeCount, 1);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Object item = getItemData(position);
        final int viewType = register.getItemViewType(item);
        if (convertView == null) {
            convertView = register.onCreateView(parent, viewType);
        }

        BasicViewHelper helper = (BasicViewHelper) convertView.getTag();
        if (helper == null) {
            helper = new BasicViewHelper(convertView);
            convertView.setTag(helper);
        }
        register.onBindView(helper, position, item);
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return getItemData(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return data.getItemCount();
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
