package vk.actor;
import java.util.List;
import java.util.Random;

import vk.simulator.Randomizer;
import vk.view.Field;
import vk.view.Location;

public class Hunter extends Human {
	private final int MAX_KILLS = 3;
	private static final Random rand = Randomizer.getRandom();
	
	private int kills = 0;

	/**
	 * Create a new animal at location in field.
	 *
	 * @param field1 The field currently occupied.
	 * @param location1 The location within the field.
	 */
	public Hunter(boolean randomAge, Field field, Location location)
	{
		super(field, location);
		if (randomAge) age = rand.nextInt();
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

		for (int i=0; i< MAX_KILLS; i++) {
			
			Location targetLocation = getRandomLocation(adjacent);

			Actor actor = field.getActor(targetLocation);

			if(actor instanceof Animal) {
				Animal prey = (Animal) actor;
				if(prey.isAlive()) {
					prey.setDead();
					kills++;
				}
			}
			if(actor instanceof Hunter) {
				Hunter hunter = (Hunter) actor;
				if(hunter.isAlive()) {
					hunter.setDead();
					kills++;
				}
				
				if(kills >= MAX_KILLS){
					actor.setDead();
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