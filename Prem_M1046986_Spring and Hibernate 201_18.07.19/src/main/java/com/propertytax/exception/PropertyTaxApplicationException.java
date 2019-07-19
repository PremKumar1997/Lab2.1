package com.propertytax.exception;

/**
 * The Class PropertyTaxApplicationException.
 *
 * @author Prem Kumar
 */
public class PropertyTaxApplicationException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new property tax application exception.
	 *
	 * @param errorMessage the error message
	 */
	public PropertyTaxApplicationException(String errorMessage) {
		super(errorMessage);
	}

}
