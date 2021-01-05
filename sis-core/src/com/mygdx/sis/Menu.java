package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MenuScreen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;


public class Menu extends Game {	
	Screen MenuScreen =getScreen();
	public String sciezka ="data/pytaniaA.txt"; // domyslnie wybrany dzial 
	public boolean m,a,h;
	public boolean menuDisplay =false;
    Texture go;
    Texture bg;
    Texture welcome;
    Texture instruction;
    long delay =2000L; //opoznienie dla czekania
    long lifeTime; //czas zycia wyswietlanego obiektu
    protected Sprite sprite;
    public final static String math = "Matematyka:  Wcisnij \"m\"" ;
    public String quests=" " ;
    public String kat;
    public String name ="";
    public final static String ang = "Angielski:       Wcisnij \"a\"" ;
    public final static String his = "Historia:         Wcisnij \"h\"" ;
    private float timer=0.1f;
	public final static String Screen_name = "Menu";
	public static float width = 600;
	public static float height = 600;
    public SpriteBatch batch;
    protected boolean startGame;
    public boolean instrDisplay;
    public boolean goToSis;
    public String currentquest[]; //static dodane do proby przesylu   
    public BitmapFont font;
    Texture img;
    TextInputListener listener;
    double time ;
   //Screens
    public GameScreen gamescreen; //game screen tworzenie obiektu
    public MenuScreen menuscreen;
    //Classes
    protected Sis game;
    
	public void update(float delta) { 
		timer -=delta;
		if(timer<=0 ) {
			timer=0.1f;
			// wybor();//odpowiednik funkcji movement w klasie a			
		}
	}
	
	private void showWelcome() {
		
		welcome = new Texture("welcome_page.png");
		sprite = new Sprite(welcome);
	    	batch.begin();
	        sprite.draw(batch);
	        batch.end();         
	}
	
	public void showInstruction() {
		
		instruction = new Texture("instrukcja.png");
		sprite = new Sprite(instruction);   
	    	batch.begin();
	        sprite.draw(batch);
	        batch.end(); 	
	}
	
	public void input() {
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
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			startGame = true;			//do przejscia ekranow
			goToSis=true;
		}
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			instrDisplay = true;		//do przejscia ekranow
			goToSis=false;
		}
		if(Gdx.input.isKeyJustPressed(Keys.BACKSPACE)) {
			instrDisplay=false;		   	//powrot z instrukcji do menu
			startGame=true;
			
		}					
	}
	
public void wybor() {
		
	if(m || a || h) {
		
	
		if(m)
		{
		 kat ="Wybrałeś kategorie: matematyka";
		 sciezka="data/pytaniaM.txt";
		 go = new Texture("math.png");
		 drawChapter();
		}
		if(a){
			 kat ="Wybrałeś kategorie: angielski";
			 sciezka="data/pytaniaA.txt";
			 go = new Texture("english.gif");
			 drawChapter();
		}
		if(h){
			 kat ="Wybrałeś kategorie: historia";
			 sciezka="data/pytaniaH.txt";
			 go = new Texture("history.gif");
			 drawChapter();	
		}
	}
	
	else {
		kat=" jeszcze nie wybrano kategorii";	
	}		
	}

    @Override
    public void create () {
    	lifeTime = System.currentTimeMillis(); //do odliczania czasu zycia,obecnie nieuwzywane
    	batch = new SpriteBatch();
    	font = new BitmapFont();
        img = new Texture("Snake_logo.png");
        go = new Texture("transparent.gif"); //domyslna wartosc 
        bg = new Texture("bg_menu.png");   
        menuscreen=new MenuScreen(this);
        game=new Sis();
        gamescreen=new GameScreen(game);
    	this.setScreen(menuscreen); //wywolanie screenu MenuScreen
    	nameInput();
    	game.create();
    	game.render();
    }
    
    private void nameInput() {
    	listener = new MyTextInputListener();
    	Gdx.input.getTextInput(listener,"Witaj w grze!",null,"Wpisz Swoje imie :)");
    }
    public class MyTextInputListener implements TextInputListener {
		   public void input (String text) {
			   name= text;
		   }

		   public void canceled () {
			   return;
		   }
		}
    
    public void category() {	
    	FileHandle file = Gdx.files.internal(sciezka);
 	   	quests = file.readString();
 	   	currentquest = quests.split(System.getProperty("line.separator"));
    }
     
   public void render() {
	 	super.render(); //wywołuje przywołany tutaj: "this.setScreen(new MenuScreen(this));" screen
	 	
/*		poprzednie wywolanie rendera gdy nie bylo stworzonych screenow 
		input();
		//input2();
		screenSwitch();
		update(Gdx.graphics.getDeltaTime()); //CZY POTRZEBNE?		   
*/
    	}
   
   public void screenSwitch() {
		if(!startGame) {
			showWelcome();		
			}
		
			if(startGame&&!instrDisplay) {
			showMenu();	
			drawChapter();
			wybor();	
			}
			
			if (instrDisplay) {
			showInstruction();
			input();
			}
			if(instrDisplay&&goToSis) {
			this.setScreen(gamescreen);
			};
   }
   
   private void drawBg() {
	    batch.begin();
	    sprite = new Sprite(bg);
	    sprite.setPosition(-30, 0);
	    sprite.draw(batch);
	    batch.end(); //tlo, wtedy glClear czyscimy
   }
   
   public void showMenu() {	   
	   	drawBg();
		wybor();
		category();
		showText();	
   }
   
   private void showText() {
		batch.begin();
		font.draw(batch, "Witaj w MENU, wybierz dzial: ", 220, 535);
		font.draw(batch,math , 220, 515);
		font.draw(batch,ang, 220, 495);
		font.draw(batch,his, 220, 475);
		font.draw(batch,kat, 220, 455);
		font.draw(batch,"Witaj w grze: "+name, 220, 435);
		//font.draw(batch,"pytania to "+quests, 220, 460); 					 stare testy
 		//font.draw(batch,"pytanie ale z pliku "+currentquest[6], 100, 415); stare testy
		if(a||h||m) {
		font.draw(batch,name+"! Wcisnij SPACE aby przejsc do gry", 220, 395);
		}
		batch.end();
   }
   
   public void drawChapter(){
	
	   sprite = new Sprite(go);
	   if(a) {							  // dla full screenów
		   sprite.scale((float) -0.8);    // -0.7
		   sprite.setPosition(-200,-200); // -200,-300
	   }
	   if(h) {
		   sprite.scale((float) -0.25 );    //0.5
		   sprite.setPosition(200,200);   //200,100
	   }
	   if(m) {
		   sprite.scale((float) -0.6 );    //brak 
		   sprite.setPosition(35,150);   // brak
	   }
		batch.begin();
		sprite.draw(batch);
		batch.end();
   }
   
    @Override
    public void dispose () {
        batch.dispose();
        font.dispose();
    }
    
    
}