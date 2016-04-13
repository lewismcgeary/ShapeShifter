package io.github.lewismcgeary.shapeshifter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class IntroActivity extends AppCompatActivity {
    ImageView logoImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        logoImageView = (ImageView)findViewById(R.id.logo_image_view);
        AnimatedVectorDrawableCompat logoZoom = AnimatedVectorDrawableCompat.create(this, R.drawable.logo_zoom);
        logoImageView.setImageDrawable(logoZoom);
        logoZoom.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                logoImageView.setImageResource(R.drawable.mic_hidden);
                startShapeShifterActivity();
            }
        },4000);
    }

    private void startShapeShifterActivity(){
        final Intent newActivity = new Intent(this, MainActivity.class);
        final ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, logoImageView, "transitionView");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(newActivity, options.toBundle());
            }
        },100);

    }
}
