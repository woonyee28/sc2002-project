package programs;

import java.util.Scanner;

import managers.*;
import models.*;
import serializers.*;

public class AdminApp {
    public static void main(String[] args) {
		AdminApp aApp = new AdminApp();
		aApp.run();
	}

    public void run(){
        int choice = -1;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("====================AdminApp======================\n");
			System.out.println("What would you like to do?");
			System.out.println("\t[1] Amend Bookings\n\t[2] Show movies, Book Tickets\n\t[3] Saales Report\n\t[4] Create new Movie Listing\n\t[5] Configure system settings");
			System.out.println("\t[6] Create new admin account\t[7] Manage Admins\t[8] Return to previous menu");
			choice = Integer.valueOf(sc.next());
			System.out.println();
			
            switch (choice) {
				case 1:
                    // Amend Booking, UIAmendBooking
					break;
				case 2:
					// Show movie listing + Book ticket, UIListingAndBooking
					break;
				case 3:
					UISalesReporting s = new UISalesReporting();
					s.run();
					break;
                case 4:
					// Create new movie listing / new session / change movie details, UINewListingSession
					break;
                case 5:
					// Config system setting, UIConfigSystem
					break;
                case 6:
					// sign up admin, UIAdminSignUp
                case 7:
					// manage admin, UIManageAdmin
					break;
				case 8:
					System.out.println("Exiting to the previous level...");
					break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
        }while (choice != 8);
    }
}
