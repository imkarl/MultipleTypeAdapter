package cn.imkarl.multipletype;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;

import cn.imkarl.multipletype.utils.MultipleTypeUtils;

public abstract class AdapterItem<T> {

    @NonNull
    private final Class<T> typeClass;

    public AdapterItem() {
        this.typeClass = MultipleTypeUtils.findActualTypeArgumentsBySuperclass(getClass());
    }

    @NonNull
    public final Class<T> getType() {
        return typeClass;
    }

    @LayoutRes
    public abstract int getLayoutResId(@NonNull T item);

    public void onViewCreated(@NonNull View itemView) {
    }

    public abstract void convert(@NonNull BasicViewHelper helper, @NonNull T item, int position);

}
