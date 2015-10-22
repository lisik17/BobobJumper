package com.jumper;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.gushikustudios.rube.RubeScene;
import com.gushikustudios.rube.loader.RubeSceneAsyncLoader;
import com.gushikustudios.rube.loader.RubeSceneLoader;


public class GameJumper extends BasicScreen implements Screen{

	//private Stair stair;
	private StairsFactory stairsFactory;
	private SpiralFactory spiralFactory;
	private CoinsFactory coinsFactory;
	private Player player;
	private Wall wall;
	private Background background;
	private CollisionManager collisionManager;
	private Earth earth;
	//private AnimationManager animation;
	private Font font;


	RubeSceneLoader loader;
	RubeScene scene;
	private AssetManager mAssetManager;

	@Override
	//public void create() {
	public void show(){
		super.create();

		player = new Player();
		wall = new Wall();

		stairsFactory = new StairsFactory();
		spiralFactory = new SpiralFactory();
		coinsFactory = new CoinsFactory();

		Resources.setSpiralFactory(spiralFactory);

		background = new Background();
		collisionManager = new CollisionManager();
		//animation = new AnimationManager();
		font = new Font();

		earth = new Earth();


		Gdx.input.setInputProcessor(new InputController(player.getBodyPlayer(), camera));

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
		background.act(10f);
		stairsFactory.act(10f);
		spiralFactory.act(10f);
		coinsFactory.act(10f);
		wall.act(10f);
		player.act(10f);
		earth.act(10f);
		//animation.act(10f);
		//font.act();

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
		spiralFactory.dispose();
		wall.dispose();
		background.dispose();
		earth.dispose();
		//animation.dispose();

		super.dispose();
	}
}
