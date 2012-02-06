package vk.actor;

import java.util.List;

import vk.view.Field;
import vk.view.Location;

public interface Actor {

	// Whether the animal is alive or not.
	public boolean alive = true;
	// The animal's field.
	public Field field = null;
	// The animal's position in the field.
	public Location location = null;

	/**
	 * Check whether the actor is alive or not.
	 * @return true if the actor is still alive.
	 */
	public abstract boolean isAlive();

	/**
	 * Indicate that the actor is no longer alive.
	 * It is removed from the field.
	 */
	public abstract void setDead();
	
	/**
	 * Make the Actor act
	 * @param newActors
	 */

	public abstract void act(List<Actor> newActors);
}