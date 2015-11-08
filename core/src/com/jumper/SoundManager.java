package com.jumper;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Roma-Alisa on 07/11/15.
 */
public class SoundManager {
    private static SoundManager instance;
    private static Sound coinSound,stairSound,spiralSound,boxSound;

    private enum SoundType{

        COIN_SOUND{
            @Override
            protected void playSound(){coinSound.play();}
        },

        STAIR_SOUND{
            @Override
            protected void playSound(){spiralSound.play();}
        },

        SPIRAL_SOUND{
            @Override
            protected void playSound(){spiralSound.play();}

        },

        BOX_SOUND{
            @Override
            protected void playSound(){boxSound.play();}
        };

        protected abstract void playSound();
    }

    private SoundManager(){}

    public static void loadSounds(){
        coinSound = Gdx.audio.newSound(Gdx.files.internal("sound/coin.mp3"));
        stairSound = coinSound;
        spiralSound = coinSound;
        boxSound = coinSound;
    }

    public static SoundManager getInstance(){



        if(null == instance){
            instance = new SoundManager();
        }
        return instance;
    }

    public void playSound(){

        CollisionManager.State state = CollisionManager.getInstance().getCollisionType();

        switch (state){

            case COL_COIN:
                SoundType.COIN_SOUND.playSound();
                break;
            case COL_STAIR:
                SoundType.STAIR_SOUND.playSound();
                break;
            case COL_SPIRAL:
                SoundType.SPIRAL_SOUND.playSound();
                break;
            case NONE:
                SoundType.SPIRAL_SOUND.playSound();
                break;
            default:
                SoundType.SPIRAL_SOUND.playSound();
                break;
        }
    }

    public void dispose(){
        coinSound.dispose();
        stairSound.dispose();
        spiralSound.dispose();
        boxSound.dispose();
        instance = null;
    }
}

