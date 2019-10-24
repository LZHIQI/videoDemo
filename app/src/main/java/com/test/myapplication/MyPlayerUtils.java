package com.test.myapplication;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * @name lzq
 * @class name：com.test.myapplication
 * @class describe
 * @time 2019-10-24 18:07
 * @change
 * @chang
 * @class describe
 */
public class MyPlayerUtils implements MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, SurfaceHolder.Callback, MediaPlayer.OnBufferingUpdateListener {



    private MediaPlayer mPlayer;
    private boolean hasPrepared;
    private SurfaceView mSurfaceView;
    SurfaceHolder holder;
    private Boolean noPlay=true;//定义播放状态
    String  dataSource;
    public MyPlayerUtils(SurfaceView surfaceView) {
        this.mSurfaceView = surfaceView;
    }

    private void initIfNecessary() {
        if (null == mPlayer) {
            mPlayer = new MediaPlayer();
            holder = mSurfaceView.getHolder();
            mPlayer.setOnErrorListener(this);
            mPlayer.setOnCompletionListener(this);
            mPlayer.setOnPreparedListener(this);
            mPlayer.setOnBufferingUpdateListener(this);
            holder.addCallback(this);
            mPlayer.setDisplay(holder);
            Log.e("initIfNecessary","实例化");
        }
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }


    /**
     *  // 通知调用处，调用play()方法进行下一个曲目的播放
     * @param mp
     */
    @Override
    public void onCompletion(MediaPlayer mp) {
        mPlayer.setLooping(true);
    }


    /**
     * // 准备完成后回调到这里
     * @param mp
     */
    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.e("onPrepared","准备完成后回调到这里");
        mPlayer.start();
    }

    /**
     * 设置播放

     * @param dataSource
     */
    public void play( String  dataSource) {
        this.dataSource=dataSource;
        hasPrepared = false; // 开始播放前讲Flag置为不可操作
        initIfNecessary(); // 如果是第一次播放/player已经释放了，就会重新创建、初始化
        try {
            mPlayer.reset();
            mPlayer.setDataSource( dataSource); // 设置资源
            Log.e("play",dataSource);
            mPlayer.prepareAsync(); // 异步的准备方法

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("play","error");
        }
        noPlay=false;
    }


     public  void onResume(){
         if(mPlayer!=null){
             mPlayer.release();
             mPlayer.setDisplay(holder);
         }
    }

    /**
     * 设置暂停
     */
    public void pause(){
        try {
            if(mPlayer.isPlaying()){
                mPlayer.pause();
            }
        }catch (Exception e){
        }
    }

    /**
     * 设置暂停
     */
    public void stop(){
        if(mPlayer.isPlaying()){
            Log.e("stop","stop");
            mPlayer.stop();
            mPlayer.reset();
            noPlay=true;//视频处于没有播放状态
        }
    }

    /**
     * 设置重置
     */
    public void onRelease(){
        if(mPlayer!=null){
            try {
                if(mPlayer.isPlaying()){
                    mPlayer.stop();
                }
                mPlayer.release();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //将播放器和SurfaceView关联起来
        Log.e("surfaceCreated","创建surface");

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("surfaceDestroyed", "surfaceDestroyed.................");
        onRelease();
    }




    public void seekTo(int msec) {
        if (mPlayer != null) {
            mPlayer.seekTo(msec);
        }
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        Log.e("   onBufferingUpdate",percent+"        percent") ;


    }
}
