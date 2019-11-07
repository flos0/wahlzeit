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
	public enum Type { Mammal, Reptile, Bird, Fish, Insect, Other };
	
	private String name;
	private Type type;
	
	/**
	 *
	 */
	public Animal(String name, Type type) {
		this.name = name;
		this.type = type;
	}

	/**
	 * @methodtype get
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @methodtype get
	 */
	public Type getType() {
		return type;
	}
}
