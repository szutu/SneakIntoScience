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

public class MenuScreen implements Screen {

	protected Stage stage;
	private OrthographicCamera camera;
	protected SpriteBatch spriteBatch;
	public SpriteBatch batch;
	public BitmapFont font;
	Texture img;
	Texture bad;
	//classes
	protected Sis game;             
	public Menu menu;
	
	public MenuScreen(Menu menu) {
		this.menu = menu;
		createCamera();
		stage = new Stage(new StretchViewport(Menu.width,Menu.height,camera)); 
		spriteBatch = new SpriteBatch();									 
		Gdx.input.setInputProcessor(stage);									 
	}
	
	private void createCamera() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false,600,600);
		camera.update();
		
	}

	
	public void create() {
		batch = new SpriteBatch();
		menu.create();
	}
	@Override
	public void render(float delta) {
	    Gdx.gl.glClearColor(1, 1, 1, 0);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    menu.input();
	    menu.screenSwitch();
		update(Gdx.graphics.getDeltaTime()); //CZY POTRZEBNE?		
		update(delta);;
		camera.update();
		spriteBatch.setProjectionMatrix(camera.combined);
}
	
	

	
	//--------puste metody aby implenetowalo screen----------------------
	private void update(float delta) {
		stage.act(delta);
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	//-------------------Stare metody nieyuwzywane obecnie
	//----------------------------------------------------------------
/*	private void clearScreen() {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 1, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	private void init() {
		bad = new Texture("badlogic.jpg"); 
		font = new BitmapFont();
	}
*/


}