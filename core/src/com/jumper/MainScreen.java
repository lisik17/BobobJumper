package com.jumper;

import com.badlogic.gdx.Game;

/**
 * Created by Roma-Alisa on 21/10/15.
 */
public class MainScreen extends Game {

    @Override
    public void create() {
        com.jumper.managers.ScreenManager.getInstance().initialize(this);
        com.jumper.managers.ScreenManager.getInstance().show(com.jumper.managers.ScreenManager.CurrentScreen.MENU);
    }

    @Override
    public void dispose() {
        super.dispose();
        com.jumper.managers.ScreenManager.getInstance().dispose();
    }
}
