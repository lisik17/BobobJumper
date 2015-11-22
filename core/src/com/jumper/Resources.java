package com.jumper;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import com.jumper.effect.JumpEffect;
import com.jumper.factories.SnowBallFactory;
import com.jumper.factories.SpiralFactory;

/**
 * Created by Roma-Alisa on 10/6/2015.
 */
public class Resources {


    private static Player player;
    private static OrthographicCamera camera;
    private static long score = 0;
    private static Font font;

    private static SpiralFactory spiralFactory;
    private static SnowBallFactory snowBallFactory;
    private static JumpEffect jumpEffect;


    public static JumpEffect getJumpEffect() {
        return jumpEffect;
    }

    public static void setJumpEffect(JumpEffect jumpEffect) {
        Resources.jumpEffect = jumpEffect;
    }


    public static com.jumper.factories.SnowBallFactory getSnowBallFactory() {
        return snowBallFactory;
    }

    public static void setSnowBallFactory(com.jumper.factories.SnowBallFactory snowBallFactory) {
        Resources.snowBallFactory = snowBallFactory;
    }

    public static Font getFont() {
        return font;
    }

    public static void setFont(Font font) {
        Resources.font = font;
    }


    public static SpiralFactory getSpiralFactory() {
        return spiralFactory;
    }

    public static void setSpiralFactory(com.jumper.factories.SpiralFactory spiralFactory) {
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
