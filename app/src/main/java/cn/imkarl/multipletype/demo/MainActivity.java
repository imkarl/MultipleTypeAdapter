package cn.imkarl.multipletype.demo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import cn.imkarl.multipletype.AdapterItem;
import cn.imkarl.multipletype.BasicViewHelper;
import cn.imkarl.multipletype.MultipleTypeAdapter;
import cn.imkarl.multipletype.MultipleTypeAdapterBuilder;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MultipleTypeAdapter multipleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        multipleAdapter = new MultipleTypeAdapterBuilder()
                .register(new IntegerAdapterItem())
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
                .bind(recyclerView);

        for (int i = 10; i < 20; i++) {
            multipleAdapter.getContents().add(i);
        }
        for (int i = 30; i < 40; i++) {
            multipleAdapter.getContents().add((long)i);
        }
        multipleAdapter.notifyDataSetChanged();
    }

}
