package com.jumper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Roma-Alisa on 08/11/15.
 */
public class GameDialog extends Dialog{


    public GameDialog(String title, Skin skin) {
        super(title, skin);
    }

    private enum ButtonType{
        YES, NO;
    }

    public GameDialog(String title, Skin skin, String windowStyleName) {
        super(title, skin, windowStyleName);
    }

    public GameDialog(String title, WindowStyle windowStyle) {
        super(title, windowStyle);
    }

    {
        text("Do you really want to leave?");
        button("Yes",ButtonType.YES);
        button("No",ButtonType.NO);
    }

    //is called when dialog button is clicked
    @Override
    protected void result(Object object) {

        switch ((ButtonType)object){
            case YES:
                Gdx.app.exit();
                break;
            case NO:
                break;
            default:
                break;
        }

        super.result(object);
    }
}
