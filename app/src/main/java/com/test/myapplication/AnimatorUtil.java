package com.test.myapplication;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * @name lzq
 * @class name：com.test.myapplication
 * @class describe
 * @time 2019-10-23 10:52
 * @change
 * @chang
 * @class describe
 */
public class AnimatorUtil {

    private static AccelerateDecelerateInterpolator LINEAR_INTERRPLATOR =new AccelerateDecelerateInterpolator();
    public static void showFab(View view,MyBehavior.AnimateListener ... listener){
        if (listener.length!=0){
            view.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .alpha(1f)
                    .setDuration(600)
                    .setInterpolator(LINEAR_INTERRPLATOR)
                    .setListener(listener[0])
                    .start();
        }else {
            view.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .alpha(1f)
                    .setDuration(600)
                    .setInterpolator(LINEAR_INTERRPLATOR)
                    .start();
        }

    }
    public static void hideFab(View view, MyBehavior.AnimateListener listener){
        view.animate()
                .scaleX(0f)
                .scaleY(0f)
                .alpha(0f)
                .setDuration(600)
                .setInterpolator(LINEAR_INTERRPLATOR)
                .setListener(listener)
                .start();
    }


}
