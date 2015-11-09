package com.jumper;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Roma-Alisa on 08/11/15.
 */
public class GameDialog extends Dialog{


    public GameDialog(String title, Skin skin) {
        super(title, skin);
    }

    public GameDialog(String title, Skin skin, String windowStyleName) {
        super(title, skin, windowStyleName);
    }

    public GameDialog(String title, WindowStyle windowStyle) {
        super(title, windowStyle);
    }

    {
        text("Do you really want to leave?");
        button("Yes");
        button("No");
    }

    //is called when dialog button is clicked
    @Override
    protected void result(Object object) {
        super.result(object);
    }
}
