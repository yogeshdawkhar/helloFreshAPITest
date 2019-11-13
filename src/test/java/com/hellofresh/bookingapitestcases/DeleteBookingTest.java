package com.hellofresh.bookingapitestcases;

import java.time.LocalDate;

import org.testng.annotations.Test;

import com.hellofresh.api.BookingApi;
import com.hellofresh.util.UtililtyFunctions;

public class DeleteBookingTest extends BookingBaseTest {

	int validBookingId;

	@Test(description = "To test scenario : Test API functionality to delete specific booking with booking id and API should return Response code 200 after delete is successful.")
	public void deleteValidBooking() {

		LocalDate curDate = LocalDate.now();

		String checkinDate = curDate.toString();
		String checkoutDate = curDate.plusDays(1).toString();
		String email = UtililtyFunctions.generateRandomEmail(8);
		String fristName = UtililtyFunctions.generateRandomStringWithAlphabetesOnly(8);
		String lastName = UtililtyFunctions.generateRandomStringWithAlphabetesOnly(8);
		String phoneNumber = UtililtyFunctions.generateRandomNumber(11);
		int roomId = Integer.parseInt(UtililtyFunctions.generateRandomNumber(3));
		response = BookingApi.createBooking(checkinDate, checkoutDate, email, fristName, lastName, phoneNumber, roomId);
		response.then().log().all();
		validBookingId = response.then().extract().path("bookingid");

		response = BookingApi.deleteSpecificBooking(validBookingId);
		response.then().statusCode(200);

	}

	@Test(description = "To test scenario : Test API functionality to delete deleted booking with booking id and API should return Response code 204 after delete is called second time.")
	public void deleteDeletedBooking() {

		LocalDate curDate = LocalDate.now();

		String checkinDate = curDate.toString();
		String checkoutDate = curDate.plusDays(1).toString();
		String email = UtililtyFunctions.generateRandomEmail(8);
		String fristName = UtililtyFunctions.generateRandomStringWithAlphabetesOnly(8);
		String lastName = UtililtyFunctions.generateRandomStringWithAlphabetesOnly(8);
		String phoneNumber = UtililtyFunctions.generateRandomNumber(11);
		int roomId = Integer.parseInt(UtililtyFunctions.generateRandomNumber(3));
		response2 = BookingApi.createBooking(checkinDate, checkoutDate, email, fristName, lastName, phoneNumber,
				roomId);
		response2.then().log().all();
		validBookingId = response2.then().extract().path("bookingid");
		BookingApi.deleteSpecificBooking(validBookingId);
		response = BookingApi.deleteSpecificBooking(validBookingId);
		response.then().statusCode(200);
	}

	@Test(description = "To test scenario : Test API functionality to delete invalid booking with invalid booking id and API should return Response code 204.")
	public void deleteInValidBooking() {

		LocalDate curDate = LocalDate.now();

		String checkinDate = curDate.toString();
		String checkoutDate = curDate.plusDays(1).toString();
		String email = UtililtyFunctions.generateRandomEmail(8);
		String fristName = UtililtyFunctions.generateRandomStringWithAlphabetesOnly(8);
		String lastName = UtililtyFunctions.generateRandomStringWithAlphabetesOnly(8);
		String phoneNumber = UtililtyFunctions.generateRandomNumber(11);
		int roomId = Integer.parseInt(UtililtyFunctions.generateRandomNumber(3));
		response2 = BookingApi.createBooking(checkinDate, checkoutDate, email, fristName, lastName, phoneNumber,
				roomId);
		response2.then().log().all();
		validBookingId = response2.then().extract().path("bookingid");
		BookingApi.deleteSpecificBooking(validBookingId);
		response = BookingApi.deleteSpecificBooking(validBookingId);
		response.then().statusCode(200);
	}
}
