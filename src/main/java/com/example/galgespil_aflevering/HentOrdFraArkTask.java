package com.example.galgespil_aflevering;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class HentOrdFraArkTask extends AsyncTask<String, Void, Galgelogik> {
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



}
