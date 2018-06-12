package cn.imkarl.multipletype.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jumpRecyclerView(View view) {
        jump(RecyclerViewActivity.class);
    }
    public void jumpListView(View view) {
        jump(ListViewActivity.class);
    }

    private void jump(Class<? extends Activity> activityClass) {
        startActivity(new Intent(this, activityClass));
    }

}
