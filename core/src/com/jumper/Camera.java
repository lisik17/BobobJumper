package com.jumper;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sun.org.apache.bcel.internal.classfile.Constant;

/**
 * Created by Roma-Alisa on 9/24/2015.
 */
public class Camera extends Actor{

    public  static Vector3 vecCameraCords = new Vector3();

    public static void moveDown(OrthographicCamera camera){
        camera.translate(Constants.CAMERA_MOVEMENT_X, Constants.CAMERA_MOVEMENT_Y);
        camera.update();
    }

    public static Vector3 cordsToMeters(OrthographicCamera camera){
        vecCameraCords.set(camera.position.x, camera.position.y, 0);
        camera.unproject(vecCameraCords);
        return  vecCameraCords;
    }

    public static void followPlayer(OrthographicCamera camera){
        camera.position.set(0,Resources.getPlayer().getBodyPlayer().getPosition().y,0);
        camera.update();
    }

    public static void act(){
        moveDown(Resources.getCamera());
        followPlayer(Resources.getCamera());
    }
}
