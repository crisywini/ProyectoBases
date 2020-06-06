package co.uniquindio.bases.supermarket.SuperMarketCampestre.database.exceptions;

public class NonexistentEntityException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NonexistentEntityException(String errorMessage) {
		super(errorMessage);
	}

}
