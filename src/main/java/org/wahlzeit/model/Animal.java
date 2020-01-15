/*
 * Classname: Animal
 *
 * Version: 1.0
 *
 * Date 05.11.19
 * 
 * Copyright: AGPL-3.0
 */

package org.wahlzeit.model;

public class Animal {
	
	private AnimalType type;
	private Gender gender;
	
	/**
	 *
	 */
	public Animal(AnimalType type, Gender gender) {
		if (type == null) throw new IllegalArgumentException("Animal needs a type");
		if (gender == null) throw new IllegalArgumentException("Animal needs a gender");
		this.type = type;
		this.gender = gender;
	}
	
	/**
	 * @methodtype get
	 */
	public AnimalType getType() {
		return type;
	}
	
	/**
	 * @methodtype get
	 */
	public Gender getGender() {
		return gender;
	}
}
