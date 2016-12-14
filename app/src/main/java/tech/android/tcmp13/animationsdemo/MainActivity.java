package tech.android.tcmp13.animationsdemo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupTextViewSpinningAnimation();
        happyShuki();
    }

    private void setupTextViewSpinningAnimation() {
        final TextView tv = (TextView) findViewById(R.id.textView);
        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                tv.animate().rotation(1800).alpha(0).setInterpolator(new DecelerateInterpolator()).setDuration(2000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        tv.animate().rotation(-1800).alpha(1).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(2000);
                    }
                });
                return false;
            }
        });
    }

    private void happyShuki() {

        final ImageView shuki = (ImageView) findViewById(R.id.shuki);
        shuki.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                shuki.animate().scaleXBy(2.5f).scaleYBy(2.5f).
                        setInterpolator(new AnticipateOvershootInterpolator()).setDuration(1000).
                        withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                shuki.animate().scaleXBy(-2.5f).scaleYBy(-2.5f).
                                        setInterpolator(new BounceInterpolator()).setDuration(1000);
                            }
                        });
                return false;
            }
        });
    }

    public void goToNextActivity(View view) {

        Intent intent = new Intent(this, NextActivity.class);
//        ActivityOptions options = ActivityOptions.makeScaleUpAnimation(view, 0, 0, view.getWidth(), view.getHeight());
        ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(view, 0, 0, view.getWidth(), view.getHeight());
        startActivity(intent, options.toBundle());
    }
}
