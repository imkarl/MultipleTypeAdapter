package cn.imkarl.multipletype;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import cn.imkarl.multipletype.exception.UnsupportedTypeException;

public final class AdapterRegister {

    /**
     * adapterItem、layoutResId的组合  对应  一个特定的viewType类型
     */
    private static final class ViewTypeBean {
        @NonNull final AdapterItem adapterItem;
        @LayoutRes final int layoutResId;
        final int viewType;

        private ViewTypeBean(@NonNull AdapterItem adapterItem, @LayoutRes int layoutResId, int viewType) {
            this.adapterItem = adapterItem;
            this.layoutResId = layoutResId;
            this.viewType = viewType;
        }
    }


    @NonNull
    private final Map<Class, Object> typeItems = new HashMap<>();
    @NonNull
    private final List<ViewTypeBean> viewTypes = new ArrayList<>();
    @NonNull
    private final AtomicInteger viewTypeGenerator = new AtomicInteger(0);

    public <T> AdapterRegister register(@NonNull AdapterItem<T> adapterItem) {
        Object oldTypeItem = typeItems.put(adapterItem.getType(), adapterItem);
        if (oldTypeItem != null) {
            throw new IllegalStateException("Already registered type: "+adapterItem.getType().getName());
        }
        return this;
    }
    public <T> AdapterRegister register(@NonNull AdapterItemGroup<T> adapterItemGroup) {
        Object oldTypeItem = typeItems.put(adapterItemGroup.getType(), adapterItemGroup);
        if (oldTypeItem != null) {
            throw new IllegalStateException("Already registered type: "+adapterItemGroup.getType().getName());
        }
        return this;
    }

    public int getItemViewType(@NonNull Object item) {
        final AdapterItem adapterItem = findAdapterItem(item);
        //noinspection unchecked
        final int layoutResId = adapterItem.getLayoutResId(item);
        ViewTypeBean bean = searchViewTypeBean(adapterItem, layoutResId);
        if (bean == null) {
            synchronized (this) {
                bean = searchViewTypeBean(adapterItem, layoutResId);
                if (bean == null) {
                    bean = new ViewTypeBean(adapterItem, layoutResId, viewTypeGenerator.getAndIncrement());
                    viewTypes.add(bean);
                }
            }
        }
        return bean.viewType;
    }

    @NonNull
    public View onCreateView(@NonNull ViewGroup parent, int viewType) {
        final ViewTypeBean bean = findViewTypeBean(viewType);
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(bean.layoutResId, parent, false);
        bean.adapterItem.onViewCreated(itemView);
        return itemView;
    }

    public void onBindView(@NonNull BasicViewHelper helper, int position, @NonNull Object item) {
        helper.position = position;
        findAdapterItem(item).convert(helper, item, position);
    }



    @NonNull
    private <T> AdapterItem<T> findAdapterItem(@NonNull T data) {
        final Object typeItem = typeItems.get(data.getClass());
        if (typeItem == null) {
            throw new UnsupportedTypeException("Unsupported type: "+data.getClass().getName());
        }
        AdapterItem<T> adapterItem;
        if (typeItem instanceof AdapterItemGroup) {
            AdapterItemGroup adapterItemGroup = (AdapterItemGroup) typeItem;
            //noinspection unchecked
            adapterItem = adapterItemGroup.getSubItem(data);
            //noinspection ConstantConditions
            if (adapterItem == null) {
                throw new UnsupportedTypeException("Unsupported sub type: " + data.getClass().getName() + " group: " + adapterItemGroup.getClass());
            }
        } else {
            //noinspection unchecked
            adapterItem = (AdapterItem<T>) typeItem;
        }
        return adapterItem;
    }
    @NonNull
    private ViewTypeBean findViewTypeBean(int viewType) {
        ViewTypeBean bean = null;
        for (ViewTypeBean item : viewTypes) {
            if (item.viewType == viewType) {
                bean = item;
                break;
            }
        }
        if (bean == null) {
            throw new IllegalStateException("You should calling #notifyDataSetChanged()");
        }
        return bean;
    }
    @Nullable
    private ViewTypeBean searchViewTypeBean(@NonNull AdapterItem adapterItem, @LayoutRes int layoutResId) {
        ViewTypeBean bean = null;
        for (ViewTypeBean itemViewType : viewTypes) {
            if (itemViewType.adapterItem == adapterItem && itemViewType.layoutResId == layoutResId) {
                bean = itemViewType;
                break;
            }
        }
        return bean;
    }

}
