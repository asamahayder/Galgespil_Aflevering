package com.example.galgespil_aflevering;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/*public class HentOrdFraArkTask extends AsyncTask<String, Void, Galgelogik> {
    private String sværhedsgrader;
    private Galgelogik spil;

    public HentOrdFraArkTask(String sværhedsgrader, Galgelogik spil) {
        this.sværhedsgrader = sværhedsgrader;
        this.spil = spil;
    }

    @Override
    protected Galgelogik doInBackground(String... strings) {
        ArrayList<String> list;
            try {
                spil.hentOrdFraRegneark(sværhedsgrader);
                spil.nulstil();
            } catch (Exception e) {
                e.printStackTrace();
            }
            list = spil.getMuligeOrd();
        System.out.println("#####################her begynder listen###################");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        return spil;
    }
}*/

//Denne klasse er lavet med hjælp fra hjælpelæren Nicolai.

public class HentOrdFraArkTask extends AsyncTask<String, String, Exception> {

    private final WeakReference<Gameactivity> activityRef; //Weak reference bruges så der ikke kommer memory leakage. En forklaring findes her: https://medium.com/google-developer-experts/finally-understanding-how-references-work-in-android-and-java-26a0d9c92f83
    private final Galgelogik spil;

    public HentOrdFraArkTask(Gameactivity activity, Galgelogik spil) {
        this.activityRef = new WeakReference<>(activity);
        this.spil = spil;
    }

    @Override
    protected Exception doInBackground(String... strings) {
        try {
            spil.hentOrdFraEgetRegneark(strings[0]);
            spil.nulstil();
            System.out.println(spil.getMuligeOrd());
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
                activityRef.get().setSynligtOrd();
            }
        }
    }
}
