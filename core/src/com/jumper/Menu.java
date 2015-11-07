package com.jumper;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


/**
 * Created by Roma-Alisa on 20/10/15.
 */
public class Menu implements Screen {

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton buttonPlay, buttonExit, buttonCoolStaff, buttonSettings, buttonMoreGames;
    private BitmapFont white,black;
    private TextButton.TextButtonStyle textButtonStyle;
    private Menu context;

    public Menu(){
        context = this;
    }

    @Override
    public void show() {


        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
/*        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(new InputController());
        Gdx.input.setInputProcessor(multiplexer);*/

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
        textButtonStyle.font = white;

        buttonPlay = new TextButton("Start new GAME",textButtonStyle);
        //buttonPlay.setPosition(Constants.SCREEN_PIXELS_SIZE_WIDTH * (.4f), Constants.SCREEN_PIXELS_SIZE_HEIGHT * (.9f));
        buttonPlay.setPosition(0, Constants.SCREEN_PIXELS_SIZE_HEIGHT * (.9f));
        buttonPlay.setSize(Constants.SCREEN_PIXELS_SIZE_WIDTH * (.1f), Constants.SCREEN_PIXELS_SIZE_HEIGHT * (.1f));
        buttonPlay.pad(20);
        buttonPlay.addAction(Actions.moveTo(Constants.SCREEN_PIXELS_SIZE_WIDTH * (.4f), Constants.SCREEN_PIXELS_SIZE_HEIGHT * (.9f), .5f));
        buttonPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //((Game) Gdx.app.getApplicationListener()).setScreen(new GameJumper(context));
                ScreenManager.getInstance().show(ScreenManager.CurrentScreen.GAME);
            }
        });

        buttonExit = new TextButton("Exit",textButtonStyle);
        //buttonExit.setPosition(Constants.SCREEN_PIXELS_SIZE_WIDTH * (.4f), Constants.SCREEN_PIXELS_SIZE_HEIGHT * (.7f));
        buttonExit.setPosition(Constants.SCREEN_PIXELS_SIZE_WIDTH, Constants.SCREEN_PIXELS_SIZE_HEIGHT * (.7f));
        buttonExit.setSize(Constants.SCREEN_PIXELS_SIZE_WIDTH * (.1f), Constants.SCREEN_PIXELS_SIZE_HEIGHT * (.1f));
        buttonExit.pad(20);
        buttonExit.addAction(Actions.moveTo(Constants.SCREEN_PIXELS_SIZE_WIDTH * (.4f), Constants.SCREEN_PIXELS_SIZE_HEIGHT * (.7f), .5f));
        buttonExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        buttonCoolStaff = new TextButton("Cool Staff",textButtonStyle);
        //buttonCoolStaff.setPosition(Constants.SCREEN_PIXELS_SIZE_WIDTH * (.4f), Constants.SCREEN_PIXELS_SIZE_HEIGHT * (.5f));
        buttonCoolStaff.setPosition(0, Constants.SCREEN_PIXELS_SIZE_HEIGHT * (.5f));
        buttonCoolStaff.setSize(Constants.SCREEN_PIXELS_SIZE_WIDTH * (.1f), Constants.SCREEN_PIXELS_SIZE_HEIGHT * (.1f));
        buttonCoolStaff.pad(20);
        buttonCoolStaff.addAction(Actions.moveTo(Constants.SCREEN_PIXELS_SIZE_WIDTH * (.4f), Constants.SCREEN_PIXELS_SIZE_HEIGHT * (.5f), .5f));
        buttonCoolStaff.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new CoolStaff());
            }
        });

        buttonSettings = new TextButton("settings",textButtonStyle);
        //buttonSettings.setPosition(Constants.SCREEN_PIXELS_SIZE_WIDTH * (.4f), Constants.SCREEN_PIXELS_SIZE_HEIGHT * (.3f));
        buttonSettings.setPosition(Constants.SCREEN_PIXELS_SIZE_WIDTH, Constants.SCREEN_PIXELS_SIZE_HEIGHT * (.3f));
        buttonSettings.setSize(Constants.SCREEN_PIXELS_SIZE_WIDTH * (.1f), Constants.SCREEN_PIXELS_SIZE_HEIGHT * (.1f));
        buttonSettings.pad(20);
        buttonSettings.addAction(Actions.moveTo(Constants.SCREEN_PIXELS_SIZE_WIDTH * (.4f), Constants.SCREEN_PIXELS_SIZE_HEIGHT * (.3f), .5f));
        buttonSettings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        buttonMoreGames = new TextButton("More fun",textButtonStyle);
        //buttonMoreGames.setPosition(Constants.SCREEN_PIXELS_SIZE_WIDTH * (.4f), Constants.SCREEN_PIXELS_SIZE_HEIGHT * (.1f));
        buttonMoreGames.setPosition(0, Constants.SCREEN_PIXELS_SIZE_HEIGHT * (.1f));
        buttonMoreGames.setSize(Constants.SCREEN_PIXELS_SIZE_WIDTH * (.1f), Constants.SCREEN_PIXELS_SIZE_HEIGHT * (.1f));
        buttonMoreGames.pad(20);
        buttonMoreGames.addAction(Actions.moveTo(Constants.SCREEN_PIXELS_SIZE_WIDTH * (.4f), Constants.SCREEN_PIXELS_SIZE_HEIGHT * (.1f), .5f));
        buttonMoreGames.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MoreGames());
            }
        });

        stage.addActor(buttonPlay);
        stage.addActor(buttonExit);
        stage.addActor(buttonCoolStaff);
        stage.addActor(buttonSettings);
        stage.addActor(buttonMoreGames);

/*        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(new InputController());
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);*/
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
