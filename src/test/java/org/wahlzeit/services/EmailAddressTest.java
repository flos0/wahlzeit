/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.services;

import org.junit.Assert;

import junit.framework.TestCase;

/**
 * Test cases for the EmailAddress class.
 */
public class EmailAddressTest extends TestCase {

	/**
	 *
	 */
	public EmailAddressTest(String name) {
		super(name);
	}

	/**
	 *
	 */
	public void testGetEmailAddressFromString() {
		// invalid email addresses are allowed for local testing and online avoided by Google

		assertTrue(createEmailAddressIgnoreException("bingo@bongo"));
		assertTrue(createEmailAddressIgnoreException("bingo@bongo.com"));
		assertTrue(createEmailAddressIgnoreException("bingo.bongo@bongo.com"));
		assertTrue(createEmailAddressIgnoreException("bingo+bongo@bango"));
	}

	/**
	 *
	 */
	protected boolean createEmailAddressIgnoreException(String ea) {
		try {
			EmailAddress.getFromString(ea);
			return true;
		} catch (IllegalArgumentException ex) {
			// creation failed
			return false;
		}
	}

	/**
	 *
	 */
	public void testEmptyEmailAddress() {
		assertFalse(EmailAddress.EMPTY.isValid());
	}
	
	
	/**
	 *
	 */
	public void testIsEmpty() {
		assertTrue(EmailAddress.EMPTY.isEmpty());
		assertFalse(EmailAddress.getFromString("bingo@bongo.de").isEmpty());
	}
	
	
	/**
	 *
	 */
	public void testIsEqual() {
		EmailAddress ea = EmailAddress.getFromString("bingo@bongo.de");
		EmailAddress ea2 = EmailAddress.getFromString("bingo@bongo.com");
		assertTrue(EmailAddress.EMPTY.isEqual(EmailAddress.EMPTY));
		assertTrue(EmailAddress.EMPTY == EmailAddress.getFromString(""));
		assertTrue(EmailAddress.getFromString("bingo@bongo.de").isEqual(ea));
		assertTrue(ea.isEqual(EmailAddress.getFromString("bingo@bongo.de")));
		assertTrue(ea.isEqual(ea));
		
		assertFalse(ea.isEqual(ea2));
		assertFalse(ea2.isEqual(ea));
		assertFalse(ea2.isEqual(EmailAddress.EMPTY));
	}
	
	/**
	 *
	 */
	public void testAsString() {
		EmailAddress ea = EmailAddress.getFromString("bingo@bongo.de");
		EmailAddress ea2 = EmailAddress.getFromString("bingo@bongo.com");
		assertTrue(EmailAddress.EMPTY.asString() == "");
		assertTrue(ea.asString() == "bingo@bongo.de");
		assertTrue(ea2.asString() == "bingo@bongo.com");
	}
	
	/**
	 *
	 */
	public void testInternetAdress() {
		EmailAddress ea = EmailAddress.getFromString("bingo@bongo.de");
		EmailAddress ea2 = EmailAddress.getFromString("bingo@bongo.com");
		try {
			EmailAddress.EMPTY.asInternetAddress();
			ea.asInternetAddress();
			ea2.asInternetAddress();
		} catch (Exception ex) {
			Assert.fail();
		}
	}
	
	/**
	 *
	 */
	public void testIsValid() {
		EmailAddress ea = EmailAddress.getFromString("bingo@bongo.de");
		EmailAddress ea2 = EmailAddress.getFromString("@");
		EmailAddress em = EmailAddress.getFromString("");
		assertTrue(ea.isValid());
		assertTrue(ea2.isValid());
		assertFalse(em.isValid());
	}
}

