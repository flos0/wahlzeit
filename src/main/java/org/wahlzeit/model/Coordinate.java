/*
 * Classname: Coordinate
 *
 * Version: 1.0
 *
 * Date 13.11.19
 * 
 * Copyright: AGPL-3.0
 */

package org.wahlzeit.model;

public interface Coordinate {
	public double getCartesianDistance(Coordinate other);
	public double getCentralAngle(Coordinate other);
	public CartesianCoordinate asCartesianCoordinate();
	public SphericCoordinate asSphericCoordinate();
	public boolean isEqual(Coordinate other);
}
