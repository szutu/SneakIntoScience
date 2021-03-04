package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Menu;
import com.mygdx.game.Sis;
/**
 * this class is a screen which render whole game 
 * @author Jakub Szutenberg 172434 G3
 *
 */
public class GameScreen implements Screen {
	
	protected Stage stage;
	private OrthographicCamera camera;
	protected SpriteBatch spriteBatch;
	Sprite sprite;
	SpriteBatch batch;
	public BitmapFont font;
	//Screens
	public GameScreen gamescreen;
	//Classes
	protected Menu menu;
	public Sis sis;
	
	public GameScreen(Sis sis) {
		this.sis = sis;
		createCamera();
		stage = new Stage(new StretchViewport(Sis.WIDTH,Sis.HEIGHT,camera)); //
		spriteBatch = new SpriteBatch();									 //
		Gdx.input.setInputProcessor(stage);				
	}
	
	private void createCamera() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false,600,600);
		camera.update();
	}
	/**
	 * this method create (and call render) instance of class Sis
	 */
	 public void create() {
		sis=new Sis(); 
		sis.create();
		sis.render();
	}
	/**
	 * this method render methods imported from Sis class
	 */
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sis.update(Gdx.graphics.getDeltaTime());
		sis.showGame();
		sis.input();
		sis.checkDot();
		sis.isTimeOut();
		sis.playSounds(sis.questNumber);			
		camera.update();
		spriteBatch.setProjectionMatrix(camera.combined);
	}
	
	//Required methods to implement screen
	@Override
	public void resize(int width, int height) {}
	@Override
	public void pause() {}
	@Override
	public void resume() {}
	@Override
	public void hide() {}
	@Override
	public void dispose() {}
	@Override
	public void show() {}
}
