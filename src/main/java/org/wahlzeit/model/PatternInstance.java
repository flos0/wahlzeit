/*
 * Classname: PatternInstance
 *
 * Version: 1.0
 *
 * Date 17.12.19
 * 
 * Copyright: AGPL-3.0
 */
package org.wahlzeit.model;

import java.lang.annotation.Repeatable;

@Repeatable(PatternWrap.class)
public @interface PatternInstance {
	String patternName();
	String[] participants();
}
