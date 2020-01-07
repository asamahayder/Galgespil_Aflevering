package com.example.galgespil_aflevering;

import android.os.AsyncTask;
import java.lang.ref.WeakReference;

//Denne klasse er lavet med hjælp fra hjælpelæren Nicolai.
public class GetWordFromWebTask extends AsyncTask<String, String, Exception> {

    private final WeakReference<GameActivity> activityRef; //Weak reference bruges så der ikke kommer memory leakage. En forklaring findes her: https://medium.com/google-developer-experts/finally-understanding-how-references-work-in-android-and-java-26a0d9c92f83
    private final Galgelogik game;

    public GetWordFromWebTask(GameActivity activity, Galgelogik game) {
        this.activityRef = new WeakReference<>(activity);
        this.game = game;
    }

    @Override
    protected Exception doInBackground(String... strings) {
        try {
            game.getWordFromWeb(strings[0]);
            game.nulstil();
            System.out.println(game.getMuligeOrd());
        } catch (Exception e) {
            return e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Exception e) {
        if (activityRef.get() != null) {
            if (e != null) {
                activityRef.get().showErrorMessage();
            } else {
                activityRef.get().setVisibleWord();
                activityRef.get().setButtonsEnabled(true);
            }
        }
    }

}
