package vk.exception;

/**
 * Returns a RunException
 * @author Pim
 *
 */

public class RunException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RunException(String error){
		super(error);
	}

}
