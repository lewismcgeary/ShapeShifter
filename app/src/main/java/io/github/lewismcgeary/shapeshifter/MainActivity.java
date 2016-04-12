package io.github.lewismcgeary.shapeshifter;

import android.os.Bundle;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements VoiceInputResultsReceiver {

    private VoiceInputRecognizer voiceInputRecognizer;
    private TextView returnedText;
    private ImageView shapeView;
    private AnimatedVectorDrawableCompat micRevealDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shapeView = (ImageView) findViewById(R.id.shape_view);
        returnedText = (TextView) findViewById(R.id.returned_text);
        voiceInputRecognizer = new VoiceInputRecognizer(this, this);
        micRevealDrawable = AnimatedVectorDrawableCompat.create(this, R.drawable.mic_hide);
        shapeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startListening();
                shapeView.setImageDrawable(micRevealDrawable);
                micRevealDrawable.start();
            }
        });
    }

    public void startListening(){
        voiceInputRecognizer.startListening();
    }

    @Override
    public void shapeIdentified(String shape) {
        int resourceId = getResources().getIdentifier(shape.concat("_reveal"), "drawable", getPackageName());
        AnimatedVectorDrawableCompat selectedShapeAVD = AnimatedVectorDrawableCompat.create(this, resourceId);
        shapeView.setImageDrawable(selectedShapeAVD);
        selectedShapeAVD.start();
    }

    @Override
    public void errorRecognizingSpeech(String errorMessage) {
        returnedText.setText(errorMessage);
    }
}
