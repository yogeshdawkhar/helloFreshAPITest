package com.hellofresh.api;

import static io.restassured.RestAssured.given;

import com.hellofresh.template.Booking;

import io.restassured.response.Response;

public class BookingApi {

	static final String booking = "/booking/";

	public static Response getAllBookings() {
		return given().contentType("application/json").log().all().when().get(booking);
	}

	public static Response getSpecificBooking(int bookingId) {
		return given().contentType("application/json").log().all().when()
				.get(String.format("%s/%s", booking, bookingId));
	}

	public static Response deleteSpecificBooking(int bookingId) {
		return given().cookie("session_id", "1234").log().all().when()
				.delete(String.format("%s/%s", booking, bookingId));
	}

	public static Response createBooking(String checkinDate, String checkoutDate, String email, String fristName,
			String lastName, String phoneNumber, int roomId) {
		return given().contentType("application/json").body(
				Booking.getBookingBodyJSON(checkinDate, checkoutDate, email, fristName, lastName, phoneNumber, roomId))
				.log().all().when().post(booking);
	}
}
