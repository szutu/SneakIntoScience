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

public class GameScreen implements Screen{
	//obiekty przekopiowane z klasy Sis, inaczej nie działolo przesyłanie funkcji 
	long startTime = TimeUtils.millis();
	long elapsedTime = TimeUtils.timeSinceMillis(startTime);
	protected boolean isGameOver=false;
	public Texture background;
	public Texture win;
	public Sound getPoint;
	public Sound lostGame;
	public Texture kropka;
	public Texture glowa;
	public Texture endGame;
	public Texture kropka2;
	public Array<Vector2>position;
	protected Vector2 kropkaPosition;
	protected Vector2 kropka2Position; 
	public float timer=0.1f;
	public int num =3;// początkowa długość węża
	public boolean isLimit;
	public boolean u,d,r=true,l;
	protected int points =0;
	public int questNum =1;
	public int prevQ =1;
	public final static String Screen_name = "SneakIntoScience";
	public boolean paused;
	public static float width = 600;
	public static float height = 600;
	public float time =21;
	public boolean timeOut;
	public boolean played =false;
	//protected String[] pyt = {"Ile to 5+10","ile to 240/12","ile to sqrt(900)","ile to (10^4)^(1/2)"}; //stare pytania 
	int[] odpPoprawna = {1,2,1,2}; //ktora odp poprawna 1 lub 2 dla "pyt" o tym samym indeksie
	protected String odp1[] = {"15","25","30","1000"};
	int[] odpPoprawnaA = {1,2,1,2,1,2,1,2,1,2,1}; //testowo
	protected String odpA1[] = {"good","bad","good","bad","good","bad","good","bad","good","bad","good"}; //testowo
	protected String odpA2[] = {"bad","good","bad","good","bad","good","bad","good","bad","good","bad"};  //testowo
	protected String odp2[] = {"14","20","35","100",};
	protected Stage stage;
	private OrthographicCamera camera;
	protected SpriteBatch spriteBatch;
	Sprite sprite;
	SpriteBatch batch;
	public BitmapFont font;
	//Screens
	public GameScreen gamescreen;
	//----------------------------------------------------- ^ kopia z Sis na próbe
	//Classes
	protected Menu menu;
	public Sis sis;
	
	public GameScreen(Sis sis) {
		this.sis = sis;
		createCamera();
		stage = new Stage(new StretchViewport(Sis.width,Sis.height,camera)); //
		spriteBatch = new SpriteBatch();									 //
		Gdx.input.setInputProcessor(stage);				
	}
	
	private void createCamera() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false,600,600);
		camera.update();
	}
	
	private void create() {
		sis=new Sis(); //definicja instancji obiektu klasy sis 
		sis.create();
	}
	
	//@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//create();
		sis.update(Gdx.graphics.getDeltaTime());
		sis.showGame();
		sis.input();
		sis.checkDot();
	//	setPaused(isGameOver); //to moze kraszowac gre, ostroznie
		sis.isTimeOut();
		sis.playSounds(sis.questNum);	
	camera.update();
	spriteBatch.setProjectionMatrix(camera.combined);
	}
	
	//puste metody ktore musza istniec zeby implementowalo Screen
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
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	

}
