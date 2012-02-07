package vk.actor;


import java.util.List;

import vk.view.Field;
import vk.view.Location;

public abstract class Human implements Actor {

	protected int age;

	// Whether the animal is alive or not.
	protected boolean alive;
	// The animal's field.
	protected Field field;
	// The animal's position in the field.
	private Location location;

	public Human(Field fieldInput, Location locationInput)
	{
		alive = true;
		this.field = fieldInput;
		setLocation(locationInput);
		age = 0;
	}

	/**
	 * Make this human act - that is: make it do
	 * whatever it wants/needs to do.
	 * @param newHumans A list to add newly born humans to.
	 */
	@Override
	public abstract void act(List<Actor> newHumans);


	/**
	 * Check whether the human is alive or not.
	 * @return true if the human is still alive.
	 */
	@Override
	public boolean isAlive()
	{
		return alive;
	}


	/**
	 * Indicate that the human is no longer alive.
	 * It is removed from the field.
	 */
	@Override
	public void setDead()
	{
		alive = false;
		if(location != null) {
			this.field.clear(this.location);
			location = null;
			field = null;
		}
	}

	/**
	 * Return the human's location.
	 * @return The human's location.
	 */
	public Location getLocation()
	{
		return this.location;
	}

	/**
	 * Return the human's field.
	 * @return The human's field.
	 */
	public Field getField()
	{
		return this.field;
	}

	/**
	 * Place the human at the new location in the given field.
	 * @param newLocation The human's new location.
	 */
	protected void setLocation(Location newLocation)
	{
		if(this.location != null) {
			this.field.clear(this.location);
		}
		this.location = newLocation;
		this.field.place(this, newLocation);
	}
}