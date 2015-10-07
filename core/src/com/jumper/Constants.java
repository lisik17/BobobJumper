package com.jumper;

import com.badlogic.gdx.Gdx;

/**
 * Created by Roma-Alisa on 9/24/2015.
 */
public class Constants {
    //WORLD
    public static final float TIME_STEP = 1/60f;
    public static int VELOCITY_ITERATIONS = 8, POSITION_ITERATIONS = 3;

    //SCREEN SIZE
    public static float SCREEN_RATIO_WIDTH = Gdx.graphics.getWidth()/24;
    public static float SCREEN_RATIO_HEIGHT = Gdx.graphics.getHeight()/50;
    public static float SCREEN_SIZE_WIDTH = 24;

    //CAMERA
    public static float CAMERA_MOVEMENT_Y = .05f;
    //public static float CAMERA_MOVEMENT_Y = 0;
    public static float CAMERA_MOVEMENT_X = 0f;

    //STAIRS
    public static int SPACE_BETWEEN_STAIRS_Y = 7;
    public static int MAXIMUM_STAIRS = 12;
    public static float STAIR_WIDTH = .5f;
    public static String STR_STAIR = "stair";

    //COINS
    public static int SPACE_BETWEEN_COINS_Y = 14;
    public static int MAXIMUM_COINS = 6;
    public static String STR_COIN = "coin";

    //PLAYER
    public static float PLAYER_WIDTH = 1f;
    public static float PLAYER_HEIGHT = 1f;
    public static String STR_PLAYER = "player";

    //FONT
    public static float STRING_SIZE_RATIO = Gdx.graphics.getWidth()/2;


}
