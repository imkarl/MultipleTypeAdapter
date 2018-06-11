package cn.imkarl.multipletype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import cn.imkarl.multipletype.recyclerview.RecyclerAdapter;

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


    @NonNull
    public RecyclerAdapter bind(@NonNull RecyclerView recyclerView) {
        RecyclerAdapter adapter = new RecyclerAdapter(data, register);
        recyclerView.setAdapter(adapter);
        return adapter;
    }

}
