package com.example.junaid.transpire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.junaid.transpire.Adapter.RecyclerViewAdapter;
import com.example.junaid.transpire.Adapter.RecyclerViewAdapter2;
import com.example.junaid.transpire.Model.Days;
import com.example.junaid.transpire.Model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class list_days extends AppCompatActivity {
    List<Days> gymList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager1;
    RecyclerView recyclerView1;
    RecyclerViewAdapter2 adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_days);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        initData();
        recyclerView1 = (RecyclerView) findViewById(R.id.list_days1);
        adapter1 = new RecyclerViewAdapter2(gymList,this);
        // layoutManager=new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setAdapter(adapter1);

    }
    private void initData() {
        gymList.add(new Days(R.drawable.chest1, "Chest workout 1 "));
        gymList.add(new Days(R.drawable.chest2, "Chest workout 2 "));
        gymList.add(new Days(R.drawable.chest3, "Chest workout 3 "));
        gymList.add(new Days(R.drawable.chest4, "Chest workout 4 "));
        gymList.add(new Days(R.drawable.chest5, "Chest workout 5 "));
        gymList.add(new Days(R.drawable.chest6, "Chest workout 6 "));
        gymList.add(new Days(R.drawable.lats1, "Lats workout 1"));
        gymList.add(new Days(R.drawable.lats2, "Lats workout 2"));
        gymList.add(new Days(R.drawable.lats3, "Lats workout 3"));
        gymList.add(new Days(R.drawable.lats4, "Lats workout 4"));
        gymList.add(new Days(R.drawable.lats5, "Lats workout 5"));
        gymList.add(new Days(R.drawable.shoulder1, "Shoulders workout 1"));
        gymList.add(new Days(R.drawable.shoulder2, "Shoulders workout 2"));
        gymList.add(new Days(R.drawable.shoulder3, "Shoulders workout 3"));
        gymList.add(new Days(R.drawable.shoulder4, "Shoulders workout 4"));
        gymList.add(new Days(R.drawable.shoulder4, "Shoulders workout 4"));
        gymList.add(new Days(R.drawable.biceps1, "Biceps workout 1 "));
        gymList.add(new Days(R.drawable.biceps2, "Biceps workout 2 "));
        gymList.add(new Days(R.drawable.biceps3, "Biceps workout 3 "));
        gymList.add(new Days(R.drawable.biceps4, "Biceps workout 4 "));
        gymList.add(new Days(R.drawable.triceps1, "Triceps workout 1 "));
        gymList.add(new Days(R.drawable.triceps2, "Triceps workout 2 "));
        gymList.add(new Days(R.drawable.triceps3, "Triceps workout 3 "));
        gymList.add(new Days(R.drawable.triceps4, "Triceps workout 4 "));
        gymList.add(new Days(R.drawable.squats1, "Squats workout 1 "));
        gymList.add(new Days(R.drawable.squats2, "Squats workout 2 "));
        gymList.add(new Days(R.drawable.squats3, "Squats workout 3 "));
        gymList.add(new Days(R.drawable.squats4, "Squats workout 4 "));
        gymList.add(new Days(R.drawable.squats5, "Squats workout 5 "));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
