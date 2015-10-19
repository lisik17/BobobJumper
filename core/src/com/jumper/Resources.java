package com.jumper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Roma-Alisa on 10/6/2015.
 */
public class Resources {


    private static Player player;
    private static OrthographicCamera camera;
    private static long score = 0;
    private static SpiralFactory spiralFactory;


    public static SpiralFactory getSpiralFactory() {
        return spiralFactory;
    }

    public static void setSpiralFactory(SpiralFactory spiralFactory) {
        Resources.spiralFactory = spiralFactory;
    }


    public static long getScore() {
        return score;
    }

    public static void setScore(long score) {
        Resources.score = score;
    }

    private static World world;

    public static OrthographicCamera getCamera() {
        return camera;
    }

    public static void setCamera(OrthographicCamera camera) {
        Resources.camera = camera;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        Resources.player = player;
    }

    public static World getWorld() {
        return world;
    }

    public static void setWorld(World world) {
        Resources.world = world;
    }

}
