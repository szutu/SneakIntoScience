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

/**
 * this class contain Menu mechanics
 * @author Jakub Szutenberg 172434 G3
 * 
 */
public class Menu extends Game {	
    Screen MenuScreen =getScreen();
    public String sciezka ="data/pytaniaA.txt"; // default chapter
    public String pathAnswer1="data/odpA1.txt";
    public String pathAnswer2="data/odpA2.txt";
    public String path4="data/goodAnswerA.txt";
    public final static String math = "Matematyka:  Wcisnij \"m\"" ; 
    public final static String ang = "Angielski:       Wcisnij \"a\"" ;
    public final static String his = "Historia:         Wcisnij \"h\"" ;
    public String quests=" " ;
    public String chapter;
    public String name ="";
    public String currentquest[] ={"pyt1","pyt2"}; 
    public String answerArray1[];
    public String answerArray2[];
    public String goodAnswer[];
    public String answer1;
    public String answer2;
    public String correctAnswer;
    public boolean mathematic,english,history; 
    public boolean menuDisplay =false;
    protected boolean startGame;
    public boolean instrDisplay;
    public boolean goToSis;
    Texture chapterImage;
    Texture bg;
    Texture welcome;
    Texture instruction;
    Texture img;
    double time ;
    private float timer=0.1f;
    public static float WIDHT = 600;
    public static float HEIGHT = 600;
    public SpriteBatch batch;
    protected Sprite sprite;
    public BitmapFont font;
    TextInputListener listener;
    //Classes
    public Mygame mygame;
    public Sis game; 
   //Screens
    public GameScreen gamescreen; 
    public MenuScreen menuscreen;
    /**
     * this method update render()
     * @param delta: this is a value of time to be subtracted
     */
	public void update(float delta) { 
		timer -=delta;
		if(timer<=0 ) {
			timer=0.1f;
		}
	}
	/**
	 * this method draw welcome screen
	 */
	private void showWelcome() {	
		welcome = new Texture("welcome_page.png");
		sprite = new Sprite(welcome);
	    batch.begin();
	    sprite.draw(batch);
	    batch.end();         
	}
	/**
	 * this method draw instruction screen
	 */
	public void showInstruction() {
		instruction = new Texture("instrukcja.png");
		sprite = new Sprite(instruction);   
	  	batch.begin();
        sprite.draw(batch);
        batch.end(); 	
	}
	/**
	 * this method listen to user inputs and determine variables values depending on the input
	 */
	public void input() {
		if(Gdx.input.isKeyJustPressed(Keys.M)) {
			mathematic=true;
			english=false;
			history=false;		 
		}
		if(Gdx.input.isKeyJustPressed(Keys.A)) {
			mathematic=false;
			english=true;
			history=false;
		}
		if(Gdx.input.isKeyJustPressed(Keys.H)) {
			mathematic=false;
			english=false;
			history=true;
		}
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			startGame = true;			
			goToSis=true;
		}
		if((Gdx.input.isKeyJustPressed(Keys.SPACE))&&goToSis) {
			instrDisplay = true;	
			goToSis=false;
		}
		if(Gdx.input.isKeyJustPressed(Keys.BACKSPACE)) {
			instrDisplay=false;		   	
			startGame=true;
			instrDisplay=false; 
			goToSis=true;
		}					
	}
	/**
	 * this method set path to Q&A depending on input() values
	 */
public void select() {
	if(mathematic || english || history) {
		if(mathematic) 
		{
		 chapter ="Wybrales kategorie: matematyka";
		 sciezka="data/pytaniaM.txt";
		 pathAnswer1="data/odpM1.txt";
		 pathAnswer2="data/odpM2.txt";
		 path4="data/goodAnswerM.txt";
		 chapterImage = new Texture("math.png");
		 drawChapter();
		}
		if(english){
			 chapter ="Wybrales kategorie: angielski";
			 sciezka="data/pytaniaA.txt";
			 pathAnswer1="data/odpA1.txt";
			 pathAnswer2="data/odpA2.txt";
			 path4="data/goodAnswerA.txt";
			 chapterImage = new Texture("english.gif");
			 drawChapter();
		}
		if(history){
			 chapter ="Wybrales kategorie: historia";
			 sciezka="data/pytaniaH.txt";
			 pathAnswer1="data/odpH1.txt";
			 pathAnswer2="data/odpH2.txt";
			 path4="data/goodAnswerH.txt";
			 chapterImage = new Texture("history.gif");
			 drawChapter();	 
		}
	}
	else {
		chapter=" jeszcze nie wybrano kategorii";		
	}		
	}
	/**
	 * this method create the object used in class Menu
	 */
	
    @Override
    public void create () {
    	batch = new SpriteBatch();
    	font = new BitmapFont();
        img = new Texture("Snake_logo.png");
        chapterImage = new Texture("transparent.gif"); 
        bg = new Texture("bg_menu.png");   
        menuscreen=new MenuScreen(this);
        game=new Sis();
        gamescreen=new GameScreen(game);
    	this.setScreen(menuscreen); 
    	nameInput();
    	game.create(); 
    }
    /**
     * this method listen to user name input
     */
    public void nameInput() {
    	listener = new MyTextInputListener();
    	Gdx.input.getTextInput(listener,"Witaj w grze!",null,"Wpisz Swoje imie :)");
    }
    /**
     * this class contain method which send an input to the variable: name
     * @author Jakub Szutenberg 172434 G3
     *
     */
    public class MyTextInputListener implements TextInputListener {
		   public void input (String text) {
			   name= text;
		   }
		   public void canceled () {
			   return;
		   }
		}
    /**
     * this method read the files specified by select() and send them to setQuestion() and setAnswer, the Sis class methods
     */
    public void category() {	
    	FileHandle file = Gdx.files.internal(sciezka);
    	FileHandle file1 = Gdx.files.internal(pathAnswer1);
    	FileHandle file2 = Gdx.files.internal(pathAnswer2);
    	FileHandle file3 = Gdx.files.internal(path4);
 	   	quests = file.readString();
 	   	answer1=file1.readString();
 	   	answer2=file2.readString();
 	    correctAnswer=file3.readString();
 	   	currentquest = quests.split(System.getProperty("line.separator"));
 	   	answerArray1=answer1.split(System.getProperty("line.separator"));
 	   	answerArray2=answer2.split(System.getProperty("line.separator"));
 	    goodAnswer=correctAnswer.split(",",13);
 	   	game.setQuestion(currentquest);
 	   	game.setAnswer(answerArray1,answerArray2,goodAnswer);
    }
     /**
      * this method render menu on screen,in this case method render() call render() from class MenuScreen
      */
   public void render() {
	 	super.render(); 
    	}
   /**
    * this method determine which what is visible on the screen depending on user inputs
    */
   public void screenSwitch() {
		if(!startGame) {
			showWelcome();		
			}
		if(startGame&&!instrDisplay) {
			showMenu();	
			drawChapter();	
			}	
		if (instrDisplay) {
			showInstruction();
			input();
			}		
		if(instrDisplay&&goToSis) {
			this.setScreen(gamescreen);
			};
   }
   /**
    * this method set the background image
    */
   private void drawBg() {
	    batch.begin();
	    sprite = new Sprite(bg);
	    sprite.setPosition(-30, 0);
	    sprite.draw(batch);
	    batch.end(); 
   }
   /**
    * this method calls methods which are crucial to launch menu correctly 
    */
   public void showMenu() {	   
	   	drawBg();
		select();
		category();
		showText();	
   }
   /**
    * this method show menu description
    */
   public void showText() {
		batch.begin();
		font.draw(batch,"Witaj w grze: "+name, 220, 530);
		font.draw(batch, "Oto MENU, wybierz dzial: ", 220, 510);
		font.draw(batch,math , 220,490);
		font.draw(batch,ang, 220, 470);
		font.draw(batch,his, 220, 450);
		font.draw(batch,chapter, 220, 430);
		if(english||history|mathematic) {
		font.draw(batch,name+"! Wcisnij SPACE aby przejsc do gry", 190, 410);
		}
		batch.end();
   }
   /**
    * this method show selected chapter picture, depending on user input
    */
   public void drawChapter(){
	   sprite = new Sprite(chapterImage);
	   if(english) {					
		   sprite.scale((float) -0.8);    
		   sprite.setPosition(-200,-200); 
	   }
	   if(history) {
		   sprite.scale((float) -0.25 );    
		   sprite.setPosition(200,200);   
	   }
	   if(mathematic) {
		   sprite.scale((float) -0.6 );    
		   sprite.setPosition(35,150);   
	   }
		batch.begin();
		sprite.draw(batch);
		batch.end();
   }
   /**
    * this method dispose called methods 
    */
    @Override
    public void dispose () {
        batch.dispose();
        font.dispose();
    }
}
