package vk.runner;

import vk.main.Main;
import vk.main.SplashScreen;

public class Runner {
	public static void main(String[] args){
		
		SplashScreen splash = new SplashScreen(5000);
		
		splash.showSplashAndExit();
		
		try { new Main(); } catch (Exception e) { e.getStackTrace(); }
	}
}
