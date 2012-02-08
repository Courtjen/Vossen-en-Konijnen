package vk.actor;
import java.util.List;
import java.util.Iterator;
import vk.view.Field;
import vk.view.Location;

/**
 * A simple model of a fox.
 * Foxes age, move, eat rabbits, and die.
 *
 * @author Pim Vellinga
 * @version 1.0
 */

public class Fox extends Animal
{
	// Characteristics shared by all foxes (static fields).

	// The age at which a fox can start to breed.
	private static int BREEDING_AGE = 10;
	// The age to which a fox can live.
	private static int MAX_AGE = 70;
	// The likelihood of a fox breeding.
	private static double BREEDING_PROBABILITY = 0.30;
	// The maximum number of births.
	private static int MAX_LITTER_SIZE = 3;
	// The food value of a single rabbit. In effect, this is the
	// number of steps a fox can go before it has to eat again.
	private static int FOX_FOOD_VALUE = 10;


	/**
	 * Create a fox. A fox can be created as a new born (age zero
	 * and not hungry) or with a random age and food level.
	 *
	 * @param randomAge If true, the fox will have random age and hunger level.
	 * @param field The field currently occupied.
	 * @param location The location within the field.
	 */
	public Fox(boolean randomAge, Field field, Location location)
	{
		super(field, location);
		if(randomAge) {
			age = rand.nextInt(MAX_AGE);
		}
		else {
			age = 0;
		}
	}

	/**
	 * This is what the fox does most of the time: it hunts for
	 * rabbits. In the process, it might breed, die of hunger,
	 * or die of old age.
	 * @param field The field currently occupied.
	 * @param newFoxes A list to add newly born foxes to.
	 */
	@Override
	public void act(List<Actor> newFoxes)
	{
		incrementAge();
		incrementHunger();
		
		if(isAlive()) {
			giveBirth(newFoxes);
			// Move towards a source of food if found.
			Location location = getLocation();
			Location newLocation = findFood(location);
			if(newLocation == null) {
				// No food found - try to move to a free location.
				newLocation = getField().freeAdjacentLocation(location);
			}
			// See if it was possible to move.
			if(newLocation != null) {
				setLocation(newLocation);
			}
			else {
				// Overcrowding.
				setDead();
			}
		}
	}

	/**
	 * Tell the fox to look for rabbits adjacent to its current location.
	 * Only the first live rabbit is eaten.
	 * @param location Where in the field it is located.
	 * @return Where food was found, or null if it wasn't.
	 */
	private Location findFood(Location location)
	{
		Field field = getField();
		List<Location> adjacent = field.adjacentLocations(getLocation());
		Iterator<Location> it = adjacent.iterator();
		while(it.hasNext()) {
			Location where = it.next();
			Object animal = field.getObjectAt(where);
			if(animal instanceof Rabbit) {
				Rabbit rabbit = (Rabbit) animal;
				if(rabbit.isAlive()) {
					rabbit.setDead();
					setFoodLevel(Fox.FOX_FOOD_VALUE);
					// Remove the dead rabbit from the field.
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