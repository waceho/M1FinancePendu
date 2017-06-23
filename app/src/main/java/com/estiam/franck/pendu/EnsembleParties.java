package com.estiam.franck.pendu;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mamadousambadiallo on 23/06/2017.
 */

public class EnsembleParties implements Serializable {

    ArrayList<Partie> parties = new ArrayList <Partie>( );

    public void save (Context context) {
        try {
            File internalDir = context.getFilesDir();
            File f = new File(internalDir, "data");
            if (f.exists()) {
                f.delete();
            }

            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static EnsembleParties load(Context context) {
        try {
            File internalDir = context.getFilesDir();
            File f = new File(internalDir, "data");

            FileInputStream fos = new FileInputStream(f);
            ObjectInputStream oos = new ObjectInputStream(fos);
            EnsembleParties e = (EnsembleParties) oos.readObject();
            oos.close();
            return e;
        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
