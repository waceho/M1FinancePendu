package com.estiam.franck.pendu;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mamadousambadiallo on 23/06/2017.
 */

public class Partie implements Serializable {
    ArrayList<Saisie> saisies = new ArrayList <Saisie>( );
    java.util.Date date = new java.util.Date( );	// préciser le package d’où vient la classe Date
    int taille_du_mot;
    int score_final;
}
