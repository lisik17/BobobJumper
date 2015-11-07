package com.jumper;

import com.badlogic.gdx.Game;

/**
 * Created by Roma-Alisa on 21/10/15.
 */
public class MainScreen extends Game {

    @Override
    public void create() {
        Resources.setBasicScreen(this);
        this.setScreen(new Menu());

    }
}
