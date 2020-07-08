package com.example.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.homework2.recycler.MyAdapter;
import com.example.homework2.recycler.MyAdapter_horizon;
import com.example.homework2.recycler.TestData;
import com.example.homework2.recycler.TestDataSet;
import com.example.homework2.recycler.TestDataSet_horizon;

public class MainActivity extends AppCompatActivity implements MyAdapter.IOnItemClickListener{
    private static final String TAG = "TAG";

    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private MyAdapter_horizon mAdapter;
    private MyAdapter mAdapter2;
    private RecyclerView.LayoutManager layoutManager;
    private GridLayoutManager gridLayoutManager;
    private Intent intent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        Log.i(TAG, "RecyclerViewActivity onCreate");
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler_horizon);
        recyclerView2=findViewById(R.id.recycler_vertical);

        recyclerView.setHasFixedSize(true);
        recyclerView2.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);

        mAdapter = new MyAdapter_horizon(TestDataSet_horizon.getData());
        mAdapter.setOnItemClickListener(this);

        mAdapter2 = new MyAdapter(TestDataSet.getData());
        mAdapter2.setOnItemClickListener(this);

        recyclerView.setAdapter(mAdapter);
        recyclerView2.setAdapter(mAdapter2);


        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView2.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "RecyclerViewActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "RecyclerViewActivity onResume");
    }


    @Override
    public void onItemCLick(int position, TestData data) {
        Toast.makeText(MainActivity.this, "点击了第" + (position+1) + "条", Toast.LENGTH_SHORT).show();
        int a=position+1;
        intent = new Intent(this, NewpageActivity.class);
        intent.putExtra("position",a+"");
        //intent.putExtra("position",position);
        startActivity(intent);
    }

    @Override
    public void onItemLongCLick(int position, TestData data) {
        Toast.makeText(MainActivity.this, "长按了第" + (position+1) + "条", Toast.LENGTH_SHORT).show();
        mAdapter.removeData(position);
    }

}