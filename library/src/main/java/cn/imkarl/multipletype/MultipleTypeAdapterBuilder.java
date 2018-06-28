package cn.imkarl.multipletype;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import cn.imkarl.multipletype.listview.ListViewAdapter;
import cn.imkarl.multipletype.recyclerview.RecyclerViewAdapter;
import cn.imkarl.multipletype.utils.MultipleTypeLogs;
import cn.imkarl.multipletype.viewpager.ViewPagerAdapter;

/**
 * @author imkarl
 */
public final class MultipleTypeAdapterBuilder {

    @NonNull
    private final AdapterData data;
    @NonNull
    private final AdapterRegister register;

    public MultipleTypeAdapterBuilder() {
        this(new AdapterData(), new AdapterRegister());
    }
    public MultipleTypeAdapterBuilder(@NonNull AdapterData data, @NonNull AdapterRegister register) {
        this.data = data;
        this.register = register;
    }


    public <T> MultipleTypeAdapterBuilder register(@NonNull AdapterItem<T> adapterItem) {
        register.register(adapterItem);
        return this;
    }

    public <T> MultipleTypeAdapterBuilder register(@NonNull AdapterItemGroup<T> adapterItemGroup) {
        register.register(adapterItemGroup);
        return this;
    }

    public <T> MultipleTypeAdapterBuilder unregister(@NonNull AdapterItem<T> adapterItem) {
        register.unregister(adapterItem);
        return this;
    }

    public <T> MultipleTypeAdapterBuilder unregister(@NonNull AdapterItemGroup<T> adapterItemGroup) {
        register.unregister(adapterItemGroup);
        return this;
    }


    @NonNull
    public RecyclerViewAdapter bind(@NonNull RecyclerView recyclerView) {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(data, register);
        recyclerView.setAdapter(adapter);
        return adapter;
    }

    @NonNull
    public ListViewAdapter bind(@NonNull ListView listView) {
        ListViewAdapter adapter = new ListViewAdapter(data, register);
        adapter.setViewTypeCount(100);
        listView.setAdapter(adapter);
        return adapter;
    }
    @NonNull
    public ListViewAdapter bind(@NonNull final ListView listView, int viewTypeCount) {
        final ListViewAdapter adapter = new ListViewAdapter(data, register);
        adapter.setViewTypeCount(viewTypeCount);
        adapter.setOnViewTypeCountChangedListener(new ListViewAdapter.OnViewTypeCountChangedListener() {
            @Override
            public void onViewTypeCountChanged(int curViewTypeCount) {
                MultipleTypeLogs.w("自动增长viewTypeCount："+curViewTypeCount+" -> "+(curViewTypeCount + 20));
                adapter.setViewTypeCount(curViewTypeCount + 20);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
        listView.setAdapter(adapter);
        return adapter;
    }

    @NonNull
    public ViewPagerAdapter bind(@NonNull ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(data, register, 10);
        viewPager.setAdapter(adapter);
        return adapter;
    }
    @NonNull
    public ViewPagerAdapter bind(@NonNull ViewPager viewPager, int maxRecycledView) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(data, register, maxRecycledView);
        viewPager.setAdapter(adapter);
        return adapter;
    }

}
