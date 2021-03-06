package vk.model;

import vk.actor.*;

import vk.controller.Controller;
import vk.exception.RunException;
import vk.main.Main;
import vk.view.Field;
import vk.view.FieldStats;
import vk.view.Location;


import java.awt.Color;
import java.util.*;

/**
 * The main Model class. 
 * This class provides all the logic behind the application
 *
 * @author Pim Vellinga
 * @version 1.0
 */

public class Model extends AbstractModel implements Runnable {
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 150;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 100;
    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.05;
    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.08;
    // The probability that a bear will be created in any given grid position.
    private static final double BEAR_CREATION_PROBABILITY = 0.05;
    // The probability that a bear will be created in any given grid position.
    private static final double HUNTER_CREATION_PROBABILITY = 0.008; 
    // The possibility that grass will be created in any given grid position.
    private static final double GRASS_CREATION_PROBABILITY = 0.008;
    
    // Colors used for empty locations.
    private static final Color EMPTY_COLOR = Color.white;
    // Color used for objects that have no defined color.
    private static final Color UNKNOWN_COLOR = Color.gray;

    
    // List of all the actors
    private List<Actor> actors;
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // A boolean to see wether the application is running or not
    public boolean run = false;
    // a Hash map to link all the colours to the Actors
    private LinkedHashMap<Class<?>, Color> colors;
        
    private FieldStats stats;
	private Main main;
    
    /**
     * Construct a simulation field with default size.
     */
    public Model(Main newMain)
    {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
        
        main = newMain;
    }
    
    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Model(int depth, int width)
    {
        if(width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            
            field = new Field(DEFAULT_DEPTH, DEFAULT_WIDTH);
        }
        else {
        	 field = new Field(depth, width);
        }
        
        actors = new ArrayList<Actor>();
        
        stats = new FieldStats();
        
        colors = new LinkedHashMap<Class<?>, Color>();

        // Create a view of the state of each location in the fiel
        setColor(Rabbit.class, Color.orange);
        setColor(Fox.class, Color.blue);
        setColor(Bear.class, Color.red);
        setColor(Hunter.class, Color.black);
        setColor(Grass.class, Color.green);
      }
    
    /**
     * Run the simulation from its current state for a reasonably long period,
     * e.g. 500 steps.
     */
    public void runLongSimulation()
    {
        simulate(500);
    }
    
    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps)
    {
        for(int step = 1; step <= numSteps && isViable(field); step++) {
            simulateOneStep();
        }
    }
    
    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * fox and rabbit.
     */
    public void simulateOneStep()
    {
        step++;

        // Provide space for newborn animals.
        List<Actor> newActors = new ArrayList<Actor>(); 
        
        try {
	        // Let all rabbits act.
	        for(Iterator<Actor> it = actors.iterator(); it.hasNext(); ) {
	            Actor actor = it.next();
	            actor.act(newActors);
	            if(! actor.isAlive()) {
	                it.remove();
	            }
	        }
	        
	        actors.addAll(newActors);
	        showStatus(step, field);
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
        
    /**
     * Reset the simulation to a starting position.
     */
    public void reset()
    {
        step = 0;
        actors.clear();
        populate();
        
        // Show the starting state in the view.
        showStatus(step, field);
    }
    
    /**
     * Randomly populate the field with foxes and rabbits.
     */
    private void populate()
    {
        Random rand = Randomizer.getRandom();
        field.clear();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Fox fox = new Fox(true, field, location);
                    actors.add(fox);
                }
                else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Rabbit rabbit = new Rabbit(true, field, location);
                    actors.add(rabbit);
                }
                else if(rand.nextDouble() <= BEAR_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Bear bear = new Bear(true, field, location);
                    actors.add(bear);
                }
                else if(rand.nextDouble() <= HUNTER_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Hunter hunter = new Hunter(true, field, location);
                    actors.add(hunter);
                }
                else if(rand.nextDouble() <= GRASS_CREATION_PROBABILITY){
                	Location location = new Location(row, col);
                	Grass grass = new Grass(field, location);
                	actors.add(grass);
                }
            }
        }
    }
    
    /**
     * Paint on grid location on this field in a given color.
     */
    public void drawMark(int x, int y, Color color)
    {
    	views.get(0).g.setColor(color);
    	views.get(0).g.fillRect(x * views.get(0).xScale, y * views.get(0).yScale, views.get(0).xScale-1, views.get(0).yScale-1);
    }
    
    
    /**
     * Show the current status of the field.
     * @param step Which iteration step it is.
     * @param field The field whose status is to be displayed.
     */
    
    public void showStatus(int step, Field field)
    {
        if(!views.get(0).isVisible()) {
            views.get(0).setVisible(true);
        }
            
        main.lblSteps.setText(main.STEP_PREFIX + step);
       
        stats.reset();
        
        views.get(0).preparePaint();

        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                Object animal = field.getObjectAt(row, col);
                if(animal != null) {
                    stats.incrementCount(animal.getClass());
                    drawMark(col, row, getColor(animal.getClass()));
                }
                else {
                    drawMark(col, row, EMPTY_COLOR);
                }
            }
        }
        stats.countFinished();

        main.population.setText(main.POPULATION_PREFIX + this.stats.getPopulationDetails(field));
        
        notifyViews();
    }
    
    
    
    /**
     * Determine whether the simulation should continue to run.
     * @return true If there is more than one species alive.
     */
    public boolean isViable(Field field)
    {
    	return stats.isViable(field);
    }


    /**
     * @return The color to be used for a given class of animal.
     */
    public Color getColor(Class<?> animalClass)
    {
    	Color col = colors.get(animalClass);
    	if(col == null) {
    		// no color defined for this class
    		return UNKNOWN_COLOR;
    	}
    	else {
    		return col;
    	}
    }

    
    /**
     * Methode om de applicatie te laten starten
     */
    
    public void runApplication() throws RunException {
    	Thread thread = new Thread(new Runnable(){
    		int steps = Controller.toRun;
    		        		
	    		public void run(){
	    			
	    			if(steps >= 1){
		    			run = true;
		    			while(run && step < steps){
		    				simulate(1);
		    				pause(75);
		    			}
	    			}
	    			else {
	    				run = true;
		    			while(run){
		    				simulate(1);
		    				pause(75);
		    			}
	    			}
	    		}
    	});
    	
    	thread.start();
    }
    
    
    /**
     * Method to run the application with a fixed number of steps.
     * Adds a thread to animate the simulation.
     * @param i
     */
    public void runApplication(final int i){
    	Thread thread = new Thread(new Runnable(){
    		int steps = i;
    		public void run(){
				if(steps >= 1){
					run = true;
					
					while(run && step < steps){
						simulate(steps);
						pause(50);
					}
				}
    		}
    	});
    	
    	thread.start();
    }
	
    
    /**
     * Stop the application
     */
	public void stop() {
		run = false;
		
	}
	
	
	/**
	 * Pause the application
	 * @param i for how long
	 */
	public static void pause(int i) {
    	try {
    		Thread.sleep(i) ;}
    	catch (InterruptedException e) {
    		e.printStackTrace();
    	}
	}
  
	/**
	 * Set a color for a animalClass
	 * @param animalClass
	 * @param color
	 */
	
	public void setColor(Class<?> animalClass, Color color)
    {
        colors.put(animalClass, color);
    }
	
	public float getCount(Class<?> actor){
		return stats.getCount(actor);
	}
	
	public int getSteps(){
		return step;
	}
	
	
	@Override
	public void run() {
		run = true;
		while(run) {
			simulate(1);
			notifyViews();
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				System.out.println("Thread error (can't sleep)!");
			}
		}
	}
    
}