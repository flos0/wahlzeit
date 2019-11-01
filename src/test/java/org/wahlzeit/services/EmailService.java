/*
 * Classname: EmailService
 *
 * Version: 1.0
 *
 * Date 01.11.19
 * 
 * Copyright: AGPL-3.0
 */

package org.wahlzeit.services;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.wahlzeit.services.mailing.EmailServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ EmailAddressTest.class, EmailServiceTest.class })
public class EmailService {

}
