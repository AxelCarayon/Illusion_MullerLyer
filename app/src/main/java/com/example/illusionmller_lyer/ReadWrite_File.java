package com.example.illusionmller_lyer;

import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

class ReadWrite_File {

    String filepath;

    public ReadWrite_File(String filepath) {
        this.filepath = filepath;
    }

    public void write(int entrainement, int exercice, long seed, int duree){
        File root = android.os.Environment.getExternalStorageDirectory();
        File dir = new File (root.getAbsolutePath() + "/IllusionMullerLyer");
        dir.mkdirs();
        File properties =  new File(dir+filepath);
        try {
            properties.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (checkExternalMedia()) {
            try (OutputStream output = new FileOutputStream(dir+filepath)) {
                Properties prop = new Properties();

                // set the properties value
                prop.setProperty("nb_entrainement", String.valueOf(entrainement));
                prop.setProperty("nb_exercice", String.valueOf(exercice));
                prop.setProperty("seed", String.valueOf(seed));
                prop.setProperty("duree",String.valueOf(duree));

                // save properties to project root folder
                prop.store(output, "test");
            } catch (IOException io) {
                io.printStackTrace();
            }
        }

    }
    public String[] read(){
        String[] resultat = new String[4];
        File root = android.os.Environment.getExternalStorageDirectory();
        File dir = new File (root.getAbsolutePath() + "/IllusionMullerLyer");
        if (checkExternalMedia()) {
            try (InputStream input = new FileInputStream(dir+filepath)) {
                Properties prop = new Properties();
                prop.load(input);
                // set the properties value
                resultat[0] = prop.getProperty("nb_entrainement");
                resultat[1] = prop.getProperty("nb_exercice");
                resultat[2] = prop.getProperty("seed");
                resultat[3] = prop.getProperty("duree");
            } catch (IOException io) {
                io.printStackTrace();
            }
        }
        return resultat;
    }

    private boolean checkExternalMedia(){
        boolean mExternalStorageAvailable = false;
        boolean mExternalStorageWriteable = false;
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // Can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // Can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {
            // Can't read or write
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }
        return mExternalStorageWriteable;
    }

}
