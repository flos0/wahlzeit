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
		assertClassInvariants();
		assertIsArgumentValid(other);
		CartesianCoordinate caCo = other.asCartesianCoordinate();
		Double res = this.doGetCartesianDistance(caCo);
		assert(res >= 0);
		assertClassInvariants();
		return res;
	}
	/**
	 * @methodtype get
	 * @methodproperties primitive
	 */
	protected double doGetCartesianDistance(CartesianCoordinate other) {
		return this.asCartesianCoordinate().doGetCartesianDistance(other);
	}
	
	/**
	 * @methodtype get
	 */
	public double getCentralAngle(Coordinate other) {
		assertClassInvariants();
		assertIsArgumentValid(other);
		SphericCoordinate spCo = other.asSphericCoordinate();
		Double res = this.doGetCentralAngle(spCo);
		assert(res >= 0 && res < 2*Math.PI);
		assertClassInvariants();
		return res;
	}
	/**
	 * @methodtype get
	 * @methodproperties primitive
	 */
	protected double doGetCentralAngle(SphericCoordinate other) {
		return this.asSphericCoordinate().doGetCentralAngle(other);
	}
	
	/**
	 * @methodtype assert
	 * @methodproperties hook
	 */
	protected abstract void assertClassInvariants();
	
	/**
	 * @methodtype assert
	 */
	protected void assertIsArgumentValid(Coordinate other) {
		if (other == null) {
			throw new IllegalArgumentException();
		}
		//other could be from a different class than AbstractCoordiant but we allow it?
		//assertClassInvariants();
	}
	
	/**
	 * @methodtype conversion
	 */
	public CartesianCoordinate asCartesianCoordinate() {
		assertClassInvariants();
		CartesianCoordinate caCo = this.doConvertToCartesianCoordinate();
		caCo.assertClassInvariants();
		assertClassInvariants();
		return caCo;
	}
	/**
	 * @methodtype conversion
	 * @methodproperties primitive
	 */
	protected abstract CartesianCoordinate doConvertToCartesianCoordinate();
	
	/**
	 * @methodtype conversion
	 */
	public SphericCoordinate asSphericCoordinate() {
		assertClassInvariants();
		SphericCoordinate spCo = this.doConvertToSphericCoordinate();
		spCo.assertClassInvariants();
		assertClassInvariants();
		return spCo;
	}
	/**
	 * @methodtype conversion
	 * @methodproperties primitive
	 */
	protected abstract SphericCoordinate doConvertToSphericCoordinate();
	
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
