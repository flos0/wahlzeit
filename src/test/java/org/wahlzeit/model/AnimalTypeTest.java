/*
 * Classname: AnimalPhotoManagerTest
 *
 * Version: 1.0
 *
 * Date 15.01.20
 * 
 * Copyright: AGPL-3.0
 */

package org.wahlzeit.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class AnimalTypeTest {
	
	/**
	 *
	 */
	@Test
	public void testIsSubtype() {
		AnimalType A0 = new AnimalType("mammalia");
		AnimalType B0 = new AnimalType("feline");
		AnimalType B1 = new AnimalType("canine");
		AnimalType C0 = new AnimalType("big cat");
		AnimalType C1 = new AnimalType("small cat");
		AnimalType D0 = new AnimalType("leopard");
		AnimalType D1 = new AnimalType("cheetah");
		AnimalType D2 = new AnimalType("wolf");
		A0.addSubType(B0);
		A0.addSubType(B1);
		B0.addSubType(C0);
		B0.addSubType(C1);
		C0.addSubType(D0);
		C1.addSubType(D1);
		B1.addSubType(D2);
		
		assertTrue(A0.isSubtype(A0));
		assertTrue(A0.isSubtype(B0));
		assertTrue(A0.isSubtype(B1));
		assertTrue(A0.isSubtype(D0));
		assertTrue(A0.isSubtype(D1));
		assertTrue(A0.isSubtype(D2));
		assertTrue(C0.isSubtype(D0));
		assertTrue(B1.isSubtype(D2));
		assertFalse(D2.isSubtype(D1));
		assertFalse(D2.isSubtype(B0));
		assertFalse(D2.isSubtype(A0));
		assertFalse(D1.isSubtype(B1));
		assertFalse(C1.isSubtype(C0));
		assertFalse(C0.isSubtype(D2));
	}
	

}