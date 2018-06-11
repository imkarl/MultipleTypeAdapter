package cn.imkarl.multipletype;

import android.support.annotation.NonNull;
import android.util.SparseArray;

import cn.imkarl.multipletype.utils.MultipleTypeUtils;

public abstract class AdapterItemGroup<T> {

    @NonNull
    private final SparseArray<AdapterItem<T>> subTypeItems = new SparseArray<>();
    @NonNull
    private final Class<T> typeClass;

    public AdapterItemGroup() {
        this.typeClass = MultipleTypeUtils.findActualTypeArgumentsBySuperclass(getClass());
    }

    @NonNull
    public final Class<T> getType() {
        return typeClass;
    }

    @NonNull
    public final AdapterItem<T> getSubItem(@NonNull T item) {
        int subType = getSubType(item);
        AdapterItem<T> subItem = subTypeItems.get(subType);
        if (subItem == null) {
            synchronized (this) {
                subItem = subTypeItems.get(subType);
                if (subItem == null) {
                    subItem = onCreateSubItem(subType);
                    subTypeItems.put(subType, subItem);
                }
            }
        }
        return subItem;
    }

    protected abstract int getSubType(@NonNull T item);

    @NonNull
    protected abstract AdapterItem<T> onCreateSubItem(int subType);

}
