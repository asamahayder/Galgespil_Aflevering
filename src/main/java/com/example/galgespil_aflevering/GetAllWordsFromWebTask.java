package com.example.galgespil_aflevering;

import android.os.AsyncTask;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

//Denne klasse er lavet med hjælp fra hjælpelæren Nicolai.
public class GetAllWordsFromWebTask extends AsyncTask<String, String, Exception> {

    private final WeakReference<ChooseOwnWordActivity> activityRef; //Weak reference bruges så der ikke kommer memory leakage. En forklaring findes her: https://medium.com/google-developer-experts/finally-understanding-how-references-work-in-android-and-java-26a0d9c92f83
    private final Galgelogik game;
    private final ArrayList<String> wordList = new ArrayList<>();

    public GetAllWordsFromWebTask(ChooseOwnWordActivity activity, Galgelogik game) {
        this.activityRef = new WeakReference<>(activity);
        this.game = game;
    }

    @Override
    protected Exception doInBackground(String... strings) {
        try {
            game.getAllWordsFromWeb();
            insertIntoList(game.getMuligeOrd());
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
                activityRef.get().displayWordList(wordList);
            }
        }
    }

    public void insertIntoList(ArrayList<String> wordList){
        for (int i = 0; i < wordList.size(); i++) {
            this.wordList.add(wordList.get(i));
        }

    }

}
