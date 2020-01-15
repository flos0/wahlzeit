/*
 * Classname: AnimalType
 *
 * Version: 1.0
 *
 * Date 15.01.20
 * 
 * Copyright: AGPL-3.0
 */
package org.wahlzeit.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.wahlzeit.services.DataObject;

public class AnimalType extends DataObject {
	protected AnimalType superType = null;
	protected Set<AnimalType> subTypes = new HashSet<AnimalType>();
	protected String typeName;

	/**
	 * 
	 */
	public AnimalType(String name) {
		this.typeName = name;
	}

	/**
	 * @methodtype get
	 */
	public String getName() {
		return typeName;
	}

	/**
	 * @methodtype get
	 */
	public AnimalType getSuperType() {
		return superType;
	}

	/**
	 * @methodtype get
	 */
	public Iterator<AnimalType> getSubTypeIterator() {
		return subTypes.iterator();
	}

	/**
	 * @methodtype command
	 */
	public void addSubType(AnimalType at) {
		assert (at != null) : "tried to set null sub-type";
		at.setSuperType(this);
		subTypes.add(at);
	}

	/**
	 * @methodtype set
	 */
	private void setSuperType(AnimalType at) {
		superType = at;
	}

	/**
	 * @methodtype querry
	 */
	// returns true if parameter is subtype of this
	public boolean isSubtype(AnimalType at) {
		assert (at != null) : "asked about null object";
		if (at == this) {return true;}
		for (AnimalType type : subTypes) {
			if (type.isSubtype(at)) {
				return true;
			}
		}
		return false;
	}
	
}
