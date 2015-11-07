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
   // private Vector3 vecTouchDownCoordinates;
    //private Vector3 vecTouchUpCoordinates;
    private OrthographicCamera camera;

    public InputController(){
        //this.vecTouchDownCoordinates = new Vector3();
        //this.vecTouchUpCoordinates = new Vector3();
        //this.bodyPlayer = Resources.getPlayer().getBodyPlayer();
        this.camera = Resources.getCamera();
    }

    @Override
    public boolean keyDown(int keycode) {
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {

        onBackKeyUp(keycode);
        return true;
    }

    private void onBackKeyUp(int keycode) {
        if ((keycode == Input.Keys.ESCAPE) || (keycode == Input.Keys.BACK) ) {

            switch (ScreenManager.getInstance().getCurrentScreen()){
                case MENU :  Gdx.app.exit();
                    break;
                case GAME : ScreenManager.getInstance().show(ScreenManager.CurrentScreen.MENU);
                    break;
                case COOL_STAFF : ScreenManager.getInstance().show(ScreenManager.CurrentScreen.MENU);
                    break;
                case SETTINGS : ScreenManager.getInstance().show(ScreenManager.CurrentScreen.MENU);
                    break;
                case MORE_GAMES : ScreenManager.getInstance().show(ScreenManager.CurrentScreen.MENU);
                    break;
                default : Gdx.app.exit();
            }
        }
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        movePlayer();

        return true;
    }

    private void movePlayer() {
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
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
