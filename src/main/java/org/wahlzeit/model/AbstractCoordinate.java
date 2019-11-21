/*
 * Classname: AbstractCoordinate
 *
 * Version: 1.0
 *
 * Date 21.11.19
 * 
 * Copyright: AGPL-3.0
 */

package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate{

	/**
	 * @methodtype get
	 */
	public double getCartesianDistance(Coordinate other) {
		return this.asCartesianCoordinate().getCartesianDistance(other);
	}
	
	/**
	 * @methodtype get
	 */
	public double getCentralAngle(Coordinate other) {
		return this.asSphericCoordinate().getCentralAngle(other);
	}

	/**
	 * @methodtype comparison
	 */
	public boolean equals(Object other) {
		if (other instanceof Coordinate)
			return isEqual((Coordinate) other);
		return false;
	}
	
	/**
	 * @methodtype comparison
	 */
	public boolean isEqual(Coordinate other) {
		return this.asCartesianCoordinate().isEqual(other);
	}

	/**
	 * @methodtype get
	 */
	public int hashCode() {
		return this.asCartesianCoordinate().hashCode();
	}

}
