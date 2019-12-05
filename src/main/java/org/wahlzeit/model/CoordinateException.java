/*
 * Classname: CoordinateException
 *
 * Version: 1.0
 *
 * Date 05.12.19
 * 
 * Copyright: AGPL-3.0
 */
package org.wahlzeit.model;

public class CoordinateException extends Throwable {
	protected Coordinate coord;

	/**
	 * 
	 */
	public CoordinateException(Coordinate co) {
		coord = co;
	}

	/**
	 * @param message
	 */
	public CoordinateException(String message, Coordinate co) {
		super(message);
		coord = co;
	}

	/**
	 * @param cause
	 */
	public CoordinateException(Throwable cause, Coordinate co) {
		super(cause);
		coord = co;
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CoordinateException(String message, Throwable cause, Coordinate co) {
		super(message, cause);
		coord = co;
	}

	/**
	 * @methodtype get
	 */
	public Coordinate getCoordinate() {
		return coord;
	}
}
