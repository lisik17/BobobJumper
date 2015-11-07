package com.jumper;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Preferences;


/**
 * Created by Roma-Alisa on 07/11/15.
 */
public class HighScore {

    private static HighScore instance;
    private static final String SCORE_PREF = "scorePef";
    private static final String SCORE = "score";
    private static Preferences scorePrefs;

    private HighScore(){
        scorePrefs = (Preferences) Gdx.app.getPreferences(SCORE_PREF);
        scorePrefs.putLong(SCORE,0);
    }

    public static HighScore getInstance(){
        if(null == instance){
            instance = new HighScore();
        }
        return instance;
    }

    public static long getHighScore(){
        scorePrefs = (Preferences) Gdx.app.getPreferences(SCORE_PREF);
        return getInstance().scorePrefs.getLong(SCORE, 0);
    }

    public static void updateScore(long newScore){
        scorePrefs = (Preferences) Gdx.app.getPreferences(SCORE_PREF);
        if(scorePrefs.getLong(SCORE,0) < newScore){
            scorePrefs.putLong(SCORE, newScore);
            scorePrefs.flush();
        }
    }
}
