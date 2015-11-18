package com.jumper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

/**
 * Created by Roma-Alisa on 08/11/15.
 */
public class GameDialog extends Dialog{

    private Skin skin;
    private WindowStyle windowStyle;
    private TextButtonStyle buttonStyle;
    private TextureAtlas atlas;
    private BitmapFont whiteFont;

    {
        atlas = new TextureAtlas(Gdx.files.internal("button/button.pack"));
        skin = new Skin(atlas);
        whiteFont = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
        buttonStyle = new TextButtonStyle();

        buttonStyle.up = skin.getDrawable("soundOff");
        buttonStyle.down = skin.getDrawable("soundOff");
        buttonStyle.checked = skin.getDrawable("soundOff");
        buttonStyle.pressedOffsetX = 1;
        buttonStyle.pressedOffsetY = -1;
        buttonStyle.font = whiteFont;
    }


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
