/*
 * Classname: CartesianCoordinate
 *
 * Version: 1.0
 *
 * Date 21.11.19
 * 
 * Copyright: AGPL-3.0
 */

package org.wahlzeit.model;

import java.util.HashMap;

@PatternInstance(
	patternName = "Flyweight",
	participants = {"ConcreteFlyweight", "FlyweightFactory"}
)
@PatternInstance(
	patternName = "Template Methode",
	participants = {"ConcreteClass"}
)
public class CartesianCoordinate extends AbstractCoordinate {
	private static HashMap<String, CartesianCoordinate> allCaCo = new HashMap<String, CartesianCoordinate>();
	public static final double epsilon = 0.0001;
	
	private final double x;
	private final double y;
	private final double z;
	
	private CartesianCoordinate(double x, double y, double z) throws CoordinateException {
		assertValidParam(x);
		assertValidParam(y);
		assertValidParam(z);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * @methodtype get
	 */
	@Override
	public double doGetCartesianDistance(CartesianCoordinate cCoord ) {
		double xdif = this.x-cCoord.x;
		double xsqr = xdif*xdif;
		double ydif = this.y-cCoord.y;
		double ysqr = ydif*ydif;
		double zdif = this.z-cCoord.z;
		double zsqr = zdif*zdif;
		return Math.sqrt(xsqr + ysqr + zsqr);
	}

	/**
	 * @methodtype assert
	 */
	protected void assertClassInvariants() throws CoordinateException {
		 if (!Double.isFinite(this.x) || !Double.isFinite(this.y) || !Double.isFinite(this.z)) {
			 throw new CoordinateException("Class invariant violated", this);
		 }
	}
	
	/**
	 * @methodtype assert
	 */
	protected void assertValidParam(Double d) throws CoordinateException {
		 if (!Double.isFinite(d)) {
			 throw new CoordinateException("Parameters value out of range", this);
		 }
	}
	
	/**
	 * @methodtype comparison
	 */
	@Override
	public boolean isEqual(Coordinate other) throws CoordinateException {
		return this.getCartesianDistance(other) < epsilon;
	}

	/**
	 * @methodtype get
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * @methodtype conversion
	 */
	@Override
	public CartesianCoordinate doConvertToCartesianCoordinate() {
		return this;
	}

	/**
	 * @methodtype conversion
	 */
	@Override
	public SphericCoordinate doConvertToSphericCoordinate() throws CoordinateException {
		double r = this.doGetCartesianDistance(getCartesianCoordinate(0,0,0));
		if (r==0) return SphericCoordinate.getSphericCoordinate(0,0,0);
		double l = Math.atan2(x,y);
		double h = Math.asin(z/r);
		return SphericCoordinate.getSphericCoordinate(h,l,r);
	}

	/**
	 * @methodtype get
	 */
	public static CartesianCoordinate getCartesianCoordinate(double x2, double y2, double z2) throws CoordinateException {
		String coord = x2+","+y2+","+z2;
		CartesianCoordinate result = allCaCo.get(coord);
		if (result == null) {
			synchronized (allCaCo) {
				result = allCaCo.get(coord);
				if (result == null) {
					result = new CartesianCoordinate(x2, y2, z2);
					allCaCo.put(coord, result);
				}
			}
		}
		return result;
	}

}
