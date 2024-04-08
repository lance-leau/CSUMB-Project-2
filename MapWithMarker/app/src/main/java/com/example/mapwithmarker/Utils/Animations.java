package com.example.mapwithmarker.Utils;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

public class Animations {
    public static void animateViewHeight(View view, int startHeight, int endHeight, int duration) {
        ValueAnimator animator = ValueAnimator.ofInt(startHeight, endHeight);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int animatedValue = (int) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = animatedValue;
                view.setLayoutParams(layoutParams);
            }
        });
        animator.setDuration(duration * 1000); // Convert seconds to milliseconds
        animator.start();
    }
}
