package com.mygdx.sis.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.sis.Menu;
import com.mygdx.sis.Sis;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		 config.title = "SneakIntoScience";   //
		 config.resizable= false;			//
	        config.width = 600;			  //
	        config.height = 600;			 //
	       new LwjglApplication(new Sis(), config);
	       
	}
}
