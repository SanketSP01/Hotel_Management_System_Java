package com.model;

public class Guest {

	private int reservation_id;
	private String guest_name;
	private int room_number;
	private String contact_number;
	private String reservation_date;
	@Override
	public String toString() {
		return "Customer [reservation_id=" + reservation_id + ", guest_name=" + guest_name + ", room_number="
				+ room_number + ", contact_number=" + contact_number + ", reservation_date=" + reservation_date + "]";
	}
	public int getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}
	public String getGuest_name() {
		return guest_name;
	}
	public void setGuest_name(String guest_name) {
		this.guest_name = guest_name;
	}
	public int getRoom_number() {
		return room_number;
	}
	public void setRoom_number(int room_number) {
		this.room_number = room_number;
	}
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	public String getReservation_date() {
		return reservation_date;
	}
	public void setReservation_date(String reservation_date) {
		this.reservation_date = reservation_date;
	}
}
