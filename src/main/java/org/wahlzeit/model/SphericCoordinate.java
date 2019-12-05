/*
 * Classname: SphericCoordinate.java
 *
 * Version: 1.0
 *
 * Date 21.11.19
 * 
 * Copyright: AGPL-3.0
 */

package org.wahlzeit.model;

public class SphericCoordinate extends AbstractCoordinate {
	private double phi;   //latitude(south-nord direction) in radians
	private double theta; //longitude(west-east direction) in radians 
	private double radius;
	
	public SphericCoordinate(double phi, double theta, double radius) throws CoordinateException {
		assertValidParam(phi, theta, radius);
		this.phi = phi;
		this.theta = theta;
		this.radius = radius;
	}

	/**
	 * @methodtype get
	 */
	@Override
	public double doGetCentralAngle(SphericCoordinate sCoord) {
		double phiDif = Math.abs(this.phi-sCoord.phi);
		double thetaDif = Math.abs(this.phi-sCoord.phi);
		
		double angle = Math.sin(thetaDif/2);
		angle *= angle * Math.cos(phi) * Math.cos(sCoord.phi);
		angle += Math.pow(Math.sin(phiDif/2), 2);
		return 2*Math.asin(Math.sqrt(angle));
	}

	/**
	 * @methodtype assert
	 */
	protected void assertClassInvariants() throws CoordinateException {
		if (!validSphere(this.phi, this.theta, this.radius)) {
			 throw new CoordinateException("Class invariant violated", this);
		 }
	}
	/**
	 * @methodtype assert
	 */
	protected void assertValidParam(Double phi, Double theta, Double radius) throws CoordinateException {
		if (!validSphere(phi, theta, radius)) {
			 throw new CoordinateException("Parameters value out of range", this);
		 }
	}
	/**
	 * @methodtype helper
	 */
	protected boolean validSphere(Double phi, Double theta, Double radius) {
		 return (validAngle(phi, 2) && validAngle(theta, 1) && radius >= 0 );
	}
	
	/**
	 * @methodtype helper
	 */
	protected boolean validAngle(Double d, int factor) {
		 return d >= -Math.PI/factor && d <= Math.PI/factor;
	}
	
	/**
	 * @methodtype conversion
	 */
	@Override
	public CartesianCoordinate doConvertToCartesianCoordinate() throws CoordinateException {
		double x = this.radius*Math.cos(this.phi)*Math.sin(this.theta);
		double y = this.radius*Math.cos(this.phi)*Math.cos(this.theta);
		double z = this.radius*Math.sin(this.phi);
		return new CartesianCoordinate(x, y, z);
	}

	/**
	 * @methodtype conversion
	 */
	@Override
	public SphericCoordinate doConvertToSphericCoordinate() {
		return this;
	}
}
