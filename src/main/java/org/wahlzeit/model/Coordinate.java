/*
 * Classname: Coordinate
 *
 * Version: 1.0
 *
 * Date 24.10.19
 * 
 * Copyright: AGPL-3.0
 */

package org.wahlzeit.model;

public class Coordinate {
	
	public static final double epsilon = 0.0001;
	
	private double x;
	private double y;
	private double z;
	
	public Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getDistance(Coordinate other) {
		double xdif = this.x-other.x;
		double xsqr = xdif*xdif;
		double ydif = this.y-other.y;
		double ysqr = ydif*ydif;
		double zdif = this.z-other.z;
		double zsqr = zdif*zdif;
		return Math.sqrt(xsqr + ysqr + zsqr);
	}
	
	public boolean isEqual(Coordinate other) {
		if (other == null) {
			return false;
		}
		return this.getDistance(other) < epsilon;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Coordinate)
			return isEqual((Coordinate) other);
		return false;
	}
}
