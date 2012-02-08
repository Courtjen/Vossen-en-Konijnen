package vk.actor;
import java.util.List;
import java.util.Random;

import vk.view.Field;
import vk.view.Location;

public class Hunter extends Human {
	
	private int kills = 0;
	private final int MAX_KILLS = 10;
	private static final int MAX_AGE = 80;
	private static final Random rand = Randomizer.getRandom();
	
	/**
	 * Create a new hunter at location in field.
	 *
	 * @param field1 The field currently occupied.
	 * @param location1 The location within the field.
	 */
	public Hunter(boolean randomAge, Field field, Location location)
	{
		super(field, location);
		if (randomAge){
			age = rand.nextInt(MAX_AGE);
		}
		else {
			age = 0;
		}
		
		kills = rand.nextInt(MAX_KILLS);
		
	}

	/**
	 * Make this animal act - that is: make it do
	 * whatever it wants/needs to do.
	 * @param newAnimals A list to add newly born animals to.
	 */
	@Override
	public void act(List<Actor> newHunters)
	{
		if(isAlive()) {
			 
			// Move towards a source of food if found
			Location location = getLocation();
			Location newLocation = findTarget();
			
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
			
			if (kills >= MAX_KILLS) { 
				setDead();
			}
		}
		
		else {
			setDead();
		}
	}

	/**
	 * Tell the fox to look for rabbits adjacent to its current location.
	 * Only the first live rabbit is eaten.
	 * @return Where food was found, or null if it wasn't.
	 */
	private Location findTarget()
	{
		Field currentField = getField();
		List<Location> adjacent = currentField.adjacentLocations(getLocation());
		 
		for (int i=0; i < MAX_KILLS; i++) {
			
			Location targetLocation = getRandomLocation(adjacent);
			
			Object object = currentField.getObjectAt(targetLocation);
			
			if (object instanceof Grass){
				return null;
			}

			if(object instanceof Animal) {
				Animal prey = (Animal) object;
				if(prey.isAlive()) {
					prey.setDead();	
					kills++;
					return targetLocation;
				}
			}
		}
		
		return null;
	}

	private static Location getRandomLocation(List<Location> location){
		int random = (int) (Math.random()*(location.size() -0));
		return location.get(random);
	}
}