package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GuestDao 
{
	public void reserveRoom(Connection con, Scanner sc) 
	{
		System.out.println("Enter Guest Name : ");
		String guestName = sc.next();
		sc.nextLine();
		
		System.out.println("Enter Room Number : ");
		int roomNumber = sc.nextInt();
		
		System.out.println("Enter Contact Number : ");
		String contactNumber = sc.next();
		sc.nextLine();
		
		String sql = "insert into reservations(guest_name, room_number, contact_number) values(?,?,?)";
		
		int check = 0;
		
		try(PreparedStatement pst = con.prepareStatement(sql);)
		{
			pst.setString(1, guestName);
			pst.setInt(2, roomNumber);
			pst.setString(3, contactNumber);
			
			check = pst.executeUpdate();
			
			if(check > 0 )
				System.out.println("Reservation Successful");
			else
				System.out.println("Reservation failed");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public void viewReservations(Connection con, Scanner sc)
	{
		String sql = "Select * from reservations";
		ResultSet rs = null;
		
		try(PreparedStatement pst = con.prepareStatement(sql);)
		{	
			rs = pst.executeQuery();
			
			System.out.println("Current Reservations:");
			System.out.println("*----------------*--------------*-------------*----------------*---------------------*");
			System.out.println("| Reservation ID |    Guest     | Room Number | Contact Number | Reservation Date    |");
			System.out.println("*----------------*--------------*-------------*----------------*---------------------*");
			
			while(rs.next())
			{
				int reservationId = (int)rs.getObject("reservation_id");
				String guestName = (String)rs.getObject("guest_name");
				int roomNumber = (int)rs.getObject("room_number");
				String contactNumber = (String)rs.getObject("contact_number");
				String reservationDate = (String) rs.getObject("reservation_date").toString();
				
				System.out.printf("| %-14d | %-12s |%-13d| %-14s |%-19s|\n", reservationId, guestName, roomNumber,contactNumber, reservationDate);
			}
			
			System.out.println("*----------------*--------------*-------------*----------------*---------------------*");
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public void getRoomNumber(Connection con, Scanner sc)
	{
		String sql = "Select room_number from reservations where reservation_id = ?";
		ResultSet rs = null;
		
		try(PreparedStatement pst = con.prepareStatement(sql);)
		{	
			System.out.println("Enter Reservation Id : ");
			int reservationId = sc.nextInt();
			 
			System.out.println("Enter Guest Name : ");
			String guestName = sc.next();
			
			pst.setInt(1, reservationId);
			rs = pst.executeQuery();
			
			if(rs.next())
			{
				int roomNumber = (int)rs.getObject("room_number");
				System.out.println("Room Number for Reservation ID : "+reservationId+" and Guest "+  guestName +" is : "+ roomNumber);
			}
			else
			{
				System.out.println("Reservation not Found for the given ID and Guest Name.");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void updateReservation(Connection con, Scanner sc)
	{
		String sql = "Update reservations set guest_name = ?, room_number = ?, contact_number =?  where reservation_id = ?";
		int check = 0;
		
		try(PreparedStatement pst = con.prepareStatement(sql);)
		{	
			System.out.println("Enter Reservation Id : ");
			int reservationId = sc.nextInt();
			 
			System.out.println("Enter new Guest Name : ");
			String newGuestName = sc.next();
			
			
			System.out.println("Enter new Contact Name : ");
			String newContactNumber = sc.next();
			
			
			System.out.println("Enter new Room Number : ");
			int newRoomNumber = sc.nextInt();
			
			pst.setString(1, newGuestName);
			pst.setInt(2, newRoomNumber);
			pst.setString(3, newContactNumber);
			pst.setInt(4, reservationId);
			check = pst.executeUpdate();
			
			if(check > 0)
			{
				System.out.println("Reservation updated Successfully");
			}
			else
			{
				System.out.println("Reservation Update failed.");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void deleteReservation(Connection con, Scanner sc)
	{
		
		int check = 0;
		
		System.out.println("Enter Reservation Id : ");
		int reservationId = sc.nextInt();
		
		String sql = "delete from reservations where reservation_id = ?";
		
		try(PreparedStatement pst = con.prepareStatement(sql))
		{	
			pst.setInt(1, reservationId);
			check = pst.executeUpdate();
			
			if(check > 0)
			{
				System.out.println("Reservation deleted Successfully.");
			}
			else
			{
				System.out.println("Reservation deletion failed.");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public boolean reservationExists(Connection con, int reservationId)
	{
		String sql = "select reservation_id from reservations where reservation_id = ?";
		ResultSet rs = null;
		
		try(PreparedStatement pst = con.prepareStatement(sql);)
		{	
			pst.setInt(1, reservationId);
			rs = pst.executeQuery();
			
			return rs.next();
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
	}

	public void exit() throws InterruptedException
	{
		System.out.println("Exiting System");
		int i = 5;
		
		while(i!=0)
		{
			System.out.print(".");
			Thread.sleep(450);
			i--;
		}
		System.out.println();
		System.out.println("Thank Youuu For Using Hotel Reservation System!!!!");
	}
}
