package com.jumper;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;



/**
 * Created by Roma-Alisa on 9/24/2015.
 */
public class InputController implements InputProcessor {

    //private Body bodyPlayer;
    private Vector3 vecTouchDownCoordinates;
    private Vector3 vecTouchUpCoordinates;
    private OrthographicCamera camera;

    public InputController(){
        this.vecTouchDownCoordinates = new Vector3();
        this.vecTouchUpCoordinates = new Vector3();
        //this.bodyPlayer = Resources.getPlayer().getBodyPlayer();
        this.camera = Resources.getCamera();
    }

    @Override
    public boolean keyDown(int keycode) {
        if ((keycode == Input.Keys.ESCAPE) || (keycode == Input.Keys.BACK) ) {

            //if(Resources.getBasicScreen().getScreen().getClass() == GameJumper.class ){
            Gdx.app.log("app", "game");
            //    ((Game) Gdx.app.getApplicationListener()).setScreen(Resources.getMenuInstance());
            // }

            //Gdx.app.log("app", "exit Down");

        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {

        if ((keycode == Input.Keys.ESCAPE) || (keycode == Input.Keys.BACK) ) {

            if (ScreenManager.getInstance().getCurrentScreen() == ScreenManager.CurrentScreen.GAME) {
                ScreenManager.getInstance().show(ScreenManager.CurrentScreen.MENU);
                return true;
            }
            if (ScreenManager.getInstance().getCurrentScreen() == ScreenManager.CurrentScreen.MENU) {
                Gdx.app.exit();
                return true;
            }
        }

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        vecTouchDownCoordinates.set(screenX, screenY, 0);
        camera.unproject(vecTouchDownCoordinates);

        //System.out.println("Down" + vecTouchDownCoordinates.x);


        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        vecTouchUpCoordinates.set(screenX, screenY, 0);
        camera.unproject(vecTouchUpCoordinates);

        movePlayer();

        return true;
    }

    private void movePlayer() {
        //bodyPlayer.setLinearVelocity((vecTouchUpCoordinates.x - vecTouchDownCoordinates.x) * 1f, (vecTouchUpCoordinates.y - vecTouchDownCoordinates.y) * 1f);
/*        if(vecTouchUpCoordinates.x < vecTouchDownCoordinates.x) {
            bodyPlayer.setAngularVelocity(2);
        }else{
            bodyPlayer.setAngularVelocity(-2);
        }*/
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        //System.out.println("dragged");
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
