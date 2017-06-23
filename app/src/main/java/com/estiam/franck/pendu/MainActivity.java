package com.estiam.franck.pendu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    //SharedPreferences stats = getSharedPreferences("successLetters", MODE_PRIVATE);

    int nbTry = 0;
    int nbGoodTry = 0;
    int nbWrongTry = 0;
    private Partie partie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.partie = new Partie();
        partie.date = new Date();

        final ImageView sol = (ImageView)findViewById(R.id.sol);
        final ImageView poteau = (ImageView)findViewById(R.id.poteau);
        final ImageView transverse = (ImageView)findViewById(R.id.transverse);
        final ImageView corde = (ImageView)findViewById(R.id.corde);
        final ImageView tete = (ImageView)findViewById(R.id.tete);
        final ImageView corp = (ImageView)findViewById(R.id.corp);
        final ImageView bg = (ImageView)findViewById(R.id.bg);
        final ImageView bd = (ImageView)findViewById(R.id.bd);
        final ImageView jg = (ImageView)findViewById(R.id.jg);
        final ImageView jd = (ImageView)findViewById(R.id.jd);




        final EditText inputLetter = (EditText) findViewById(R.id.inputLetter);
        final TextView secretWord = (TextView) findViewById(R.id.secretWord);
        final TextView usedLetters = (TextView) findViewById(R.id.usedLetters);
        final TextView nbEssai = (TextView) findViewById(R.id.nbEssai);
        final TextView resultat = (TextView) findViewById(R.id.resultat);
        final Button send = (Button) findViewById(R.id.send);
        final Button reload = (Button) findViewById(R.id.reload);

        final List<String> wrongLetters = new ArrayList<String>();
        final List<String> goodLetters = new ArrayList<String>();
        String toDisplay = null;

        final String[] words = {"lunette", "Amour", "vodka", "inflation", "enfant", "galaxie", "whisky", "haribo", "unijambiste", "estiam", "zino", "mayonnaise"};
        System.out.println(nbTry);
        Random rand = new Random();
        int max = words.length;
        //int randomNum = rand.nextInt(max + 1);
        int randomNum =(int)(Math.random()*words.length);
        System.out.println(randomNum);
        String wordToDiscover = words[randomNum];

        partie.taille_du_mot = wordToDiscover.length();
        final char[] splitWord = wordToDiscover.toCharArray();
        System.out.println(java.util.Arrays.toString(splitWord));
        toDisplay = splitWord[0] + " ";

        final String reponse = String.valueOf(splitWord);
        System.out.println(reponse);
        if(nbTry == 0) {
            for (int i = 1; i < splitWord.length; i++) {

                toDisplay = toDisplay + "_ ";
            }
            secretWord.setText(toDisplay);

            sol.setVisibility(View.INVISIBLE);
            poteau.setVisibility(View.INVISIBLE);
            transverse.setVisibility(View.INVISIBLE);
            corde.setVisibility(View.INVISIBLE);
            tete.setVisibility(View.INVISIBLE);
            corp.setVisibility(View.INVISIBLE);
            bg.setVisibility(View.INVISIBLE);
            bd.setVisibility(View.INVISIBLE);
            jg.setVisibility(View.INVISIBLE);
            jd.setVisibility(View.INVISIBLE);
        }

        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nbTry++;
                String test = null;
                if(inputLetter != null) {
                    test = inputLetter.getText().toString();
                }
                String toDisplay = splitWord[0] + " ";
                boolean usefulTry = false;
                boolean oldLetter;

                for (int i = 1; i < splitWord.length; i++) {
                    oldLetter = false;
                    if(test.charAt(0) == splitWord[i]){

                        toDisplay = toDisplay + splitWord[i] + " ";
                        usefulTry = true;
                    }else if(goodLetters.size() != 0){
                        for(int j = 0; j < goodLetters.size(); j++) {
                            if(Objects.equals(String.valueOf(goodLetters.get(j)), String.valueOf(splitWord[i]))){
                                toDisplay = toDisplay + String.valueOf(goodLetters.get(j)) + " ";
                                oldLetter = true;
                                break;
                            }
                        }
                        if(oldLetter == false){
                            toDisplay = toDisplay + "_ ";
                        }
                    }else{
                        toDisplay = toDisplay + "_ ";
                    }

                }
                if(usefulTry == false){
                    wrongLetters.add(test + " ");
                    usedLetters.setText(String.valueOf(wrongLetters));
                    nbWrongTry++;

                } else {
                    goodLetters.add(test);
                    System.out.println(goodLetters);
                }
                System.out.println(toDisplay.replace(" ", ""));
                String tempDisplay = toDisplay.replace(" ", "");
                if(Objects.equals(reponse, tempDisplay)){
                    resultat.setText("YOU WON !!!");
                    inputLetter.setVisibility(View.INVISIBLE);
                    send.setVisibility(View.INVISIBLE);
                    reload.setVisibility(View.VISIBLE);
                }
                secretWord.setText(toDisplay);
                inputLetter.setText("");
                nbEssai.setText("Nb Essai " + nbWrongTry +"/10");

                if(nbWrongTry == 1){
                    sol.setVisibility(View.VISIBLE);
                }else if(nbWrongTry == 2){
                    poteau.setVisibility(View.VISIBLE);
                }else if(nbWrongTry == 3){
                    transverse.setVisibility(View.VISIBLE);
                }else if(nbWrongTry == 4){
                    corde.setVisibility(View.VISIBLE);
                }else if(nbWrongTry == 5){
                    tete.setVisibility(View.VISIBLE);
                }else if(nbWrongTry == 6){
                    corp.setVisibility(View.VISIBLE);
                }else if(nbWrongTry == 7){
                    bg.setVisibility(View.VISIBLE);
                }else if(nbWrongTry == 8){
                    bd.setVisibility(View.VISIBLE);
                }else if(nbWrongTry == 9){
                    jg.setVisibility(View.VISIBLE);
                }else if(nbWrongTry == 10){
                    jd.setVisibility(View.VISIBLE);
                    resultat.setText("YOU LOSE !!!");
                    inputLetter.setVisibility(View.INVISIBLE);
                    send.setVisibility(View.INVISIBLE);
                    reload.setVisibility(View.VISIBLE);

                }

                Saisie s = new Saisie();
                s.lettre = test.charAt(0);
                s.nbre_de_vie = 10-nbWrongTry;
                s.valide = usefulTry;

                MainActivity.this.partie.saisies.add(s);
            }
            //SharedPreferences.Editor stats = getSharedPreferences("successLetters", MODE_PRIVATE).edit();
            //            stats.putString("letters", test)


        });
        reload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.partie.score_final = 10 - nbWrongTry;

                EnsembleParties e = EnsembleParties.load(MainActivity.this);
                if(e == null)
                    e = new EnsembleParties();
                e.parties.add(MainActivity.this.partie);

                e.save(MainActivity.this);

                finish();
                startActivity(getIntent());
            }
        });
    }

}
