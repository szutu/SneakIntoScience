package com.mygdx.sis;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.sis.screens.GameScreen;
import com.mygdx.sis.screens.MenuScreen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.Input.*;
//import com.badlogic.gdx.Input.*;
public class Menu extends Game {
	private Stage stage;
	private boolean m,a,h;
	//long startTime = TimeUtils.millis();
	//long startTime = System.currentTimeMillis();
	//long elapsedTime = TimeUtils.timeSinceMillis(startTime);
	//long elapsedtime ;
    private Texture myTexture;
  //  FileHandle handle = Gdx.files.local("pytania.txt"); //
  //  String text = handle.readString();					//
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;
    private ImageButton button;
    public final static String math = "Matematyka:  Wcisnij \"m\"" ;
    public String quest ;
    public final static String ang = "Angielski:       Wcisnij \"a\"" ;
    public final static String his = "Historia:         Wcisnij \"h\"" ;
    private float timer=0.1f;
	public final static String Screen_name = "Menu";
	public static float width = 600;
	public static float height = 600;
    public SpriteBatch batch;
    //ShapeRenderer shapeRenderer;
    BitmapFont font;
    Texture img;
    TextInputListener listener;
  
	private void update(float delta) {  //CZY POTRZEBNE? WYWOŁANIE W render();
		timer -=delta;
		if(timer<=0 ) {
			timer=0.1f;
		}
	}
	private void input() {
		if(Gdx.input.isKeyJustPressed(Keys.M)) {
			m=true;
			a=false;
			h=false;
		}
		if(Gdx.input.isKeyJustPressed(Keys.A)) {
			m=false;
			a=true;
			h=false;
		}
		if(Gdx.input.isKeyJustPressed(Keys.H)) {
			m=false;
			a=false;
			h=true;
		}
	}
private void wybor() {
		
	
		if(m)
		{
			
		}
		if(a){
			
		}
		if(h){
			
		}
		
	}
    @Override
    public void create () {
    	FileHandle file = Gdx.files.internal("data/pytania.txt");
    	quest = file.readString();
    //	Gdx.app.log("Menu",file.readString());
    	this.setScreen(new MenuScreen(this));
    	listener = new MyTextInputListener();
        batch = new SpriteBatch();
        //shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
      // setScreen(new MenuScreen(this));
        img = new Texture("Snake_logo.png");
    	Gdx.input.getTextInput(listener,"Witaj w grze!",null,"Twoje imie");;
    	
    /*   myTexture = new Texture(Gdx.files.internal("Snake_logo.png"));
         myTextureRegion = new TextureRegion(myTexture);
         myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
         button = new ImageButton(myTexRegionDrawable); //Set the button up

         stage = new Stage(new ScreenViewport()); //Set up a stage for the ui
         stage.addActor(button); //Add the button to the stage to perform rendering and take input.
       Gdx.input.setInputProcessor(stage); //Start taking input from the ui
    */     
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
		//elapsedtime=(long) ((System.nanoTime()-startTime)/1000000000.0f);
		batch.begin();
		font.draw(batch, "Witaj w MENU, wybierz dzial: ", 220, 580);
		font.draw(batch,math , 220, 560);
		font.draw(batch,ang, 220, 540);
		font.draw(batch,his, 220, 520);
		font.draw(batch,"wartosc h: "+quest, 220, 500);
		//font.draw(batch,"Uplynelo: "+elapsedtime, 220, 480);
	//	batch.draw(img, 80,20);
		batch.end();
	//	stage.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
		//stage.draw(); //Draw the ui
		update(Gdx.graphics.getDeltaTime()); //CZY POTRZEBNE?
    	}
 

    @Override
    public void dispose () {
        batch.dispose();
       // shapeRenderer.dispose();
        font.dispose();
    }
    
    
}