package com.hellofresh.template;

import com.google.gson.Gson;

public class Booking {

	BookingDate bookingdates;
	Boolean depositepaid;
	String email;
	String firstname;
	String lastname;
	String phone;
	int roomid;

	static Booking booking = new Booking();

	public static String getBookingBodyJSON(String checkinDate, String checkoutDate, String email, String fristName,
			String lastName, String phoneNumber, int roomId) {
		booking.bookingdates = BookingDate.getBookingDatesJSON(checkinDate, checkoutDate);
		booking.email = email;
		booking.firstname = fristName;
		booking.lastname = lastName;
		booking.phone = phoneNumber;
		booking.roomid = roomId;
		return new Gson().toJson(booking);
	}
}