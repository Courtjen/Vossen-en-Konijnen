package vk.actor;
import java.util.List;
import java.util.Random;

import vk.view.Field;
import vk.view.Location;

/**
 * A class representing shared characteristics of animals.
 * 
 * @author Pim Vellinga
 * @version 1.0
 */
public abstract class Animal implements Actor
{
	// Whether the animal is alive or not.
	private boolean alive = true;
	// The animal's field.
	private Field field = null;
	// The animal's position in the field.
	private Location location = null;
	// The animal's age
	protected int age = 0;
	// A shared random number generator to control breeding.
	protected static final Random rand = Randomizer.getRandom();
	// The animal's food level, which is increased by eating rabbits.
	protected int foodLevel = 0;

	/**
	 * Create a new animal at location in field.
	 *
	 * @param fieldInput The field currently occupied.
	 * @param locationInput The location within the field.
	 */
	public Animal(Field fieldInput, Location locationInput)
	{
		alive = true;
		field = fieldInput;
		setLocation(locationInput);
	}

	public void setFoodLevel(int newFoodLevel){
		foodLevel = newFoodLevel;
	}

	public int getFoodLevel(){
		return foodLevel;
	}

	/**
	 * Make this animal act - that is: make it do
	 * whatever it wants/needs to do.
	 * @param newAnimals A list to add newly born animals to.
	 */
	@Override
	public abstract void act(List<Actor> newActors);

	/**
	 * Check whether the animal is alive or not.
	 * @return true if the animal is still alive.
	 */
	@Override
	public boolean isAlive()
	{
		return alive;
	}

	/**
	 * Indicate that the animal is no longer alive.
	 * It is removed from the field.
	 */
	@Override
	public void setDead()
	{
		alive = false;
		if(location != null) {
			field.clear(location);
			location = null;
			field = null;
		}
	}

	/**
	 * Return the animal's location.
	 * @return The animal's location.
	 */
	public Location getLocation()
	{
		return location;
	}

	/**
	 * Return the animal's field.
	 * @return The animal's field.
	 */
	public Field getField()
	{
		return field;
	}

	/**
	 * Place the animal at the new location in the given field.
	 * @param newLocation The animal's new location
	 */
	public void setLocation(Location newLocation){
		if (location != null) field.clear(location);
		location = newLocation;
		field.place(this, newLocation);
	}

	/**
	 * Generate a number representing the number of births,
	 * if it can breed.
	 * @return The number of births (may be zero).
	 */
	protected int breed()
	{
		int births = 0;
		if(canBreed() && rand.nextDouble() <= getBreedingProbability()) {
			births = rand.nextInt(getMaxLitterSize()) + 1;
		}
		return births;
	}

	/**
	 * Returns the breeding probability of an animal.
	 */
	abstract protected double getBreedingProbability();

	/**
	 * Returns the maximum amount of offspring an animal can have.
	 */
	abstract protected int getMaxLitterSize();

	/**
	 * Return the animal's age.
	 * @return The animal's age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Set's the animal's age.
	 * @param The animal's age.
	 */
	public void setAge(int ageInput) {
		age = ageInput;
	}

	/**
	 * Increase the age. This could result in the fox's death.
	 */
	protected void incrementAge() {
		age++;
		if(age > getMaxAge()) {
			setDead();
		}
	}
	

	/*
	 * Increments the Hunger
	 */
	protected void incrementHunger() {

		foodLevel--;

		if (foodLevel <= 0) setDead();

	}

	/**
	 * Returns the maximum age of an animal
	 */
	abstract protected int getMaxAge();

	/**
	 * A fox can breed if it has reached the breeding age.
	 */
	public boolean canBreed() {
		return (age >= getBreedingAge());
	}

	abstract protected int getBreedingAge();

	/**
	 * Check whether or not this fox is to give birth at this step.
	 * New births will be made into free adjacent locations.
	 * @param newFoxes A list to add newly born foxes to.
	 */
	protected void giveBirth(List<Actor> newAnimals) {

		// New foxes are born into adjacent locations.
		// Get a list of adjacent free locations.

		Animal animal = this;
		Animal young = null;
		Field currentField = getField();
		List<Location> free = currentField.getFreeAdjacentLocations(getLocation());
		int births = breed();
		for(int b = 0; b < births && free.size() > 0; b++) {
			Location loc = free.remove(0);
			if (animal instanceof Fox){
				young = new Fox(false, currentField, loc);
			}
			else if(animal instanceof Rabbit){
				young = new Rabbit(false, currentField, loc);
			}
			else if(animal instanceof Bear){
				young = new Rabbit(false, currentField, loc);
			}
			else if(animal instanceof Grass){
				young = new Grass(currentField, loc);
			}
			newAnimals.add(young);
		}
	}
}

