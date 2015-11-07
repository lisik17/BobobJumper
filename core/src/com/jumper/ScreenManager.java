package com.jumper;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.IntMap;

/**
 * Created by Roma-Alisa on 07/11/15.
 */
public class ScreenManager {
    private static ScreenManager instance;

    private Game game;
    private CurrentScreen currentScreen;

    private IntMap<com.badlogic.gdx.Screen> screens;

    public enum CurrentScreen {

        MENU {
            @Override
            protected com.badlogic.gdx.Screen getScreenInstance() {
               return new Menu();
            }
        },

        GAME {
            @Override
            protected com.badlogic.gdx.Screen getScreenInstance() {
                return new GameJumper(new Menu());
                //return null;
            }

        };

        protected abstract com.badlogic.gdx.Screen getScreenInstance();

    }

    private ScreenManager() {
        screens = new IntMap<com.badlogic.gdx.Screen>();
    }

    public static ScreenManager getInstance() {
        if (null == instance) {
            instance = new ScreenManager();
        }
        return instance;
    }

    public void initialize(Game game) {
        this.game = game;
    }

    public void show(CurrentScreen screen) {
        if (null == game) return;
        if (!screens.containsKey(screen.ordinal())) {
            screens.put(screen.ordinal(), screen.getScreenInstance());
        }
        currentScreen = screen;
        game.setScreen(screens.get(screen.ordinal()));
    }

    public CurrentScreen getCurrentScreen(){
        return  currentScreen;
    }

    public void dispose(CurrentScreen screen) {
        if (!screens.containsKey(screen.ordinal())) return;
        screens.remove(screen.ordinal()).dispose();
    }

    public void dispose() {
        //for (com.jumperScreen screen : screens.values()) {
        for (com.badlogic.gdx.Screen screen : screens.values() ) {
            screen.dispose();
        }
        screens.clear();
        instance = null;
    }
}
