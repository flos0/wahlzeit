/*
 * Classname: AnimalPhoto
 *
 * Version: 1.0
 *
 * Date 05.11.19
 * 
 * Copyright: AGPL-3.0
 */

package org.wahlzeit.model;

@PatternInstance(
	patternName = "Abstract Factory",
	participants = {"AbstractProduct", "ConcreteProduct"}
)
@PatternInstance(
	patternName = "Abstract Methode",
	participants = {"Product", "ConcreteProduct"}
)
public class AnimalPhoto extends Photo {
	private Animal animal;

	/**
	 * @methodtype constructor
	 */
	public AnimalPhoto() {
		super();
	}

	/**
	 * @methodtype constructor
	 */
	public AnimalPhoto(PhotoId myId) {
		super(myId);
	}

	/**
	 * @methodtype get
	 */
	public Animal getAnimal() {
		return animal;
	}

	/**
	 * @methodtype set
	 */
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	
}
