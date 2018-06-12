package cn.imkarl.multipletype.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;
import android.widget.ListView;

import cn.imkarl.multipletype.AdapterItem;
import cn.imkarl.multipletype.BasicViewHelper;
import cn.imkarl.multipletype.MultipleTypeAdapter;
import cn.imkarl.multipletype.MultipleTypeAdapterBuilder;
import cn.imkarl.multipletype.demo.items.DoubleAdapterItem;
import cn.imkarl.multipletype.demo.items.FloatAdapterItem;
import cn.imkarl.multipletype.demo.items.IntegerAdapterItem;
import cn.imkarl.multipletype.demo.items.ShortAdapterItem;

public class ListViewActivity extends AppCompatActivity {

    private ListView listView;
    private MultipleTypeAdapter multipleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        listView = findViewById(R.id.listView);

        multipleAdapter = new MultipleTypeAdapterBuilder()
                .register(new ShortAdapterItem())
                .register(new IntegerAdapterItem())
                .register(new FloatAdapterItem())
                .register(new DoubleAdapterItem())
                .register(new AdapterItem<Long>() {
                    @Override
                    public int getLayoutResId(@NonNull Long item) {
                        return R.layout.item_layout_long;
                    }
                    @Override
                    public void convert(@NonNull BasicViewHelper helper, @NonNull Long item, int position) {
                        helper.setText(R.id.tv_long, "item: "+item+"  position: "+position);
                    }
                })
                .bind(listView, 1);
    }

    @Override
    protected void onResume() {
        super.onResume();

        for (int i = 10; i < 20; i++) {
            multipleAdapter.getContents().add(i);
        }
        for (int i = 30; i < 40; i++) {
            multipleAdapter.getContents().add((long)i);
        }
        for (int i = 40; i < 50; i++) {
            multipleAdapter.getContents().add((short)i);
        }
        for (int i = 50; i < 60; i++) {
            multipleAdapter.getContents().add((double)i);
        }
        for (int i = 60; i < 70; i++) {
            multipleAdapter.getContents().add((float)i);
        }
        multipleAdapter.notifyDataSetChanged();
    }

}
