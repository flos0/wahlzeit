/*
 * Classname: AllTests
 *
 * Version: 1.0
 *
 * Date 01.11.19
 * 
 * Copyright: AGPL-3.0
 */

package org.wahlzeit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.wahlzeit.handlers.TellFriendTest;
import org.wahlzeit.model.*;
import org.wahlzeit.model.persistence.*;
import org.wahlzeit.services.*;
import org.wahlzeit.utils.*;

@RunWith(Suite.class)
@SuiteClasses({ AccessRightsTest.class, CoordinateTest.class, FlagReasonTest.class, GenderTest.class, GuestTest.class,
		PhotoFilterTest.class, TagsTest.class, UserStatusTest.class, ValueTest.class, TellFriendTest.class,
		DatastoreAdapterTest.class, EmailService.class, LogBuilderTest.class, StringUtilTest.class, VersionTest.class})

public class AllTests {

}
