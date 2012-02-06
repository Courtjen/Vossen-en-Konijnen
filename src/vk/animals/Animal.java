package vk.animals;
import java.util.List;
import java.util.Random;

import vk.actor.Actor;
import vk.simulator.Randomizer;
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
    private boolean alive;
    // The animal's field.
    private Field field;
    // The animal's position in the field.
    private Location location;
    // The animal's age
    protected int age;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    /**
     * Create a new animal at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Animal(Field field, Location location)
    {
        alive = true;
        this.field = field;
        setLocation(location);
    }
    
    /**
     * Make this animal act - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimals A list to add newly born animals to.
     */
    abstract public void act(List<Animal> newAnimals);

    /**
     * Check whether the animal is alive or not.
     * @return true if the animal is still alive.
     */
    public boolean isAlive()
    {
        return alive;
    }

    /**
     * Indicate that the animal is no longer alive.
     * It is removed from the field.
     */
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
     * @param newLocation The animal's new location.
     */
    public void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
    
    /**
     * A animal can breed if it has reached the breeding age.
     * @return true if the animal can breed, false otherwise.
     */
    public boolean canBreed()
    {
    	return this.age >= getBreedingAge();
    }
    abstract protected int getBreedingAge();

    /**
     * Increase the age. This could result in the animal's death.
     */
    protected void incrementAge()
    {
    	this.age++;
    	if(this.age > getMaxAge()) {
    		setDead();
    	}
    }
    abstract protected int getMaxAge();

    /**
     * Make this fox more hungry. This could result in the fox's death.
     */
    protected void incrementHunger()
    {
    	int foodlevel = getFoodlevel()-1;
    	if(foodlevel <= 0) {
    		setDead();
    	}
    }
    abstract protected int getFoodlevel();

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
    
    abstract protected double getBreedingProbability();
    abstract protected int getMaxLitterSize();
    
}

