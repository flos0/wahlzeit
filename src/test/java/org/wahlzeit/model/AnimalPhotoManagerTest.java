/*
 * Classname: AnimalPhotoManagerTest
 *
 * Version: 1.0
 *
 * Date 07.11.19
 * 
 * Copyright: AGPL-3.0
 */

package org.wahlzeit.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesServiceFactory;

public class AnimalPhotoManagerTest {
	@Rule
	public TestRule chain = RuleChain.outerRule(new LocalDatastoreServiceTestConfigProvider())
			.around(new RegisteredOfyEnvironmentProvider());;
	
	/**
	 *
	 */
	@Test
	public void testInstanceValid() {
		AnimalPhotoManager apm = AnimalPhotoManager.getInstance();
		assertNotNull(apm);
	}
	
	/**
	 *
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCreateImgNull() throws Exception {
		AnimalPhotoManager apm = AnimalPhotoManager.getInstance();
		AnimalPhoto ph = apm.createPhoto("test", null);
	}
	
	/**
	 * This one is testing the whole manager->util->factory->item cycle
	 */
	@Test
	public void testSetAndGetId() throws Exception {
		AnimalPhotoManager apm = AnimalPhotoManager.getInstance();
		URL url = getClass().getClassLoader().getResource("pictures");
		byte[] f = Files.readAllBytes(Paths.get(new File(url.getPath(),"/leopard.jpg").getAbsolutePath()));
		Image img = ImagesServiceFactory.makeImage(f);
		AnimalPhoto phOriginal = apm.createPhoto("test", img);
		phOriginal.setAnimal(new Animal("leopard", Animal.Type.Mammal));
		PhotoId id = phOriginal.getId();
		AnimalPhoto phLoad = apm.getPhotoFromId(id);
		AnimalPhoto phNew = apm.createPhoto("test2", img);
				
		assertTrue(phOriginal == phLoad);
		assertTrue(phLoad.getId() == id);
		assertTrue(phLoad.getAnimal().getName() == "leopard");
		assertTrue(phLoad.getAnimal().getType() == Animal.Type.Mammal);
		
		assertFalse(phOriginal == phNew);
		assertFalse(phOriginal.getId() == phNew.getId());
		assertNull(phNew.getAnimal());
	}
	
	/**
	 *
	 */
	@Test
	public void testPhotoCreation() throws Exception {
		AnimalPhotoManager apm = AnimalPhotoManager.getInstance();
		AnimalPhotoFactory apf = AnimalPhotoFactory.getInstance();
		
		AnimalPhoto phOriginal = apf.createPhoto();
		PhotoId id = phOriginal.getId();
		apm.addPhoto(phOriginal);
		AnimalPhoto phLoad = apm.getPhotoFromId(id);
		AnimalPhoto phNew = apf.createPhoto();
				
		assertTrue(phOriginal == phLoad);
		assertTrue(phLoad.getId() == id);
		
		assertFalse(phOriginal == phNew);
		assertFalse(phOriginal.getId() == phNew.getId());
		assertNull(phNew.getAnimal());
	}

}