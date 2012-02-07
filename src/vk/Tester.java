package vk;

import vk.main.Main;

/**
 * Tester for the application
 * @author Pim
 *
 */

public class Tester {

	static Main main;
	@SuppressWarnings("unused")
	private static int heigth;
	@SuppressWarnings("unused")
	private static int width;
	public static void main(String[] args){
		if (args.length==2){
			 heigth = Integer.parseInt(args[0]);
			 width = Integer.parseInt(args[1]);
		}
		main = new Main();
		
	}
}