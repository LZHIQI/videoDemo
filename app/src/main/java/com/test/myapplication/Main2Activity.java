package com.test.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

public class Main2Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    SurfaceView surfaceView;
    LinearLayoutManager linearLayoutManager;
    private int startPosition = 0;
    MyPlayerUtils myPlayerUtils;
    String[] videoUrls = {
            "http://data.yibenjiankang.com/userunmodifieddata/201910/pFvlviHO3E9kLUyvVIFgKZ66HArMrIq10Dg8ZMNMn5iRkuIGwzS3ToDA9D6sAkuz.mp4",
            "http://data.yibenjiankang.com/userdata/cad9146e728a48f7882aa16746a20040/videos/20190719/1563504188635.mp4",
            "http://data.yibenjiankang.com/userdata/cad9146e728a48f7882aa16746a20040/videos/20190710/1562741831990.mp4",
            "http://data.yibenjiankang.com/userdata/c6852f7fb5aa4fc1b880d956d847ed6a/videos/20190710/1562741730049.mp4",
            "http://data.yibenjiankang.com/userdata/cad9146e728a48f7882aa16746a20040/videos/20190710/1562740684969.mp4",
            "http://data.yibenjiankang.com/userunmodifieddata/201810/180UUunaxNWgheopSvsWZ4awFq0T9aKbWgz67qx8kJA48vrXp6abZ7sXIVUoR9zb.MP4",
            "http://data.yibenjiankang.com/userunmodifieddata/201810/7oBkXsbpVNUCZMzPRvjSPbJ3r6oJyC6XhZuqIWcQveq544IDokP1oEhPmKFomUh6.MP4",
            "http://data.yibenjiankang.com/userunmodifieddata/201806/KLsuOmxWznJAKFf0iGWLHTF8uLO4NNAQIyNg84SQIbaXx0FYfKa7REIPbYQhsfpb.mp4"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView = findViewById(R.id.recycle);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recyclerView);
        surfaceView = findViewById(R.id.tv_bg);
        myPlayerUtils=new MyPlayerUtils(surfaceView);
        recyclerView.setAdapter(new RecyclerView.Adapter<MyViewHolder>() {
            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new MyViewHolder(LayoutInflater.from(getThis()).inflate(R.layout.item_layout, viewGroup, false));
            }

            @Override
            public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
                viewHolder.tt.setText(i + "" + videoUrls[i]);
            }

            @Override
            public int getItemCount() {
                return videoUrls.length;
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
             //   Log.e("onScrolled", "dx   " + dx);
                View view = recyclerView.getChildAt(0);
                int firstItem = recyclerView.getChildLayoutPosition(view);
             //   Log.e("滑动到指定位置", "         view    " + firstItem + "               " + view.getX());

                View view2 = recyclerView.getChildAt(recyclerView.getChildCount() - 1);
                int lastItem = recyclerView.getChildLayoutPosition(view2);
              //  Log.e("滑动到指定位置", "        view2    " + lastItem + "                " + view2.getPivotX());
                boolean start = (firstItem == lastItem);
                transfer(dx, start, firstItem, lastItem, surfaceView);
            }
        });


    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tt = itemView.findViewById(R.id.tv_tt);
        }
    }

    public Activity getThis() {
        return this;
    }

    public void transfer(int transfer, boolean start, int firstItem,final int lastItem, final View view) {
        startPosition = startPosition - transfer;
        if (start) {
            startPosition = 0;
            surfaceView.post(new Runnable() {
                @Override
                public void run() {
                    myPlayerUtils.play( videoUrls[lastItem]);
                }
            });
        }
        view.layout(startPosition, view.getTop(), startPosition + view.getWidth(), view.getBottom());

    }

    @Override
    protected void onStart() {
        super.onStart();

    }



    @Override
    protected void onPause() {
        super.onPause();
        myPlayerUtils.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        myPlayerUtils.onResume();
    }
}
