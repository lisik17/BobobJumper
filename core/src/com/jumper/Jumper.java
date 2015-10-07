package com.jumper;


import com.badlogic.gdx.Gdx;

public class Jumper extends BasicScreen {

	//private Stair stair;
	private StairsFactory stairsFactory;
	private CoinsFactory coinsFactory;
	private Player player;
	private Wall wall;
	private Background background;
	private CollisionManager collisionManager;
	private AnimationManager animation;
	private Font font;

	@Override
	public void render() {

		super.render();
		//background.act(10f);
		stairsFactory.act(10f);
		coinsFactory.act(10f);
		wall.act(10f);
		animation.act(10f);
		font.act();

		Camera.moveDown(camera);
	}

	@Override
	public void create() {
		super.create();

		player = new Player();
		wall = new Wall();
		stairsFactory = new StairsFactory();
		coinsFactory = new CoinsFactory();
		background = new Background();
		collisionManager = new CollisionManager();
		animation = new AnimationManager();
		font = new Font();

		font.setStringMessage("hello");


		Gdx.input.setInputProcessor(new InputController(player.getBodyPlayer(),camera));
	}

	@Override
	public void dispose() {
		player.dispose();
		stairsFactory.dispose();
		coinsFactory.dispose();
		wall.dispose();
		background.dispose();
		animation.dispose();

		super.dispose();
	}
}
