package cn.imkarl.multipletype.demo.items;

import android.support.annotation.NonNull;

import cn.imkarl.multipletype.AdapterItem;
import cn.imkarl.multipletype.BasicViewHelper;
import cn.imkarl.multipletype.demo.R;

public class FloatAdapterItem extends AdapterItem<Float> {
    @Override
    public int getLayoutResId(@NonNull Float item) {
        return R.layout.item_layout_integer;
    }
    @Override
    public void convert(@NonNull BasicViewHelper helper, @NonNull Float item, int position) {
        helper.setText(R.id.tv_integer, "item: "+item+"(float)  position: "+position);
    }
}
