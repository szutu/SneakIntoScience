package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Menu;
import com.mygdx.game.Sis;
/**
 *  this class is a screen which render whole menu 
 * @author Jakub Szutenberg 172434 G3
 */
public class MenuScreen implements Screen {
	protected Stage stage;
	private OrthographicCamera camera;
	protected SpriteBatch spriteBatch;
	public SpriteBatch batch;
	public BitmapFont font;
	Texture img;
	//Classes
	public Sis game;             
	public Menu menu;
	public MenuScreen(Menu menu) {
		this.menu = menu;
		createCamera();
		stage = new Stage(new StretchViewport(Menu.WIDHT,Menu.HEIGHT,camera)); 
		spriteBatch = new SpriteBatch();									 
		Gdx.input.setInputProcessor(stage);									 
	}
	
	private void createCamera() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false,600,600);
		camera.update();
	}
	/**
	 * this method create objects used in class MenuScreen
	 */
	public void create() {
		batch = new SpriteBatch();
	}
	/**
	 * this method render methods imported from menu class
	 */
	@Override
	public void render(float delta) {
	    Gdx.gl.glClearColor(1, 1, 1, 0);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    menu.input();
	    menu.screenSwitch();
		update(Gdx.graphics.getDeltaTime());
		update(delta);;
		camera.update();
		spriteBatch.setProjectionMatrix(camera.combined);
}
	//Required methods to implement screen
	private void update(float delta) {
		stage.act(delta);	
	}
	@Override
	public void show() {}
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
}
