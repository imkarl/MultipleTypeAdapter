package cn.imkarl.multipletype.demo.items;

import android.support.annotation.NonNull;

import cn.imkarl.multipletype.AdapterItem;
import cn.imkarl.multipletype.BasicViewHelper;
import cn.imkarl.multipletype.demo.R;

public class DoubleAdapterItem extends AdapterItem<Double> {
    @Override
    public int getLayoutResId(@NonNull Double item) {
        return R.layout.item_layout_integer;
    }
    @Override
    public void convert(@NonNull BasicViewHelper helper, @NonNull Double item, int position) {
        helper.setText(R.id.tv_integer, "item: "+item+"(double)  position: "+position);
    }
}
