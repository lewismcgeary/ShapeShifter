package io.github.lewismcgeary.shapeshifter;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements VoiceInputResultsReceiver {

    private VoiceInputRecognizer voiceInputRecognizer;
    private TextView returnedText;
    private ImageView shapeView;
    private ImageView emptyMicImageView;
    private ImageView instructionsImageView;

    private RecyclerView debugRecyclerView;
    private RecyclerView.LayoutManager debugLayoutManager;
    private RecyclerView.Adapter debugAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shapeView = (ImageView) findViewById(R.id.shape_view);
        emptyMicImageView = (ImageView) findViewById(R.id.empty_mic_image_view);
        instructionsImageView = (ImageView) findViewById(R.id.instructions_image_view);
        returnedText = (TextView) findViewById(R.id.returned_text);
        voiceInputRecognizer = new VoiceInputRecognizer(this, this);
        revealEmptyMicIcon();

        debugRecyclerView = (RecyclerView) findViewById(R.id.debug_recycler_view);
        debugRecyclerView.setHasFixedSize(true);
        debugLayoutManager = new GridLayoutManager(this, 2);
        debugRecyclerView.setLayoutManager(debugLayoutManager);
        String[] array = getResources().getStringArray(R.array.valid_shapes);
        debugAdapter = new DebugRecyclerAdapter(this, array);
        debugRecyclerView.setAdapter(debugAdapter);

        shapeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startListening();
                revealMic();
                hideEmptyMicIcon();
                v.setClickable(false);
            }
        });
    }

    @Override
    protected void onDestroy() {
        voiceInputRecognizer.destroy();
        super.onDestroy();
    }

    public void startListening(){
        voiceInputRecognizer.startListening();
    }

    public void revealMic(){
        AnimatedVectorDrawableCompat micRevealDrawable;
        micRevealDrawable = AnimatedVectorDrawableCompat.create(this, R.drawable.mic_reveal);
        shapeView.setImageDrawable(micRevealDrawable);
        micRevealDrawable.start();
    }

    public void hideMic(){

        AnimatedVectorDrawableCompat micHideDrawable;
        micHideDrawable = AnimatedVectorDrawableCompat.create(this, R.drawable.mic_hide);
        shapeView.setImageDrawable(micHideDrawable);
        micHideDrawable.start();
    }

    public void revealEmptyMicIcon(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                emptyMicImageView.setVisibility(View.VISIBLE);
            }
        }, 1000);
    }

    public void hideEmptyMicIcon(){
        emptyMicImageView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void shapeIdentified(String shape) {
        instructionsImageView.setVisibility(View.INVISIBLE);
        hideMic();
        revealShape(shape, 1000);
        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {

            @Override
            public void run() {
                startListening();
                revealMic();
            }

        }, 4000);
    }

    private void revealShape(String shape, int delay) {
        int resourceId = getResources().getIdentifier(shape.concat("_reveal"), "drawable", getPackageName());
        final AnimatedVectorDrawableCompat selectedShapeAVD = AnimatedVectorDrawableCompat.create(this, resourceId);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                shapeView.setImageDrawable(selectedShapeAVD);
                selectedShapeAVD.start();
            }

        }, delay);
    }

    @Override
    public void noShapeIdentified(String results) {
        //returnedText for debugging only
        //returnedText.setText(results);
        shapeView.setClickable(true);
        hideMic();
        revealEmptyMicIcon();
        Snackbar.make(shapeView, "I don't know that shape", Snackbar.LENGTH_LONG).show();

    }

    @Override
    public void errorRecognizingSpeech(String errorMessage) {
        //returnedText for debugging only
        //returnedText.setText(errorMessage);
        shapeView.setClickable(true);
        hideMic();
        revealEmptyMicIcon();
        Snackbar.make(shapeView, "Sorry, I didn't understand", Snackbar.LENGTH_LONG).show();


    }

    @Override
    public void debugShapeSelected(String shape) {
        revealShape(shape, 0);
    }
}
