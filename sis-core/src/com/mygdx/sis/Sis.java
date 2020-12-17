package com.mygdx.sis;

import com.badlogic.gdx.ApplicationAdapter;
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
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Vector2;

public class Sis extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	BitmapFont font;
	Sprite sprite;
	TextInputListener listener;
	Texture kropka, glowa;
	private Array<Vector2>position;
	private Vector2 kropkaPosition;
	private float timer=0.1f;
	private int num =3;
	private boolean u,d,r=true,l;
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		img = new Texture("Snake_logo.png");
		//logo = new Texture("SneakIntoScience_projekt_2.png");
		font = new BitmapFont();
		font.setColor(Color.VIOLET);
		sprite = new Sprite(img);
		sprite.setPosition(0, 0);
		listener = new MyTextInputListener();
		Gdx.input.getTextInput(listener,"Witaj w grze!",null,"Twoje imie");;
		glowa = new Texture("glowa.png");
		kropka = new Texture("kropka.png");
		position = new Array<Vector2>();
		for (int i=0;i<num;i++) {
			
			position.add(new Vector2(50+i*10, 50));
		}
		kropkaPosition = new Vector2(100,100);
		
		
	}
	
	public class MyTextInputListener implements TextInputListener {
		   public void input (String text) {
		   }

		   public void canceled () {
		   }
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
			kropkaPosition.x=x;
			kropkaPosition.y=y;
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
		batch.end();
		batch.begin();
		sprite.draw(batch);
		batch.end();
		
		batch.begin();
		for(int i=0; i<position.size;i++ ) {
			
			batch.draw(glowa, position.get(i).x, position.get(i).y);
		}
		batch.draw(kropka, kropkaPosition.x, kropkaPosition.y);
		batch.end();
		update(Gdx.graphics.getDeltaTime());
		input();
		checkDot();
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		
	}
}
