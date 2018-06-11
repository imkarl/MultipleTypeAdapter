package cn.imkarl.multipletype.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import cn.imkarl.multipletype.BasicViewHelper;

class BasicViewHolder extends RecyclerView.ViewHolder {

    @NonNull
    final BasicViewHelper helper;

    BasicViewHolder(@NonNull View itemView) {
        super(itemView);
        this.helper = new BasicViewHelper(itemView);
    }

}
