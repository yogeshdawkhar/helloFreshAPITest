package com.hellofresh.dataprovider;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.hellofresh.constants.LoggingMessageContants;
import com.hellofresh.util.UtililtyFunctions;

public class RepositoryCreateBookingTestCase {

	@DataProvider(name = "TestData", parallel = true)
	public static Object[][] bothCaseData(Method mtd) {

		Object detail[][] = null;
		if (mtd.getName().equalsIgnoreCase("createNewBookingAPIValidationForFristNameField")) {
			return new Object[][] {

					{ UtililtyFunctions.generateRandomStringWithAlphabetesOnly(2),
							LoggingMessageContants.SIZE_MUST_BE_BETWEEN_3_TO_18 },
					{ UtililtyFunctions.generateRandomStringWithAlphabetesOnly(19),
							LoggingMessageContants.SIZE_MUST_BE_BETWEEN_3_TO_18 } };
		} else if (mtd.getName().equalsIgnoreCase("createNewBookingAPIValidationForLastNameField")) {
			return new Object[][] {
					{ UtililtyFunctions.generateRandomStringWithAlphabetesOnly(2),
							LoggingMessageContants.SIZE_MUST_BE_BETWEEN_3_TO_30 },
					{ UtililtyFunctions.generateRandomStringWithAlphabetesOnly(31),
							LoggingMessageContants.SIZE_MUST_BE_BETWEEN_3_TO_30 } };
		} else if (mtd.getName().equalsIgnoreCase("createNewBookingAPIValidationForCheckInField")) {
			return new Object[][] { { "", LoggingMessageContants.MUST_NOT_BE_NULL } };
		} else if (mtd.getName().equalsIgnoreCase("createNewBookingAPIValidationForCheckOutField")) {
			return new Object[][] { { "", LoggingMessageContants.MUST_NOT_BE_NULL } };
		} else if (mtd.getName().equalsIgnoreCase("createNewBookingAPIValidationForEmailFieldWithInvalidEmaildata")) {
			return new Object[][] { { "plainaddress" }, { "#@%^%#$@#$@#.com" }, { "@domain.com" },
					{ "Yogesh D <email@domain.com>" }, { "email.domain.com" }, { "email@domain@domain.com" },
					{ ".email@domain.com" }, { "email.@domain.com" }, { "email@domain.com (Yogesh D)" },
					{ "email@-domain.com" }, { "email@domain..com" }, { "Abc.example.com" }, { "1" }, { ".@." } };
		} else if (mtd.getName().equalsIgnoreCase("createNewBookingAPIValidationForPhoneField")) {
			return new Object[][] {
					{ UtililtyFunctions.generateRandomStringWithAlphabetesOnly(10),
							LoggingMessageContants.SIZE_MUST_BE_BETWEEN_11_TO_21 },
					{ UtililtyFunctions.generateRandomStringWithAlphabetesOnly(22),
							LoggingMessageContants.SIZE_MUST_BE_BETWEEN_11_TO_21 } };
		}
		return detail;
	}
}
