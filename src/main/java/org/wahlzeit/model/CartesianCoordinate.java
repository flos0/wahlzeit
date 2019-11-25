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

public class CartesianCoordinate extends AbstractCoordinate {
	public static final double epsilon = 0.0001;
	
	private double x;
	private double y;
	private double z;
	
	public CartesianCoordinate(double x, double y, double z) {
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
	protected void assertClassInvariants() {
		 if (!Double.isFinite(this.x) || !Double.isFinite(this.y) || !Double.isFinite(this.z)) {
			 throw new IllegalStateException();
		 }
	}
	
	/**
	 * @methodtype assert
	 */
	protected void assertValidParam(Double d) {
		 if (!Double.isFinite(d)) {
			 throw new IllegalArgumentException();
		 }
	}
	
	/**
	 * @methodtype comparison
	 */
	@Override
	public boolean isEqual(Coordinate other) {
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
	public SphericCoordinate doConvertToSphericCoordinate() {
		double r = this.getCartesianDistance(new CartesianCoordinate(0,0,0));
		if (r==0) return new SphericCoordinate(0,0,0);
		double l = Math.atan2(x,y);
		double h = Math.asin(z/r);
		return new SphericCoordinate(h,l,r);
	}

}
