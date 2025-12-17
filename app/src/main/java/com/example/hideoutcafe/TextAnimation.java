package com.example.hideoutcafe;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class TextAnimation extends AppCompatTextView {
    private Animation shakeAnimation;

    public TextAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextAnimation(Context context) {
        super(context);
        init();
    }

    private void init() {
        shakeAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.shake);
    }

    public void setTextWithShake(String text) {
        setText(""); // Clear the text before starting the animation
        animateTextWithShake(text);
    }

    private void animateTextWithShake(final String text) {
        if (text == null || text.isEmpty()) {
            return;
        }

        post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < text.length(); i++) {
                    final char character = text.charAt(i);

                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            append(String.valueOf(character));
                            startAnimation(shakeAnimation);
                        }
                    }, i * shakeAnimation.getDuration());
                }
            }
        });
    }
}






