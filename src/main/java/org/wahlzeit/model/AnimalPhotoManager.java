/*
 * Classname: AnimalPhotoManager
 *
 * Version: 1.0
 *
 * Date 05.11.19
 * 
 * Copyright: AGPL-3.0
 */

package org.wahlzeit.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.google.appengine.api.images.Image;

/**
 * A photo manager provides access to and manages photos.
 */
@PatternInstance(
	patternName = "Singleton",
	participants = {"Singleton"}
)
public class AnimalPhotoManager extends PhotoManager {

	/**
	 *
	 */
	protected static final AnimalPhotoManager instance = new AnimalPhotoManager();

	private static final Logger log = Logger.getLogger(AnimalPhotoManager.class.getName());

	/**
	 * In-memory cache for photos
	 */
	protected Map<PhotoId, AnimalPhoto> photoCache = new HashMap<PhotoId, AnimalPhoto>();

	/**
	 * 
	 */
	protected PhotoTagCollector photoTagCollector = null;

	/**
	 * @methodtype constructor
	 */
	public AnimalPhotoManager() {
		photoTagCollector = PhotoFactory.getInstance().createPhotoTagCollector();
	}

	/**
	 * @methodtype get
	 */
	public static AnimalPhotoManager getInstance() {
		return instance;
	}
	
	/**
	 *
	 */
	public AnimalPhoto createPhoto(String filename, Image uploadedImage) throws Exception {
		PhotoId id = PhotoId.getNextId();
		AnimalPhoto result = AnimalPhotoUtil.createPhoto(filename, id, uploadedImage);
		addPhoto(result);
		return result;
	}
	
	/**
	 * @methodtype command
	 */
	public void addPhoto(AnimalPhoto photo) throws IOException {
		PhotoId id = photo.getId();
		assertIsNewPhoto(id);
		doAddPhoto(photo);

		GlobalsManager.getInstance().saveGlobals();
	}

	/**
	 * @methodtype command
	 * @methodproperties primitive
	 */
	protected void doAddPhoto(AnimalPhoto myPhoto) {
		photoCache.put(myPhoto.getId(), myPhoto);
	}
	
	/**
	 * @methodtype get
	 */
	public AnimalPhoto getPhotoFromId(PhotoId id) {
		if (id == null) {
			return null;
		}

		AnimalPhoto result = doGetPhotoFromId(id);

		if (result == null) {
			result = AnimalPhotoFactory.getInstance().loadPhoto(id);
			if (result != null) {
				doAddPhoto(result);
			}
		}

		return result;
	}

	/**
	 * @methodtype get
	 * @methodproperties primitive
	 */
	protected AnimalPhoto doGetPhotoFromId(PhotoId id) {
		return this.photoCache.get(id);
	}
	

	/**
	 * @methodtype get
	 */
	public AnimalPhoto getVisiblePhoto(PhotoFilter filter) {
		filter.generateDisplayablePhotoIds();
		return getPhotoFromId(filter.getRandomDisplayablePhotoId());
	}

}
