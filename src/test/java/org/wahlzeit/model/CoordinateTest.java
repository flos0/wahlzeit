/*
 * Classname: CoordinateTest
 *
 * Version: 1.0
 *
 * Date 13.11.19
 * 
 * Copyright: AGPL-3.0
 */

package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CoordinateTest {

	/**
	 *
	 */
	@Test
	public void testCartesianDistance() {
		Coordinate co0 = new CartesianCoordinate(0, 0, 0);
		Coordinate co1 = new CartesianCoordinate(1, 0, 0);
		Coordinate co2 = new CartesianCoordinate(2, 0, 0);
		Coordinate co3 = new CartesianCoordinate(0, 3, 4);
		
		assertTrue(co0.getCartesianDistance(co1) == 1);
		assertTrue(co1.getCartesianDistance(co0) == 1);
		assertTrue(co1.getCartesianDistance(co2) == 1);
		assertTrue(co2.getCartesianDistance(co1) == 1);
		assertTrue(co2.getCartesianDistance(co0) == 2);
		assertTrue(co3.getCartesianDistance(co0) == 5);
		assertTrue(co2.getCartesianDistance(co3) == Math.sqrt(29));
		assertTrue(Double.isNaN(co0.getCartesianDistance(null)));
		assertTrue(Double.isNaN(co1.getCartesianDistance(null)));
	}
	
	@Test
	public void testIsEqualCartesian() {
		double a = 0.1;
		double b = 0.1 + 1000 - 1000;
		double c = 0.101;
		Coordinate coa = new CartesianCoordinate(a, a, a);
		Coordinate cob = new CartesianCoordinate(b, b, b);
		Coordinate coc = new CartesianCoordinate(c, c, c);
		Coordinate cod = new CartesianCoordinate(a, b, c);
		Coordinate coe = new CartesianCoordinate(c, b, a);
		
		assertTrue(coa.isEqual(coa));
		assertTrue(cob.isEqual(cob));
		assertTrue(coc.isEqual(coc));
		assertTrue(coa.isEqual(cob));
		assertTrue(cob.isEqual(coa));
		
		assertFalse(coc.isEqual(coa));
		assertFalse(coa.isEqual(coc));
		assertFalse(coc.isEqual(cob));
		assertFalse(coc.isEqual(null));
		assertFalse(coa.isEqual(null));
		assertFalse(cod.isEqual(coe));
	}
	
	@Test
	public void testEqualsCartesian() {
		double a = 0.1;
		double b = 0.1 + 1000 - 1000;
		double c = 0.101;
		Coordinate coa = new CartesianCoordinate(a, a, a);
		Coordinate cob = new CartesianCoordinate(b, b, b);
		Coordinate coc = new CartesianCoordinate(c, c, c);
		Coordinate cod = new CartesianCoordinate(a, b, c);
		Coordinate coe = new CartesianCoordinate(c, b, a);
		
		assertTrue(coa.equals(coa));
		assertTrue(cob.equals(cob));
		assertTrue(coc.equals(coc));
		assertTrue(coa.equals(cob));
		assertTrue(cob.equals(coa));
		
		assertFalse(coc.equals(coa));
		assertFalse(coa.equals(coc));
		assertFalse(coc.equals(cob));
		assertFalse(coc.equals(null));
		assertFalse(coa.equals(null));
		assertFalse(cod.equals(coe));
		
		assertFalse(coa.equals("banane"));
		assertFalse(cob.equals(1337));
		assertFalse(coc.equals(this));
		assertFalse(cod.equals(Photo.class));
	}

	@Test
	public void testCentralAngle() {
		double delta = 0.0001;
		Coordinate co0 = new SphericCoordinate(Math.PI/2, 0, 10);
		Coordinate co1 = new SphericCoordinate(0, 0, 0);
		Coordinate co2 = new SphericCoordinate(0, -Math.PI/2, 1);
		
		assertEquals(co0.getCentralAngle(co1), Math.PI/2, delta);
		assertEquals(co1.getCentralAngle(co0), Math.PI/2, delta);
		assertEquals(co1.getCentralAngle(co2), 0, delta);
		assertEquals(co2.getCentralAngle(co0), Math.PI/2, delta);
		assertEquals(co0.getCentralAngle(co0), 0, delta);
		assertTrue(Double.isNaN(co0.getCentralAngle(null)));
		assertTrue(Double.isNaN(co1.getCentralAngle(null)));
	}
	
	@Test
	public void testIsEqualSpheric() {
		double a = 0.1;
		double b = 0.1 + 1000 - 1000;
		double c = 0.101;
		Coordinate coa = new SphericCoordinate(a, a, a);
		Coordinate cob = new SphericCoordinate(b, b, b);
		Coordinate coc = new SphericCoordinate(c, c, c);
		
		assertTrue(coa.isEqual(coa));
		assertTrue(cob.isEqual(cob));
		assertTrue(coc.isEqual(coc));
		assertTrue(coa.isEqual(cob));
		assertTrue(cob.isEqual(coa));
		
		assertFalse(coc.isEqual(coa));
		assertFalse(coa.isEqual(coc));
		assertFalse(coc.isEqual(cob));
		assertFalse(coc.isEqual(null));
		assertFalse(coa.isEqual(null));
	}
	
	@Test
	public void testEqualsSpheric() {
		double a = 0.1;
		double b = 0.1 + 1000 - 1000;
		double c = 0.101;
		Coordinate coa = new SphericCoordinate(a, a, a);
		Coordinate cob = new SphericCoordinate(b, b, b);
		Coordinate coc = new SphericCoordinate(c, c, c);
		
		assertTrue(coa.equals(coa));
		assertTrue(cob.equals(cob));
		assertTrue(coc.equals(coc));
		assertTrue(coa.equals(cob));
		assertTrue(cob.equals(coa));
		
		assertFalse(coc.equals(coa));
		assertFalse(coa.equals(coc));
		assertFalse(coc.equals(cob));
		assertFalse(coc.equals(null));
		assertFalse(coa.equals(null));
		
		assertFalse(coa.equals("banane"));
		assertFalse(cob.equals(1337));
		assertFalse(coc.equals(this));
	}
	
	@Test
	public void testEqualAfterConversion() {
		Coordinate coS = new SphericCoordinate(1, 2.2, 3);
		Coordinate coC = new CartesianCoordinate(4.4, 5, 6);
		Coordinate coS2 = coS.asCartesianCoordinate();
		Coordinate coC2 = coC.asSphericCoordinate();
		Coordinate coS3 = coS2.asSphericCoordinate();
		Coordinate coC3 = coC2.asCartesianCoordinate();
		
		assertTrue(coS.equals(coS2));
		assertTrue(coS.equals(coS3));
		assertTrue(coS3.equals(coS));
		
		assertTrue(coC.equals(coC2));
		assertTrue(coC.equals(coC3));
		assertTrue(coC3.equals(coC2));
	}
}
