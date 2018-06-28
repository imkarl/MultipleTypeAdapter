package cn.imkarl.multipletype.viewpager;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;

import cn.imkarl.multipletype.AdapterData;
import cn.imkarl.multipletype.AdapterRegister;
import cn.imkarl.multipletype.BasicViewHelper;
import cn.imkarl.multipletype.MultipleTypeAdapter;

public class ViewPagerAdapter<T> extends PagerAdapter implements MultipleTypeAdapter {

    @NonNull
    private final AdapterData data;
    @NonNull
    private final AdapterRegister register;

    private final LinkedList<Pair<View, Integer>> recycledViews = new LinkedList<>();
    private final int maxRecycledView;
    private int fixCount = 0;

    public ViewPagerAdapter(@NonNull AdapterData data, @NonNull AdapterRegister register, int maxRecycledView) {
        this.data = data;
        this.register = register;
        this.maxRecycledView = maxRecycledView;
    }

    @Override
    public int getCount() {
        return data.getItemCount();
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final Object item = getItemData(position);
        final int viewType = register.getItemViewType(item);

        View itemView = null;
        synchronized (recycledViews) {
            for (int i = 0; i < recycledViews.size(); i++) {
                Pair<View, Integer> pair = recycledViews.get(i);
                if (pair.second == viewType) {
                    itemView = pair.first;
                    recycledViews.remove(i);
                    break;
                }
            }
        }
        if (itemView == null) {
            itemView = register.onCreateView(container, viewType);
        }
        container.addView(itemView);

        BasicViewHelper helper = (BasicViewHelper) itemView.getTag();
        if (helper == null) {
            helper = new BasicViewHelper(itemView);
            itemView.setTag(helper);
        }

        register.onBindView(helper, position, item);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View itemView = (View) object;
        container.removeView(itemView);

        final Object item = getItemData(position);
        final int viewType = register.getItemViewType(item);
        synchronized (recycledViews) {
            recycledViews.add(Pair.create(itemView, viewType));
            if (recycledViews.size() > maxRecycledView) {
                recycledViews.removeFirst();
            }
        }
    }

    @Override
    public void notifyDataSetChanged() {
        fixCount = getCount()+1;
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        // 修复动态更新item数量，不刷新的bug
        if (fixCount > 0) {
            fixCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
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
