package com.system;

import java.sql.Connection;
import java.util.Scanner;

import com.dao.GuestDao;
import com.util.MyDatabase;

public class HotelManagementSystem {

	public void System()
	{
		GuestDao gd = new GuestDao();
		Connection con = MyDatabase.myConnection();
		while(true)
        {
        	System.out.println();
        	System.out.println("Hotel Management System");
        	Scanner sc = new Scanner(System.in);
        	System.out.println("1. Reserve a Room");
        	System.out.println("2. View Reservations");
        	System.out.println("3. Get Room Number");
        	System.out.println("4. Update Reservations");
        	System.out.println("5. Delete Reservations");
        	System.out.println("6. Exit");
        	System.out.println("Choose an Option: ");
        	
        	int choice = sc.nextInt();
        	switch(choice) 
        	{
        	case 1:
        		gd.reserveRoom(con, sc);
        		break;
        	case 2:
        		gd.viewReservations(con, sc);
        		break;
        	case 3:
        		gd.getRoomNumber(con, sc);
        		break;
        	case 4:
        		gd.updateReservation(con, sc);
        		break;
        	case 5:
        		gd.deleteReservation(con, sc);
        		break;
        	case 6:
        		try {
					gd.exit();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        		break;
        	}
        }
	}
}
