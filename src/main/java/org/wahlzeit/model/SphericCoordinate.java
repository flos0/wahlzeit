/*
 * Classname: SphericCoordinate.java
 *
 * Version: 1.0
 *
 * Date 13.11.19
 * 
 * Copyright: AGPL-3.0
 */
package org.wahlzeit.model;

public class SphericCoordinate implements Coordinate {
	private double phi;   //latitude(south-nord direction) in radians
	private double theta; //longitude(west-east direction) in radians 
	private double radius;
	
	public SphericCoordinate(double phi, double theta, double radius) {
		this.phi = phi;
		this.theta = theta;
		this.radius = radius;
	}

	/**
	 * @methodtype get
	 */
	@Override
	public double getCentralAngle(Coordinate other) {
		if (other == null) {
			return Double.NaN;
		}
		SphericCoordinate sCoord = other.asSphericCoordinate();
		double phiDif = Math.abs(this.phi-sCoord.phi);
		double thetaDif = Math.abs(this.phi-sCoord.phi);
		
		double angle = Math.sin(thetaDif/2);
		angle *= angle * Math.cos(phi) * Math.cos(sCoord.phi);
		angle += Math.pow(Math.sin(phiDif/2), 2);
		return 2*Math.asin(Math.sqrt(angle));
	}

	/**
	 * @methodtype conversion
	 */
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		double x = this.radius*Math.cos(this.phi)*Math.sin(this.theta);
		double y = this.radius*Math.cos(this.phi)*Math.cos(this.theta);
		double z = this.radius*Math.sin(this.phi);
		return new CartesianCoordinate(x, y, z);
	}

	/**
	 * @methodtype get
	 */
	@Override
	public double getCartesianDistance(Coordinate other) {
		return this.asCartesianCoordinate().getCartesianDistance(other);
	}
	
	/**
	 * @methodtype conversion
	 */
	@Override
	public SphericCoordinate asSphericCoordinate() {
		return this;
	}

	/**
	 * @methodtype comparison
	 */
	@Override
	public boolean isEqual(Coordinate other) {
		return this.asCartesianCoordinate().isEqual(other);
	}
	
	/**
	 * @methodtype comparison
	 */
	@Override
	public boolean equals(Object other) {
		return this.asCartesianCoordinate().equals(other);
	}

	/**
	 * @methodtype get
	 */
	@Override
	public int hashCode() {
		return this.asCartesianCoordinate().hashCode();
	}

}
