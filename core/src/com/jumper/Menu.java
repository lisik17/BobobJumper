package com.jumper;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.awt.Label;


/**
 * Created by Roma-Alisa on 20/10/15.
 */
public class Menu implements Screen {

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton bottonPlay, buttonExit;
    private BitmapFont white,black;
    private Label heading;
    private TextButton.TextButtonStyle textButtonStyle;
    private final MainScreen game;

    public Menu(MainScreen game){
        this.game =game;
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas(Gdx.files.internal("button/button.pack"));
        skin = new Skin(atlas);

        white = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
        black = new BitmapFont(Gdx.files.internal("font/black.fnt"),false);

        table = new Table(skin);
        table.setBounds(0,0,Constants.SCREEN_PIXELS_SIZE_WIDTH,Constants.SCREEN_PIXELS_SIZE_HEIGHT);

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("soundOff");
        textButtonStyle.down = skin.getDrawable("soundOff");
        textButtonStyle.checked = skin.getDrawable("soundOff");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = black;

        buttonExit = new TextButton("Start new GAME",textButtonStyle);
        buttonExit.pad(20);


        Gdx.app.log("app", "!!");

        buttonExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(new GameScreen(game));
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameJumper());
                //this.setS
                Gdx.app.log("app", "here!!");

                //game.setScreen(new GameJumper());

            }
        });


        table.add(buttonExit);
        stage.addActor(table);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        black.dispose();
        white.dispose();
        skin.dispose();
        atlas.dispose();
    }
}
