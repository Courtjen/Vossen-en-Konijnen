package vk.actor;
import java.util.Iterator;
import java.util.List;

import vk.view.Field;
import vk.view.Location;

/**
 * A simple model of a rabbit.
 * Rabbits age, move, breed, and die.
 *
 * @author Pim Vellinga
 * @version 1.0
 */
public class Rabbit extends Animal
{
	// Characteristics shared by all rabbits (static fields).

	// The age at which a rabbit can start to breed.
	private static int BREEDING_AGE = 5;
	// The age to which a rabbit can live.
	private static int MAX_AGE = 40;
	// The likelihood of a rabbit breeding.
	private static double BREEDING_PROBABILITY = 0.75;
	// The maximum number of births.
	private static int MAX_LITTER_SIZE = 4;
	// The food value of a single grass. In effect, this is the
	// number of steps a rabbit can go before it has to eat again.
	private static int GRASS_FOOD_VALUE = 5;

	/**
	 * Create a new rabbit. A rabbit may be created with age
	 * zero (a new born) or with a random age.
	 *
	 * @param randomAge If true, the rabbit will have a random age.
	 * @param field The field currently occupied.
	 * @param location The location within the field.
	 */
	public Rabbit(boolean randomAge, Field fieldInput, Location locationInput)
	{
		super(fieldInput, locationInput);
		if(randomAge) {
			age = rand.nextInt(MAX_AGE);
		}
		else {
			age = MAX_AGE;
		}
		
		foodLevel = GRASS_FOOD_VALUE;
	}

	/**
	 * This is what the rabbit does most of the time - it runs
	 * around. Sometimes it will breed or die of old age.
	 * @param newRabbits A list to add newly born rabbits to.
	 */
	@Override
	public void act(List<Actor> newRabbits)
	{
		incrementAge();
		incrementHunger();
				
		if(isAlive()) {
			giveBirth(newRabbits);
			// Try to move into a free location.
			Location location =  getLocation();
			Location newLocation = findFood(location);
			if(newLocation == null) {
				newLocation = getField().freeAdjacentLocation(location);
			}
			if(newLocation != null){
				setLocation(newLocation);
			}
			else {
				// Overcrowding.
				setDead();
			}
		}
	}
	
	/**
	 * Tell the rabbit to look for grass
	 * @param location
	 * @return the location of the grass
	 */
	
	private Location findFood(Location location)
	{
		Field field = getField();
		List<Location> adjacent = field.adjacentLocations(getLocation());
		Iterator<Location> it = adjacent.iterator();
		while(it.hasNext()) {
			Location where = it.next();
			Object animal = field.getObjectAt(where);
			if(animal instanceof Grass) {
				Grass grass = (Grass) animal;
				if(grass.isAlive()) {
					grass.setDead();
					setFoodLevel(Rabbit.GRASS_FOOD_VALUE);
					// Remove the dead piece of grass from the field.
					return where;
				}
			}
		}
		return null;
	}

	@Override
	protected double getBreedingProbability(){
		return BREEDING_PROBABILITY;
	}

	@Override
	protected int getMaxLitterSize(){
		return MAX_LITTER_SIZE;
	}

	@Override
	protected int getBreedingAge() {
		return BREEDING_AGE;
	}

	@Override
	protected int getMaxAge(){
		return MAX_AGE;
	}
}