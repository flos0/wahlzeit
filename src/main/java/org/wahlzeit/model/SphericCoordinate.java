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

import java.util.HashMap;

public class SphericCoordinate extends AbstractCoordinate {
	private static HashMap<String, SphericCoordinate> allSpCo = new HashMap<String, SphericCoordinate>();
	private final double phi;   //latitude(south-nord direction) in radians
	private final double theta; //longitude(west-east direction) in radians 
	private final double radius;
	
	private SphericCoordinate(double phi, double theta, double radius) throws CoordinateException {
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
	protected void assertValidParam(double phi, double theta, double radius) throws CoordinateException {
		if (!validSphere(phi, theta, radius)) {
			 throw new CoordinateException("Parameters value out of range", this);
		 }
	}
	/**
	 * @methodtype helper
	 */
	protected boolean validSphere(double phi, double theta, double radius) {
		 return (validAngle(phi, 2) && validAngle(theta, 1) && radius >= 0 );
	}
	
	/**
	 * @methodtype helper
	 */
	protected boolean validAngle(double d, int factor) {
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
		return CartesianCoordinate.getCartesianCoordinate(x, y, z);
	}

	/**
	 * @methodtype conversion
	 */
	@Override
	public SphericCoordinate doConvertToSphericCoordinate() {
		return this;
	}

	/**
	 * @methodtype get
	 */
	public static SphericCoordinate getSphericCoordinate(double phi2, double theta2, double radius2) throws CoordinateException {
		String coord = phi2+","+theta2+","+radius2;
		SphericCoordinate result = allSpCo.get(coord);
		if (result == null) {
			synchronized (allSpCo) {
				result = allSpCo.get(coord);
				if (result == null) {
					result =  new SphericCoordinate(phi2, theta2, radius2);
					allSpCo.put(coord, result);
				}
			}
		}
		return result;
	}
}
