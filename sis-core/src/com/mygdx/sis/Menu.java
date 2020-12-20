package com.mygdx.sis;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Input.TextInputListener;
//import com.badlogic.gdx.Input.*;
public class Menu extends Game {
	public final static String Screen_name = "Menu";
    SpriteBatch batch;
    //ShapeRenderer shapeRenderer;
    BitmapFont font;
    Texture img;
    TextInputListener listener;
    @Override
    public void create () {
    	listener = new MyTextInputListener();
        batch = new SpriteBatch();
        //shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
       // setScreen(new TitleScreen(this));
        img = new Texture("Snake_logo.png");
    	Gdx.input.getTextInput(listener,"Witaj w grze!",null,"Twoje imie");;
    }
    public class MyTextInputListener implements TextInputListener {
		   public void input (String text) {
		   }

		   public void canceled () {
		   }
		}
    public void render() {
    	Gdx.gl.glClearColor(1, 0, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
		batch.begin();
		font.draw(batch, "Witaj w MENU, wybierz dzial: ", 220, 580);
		batch.draw(img, 80,50);
		batch.end();
    	
    	
    }

    @Override
    public void dispose () {
        batch.dispose();
       // shapeRenderer.dispose();
        font.dispose();
    }
    
    
}