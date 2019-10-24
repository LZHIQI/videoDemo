package com.test.myapplication;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

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
public class MyPlayerUtils implements MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {

    private MediaPlayer mPlayer;
    private boolean hasPrepared;

    private void initIfNecessary() {
        if (null == mPlayer) {
            mPlayer = new MediaPlayer();
            mPlayer.setOnErrorListener(this);
            mPlayer.setOnCompletionListener(this);
            mPlayer.setOnPreparedListener(this);
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

    }


    /**
     * // 准备完成后回调到这里
     * @param mp
     */
    @Override
    public void onPrepared(MediaPlayer mp) {

    }


    public void play(Context context, Uri dataSource) {
        hasPrepared = false; // 开始播放前讲Flag置为不可操作
        initIfNecessary(); // 如果是第一次播放/player已经释放了，就会重新创建、初始化
        try {
            mPlayer.reset();
            mPlayer.setDataSource(context, dataSource); // 设置曲目资源
            mPlayer.prepareAsync(); // 异步的准备方法
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
