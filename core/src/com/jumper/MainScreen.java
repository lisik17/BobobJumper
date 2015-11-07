package com.jumper;

import com.badlogic.gdx.Game;

/**
 * Created by Roma-Alisa on 21/10/15.
 */
public class MainScreen extends Game {

    @Override
    public void create() {
        ScreenManager.getInstance().initialize(this);
        ScreenManager.getInstance().show(ScreenManager.CurrentScreen.MENU);
    }

    @Override
    public void dispose() {
        super.dispose();
        ScreenManager.getInstance().dispose();
    }
}
