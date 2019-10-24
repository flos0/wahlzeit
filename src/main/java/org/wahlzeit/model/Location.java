/*
 * Classname: Location
 *
 * Version: 1.0
 *
 * Date 24.10.19
 * 
 * Copyright: AGPL-3.0
 */

package org.wahlzeit.model;

public class Location {
	protected Coordinate coordinate;
	
	public Location(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	public Coordinate getCoordinate() {
		return this.coordinate;
	}
	
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
}
