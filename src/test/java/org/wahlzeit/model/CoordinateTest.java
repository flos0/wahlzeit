/*
 * Classname: CoordinateTest
 *
 * Version: 1.0
 *
 * Date 24.10.19
 * 
 * Copyright: AGPL-3.0
 */

package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CoordinateTest {

	/**
	 *
	 */
	@Test
	public void testDistance() {
		Coordinate co0 = new Coordinate(0, 0, 0);
		Coordinate co1 = new Coordinate(1, 0, 0);
		Coordinate co2 = new Coordinate(2, 0, 0);
		
		assertTrue(co0.getDistance(co1) == 1);
		assertTrue(co1.getDistance(co0) == 1);
		assertTrue(co1.getDistance(co2) == 1);
		assertTrue(co2.getDistance(co1) == 1);
		assertTrue(co2.getDistance(co0) == 2);
		
		Coordinate co3 = new Coordinate(0, 3, 4);
		assertTrue(co3.getDistance(co0) == 5);
		assertTrue(co2.getDistance(co3) == Math.sqrt(29));
	}
	
	@Test
	public void testIsEqual() {
		double a = 0.1;
		double b = 0.1 + 1000 - 1000;
		double c = 0.101;
		Coordinate coa = new Coordinate(a, a, a);
		Coordinate cob = new Coordinate(b, b, b);
		Coordinate coc = new Coordinate(c, c, c);
		Coordinate cod = new Coordinate(a, b, c);
		Coordinate coe = new Coordinate(c, b, a);
		
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
	public void testEquals() {
		double a = 0.1;
		double b = 0.1 + 1000 - 1000;
		double c = 0.101;
		Coordinate coa = new Coordinate(a, a, a);
		Coordinate cob = new Coordinate(b, b, b);
		Coordinate coc = new Coordinate(c, c, c);
		Coordinate cod = new Coordinate(a, b, c);
		Coordinate coe = new Coordinate(c, b, a);
		
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

}
