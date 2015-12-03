package com.jumper;

import com.badlogic.gdx.Gdx;

/**
 * Created by Roma-Alisa on 9/24/2015.
 */
public class Constants {
    //WORLD
    public static final float TIME_STEP = 1/50f;
    public static final int VELOCITY_ITERATIONS = 8, POSITION_ITERATIONS = 3;
    public static final float GRAVITY_EARTH =  -9.8f;

    //SCREEN SIZE
    public static final float SCREEN_RATIO_WIDTH = Gdx.graphics.getWidth()/24;
    public static final float SCREEN_RATIO_HEIGHT = Gdx.graphics.getHeight()/50;
    public static final float SCREEN_SIZE_WIDTH = 24;
    public static final float SCREEN_SIZE_HEIGHT = 50;
    public static final float SCREEN_PIXELS_SIZE_WIDTH = Gdx.graphics.getWidth();
    public static final float SCREEN_PIXELS_SIZE_HEIGHT = Gdx.graphics.getHeight();


    //CAMERA
    //public static float CAMERA_MOVEMENT_Y = .05f;
    //public static float CAMERA_MOVEMENT_Y = 5f;
    public static final float CAMERA_MOVEMENT_Y = 0;
    public static final float CAMERA_MOVEMENT_X = 0f;

    //STAIRS
    //public static int SPACE_BETWEEN_STAIRS_Y = 7;
    public static final int SPACE_BETWEEN_STAIRS_Y = 10;
    public static final int MAXIMUM_STAIRS = 20;
    public static final float STAIR_WIDTH = .7f;
    public static final String STR_STAIR = "stair";
    public static final int STAIR_LENGTH = 6;

    //COINS
    public static final int SPACE_BETWEEN_COINS_Y = 14;
    public static final int MAXIMUM_COINS = 6;
    public static final String STR_COIN = "coin";

    //SPIRAL
    public static final float SPIRAL_WIDTH = 1f;
    public static final float SPIRAL_PIC_WIDTH = 2f;
    public static final String STR_SPIRAL = "spiral";

    //PLAYER
    public static final float PLAYER_WIDTH = 3f;
    public static final float PLAYER_HEIGHT = 3f;
    public static final String STR_PLAYER = "player";

    //SNOW_BALL
    public static final String STR_SNOW_BALL = "snow_ball";
    public static final float SNOW_BALL_RADIUS = 1f;
    public static final float SNOW_BALL_SPEED = 5;

    //FONT
    public static final float STRING_SIZE_RATIO = Gdx.graphics.getWidth()/2;

    //ASSETS_NAMES
    public static final String PIC_PLAYER = "snowman.png";
    public static final String PIC_COIN = "Crystal2.png";
    public static final String PIC_STAIR = "Bar.png";
    public static final String PIC_SPIRAL = "spiral2.png";



}
