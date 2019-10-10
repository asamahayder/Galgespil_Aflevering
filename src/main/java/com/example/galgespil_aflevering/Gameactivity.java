package com.example.galgespil_aflevering;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Layout;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Gameactivity extends AppCompatActivity implements View.OnClickListener {

    TextView gameMode;
    TextView synligtOrd;
    TextView brugteBogstaver;
    TextView numberOfLives;
    ImageView image;
    LinearLayout linearLayout1;
    LinearLayout linearLayout2;
    LinearLayout linearLayout3;
    LinearLayout linearLayout4;

    Galgelogik spil;
    EditText gætBogstavInputFelt;
    Button submit, buttonA, buttonB, buttonC, buttonD, buttonE, buttonF, buttonG, buttonH, buttonI, buttonJ, buttonK, buttonL, buttonM, buttonN, buttonO, buttonP, buttonQ, buttonR, buttonS, buttonT, buttonU, buttonV, buttonW, buttonX, buttonY, buttonZ, buttonÆ, buttonØ, buttonÅ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        String mode = intent.getStringExtra("mode");
        spil = new Galgelogik();
        gætBogstavInputFelt = findViewById(R.id.indsætBogstav);
        synligtOrd = findViewById(R.id.synligtOrd);
        submit = findViewById(R.id.submit);
        brugteBogstaver = findViewById(R.id.brugteBogstaver);
        image = findViewById(R.id.grafik);
        numberOfLives = findViewById(R.id.numberOfLives);
        linearLayout1 = findViewById(R.id.linearLayout1);
        linearLayout2 = findViewById(R.id.linearLayout2);
        linearLayout3 = findViewById(R.id.linearLayout3);

        //TODO ting at spørge hjælpelære om: weightsum, clickevent, hentfraregneark
        submit.setVisibility(View.GONE);
        gætBogstavInputFelt.setVisibility(View.GONE);
        brugteBogstaver.setVisibility(View.GONE);

        for (int i = 0; i < 26; i++) {
            //Button button = new Button(this);
            final TextView button = new TextView(this);
            button.setBackgroundResource(R.drawable.rounded_buttons);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleButtonClick(button);
                }
            });
            button.setTextSize(TypedValue.COMPLEX_UNIT_SP,27);
            button.setTextColor(Color.parseColor("yellow"));
            button.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            params.setMargins(6,6,6,6);
            button.setPadding(10,10,10,10);
            button.setLayoutParams(params);

            //button.setWidth((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics()));
            //button.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            switch (i){
                case 0: button.setText("Q"); break;
                case 1: button.setText("W"); break;
                case 2: button.setText("E"); break;
                case 3: button.setText("R"); break;
                case 4: button.setText("T"); break;
                case 5: button.setText("Y"); break;
                case 6: button.setText("U"); break;
                case 7: button.setText("I"); break;
                case 8: button.setText("O"); break;
                case 9: button.setText("P"); break;
                case 10: button.setText("A"); break;
                case 11: button.setText("S"); break;
                case 12: button.setText("D"); break;
                case 13: button.setText("F"); break;
                case 14: button.setText("G"); break;
                case 15: button.setText("H"); break;
                case 16: button.setText("J"); break;
                case 17: button.setText("K"); break;
                case 18: button.setText("L"); break;
                case 19: button.setText("Z"); break;
                case 20: button.setText("X"); break;
                case 21: button.setText("C"); break;
                case 22: button.setText("V"); break;
                case 23: button.setText("B"); break;
                case 24: button.setText("N"); break;
                case 25: button.setText("M"); break;

            }
            if (i < 10){
                linearLayout1.addView(button);
            }else if (i < 19){
                linearLayout2.addView(button);
            }else{
                linearLayout3.addView(button);
            }
        }

//TODO overvej forloops, programatisk, modulo, gravity, weightsum, arrays, ingen vertikal
        /*buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);
        buttonE = findViewById(R.id.buttonE);
        buttonF = findViewById(R.id.buttonF);
        buttonG = findViewById(R.id.buttonG);
        buttonH = findViewById(R.id.buttonH);
        buttonI = findViewById(R.id.buttonI);
        buttonJ = findViewById(R.id.buttonJ);
        buttonK = findViewById(R.id.buttonK);
        buttonL = findViewById(R.id.buttonL);
        buttonM = findViewById(R.id.buttonM);
        buttonN = findViewById(R.id.buttonN);
        buttonO = findViewById(R.id.buttonO);
        buttonP = findViewById(R.id.buttonP);
        buttonQ = findViewById(R.id.buttonQ);
        buttonR = findViewById(R.id.buttonR);
        buttonS = findViewById(R.id.buttonS);
        buttonT = findViewById(R.id.buttonT);
        buttonU = findViewById(R.id.buttonU);
        buttonV = findViewById(R.id.buttonV);
        buttonW = findViewById(R.id.buttonW);
        buttonX = findViewById(R.id.buttonX);
        buttonY = findViewById(R.id.buttonY);
        buttonZ = findViewById(R.id.buttonZ);*/



        gameMode = findViewById(R.id.chosenMode);

        if (mode.equals("1")){
            //gameMode.setText(R.string.easy_mode);
            //HentOrdFraArkTask hentOrdFraArkTask = new HentOrdFraArkTask("1", spil);
            //hentOrdFraArkTask.execute();
            spil.hentOrdFraKode("1");
        }else if (mode.equals("2")){
            gameMode.setText(R.string.standard_mode);
            //HentOrdFraArkTask hentOrdFraArkTask = new HentOrdFraArkTask("2", spil);
            //hentOrdFraArkTask.execute();
            spil.hentOrdFraKode("2");
        }else{
            gameMode.setText(R.string.hard_mode);
            //HentOrdFraArkTask hentOrdFraArkTask = new HentOrdFraArkTask("3", spil);
            //hentOrdFraArkTask.execute();
            spil.hentOrdFraKode("3");
        }

        spil.nulstil();
        numberOfLives.setText("7");
        synligtOrd.setText(spil.getSynligtOrd());
        submit.setOnClickListener(this);
        /*buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonD.setOnClickListener(this);
        buttonE.setOnClickListener(this);
        buttonF.setOnClickListener(this);
        buttonG.setOnClickListener(this);
        buttonH.setOnClickListener(this);
        buttonI.setOnClickListener(this);
        buttonJ.setOnClickListener(this);
        buttonK.setOnClickListener(this);
        buttonL.setOnClickListener(this);
        buttonM.setOnClickListener(this);
        buttonN.setOnClickListener(this);
        buttonO.setOnClickListener(this);
        buttonP.setOnClickListener(this);
        buttonQ.setOnClickListener(this);
        buttonR.setOnClickListener(this);
        buttonS.setOnClickListener(this);
        buttonT.setOnClickListener(this);
        buttonU.setOnClickListener(this);
        buttonV.setOnClickListener(this);
        buttonW.setOnClickListener(this);
        buttonX.setOnClickListener(this);
        buttonY.setOnClickListener(this);
        buttonZ.setOnClickListener(this);
        buttonÆ.setOnClickListener(this);
        buttonØ.setOnClickListener(this);
        buttonÅ.setOnClickListener(this);*/
    }

    /*@Override
    public void onClick(View v) {
        if (v == submit){
            //handleGuess();
            handleGuessV2();
            handleGameFinish();
        }
    }*/

    //jeg kunne gøre dette med meget mindre kode vha et textfield, og en submit button. Og faktisk har jeg allerede skrevet kode for dette. Men denne måde ser bare mere cool ud.
    @Override
    public void onClick(View v) {
        /*switch (v.getId()){
            case R.id.buttonA:
                handleGuessV2(buttonA.getText().toString().toLowerCase());
                buttonA.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonB:
                handleGuessV2(buttonB.getText().toString().toLowerCase());
                buttonB.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonC:
                handleGuessV2(buttonC.getText().toString().toLowerCase());
                buttonC.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonD:
                handleGuessV2(buttonD.getText().toString().toLowerCase());
                buttonD.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonE:
                handleGuessV2(buttonE.getText().toString().toLowerCase());
                buttonE.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonF:
                handleGuessV2(buttonF.getText().toString().toLowerCase());
                buttonF.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonG:
                handleGuessV2(buttonG.getText().toString().toLowerCase());
                buttonG.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonH:
                handleGuessV2(buttonH.getText().toString().toLowerCase());
                buttonH.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonI:
                handleGuessV2(buttonI.getText().toString().toLowerCase());
                buttonI.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonJ:
                handleGuessV2(buttonJ.getText().toString().toLowerCase());
                buttonJ.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonK:
                handleGuessV2(buttonK.getText().toString().toLowerCase());
                buttonK.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonL:
                handleGuessV2(buttonL.getText().toString().toLowerCase());
                buttonL.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonM:
                handleGuessV2(buttonM.getText().toString().toLowerCase());
                buttonM.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonN:
                handleGuessV2(buttonN.getText().toString().toLowerCase());
                buttonN.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonO:
                handleGuessV2(buttonO.getText().toString().toLowerCase());
                buttonO.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonP:
                handleGuessV2(buttonP.getText().toString().toLowerCase());
                buttonP.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonQ:
                handleGuessV2(buttonQ.getText().toString().toLowerCase());
                buttonQ.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonR:
                handleGuessV2(buttonR.getText().toString().toLowerCase());
                buttonR.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonS:
                handleGuessV2(buttonS.getText().toString().toLowerCase());
                buttonS.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonT:
                handleGuessV2(buttonT.getText().toString().toLowerCase());
                buttonT.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonU:
                handleGuessV2(buttonU.getText().toString().toLowerCase());
                buttonU.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonV:
                handleGuessV2(buttonV.getText().toString().toLowerCase());
                buttonV.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonW:
                handleGuessV2(buttonW.getText().toString().toLowerCase());
                buttonW.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonX:
                handleGuessV2(buttonX.getText().toString().toLowerCase());
                buttonX.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonY:
                handleGuessV2(buttonY.getText().toString().toLowerCase());
                buttonY.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonZ:
                handleGuessV2(buttonZ.getText().toString().toLowerCase());
                buttonZ.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonÆ:
                handleGuessV2(buttonÆ.getText().toString().toLowerCase());
                buttonÆ.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonØ:
                handleGuessV2(buttonØ.getText().toString().toLowerCase());
                buttonØ.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.buttonÅ:
                handleGuessV2(buttonÅ.getText().toString().toLowerCase());
                buttonÅ.getBackground().setColorFilter(0xFF808080, PorterDuff.Mode.MULTIPLY);
                break;

        }*/
        handleLifePoints();
        handleGameFinish();
    }

    public void handleButtonClick(TextView button){
        String guess = button.getText().toString().toLowerCase();
        handleGuessV2(guess);
        if (spil.erSidsteBogstavKorrekt()){
            button.setBackgroundResource(0);
            //button.setBackgroundColor(Color.parseColor("transparent"));
            button.setTextColor(Color.parseColor("green"));
        }else{
            button.setBackgroundResource(0);
            //button.setBackgroundColor(Color.parseColor("transparent"));
            button.setTextColor(Color.parseColor("red"));
        }
        handleLifePoints();
        handleGameFinish();
    }

    public void handleGuessV2(String guess){
        spil.gætBogstav(guess);
        //gætBogstavInputFelt.setText("");
        synligtOrd.setText(spil.getSynligtOrd());
        //brugteBogstaver.setText("brugte bogstaver: " + spil.getBrugteBogstaver().toString());

    }

    public void handleLifePoints(){
        if (!spil.erSidsteBogstavKorrekt()){
            int numberOfLivesLeft = 6-spil.getAntalForkerteBogstaver();
            String NOLstring = Integer.toString(numberOfLivesLeft);
            numberOfLives.setText(NOLstring);
            switch (spil.getAntalForkerteBogstaver()){
                case 1: image.setImageResource(R.drawable.forkert1);
                    break;
                case 2: image.setImageResource(R.drawable.forkert2);
                    break;
                case 3: image.setImageResource(R.drawable.forkert3);
                    break;
                case 4: image.setImageResource(R.drawable.forkert4);
                    break;
                case 5: image.setImageResource(R.drawable.forkert5);
                    break;
                case 6: image.setImageResource(R.drawable.forkert6);
                    break;
            }
        }
    }


    public void handleGuess(){
        spil.gætBogstav(gætBogstavInputFelt.getText().toString());
        gætBogstavInputFelt.setText("");
        synligtOrd.setText(spil.getSynligtOrd());
        brugteBogstaver.setText("brugte bogstaver: " + spil.getBrugteBogstaver().toString());
        if (!spil.erSidsteBogstavKorrekt()){
            int numberOfLivesLeft = 7-spil.getAntalForkerteBogstaver();
            String NOLstring = Integer.toString(numberOfLivesLeft);
            numberOfLives.setText(NOLstring);
            switch (spil.getAntalForkerteBogstaver()){
                case 1: image.setImageResource(R.drawable.forkert2);
                    break;
                case 2: image.setImageResource(R.drawable.forkert3);
                    break;
                case 3: image.setImageResource(R.drawable.forkert4);
                    break;
                case 4:
                case 5:
                case 6: image.setImageResource(R.drawable.forkert5);
                    break;
                case 7: image.setImageResource(R.drawable.forkert6);
            }
        }
    }

    public void handleGameFinish(){
        if (spil.erSpilletSlut()){
            //TODO lav en ordentlig afslutning på spillet
            String ord = spil.getOrdet();
            synligtOrd.setText("spillet er slut!");
            AlertDialog.Builder alertdialogBuilder = new AlertDialog.Builder(this);
            if (spil.erSpilletVundet()){
                alertdialogBuilder.setMessage("Du har vundet. Ordet var: " + ord);
            }else{
                alertdialogBuilder.setMessage("Du har tabt. Ordet var: " + ord);
            }

            alertdialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog alertDialog = alertdialogBuilder.create();
            alertDialog.show();
        }
    }
}
