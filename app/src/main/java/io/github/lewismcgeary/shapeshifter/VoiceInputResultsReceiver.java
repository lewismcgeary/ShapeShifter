package io.github.lewismcgeary.shapeshifter;

/**
 * Created by Lewis on 31/03/2016.
 */
public interface VoiceInputResultsReceiver {

    public void shapeIdentified(String shape);

    public void noShapeIdentified(String results);

    public void errorRecognizingSpeech(String errorMessage);

    public void debugShapeSelected(String shape);
}
