package cn.imkarl.multipletype.demo.items;

import android.support.annotation.NonNull;

import cn.imkarl.multipletype.AdapterItem;
import cn.imkarl.multipletype.BasicViewHelper;
import cn.imkarl.multipletype.demo.R;

public class IntegerAdapterItem extends AdapterItem<Integer> {
    @Override
    public int getLayoutResId(@NonNull Integer item) {
        return R.layout.item_layout_integer;
    }
    @Override
    public void convert(@NonNull BasicViewHelper helper, @NonNull Integer item, int position) {
        helper.setText(R.id.tv_integer, "item: "+item+"  position: "+position);
    }
}
