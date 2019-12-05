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
	public double getCartesianDistance(Coordinate other) throws CoordinateException {
		assertClassInvariants();
		assertIsArgumentValid(other);
		CartesianCoordinate caCo = other.asCartesianCoordinate();
		Double res;
		try {
			res = this.doGetCartesianDistance(caCo);
		} catch (Exception e) {
			throw new CoordinateException("Unexpected error during calculation", e, this);
		}
		try {
			assert(res >= 0) : "Distance has to be positive";
		} catch (AssertionError e) {
			throw new CoordinateException(e, this);
		}
		assertClassInvariants();
		return res;
	}
	/**
	 * @methodtype get
	 * @methodproperties primitive
	 */
	protected double doGetCartesianDistance(CartesianCoordinate other) throws CoordinateException {
		return this.asCartesianCoordinate().doGetCartesianDistance(other);
	}
	
	/**
	 * @methodtype get
	 */
	public double getCentralAngle(Coordinate other) throws CoordinateException {
		assertClassInvariants();
		assertIsArgumentValid(other);
		SphericCoordinate spCo = other.asSphericCoordinate();
		Double res;
		try {
			res = this.doGetCentralAngle(spCo);
		} catch (Exception e) {
			throw new CoordinateException("Unexpected error during calculation", e, this);
		}
		try {
			assert(res >= 0 && res < 2*Math.PI) : "Angle not in the valid range";
		} catch (AssertionError e) {
			throw new CoordinateException(e, this);
		}
		assertClassInvariants();
		return res;
	}
	/**
	 * @methodtype get
	 * @methodproperties primitive
	 */
	protected double doGetCentralAngle(SphericCoordinate other) throws CoordinateException {
		return this.asSphericCoordinate().doGetCentralAngle(other);
	}
	
	/**
	 * @methodtype assert
	 * @methodproperties hook
	 */
	protected abstract void assertClassInvariants() throws CoordinateException;
	
	/**
	 * @methodtype assert
	 */
	protected void assertIsArgumentValid(Coordinate other) throws CoordinateException {
		if (other == null) {
			throw new CoordinateException("Null is not a valid argument for Coordinate", this);
		}
		//other could be from a different class than AbstractCoordiant but we allow it?
		//assertClassInvariants();
	}
	
	/**
	 * @methodtype conversion
	 */
	public CartesianCoordinate asCartesianCoordinate() throws CoordinateException {
		assertClassInvariants();
		CartesianCoordinate caCo;
		try {
			caCo = this.doConvertToCartesianCoordinate();
		} catch (Exception e) {
			throw new CoordinateException("Unexpected error during calculation", e, this);
		}
		caCo.assertClassInvariants();
		assertClassInvariants();
		return caCo;
	}
	/**
	 * @methodtype conversion
	 * @methodproperties primitive
	 */
	protected abstract CartesianCoordinate doConvertToCartesianCoordinate() throws CoordinateException;
	
	/**
	 * @methodtype conversion
	 */
	public SphericCoordinate asSphericCoordinate() throws CoordinateException {
		assertClassInvariants();
		SphericCoordinate spCo;
		try {
			spCo = this.doConvertToSphericCoordinate();
		} catch (Exception e) {
			throw new CoordinateException("Unexpected error during calculation", e, this);
		}
		spCo.assertClassInvariants();
		assertClassInvariants();
		return spCo;
	}
	/**
	 * @methodtype conversion
	 * @methodproperties primitive
	 */
	protected abstract SphericCoordinate doConvertToSphericCoordinate() throws CoordinateException;
	
	/**
	 * @methodtype comparison
	 */
	public boolean equals(Object other) {
		if (other instanceof Coordinate)
			try {
				return isEqual((Coordinate) other);
			} catch (CoordinateException e) {
				//If comparison has an error the object shouldn't be seen as equal
			}
		return false;
	}
	
	/**
	 * @methodtype comparison
	 */
	public boolean isEqual(Coordinate other) throws CoordinateException {
		return this.doConvertToCartesianCoordinate().isEqual(other);
	}

	/**
	 * @methodtype get
	 */
	public int hashCode() {
		try {
			return this.asCartesianCoordinate().hashCode();
		} catch (CoordinateException e) {
			//Something failsafe and mostly unique
			return (int) (Math.random()*Integer.MIN_VALUE);
		}
	}

}
