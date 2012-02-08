package vk.actor;
import java.util.Random;

/**
 * Provide control over the randomization of the simulation.
 * 
 * @author Pim Vellinga
 * @version 1.0
 */
public class Randomizer
{
    // The default seed for control of randomization.
    private static final long SEED = System.currentTimeMillis();
    // A shared Random object, if required.
    private static final Random rand = new Random(SEED);
    // Determine whether a shared random generator is to be provided.
    private static final boolean useShared = true;

    /**
     * Constructor for objects of class Randomizer
     */
    public Randomizer()
    {
    }

    /**
     * Provide a random generator.
     * @return A random object.
     */
    public static Random getRandom()
    {
        if(useShared) {
            return rand;
        }
        else {
            return new Random();
        }
    }
    
    
    /**
     * Generates random integer between min and max
     * @param min
     * @param max
     * @return the number
     */
    
    public static int getRandomInt(int min, int max){
    	int randomNumber = rand.nextInt(max - min +1) + min;
    	
    	return randomNumber;
    }
    
    /**
     * Reset the randomization.
     * This will have no effect if randomization is not through
     * a shared Random generator.
     */
    public static void reset()
    {
        if(useShared) {
            rand.setSeed(SEED);
        }
    }
}
