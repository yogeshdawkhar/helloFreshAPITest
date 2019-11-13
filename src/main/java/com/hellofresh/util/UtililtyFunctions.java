package com.hellofresh.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

import com.hellofresh.constants.LoggingMessageContants;

/**
 * Class for common methods which can be used thought the project
 */
public class UtililtyFunctions {

	/**
	 * @Purpose This method will generate a random Credentials
	 * @param length --> the length of the random Credentials we want to generate
	 * @return method will return a random Credentials String
	 */
	public static String generateRandomCredentials(int length) {
		String allowedChars = LoggingMessageContants.ALPHABETES_STRING + LoggingMessageContants.NUMBERS_STRING
				+ LoggingMessageContants.SPECIAL_CHARACTERS_STRING + simpleDateFormated();
		return RandomStringUtils.random(length, allowedChars).substring(0, length);
	}

	/**
	 * @Purpose This method will generate a random Email
	 * @param length --> the length of the random emails we want to generate
	 * @return method will return new email String
	 */
	public static String generateRandomEmail(int length) {
		String allowedChars = LoggingMessageContants.ALPHABETES_STRING + LoggingMessageContants.NUMBERS_STRING
				+ simpleDateFormated();
		return RandomStringUtils.random(length, allowedChars).substring(0, length).toLowerCase() + "@mailinator.com";
	}

	/**
	 * @Purpose This method will generate a random String With Alphabets only
	 * @param length --> the length of the random Alphabets only string we want to
	 *               generate
	 * @return method will return a random Alphabets only string
	 */

	public static String generateRandomStringWithAlphabetesOnly(int length) {
		String allowedChars = LoggingMessageContants.ALPHABETES_STRING;
		return RandomStringUtils.random(length, allowedChars).substring(0, length);
	}

	/**
	 * @Purpose This method will generate a random String With Alphabets and Number
	 * @param length --> the length of the random Alphabets and Number string we
	 *               want to generate
	 * @return method will return a random Alphabets and Number String
	 */
	public static String generateRandomStringwithAlphabetAndNumber(int length) {
		String allowedChars = LoggingMessageContants.ALPHABETES_STRING + LoggingMessageContants.NUMBERS_STRING;
		return RandomStringUtils.random(length, allowedChars).substring(0, length);
	}

	/**
	 * @Purpose This method will generate a random String for Image name
	 * @param length --> the length of the random Image Name string we want to
	 *               generate
	 * @return method will return a random Image name String.
	 */

	public static String generateRandomImageName(int length) {
		String allowedChars = LoggingMessageContants.ALPHABETES_STRING + LoggingMessageContants.NUMBERS_STRING
				+ simpleDateFormated();
		return RandomStringUtils.random(length, allowedChars).substring(0, length);
	}

	/**
	 * @Purpose This method will generate a random String With Numbers only
	 * @param length --> the length of the random String with Numbers we want to
	 *               generate
	 * @return method will return a random Numbers string
	 */

	public static String generateRandomNumber(int length) {
		String allowedChars = LoggingMessageContants.NUMBERS_STRING;
		return RandomStringUtils.random(length, allowedChars).substring(0, length);
	}

	/**
	 * @Purpose This method will generate a current date in simple format.
	 * @return method will return a string of formated date
	 */
	static String simpleDateFormated() {
		return new SimpleDateFormat("ddMMMyyHHmmssSSS").format(new Date());
	}
}
