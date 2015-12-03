package com.jumper.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gushikustudios.rube.RubeScene;
import com.gushikustudios.rube.loader.RubeSceneAsyncLoader;
import com.gushikustudios.rube.loader.RubeSceneLoader;
import com.jumper.Background;
import com.jumper.BasicScreen;
import com.jumper.Camera;
import com.jumper.Constants;
import com.jumper.Font;
import com.jumper.Player;
import com.jumper.Resources;
import com.jumper.effect.JumpEffect;
import com.jumper.entities.Wall;
import com.jumper.factories.CoinsFactory;
import com.jumper.factories.SnowBallFactory;
import com.jumper.factories.StairsFactory;
import com.jumper.managers.InputController;
import com.jumper.managers.SoundManager;


public class GameJumper extends BasicScreen implements Screen{

	//private Stair stair;
	private com.jumper.factories.StairsFactory stairsFactory;
	private com.jumper.factories.SpiralFactory spiralFactory;
	private com.jumper.factories.CoinsFactory coinsFactory;
	private SnowBallFactory snowBallFactory;

	private Player player;
	private com.jumper.entities.Wall wall;
	private Background background;
	private com.jumper.managers.CollisionManager collisionManager;
	private com.jumper.entities.Earth earth;
	private com.jumper.managers.SoundManager soundManager;
	private Font font;

	private ParticleEffect effect;
	private SpriteBatch batch;

	public static Sound sound;
	private JumpEffect jumpEffect;


	RubeSceneLoader loader;
	RubeScene scene;
	private AssetManager mAssetManager;

	public GameJumper() {
		//Gdx.input.setCatchMenuKey(true);
		//Resources.setMenuInstance(context);
	}

	@Override
	//public void create() {
	public void show(){
		super.create();

		//SoundManager.getInstance().playSound();

		player = new Player();
		wall = new Wall();
		Resources.setScore(0);

		stairsFactory = new StairsFactory();
		spiralFactory = new com.jumper.factories.SpiralFactory();
		coinsFactory = new CoinsFactory();
		snowBallFactory = new com.jumper.factories.SnowBallFactory();


		soundManager = com.jumper.managers.SoundManager.getInstance();

		Resources.setSpiralFactory(spiralFactory);
		Resources.setSnowBallFactory(snowBallFactory);

		background = new Background();
		collisionManager = new com.jumper.managers.CollisionManager();

		AssetManager assetManager = new AssetManager();
		jumpEffect = new JumpEffect();
		Resources.setJumpEffect(jumpEffect);

		SoundManager.loadSounds();

/*
		if(assetManager.isLoaded("sound/coin.mp3")) {
			sound = assetManager.get("sound/coin.mp3", Sound.class);
		}else{
			Gdx.app.log("app","not loaded yet");
		}
*/

/*		batch = new SpriteBatch();
		effect = new ParticleEffect();
		effect.load(Gdx.files.internal("effects/flame.p"), Gdx.files.internal("effects"));*/

		//Gdx.app.log("app", "x " + Resources.getPlayer().getBodyPlayer().getPosition().x * Constants.SCREEN_RATIO_WIDTH + Gdx.graphics.getWidth() / 2f);
		//Gdx.app.log("app", "y " + Resources.getPlayer().getBodyPlayer().getPosition().y * Constants.SCREEN_RATIO_HEIGHT + Gdx.graphics.getHeight()/ 2f);


/*		effect.setPosition(Resources.getPlayer().getBodyPlayer().getPosition().x * Constants.SCREEN_RATIO_WIDTH + Gdx.graphics.getWidth() / 2f,
				           Resources.getPlayer().getBodyPlayer().getPosition().y * Constants.SCREEN_RATIO_HEIGHT + Gdx.graphics.getHeight()/ 2f);
		effect.start();*/

		//animation = new AnimationManager();
		font = new Font();
		font.setStateGamePlay();
		Resources.setFont(font);

		earth = new com.jumper.entities.Earth();


		Gdx.input.setInputProcessor(new InputController());

		//loadRUBEscen();

	}

	private void loadRUBEscen() {
		loader = new RubeSceneLoader();
		scene = loader.loadScene(Gdx.files.internal("palm.json"));

		world = scene.getWorld();

		mAssetManager = new AssetManager();
		mAssetManager.setLoader(RubeScene.class, new RubeSceneAsyncLoader(new InternalFileHandleResolver()));
	}

	@Override
	public void render(float delta) {



		super.render();

		jumpEffect.act(delta);

/*		batch.begin();
		effect.setPosition(Resources.getPlayer().getBodyPlayer().getPosition().x * Constants.SCREEN_RATIO_WIDTH + Gdx.graphics.getWidth() / 2f,
				Resources.getPlayer().getBodyPlayer().getPosition().y * Constants.SCREEN_RATIO_HEIGHT + Gdx.graphics.getHeight() / 2f);
		effect.draw(batch,delta);
		batch.end();*/

		//background.act(10f);
		stairsFactory.act(10f);
		spiralFactory.act(10f);
		coinsFactory.act(10f);
		snowBallFactory.act(10f);
		wall.act(10f);
		player.act(10f);
		earth.act(10f);
		//animation.act(10f);
		font.act();

		//player.destroyPlayer();

		//Camera.moveDown(camera);
		Camera.act();

		Gdx.input.setCatchBackKey(true);



	}


/*	@Override
	public void show() {

	}*/



	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		player.dispose();

		stairsFactory.dispose();
		coinsFactory.dispose();
		snowBallFactory.dispose();
		spiralFactory.dispose();

		wall.dispose();

		if(null != effect) {
			effect.dispose();
		}
		//background.dispose();
		earth.dispose();
		//collisionManager.dispose();
		//animation.dispose();

		super.dispose();
	}
}
