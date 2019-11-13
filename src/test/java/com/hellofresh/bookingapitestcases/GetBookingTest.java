package com.hellofresh.bookingapitestcases;

import static org.hamcrest.Matchers.equalTo;

import java.time.LocalDate;

import org.testng.annotations.Test;

import com.hellofresh.api.BookingApi;
import com.hellofresh.util.UtililtyFunctions;

public class GetBookingTest extends BookingBaseTest {

	/*
	 * String checkinDate; String checkoutDate; String email; String fristName;
	 * String lastName; String phoneNumber; int roomId; int validBookingId; int
	 * inValidBookingId = 0;
	 */

	@Test(description = "To test scenario : Test API returning valid data for specific booking id and API should return Response code 200 and details for booking ID.")
	public void getValidBooking() {

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
		int validBookingId = response2.then().extract().path("bookingid");

		response = BookingApi.getSpecificBooking(validBookingId);
		response.then().log().all();
		response.then().statusCode(200);
		response.then().assertThat().body("bookingid", equalTo(validBookingId));
		response.then().assertThat().body("roomid", equalTo(roomId));
		response.then().assertThat().body("firstname", equalTo(fristName));
		response.then().assertThat().body("lastname", equalTo(lastName));
		response.then().assertThat().body("depositpaid", equalTo(false));
		response.then().assertThat().body("bookingdates.checkin", equalTo(checkinDate));
		response.then().assertThat().body("bookingdates.checkout", equalTo(checkoutDate));
	}

	@Test(description = "To test scenario : Test API response for invalid Booking ID and API should return Response code 404.")
	public void getInValidBooking() {
		response = BookingApi.getSpecificBooking(0);
		response.then().log().all();
		response.then().statusCode(404);
	}
}
