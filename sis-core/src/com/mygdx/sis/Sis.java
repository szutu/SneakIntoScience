package com.mygdx.sis;

//import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g3d.particles.ResourceData.AssetData;
import com.badlogic.gdx.graphics.g2d.BitmapFont; //
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Input.*;
//import com.badlogic.gdx.Input.Keys; 
//import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.sis.screens.GameScreen;
import com.badlogic.gdx.math.Vector2;

public class Sis extends Game { //akronim nazwy SneakIntoScience, glowna klasa gry 
	//zamiana extends ApplicationAdapter na Game
	long startTime = TimeUtils.millis();
	long elapsedTime = TimeUtils.timeSinceMillis(startTime);
	SpriteBatch batch;
	Texture img;
	BitmapFont font;
	Sprite sprite;
	//TextInputListener listener;
	Texture kropka, glowa,kropka2;
	private Array<Vector2>position;
	private Vector2 kropkaPosition;
	private Vector2 kropka2Position; //
	private float timer=0.1f;
	private int num =3;
	private boolean u,d,r=true,l;
	private int points =-1;
	public final static String Screen_name = "SneakIntoScience";
	private boolean paused;
	public static float width = 600;
	public static float height = 600;
	@Override
	public void create () {
		this.setScreen(new GameScreen(this));
		batch = new SpriteBatch();
		img = new Texture("Snake_logo.png");
		//logo = new Texture("SneakIntoScience_projekt_2.png");
		font = new BitmapFont();
		font.setColor(Color.VIOLET);
		sprite = new Sprite(img);
		sprite.setPosition(0, 0);
		//listener = new MyTextInputListener();
		//Gdx.input.getTextInput(listener,"Witaj w grze!",null,"Twoje imie");;
		glowa = new Texture("glowa.png");
		kropka = new Texture("kropka.png");
		kropka2 = new Texture("kropka2.png");
		position = new Array<Vector2>();
		for (int i=0;i<num;i++) {
			
			position.add(new Vector2(50+i*10, 50));
		}

			kropkaPosition = new Vector2(250,250);
			kropka2Position = new Vector2(50,50);

		
		
		
	}
	
	
	
	private void update(float delta) {
		timer -=delta;
		if(timer<=0 ) {
			timer=0.1f;
			movement();
		}
		
	}
	private void checkDot() {
		
		if(position.get(0).x==kropkaPosition.x&&position.get(0).y==kropkaPosition.y) {
			int x=(int)(Math.random()*50)*10;
			int y=(int)(Math.random()*50)*10;
			int x2=(int)(Math.random()*50)*10;
			int y2=(int)(Math.random()*50)*10;
			kropkaPosition.x=x;
			kropkaPosition.y=y;
			kropka2Position.x=x2;
			kropka2Position.y=y2;
			points++;
			position.add(new Vector2(position.get(position.size-1).x,position.get(position.size-1).y));
	
			
		}
		if(position.get(0).x==kropka2Position.x&&position.get(0).y==kropka2Position.y) {
			int x2=(int)(Math.random()*50)*10;
			int y2=(int)(Math.random()*50)*10;
			int x=(int)(Math.random()*50)*10;
			int y=(int)(Math.random()*50)*10;
			kropkaPosition.x=x;
			kropkaPosition.y=y;
			kropka2Position.x=x2;
			kropka2Position.y=y2;
			points++;
			position.add(new Vector2(position.get(position.size-1).x,position.get(position.size-1).y));
			
		}
		
	}
	private void movement() {
		
		for(int i=position.size-1;i>0;i--) {
			position.get(i).x=position.get(i-1).x;
			position.get(i).y=position.get(i-1).y;
		}
		
		if(u)
		{
			position.get(0).y+=10;
		}
		if(d){
			position.get(0).y-=10;
		}
		if(r){
			position.get(0).x+=10;
		}
		if(l){
			position.get(0).x-=10;
		}
	}
	
	private void input() {
		if(Gdx.input.isKeyJustPressed(Keys.UP)) {
			u=true;
			d=false;
			r=false;
			l=false;
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			u=false;
			d=true;
			r=false;
			l=false;
		}
		if(Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			u=false;
			d=false;
			r=false;
			l=true;
		}
		if(Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			u=false;
			d=false;
			r=true;
			l=false;
		}
	}
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
      

		batch.begin();
		font.draw(batch, "Witaj w grze SneakIntoScience", 280, 460);
		font.draw(batch, "Punkty: "+points, 280, 500);

		batch.end();
		batch.begin();
		sprite.draw(batch);
		batch.end();
		
		batch.begin();
		for(int i=0; i<position.size;i++ ) {
			
			batch.draw(glowa, position.get(i).x, position.get(i).y);
		}
		batch.draw(kropka, kropkaPosition.x, kropkaPosition.y);
		batch.draw(kropka2, kropka2Position.x, kropka2Position.y);
		font.draw(batch,"Uplynelo: "+elapsedTime, 220, 480);
		batch.end();
		update(Gdx.graphics.getDeltaTime());
		input();
		checkDot();
		
	}
	
	// getters and setters
	@Override
	public void dispose () {
		batch.dispose();
		
	}
	public boolean isPaused() {
		return paused;
	}
	public void setPaused(boolean paused) {
		this.paused = paused;
	}
}
