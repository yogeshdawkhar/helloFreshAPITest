package com.hellofresh.bookingapitestcases;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import java.time.Instant;
import java.time.LocalDate;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.hellofresh.api.BookingApi;
import com.hellofresh.constants.LoggingMessageContants;
import com.hellofresh.dataprovider.RepositoryCreateBookingTestCase;
import com.hellofresh.util.UtililtyFunctions;

public class CreateBookingTest extends BookingBaseTest {
	/*
	 * String checkinDate; String checkoutDate; String email; String fristName;
	 * String lastName; String phoneNumber; int roomId;
	 */
	LocalDate curDate;

	@Test(description = "To test scenario : When all valid data is passed booking should be created and API should return Response code 201 and booking ID.")
	public void createNewBookingWithValidInput() {

		curDate = LocalDate.now();

		String checkinDate = curDate.toString();
		String checkoutDate = curDate.plusDays(1).toString();
		String email = UtililtyFunctions.generateRandomEmail(8);
		String fristName = UtililtyFunctions.generateRandomStringWithAlphabetesOnly(8);
		String lastName = UtililtyFunctions.generateRandomStringWithAlphabetesOnly(8);
		String phoneNumber = UtililtyFunctions.generateRandomNumber(11);
		int roomId = Integer.parseInt(UtililtyFunctions.generateRandomNumber(3));
		response = BookingApi.createBooking(checkinDate, checkoutDate, email, fristName, lastName, phoneNumber, roomId);
		response.then().log().all();

		response.then().statusCode(201);
		response.then().body("bookingid", Matchers.any(Integer.class));
		response.then().assertThat().body("booking.roomid", equalTo(roomId));
		response.then().assertThat().body("booking.firstname", equalTo(fristName));
		response.then().assertThat().body("booking.lastname", equalTo(lastName));
		response.then().assertThat().body("booking.bookingdates.checkin", equalTo(checkinDate));
		response.then().assertThat().body("booking.bookingdates.checkout", equalTo(checkoutDate));
	}

	@Test(description = "To test scenario : When all valid data is passed booking should be created and API should return Response code 201 and booking ID.")
	public void createNewBookingWithTimeStampInDates() {

		String checkinDate = Instant.now().toString();
		String checkoutDate = Instant.now().toString().toString();
		String email = UtililtyFunctions.generateRandomEmail(8);
		String fristName = UtililtyFunctions.generateRandomStringWithAlphabetesOnly(8);
		String lastName = UtililtyFunctions.generateRandomStringWithAlphabetesOnly(8);
		String phoneNumber = UtililtyFunctions.generateRandomNumber(11);
		int roomId = Integer.parseInt(UtililtyFunctions.generateRandomNumber(3));
		response = BookingApi.createBooking(checkinDate, checkoutDate, email, fristName, lastName, phoneNumber, roomId);
		response.then().log().all();

		response.then().statusCode(201);
		response.then().assertThat().body("booking.roomid", equalTo(roomId));
		response.then().assertThat().body("booking.firstname", equalTo(fristName));
		response.then().assertThat().body("booking.lastname", equalTo(lastName));
	}

	@Test(description = "To test scenario : If a user tries to book room more than once for a given date, API should return response code 409")
	public void bookingSameRoomForSameDatesValidationTest() {
		curDate = LocalDate.now();
		String checkinDate = curDate.toString();
		String checkoutDate = curDate.plusDays(1).toString();
		String email = UtililtyFunctions.generateRandomEmail(8);
		String fristName = UtililtyFunctions.generateRandomStringWithAlphabetesOnly(8);
		String lastName = UtililtyFunctions.generateRandomStringWithAlphabetesOnly(8);
		String phoneNumber = UtililtyFunctions.generateRandomNumber(11);
		int roomId = Integer.parseInt(UtililtyFunctions.generateRandomNumber(3));
		response2 = BookingApi.createBooking(checkinDate, checkoutDate, email, fristName, lastName, phoneNumber,
				roomId);
		response = BookingApi.createBooking(checkinDate, checkoutDate, email, fristName, lastName, phoneNumber, roomId);
		response.then().log().all();

		response.then().statusCode(409);
	}

	@Test(description = "To test scenario : If check-in date is greater than the check-out date API should return response code 409")
	public void bookingWithCheckInDateGreaterThanCheckoutDateValidationTest() {
		curDate = LocalDate.now();

		String checkinDate = curDate.plusDays(1).toString();
		String checkoutDate = curDate.toString();
		String email = UtililtyFunctions.generateRandomEmail(8);
		String fristName = UtililtyFunctions.generateRandomStringWithAlphabetesOnly(8);
		String lastName = UtililtyFunctions.generateRandomStringWithAlphabetesOnly(8);
		String phoneNumber = UtililtyFunctions.generateRandomNumber(11);
		int roomId = Integer.parseInt(UtililtyFunctions.generateRandomNumber(3));
		response2 = BookingApi.createBooking(checkinDate, checkoutDate, email, fristName, lastName, phoneNumber,
				roomId);
		response = BookingApi.createBooking(checkinDate, checkoutDate, email, fristName, lastName, phoneNumber, roomId);
		response.then().log().all();

		response.then().statusCode(409);
	}

	@Test(description = "To test scenario : When Input for fristname field is invalid then API should return Response code 400.", dataProviderClass = RepositoryCreateBookingTestCase.class, dataProvider = "TestData")
	public void createNewBookingAPIValidationForFristNameField(String fristName, String validationMessage) {

		curDate = LocalDate.now();

		String checkinDate = curDate.toString();
		String checkoutDate = curDate.plusDays(1).toString();
		String email = UtililtyFunctions.generateRandomEmail(8);
		String lastName = UtililtyFunctions.generateRandomStringWithAlphabetesOnly(8);
		String phoneNumber = UtililtyFunctions.generateRandomNumber(11);
		int roomId = Integer.parseInt(UtililtyFunctions.generateRandomNumber(3));
		response = BookingApi.createBooking(checkinDate, checkoutDate, email, fristName, lastName, phoneNumber, roomId);
		response.then().log().all();
		response.then().statusCode(400);
		response.then().assertThat().body("errors.defaultMessage", hasItems(validationMessage));
	}

	@Test(description = "To test scenario : When Input for lastname field is invalid then API should return Response code 400.", dataProviderClass = RepositoryCreateBookingTestCase.class, dataProvider = "TestData")
	public void createNewBookingAPIValidationForLastNameField(String lastName, String validationMessage) {
		curDate = LocalDate.now();

		String checkinDate = curDate.toString();
		String checkoutDate = curDate.plusDays(1).toString();
		String email = UtililtyFunctions.generateRandomEmail(8);
		String fristName = UtililtyFunctions.generateRandomStringWithAlphabetesOnly(8);
		String phoneNumber = UtililtyFunctions.generateRandomNumber(11);
		int roomId = Integer.parseInt(UtililtyFunctions.generateRandomNumber(3));
		response = BookingApi.createBooking(checkinDate, checkoutDate, email, fristName, lastName, phoneNumber, roomId);
		response.then().log().all();
		response.then().statusCode(400);
		response.then().assertThat().body("errors.defaultMessage", hasItems(validationMessage));
	}

	@Test(description = "To test scenario : When Input for Email field is invalid then API should return Response code 400.", dataProviderClass = RepositoryCreateBookingTestCase.class, dataProvider = "TestData")
	public void createNewBookingAPIValidationForEmailFieldWithInvalidEmaildata(String email) {
		curDate = LocalDate.now();

		String checkinDate = curDate.toString();
		String checkoutDate = curDate.plusDays(1).toString();
		String lastName = UtililtyFunctions.generateRandomStringWithAlphabetesOnly(8);
		String fristName = UtililtyFunctions.generateRandomStringWithAlphabetesOnly(8);
		String phoneNumber = UtililtyFunctions.generateRandomNumber(11);
		int roomId = Integer.parseInt(UtililtyFunctions.generateRandomNumber(3));
		response = BookingApi.createBooking(checkinDate, checkoutDate, email, fristName, lastName, phoneNumber, roomId);
		response.then().log().all();
		response.then().statusCode(400);
		response.then().assertThat().body("errors.defaultMessage",
				hasItems(LoggingMessageContants.MUST_BE_A_WELL_FORMATED_EMAIL_ADDRESS));
	}

	@Test(description = "To test scenario : When Input for Phone field field is invalid then API should return Response code 400.", dataProviderClass = RepositoryCreateBookingTestCase.class, dataProvider = "TestData")
	public void createNewBookingAPIValidationForPhoneField(String phoneNumber, String validationMessage) {
		curDate = LocalDate.now();

		String checkinDate = curDate.toString();
		String checkoutDate = curDate.plusDays(1).toString();
		String email = UtililtyFunctions.generateRandomEmail(8);
		String fristName = UtililtyFunctions.generateRandomStringWithAlphabetesOnly(8);
		String lastName = UtililtyFunctions.generateRandomNumber(8);
		int roomId = Integer.parseInt(UtililtyFunctions.generateRandomNumber(3));
		response = BookingApi.createBooking(checkinDate, checkoutDate, email, fristName, lastName, phoneNumber, roomId);
		response.then().log().all();
		response.then().statusCode(400);
		response.then().assertThat().body("errors.defaultMessage", hasItems(validationMessage));
	}

	@Test(description = "To test scenario : When Input for checkin field is invalid then API should return Response code 400.", dataProviderClass = RepositoryCreateBookingTestCase.class, dataProvider = "TestData")
	public void createNewBookingAPIValidationForCheckInField(String checkinDate, String validationMessage) {

		curDate = LocalDate.now();
		String checkoutDate = curDate.plusDays(1).toString();
		String fristName = UtililtyFunctions.generateRandomStringWithAlphabetesOnly(8);

		String email = UtililtyFunctions.generateRandomEmail(8);
		String lastName = UtililtyFunctions.generateRandomStringWithAlphabetesOnly(8);
		String phoneNumber = UtililtyFunctions.generateRandomNumber(11);
		int roomId = Integer.parseInt(UtililtyFunctions.generateRandomNumber(3));
		response = BookingApi.createBooking(checkinDate, checkoutDate, email, fristName, lastName, phoneNumber, roomId);
		response.then().log().all();
		response.then().statusCode(400);
		response.then().assertThat().body("errors.defaultMessage", hasItems(validationMessage));
	}

	@Test(description = "To test scenario : When Input for CheckOut field is invalid then API should return Response code 400.", dataProviderClass = RepositoryCreateBookingTestCase.class, dataProvider = "TestData")
	public void createNewBookingAPIValidationForCheckOutField(String checkoutDate, String validationMessage) {

		curDate = LocalDate.now();

		String checkinDate = curDate.toString();
		String fristName = UtililtyFunctions.generateRandomStringWithAlphabetesOnly(8);
		String email = UtililtyFunctions.generateRandomEmail(8);
		String lastName = UtililtyFunctions.generateRandomStringWithAlphabetesOnly(8);
		String phoneNumber = UtililtyFunctions.generateRandomNumber(11);
		int roomId = Integer.parseInt(UtililtyFunctions.generateRandomNumber(3));
		response = BookingApi.createBooking(checkinDate, checkoutDate, email, fristName, lastName, phoneNumber, roomId);
		response.then().log().all();
		response.then().statusCode(400);
		response.then().assertThat().body("errors.defaultMessage", hasItems(validationMessage));
	}
}