package com.mygdx.game.desktop;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Menu;
import com.mygdx.game.Sis;
import com.mygdx.game.screens.MenuScreen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		 config.resizable= false;			
	     config.width = 600;			  
	     config.height = 600;			
	        
	  // new LwjglApplication(new Sis(), config); //nieuzywane obecnie, zostaje do testowania czasem
	     new LwjglApplication(new Menu(), config);
	     

	
	    


	       
	}
}
