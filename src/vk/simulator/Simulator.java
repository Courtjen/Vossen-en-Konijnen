package vk.simulator;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

import vk.SimulatorView;
import vk.actor.Hunter;
import vk.animals.Animal;
import vk.animals.Bear;
import vk.animals.Fox;
import vk.animals.Rabbit;

/**
 * A simple predator-prey simulator, based on a rectangular field
 * containing rabbits and foxes.
 * 
 * @author Pim Vellinga
 * @version 1.0
 */
public class Simulator
{
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 50;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 50;
    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.05;
    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.08;
    // The probability that a bear will be created in any given grid position.
    private static final double BEAR_CREATION_PROBABILITY = 0.05;
    // The probability that a bear will be created in any given grid position.
    private static final double HUNTER_CREATION_PROBABILITY = 0.009; 

    // List of animals in the field.
    private static List<Animal> animals;
    // List of animals in the field.
    private static List<Hunter> hunters;
    // The current state of the field.
    private static Field field;
    // The current step of the simulation.
    private static int step;
    // A graphical view of the simulation.
    private static SimulatorView view;
    
    public static boolean run;
    
    public static int width = 0;
    public static int depth = 0;
    
    /**
     * Construct a simulation field with default size.
     */
    public Simulator()
    {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }
    
    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth1, int width1)
    {
        if(width1 <= 0 || depth1 <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            Simulator.depth = DEFAULT_DEPTH;
            Simulator.width = DEFAULT_WIDTH;
        } else {
        	Simulator.depth = depth1;
            Simulator.width = width1;
        }
        
        Simulator.animals = new ArrayList<Animal>();
        Simulator.hunters = new ArrayList<Hunter>();
        Simulator.field = new Field(depth, width);

        // Create a view of the state of each location in the field.
        Simulator.view = new SimulatorView(depth, width);
        Simulator.view.setColor(Rabbit.class, Color.orange);
        Simulator.view.setColor(Fox.class, Color.blue);
        Simulator.view.setColor(Bear.class, Color.red);
        Simulator.view.setColor(Hunter.class, Color.black);
        
        // Setup a valid starting point.
        reset();
    }
    
    /**
     * Run the simulation from its current state for a reasonably long period,
     * e.g. 500 steps.
     */
    public static void runLongSimulation()
    {
        simulate(500);
    }
    
    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     * @param numSteps The number of steps to run for.
     */
    public static void simulate(int numSteps)
    {
        for(int step = 1; step <= numSteps && Simulator.view.isViable(field); step++) {
            simulateOneStep();
        }
    }
    
    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * fox and rabbit.
     */
    public static void simulateOneStep()
    {
        Simulator.step++;

        // Provide space for newborn animals.
        List<Animal> newAnimals = new ArrayList<Animal>(); 
        
        try {
	        // Let all rabbits act.
	        for(Iterator<Animal> it = Simulator.animals.iterator(); it.hasNext(); ) {
	            Animal animal = it.next();
	            animal.act(newAnimals);
	            if(! animal.isAlive()) {
	                it.remove();
	            }
	        }
        }
        catch (Exception e){
            System.out.println(e);
        }
        
        
        try {
            // Let all rabbits act.
            for(Iterator<Hunter> it = Simulator.hunters.iterator(); it.hasNext(); ) {
                Hunter hunter = it.next();
                hunter.act();
                if(! hunter.isAlive()) {
                    it.remove();
                }
            }
        }
        catch (Exception e) {
        	System.out.println(e);
        }
               
        // Add the newly born foxes and rabbits to the main lists.
        Simulator.animals.addAll(newAnimals);

        Simulator.view.showStatus(step, field);
    }
        
    /**
     * Reset the simulation to a starting position.
     */
    public static void reset()
    {
        Simulator.step = 0;
        Simulator.animals.clear();
        populate();
        
        // Show the starting state in the view.
        Simulator.view.showStatus(step, field);
    }
    
    /**
     * Randomly populate the field with foxes and rabbits.
     */
    private static void populate()
    {
        Random rand = Randomizer.getRandom();
        Simulator.field.clear();
        for(int row = 0; row < Simulator.field.getDepth(); row++) {
            for(int col = 0; col < Simulator.field.getWidth(); col++) {
                if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Fox fox = new Fox(true, Simulator.field, location);
                    animals.add(fox);
                }
                else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Rabbit rabbit = new Rabbit(true, Simulator.field, location);
                    Simulator.animals.add(rabbit);
                }
                else if(rand.nextDouble() <= BEAR_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Bear bear = new Bear(false, Simulator.field, location);
                    Simulator.animals.add(bear);
                }
                else if(rand.nextDouble() <= HUNTER_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Hunter hunter = new Hunter(true, Simulator.field, location);
                    Simulator.hunters.add(hunter);
                }
            }
        }
    }
    
    /**
     * Methode om de applicatie te laten starten
     */
    
    public static void runApplication(){
    	Thread thread = new Thread(new Runnable(){
    		int steps = SimulatorView.simulateValue1;
    		        		
	    		public void run(){
	    			
	    			if(steps >= 1){
		    			run = true;
		    			while(run && step < steps){
		    				simulate(1);
		    				pause(50);
		    			}
	    			}
	    			else {
	    				run = true;
		    			while(run){
		    				simulate(1);
		    				pause(50);
		    			}
	    			}
	    		}
    	});
    	
    	thread.start();
    }
	
	static public void stop() {
		run = false;
		
	}
	
	public static void pause(int i) {
    	try {
    		Thread.sleep(i) ;}
    	catch (InterruptedException e) {
    		e.printStackTrace();
    	}
	}
    
}
