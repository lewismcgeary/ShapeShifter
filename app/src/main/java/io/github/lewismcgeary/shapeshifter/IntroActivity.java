package io.github.lewismcgeary.shapeshifter;

import android.content.Intent;
import android.os.Bundle;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ImageView logoImageView = (ImageView)findViewById(R.id.logo_image_view);
        AnimatedVectorDrawableCompat logoZoom = AnimatedVectorDrawableCompat.create(this, R.drawable.logo_zoom);
        logoImageView.setImageDrawable(logoZoom);
        logoZoom.start();
        logoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startShapeShifterActivity();
            }
        });
    }

    private void startShapeShifterActivity(){
        Intent newActivity = new Intent(this, MainActivity.class);
        startActivity(newActivity);
    }
}
