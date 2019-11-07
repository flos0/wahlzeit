/*
 * Classname: AnimalPhotoUtil
 *
 * Version: 1.0
 *
 * Date 07.11.19
 * 
 * Copyright: AGPL-3.0
 */

package org.wahlzeit.model;

import com.google.appengine.api.images.Image;

public class AnimalPhotoUtil extends PhotoUtil {
	
	/**
	 * @methodtype creation
	 */
	public static AnimalPhoto createPhoto(String filename, PhotoId id, Image uploadedImage) throws Exception {
		AnimalPhoto result = AnimalPhotoFactory.getInstance().createPhoto(id);
		result.setEnding(filename.substring(filename.lastIndexOf(".") + 1));

		createImageFiles(uploadedImage, result);

		int sourceWidth = uploadedImage.getWidth();
		int sourceHeight = uploadedImage.getHeight();
		result.setWidthAndHeight(sourceWidth, sourceHeight);

		return result;
	}

}
