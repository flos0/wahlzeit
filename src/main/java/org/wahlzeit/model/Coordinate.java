/*
 * Classname: Coordinate
 *
 * Version: 1.0
 *
 * Date 05.12.19
 * 
 * Copyright: AGPL-3.0
 */

package org.wahlzeit.model;

public interface Coordinate {
	public double getCartesianDistance(Coordinate other) throws CoordinateException;
	public double getCentralAngle(Coordinate other) throws CoordinateException;
	public CartesianCoordinate asCartesianCoordinate() throws CoordinateException;
	public SphericCoordinate asSphericCoordinate() throws CoordinateException;
	public boolean isEqual(Coordinate other) throws CoordinateException;
}
