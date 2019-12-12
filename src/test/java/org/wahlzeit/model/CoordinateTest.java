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

import com.google.apphosting.utils.config.AppEngineWebXml.CpuUtilization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CoordinateTest {

	/**
	 *
	 */
	@Test
	public void testCartesianDistance() throws CoordinateException {
		Coordinate co0 = CartesianCoordinate.getCartesianCoordinate(0, 0, 0);
		Coordinate co1 = CartesianCoordinate.getCartesianCoordinate(1, 0, 0);
		Coordinate co2 = CartesianCoordinate.getCartesianCoordinate(2, 0, 0);
		Coordinate co3 = CartesianCoordinate.getCartesianCoordinate(0, 3, 4);

		assertTrue(co0.getCartesianDistance(co1) == 1);
		assertTrue(co1.getCartesianDistance(co0) == 1);
		assertTrue(co1.getCartesianDistance(co2) == 1);
		assertTrue(co2.getCartesianDistance(co1) == 1);
		assertTrue(co2.getCartesianDistance(co0) == 2);
		assertTrue(co3.getCartesianDistance(co0) == 5);
		assertTrue(co2.getCartesianDistance(co3) == Math.sqrt(29));
	}
	
	@Test
	public void testIsEqualCartesian() throws CoordinateException {
		double a = 0.1;
		double b = 0.1 + 1000 - 1000;
		double c = 0.101;
		Coordinate coa = CartesianCoordinate.getCartesianCoordinate(a, a, a);
		Coordinate cob = CartesianCoordinate.getCartesianCoordinate(b, b, b);
		Coordinate coc = CartesianCoordinate.getCartesianCoordinate(c, c, c);
		Coordinate cod = CartesianCoordinate.getCartesianCoordinate(a, b, c);
		Coordinate coe = CartesianCoordinate.getCartesianCoordinate(c, b, a);
		
		assertTrue(coa.isEqual(coa));
		assertTrue(cob.isEqual(cob));
		assertTrue(coc.isEqual(coc));
		assertTrue(coa.isEqual(cob));
		assertTrue(cob.isEqual(coa));
		
		assertFalse(coc.isEqual(coa));
		assertFalse(coa.isEqual(coc));
		assertFalse(coc.isEqual(cob));
		assertFalse(cod.isEqual(coe));
	}
	
	@Test
	public void testEqualsCartesian() throws CoordinateException {
		double a = 0.1;
		double b = 0.1 + 1000 - 1000;
		double c = 0.101;
		Coordinate coa = CartesianCoordinate.getCartesianCoordinate(a, a, a);
		Coordinate cob = CartesianCoordinate.getCartesianCoordinate(b, b, b);
		Coordinate coc = CartesianCoordinate.getCartesianCoordinate(c, c, c);
		Coordinate cod = CartesianCoordinate.getCartesianCoordinate(a, b, c);
		Coordinate coe = CartesianCoordinate.getCartesianCoordinate(c, b, a);
		
		assertTrue(coa.equals(coa));
		assertTrue(cob.equals(cob));
		assertTrue(coc.equals(coc));
		assertTrue(coa.equals(cob));
		assertTrue(cob.equals(coa));
		
		assertFalse(coc.equals(coa));
		assertFalse(coa.equals(coc));
		assertFalse(coc.equals(cob));
		assertFalse(cod.equals(coe));
		
		assertFalse(coa.equals("banane"));
		assertFalse(cob.equals(1337));
		assertFalse(coc.equals(this));
		assertFalse(cod.equals(Photo.class));
	}

	@Test
	public void testCentralAngle() throws CoordinateException {
		double delta = 0.0001;
		Coordinate co0 = SphericCoordinate.getSphericCoordinate(Math.PI/2, 0, 10);
		Coordinate co1 = SphericCoordinate.getSphericCoordinate(0, 0, 0);
		Coordinate co2 = SphericCoordinate.getSphericCoordinate(0, -Math.PI/2, 1);
		
		assertEquals(co0.getCentralAngle(co1), Math.PI/2, delta);
		assertEquals(co1.getCentralAngle(co0), Math.PI/2, delta);
		assertEquals(co1.getCentralAngle(co2), 0, delta);
		assertEquals(co2.getCentralAngle(co0), Math.PI/2, delta);
		assertEquals(co0.getCentralAngle(co0), 0, delta);
	}
	
	@Test
	public void testIsEqualSpheric() throws CoordinateException {
		double a = 0.1;
		double b = 0.1 + 1000 - 1000;
		double c = 0.101;
		Coordinate coa = SphericCoordinate.getSphericCoordinate(a, a, a);
		Coordinate cob = SphericCoordinate.getSphericCoordinate(b, b, b);
		Coordinate coc = SphericCoordinate.getSphericCoordinate(c, c, c);
		
		assertTrue(coa.isEqual(coa));
		assertTrue(cob.isEqual(cob));
		assertTrue(coc.isEqual(coc));
		assertTrue(coa.isEqual(cob));
		assertTrue(cob.isEqual(coa));
		
		assertFalse(coc.isEqual(coa));
		assertFalse(coa.isEqual(coc));
		assertFalse(coc.isEqual(cob));
	}
	
	@Test
	public void testEqualsSpheric() throws CoordinateException {
		double a = 0.1;
		double b = 0.1 + 1000 - 1000;
		double c = 0.101;
		Coordinate coa = SphericCoordinate.getSphericCoordinate(a, a, a);
		Coordinate cob = SphericCoordinate.getSphericCoordinate(b, b, b);
		Coordinate coc = SphericCoordinate.getSphericCoordinate(c, c, c);
		
		assertTrue(coa.equals(coa));
		assertTrue(cob.equals(cob));
		assertTrue(coc.equals(coc));
		assertTrue(coa.equals(cob));
		assertTrue(cob.equals(coa));
		
		assertFalse(coc.equals(coa));
		assertFalse(coa.equals(coc));
		assertFalse(coc.equals(cob));
		
		assertFalse(coa.equals("banane"));
		assertFalse(cob.equals(1337));
		assertFalse(coc.equals(this));
		assertFalse(coc.equals(null));
	}
	
	@Test
	public void testEqualAfterConversion() throws CoordinateException {
		Coordinate coS = SphericCoordinate.getSphericCoordinate(1, -2.2, 3);
		Coordinate coC = CartesianCoordinate.getCartesianCoordinate(4.4, 5, 6);
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
	
	@Test(expected = CoordinateException.class)
	public void testExeptionNegativeRadius() throws CoordinateException {
		SphericCoordinate.getSphericCoordinate(1, 1, -3);
	}
	@Test(expected = CoordinateException.class)
	public void testExeptionAngleOutOfRange() throws CoordinateException {
		SphericCoordinate.getSphericCoordinate(-2, 2, 3);
	}
	@Test(expected = CoordinateException.class)
	public void testExeptionNaNAngleParam() throws CoordinateException {
		SphericCoordinate.getSphericCoordinate(Double.NaN, 2, 3);
	}
	@Test(expected = CoordinateException.class)
	public void testExeptionNaNAxisParam() throws CoordinateException {
		CartesianCoordinate.getCartesianCoordinate(Double.NaN, 2, 3);
	}
	@Test(expected = CoordinateException.class)
	public void testExeptionInfiniteParam() throws CoordinateException {
		CartesianCoordinate.getCartesianCoordinate(5, 6, Double.POSITIVE_INFINITY);
	}
	@Test(expected = CoordinateException.class)
	public void testExeptionNullParam() throws CoordinateException {
		CartesianCoordinate co = CartesianCoordinate.getCartesianCoordinate(-55, 67, 4);
		co.getCartesianDistance(null);
	}
	@Test(expected = CoordinateException.class)
	public void testExeptionNullParam2() throws CoordinateException {
		CartesianCoordinate co = CartesianCoordinate.getCartesianCoordinate(-55, 67, 4);
		co.getCentralAngle(null);
	}
	
	@Test
	public void testObjectSharing() throws CoordinateException {
		Coordinate coS1 = SphericCoordinate.getSphericCoordinate(1.1, -2.3, 2);
		Coordinate coS2 = SphericCoordinate.getSphericCoordinate(1.1, -2.3, 2);
		Coordinate coC1 = coS1.asCartesianCoordinate();
		Coordinate coC2 = coS2.asCartesianCoordinate();
		
		assertTrue(coS1 == coS2);
		assertTrue(coC1 == coC2);
	}
	
	@Test
	public void testDifferentObjectNotSharing() throws CoordinateException {
		double a = 0.1;
		double b = 0.1 + 1000 - 1000;
		Coordinate coa = SphericCoordinate.getSphericCoordinate(a, a, a);
		Coordinate cob = SphericCoordinate.getSphericCoordinate(b, b, b);
		
		assertTrue(coa != cob);
		assertTrue(coa.isEqual(cob));
	}
}
