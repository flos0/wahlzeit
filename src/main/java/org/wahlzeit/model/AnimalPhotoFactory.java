/*
 * Classname: AnimalPhotoFactory
 *
 * Version: 1.0
 *
 * Date 01.11.19
 * 
 * Copyright: AGPL-3.0
 */

package org.wahlzeit.model;

import java.util.logging.Logger;

import org.wahlzeit.services.LogBuilder;

/**
 * 
 * An Abstract Factory for creating animal photos and related objects.
 */
@PatternInstance(
	patternName = "Abstract Factory",
	participants = {"AbstractFactory", "ConcreteFactory"}
)
@PatternInstance(
	patternName = "Factory Method",
	participants = {"Creator", "ConcreteCreator"}
)
@PatternInstance(
	patternName = "Singleton",
	participants = {"Singleton"}
)
public class AnimalPhotoFactory extends PhotoFactory {

	private static final Logger log = Logger.getLogger(AnimalPhotoFactory.class.getName());
	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	private static AnimalPhotoFactory instance = null;

	/**
	 * @methodtype constructor
	 */
	protected AnimalPhotoFactory() {
		// do nothing
	}

	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	public static void initialize() {
		getInstance(); // drops result due to getInstance() side-effects
	}

	/**
	 * Public singleton access method.
	 */
	public static synchronized AnimalPhotoFactory getInstance() {
		if (instance == null) {
			log.config(LogBuilder.createSystemMessage().addAction("setting generic AnimalPhotoFactory").toString());
			setInstance(new AnimalPhotoFactory());
		}

		return instance;
	}

	/**
	 * Method to set the singleton instance of PhotoFactory.
	 */
	protected static synchronized void setInstance(AnimalPhotoFactory photoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize AnimalPhotoFactory twice");
		}

		instance = photoFactory;
	}

	/**
	 * @methodtype factory
	 */
	public AnimalPhoto createPhoto() {
		return new AnimalPhoto();
	}

	/**
	 * Loads a photo. The Java object is loaded from the Google Datastore, the Images in all sizes are loaded from the
	 * Google Cloud storage.
	 */
	public AnimalPhoto loadPhoto(PhotoId id) {
		return null;
	}
	
	/**
	 * Creates a new photo with the specified id
	 */
	public AnimalPhoto createPhoto(PhotoId id) {
		return new AnimalPhoto(id);
	}

}
