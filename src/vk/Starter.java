package vk;

import vk.simulator.Simulator;

public class Starter {

	private static int heigth = 0;
	private static int width = 0;
	static Simulator sim;

	public static void main(String[] args){
		if (args.length==2){
			heigth = Integer.parseInt(args[0]);
			width = Integer.parseInt(args[1]);
		}
		try{
			sim = new Simulator(heigth,width);
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println();
		}
	}
}