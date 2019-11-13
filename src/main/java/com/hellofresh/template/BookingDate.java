package com.hellofresh.template;

public class BookingDate {

	String checkin;
	String checkout;

	static BookingDate BookingDate = new BookingDate();

	public static BookingDate getBookingDatesJSON(String checkinDate, String checkoutDate) {
		BookingDate.checkin = checkinDate;
		BookingDate.checkout = checkoutDate;
		return BookingDate;
	}

}