package com.hellofresh.bookingapitestcases;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.testng.annotations.Test;

import com.hellofresh.api.BookingApi;
import com.hellofresh.util.UtililtyFunctions;

public class GetAllBookingsTest extends BookingBaseTest {

	public int getBookingId() {
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
		response2.then().statusCode(201);
		return response2.then().extract().path("bookingid");
	}

	@Test(description = "To test scenario : to test added bookings are returned in API response. API should return response code 200 and booking details")
	public void testAllProductsAPIShouldReturnCompletedBookingDeatils() {
		int validBookingId1 = getBookingId();
		int validBookingId2 = getBookingId();
		response = BookingApi.getAllBookings();
		response.then().log().all().statusCode(200);
		response.then().body("bookings.bookingid", hasItems(validBookingId1, validBookingId2));

		List<Object> company1 = response.jsonPath().getList("bookings.bookingid");
		assertTrue(company1.size() > 2);
	}
}
