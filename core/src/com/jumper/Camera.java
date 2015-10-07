package com.jumper;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.sun.org.apache.bcel.internal.classfile.Constant;

/**
 * Created by Roma-Alisa on 9/24/2015.
 */
public class Camera {

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
}
