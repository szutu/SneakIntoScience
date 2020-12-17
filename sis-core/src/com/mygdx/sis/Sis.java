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
//import com.badlogic.gdx.Input;
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
	private void movement() {
		
		
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
		batch.end();
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
