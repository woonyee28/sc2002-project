package programs;

import java.util.Scanner;

import managers.*;
import models.*;
import serializers.*;

public class MemberApp {
    
    public void run(){
        int choice = -1;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("====================MemberApp======================\n");
			System.out.println("What would you like to do?");
			System.out.println("\t[1] View your booking history and review ratings\n\t[2] Amend Bookings\n\t[3] View Movies and Book Tickets\n\t[4] View Sales\n\t[5] Go back to previous menu.");
			choice = Integer.valueOf(sc.next());
			System.out.println();
			
            switch (choice) {
                case 1:
                    // Booking History + review rating, UIBookHistAndReview
					// Woon Yee
                    break;
				case 2:
                    // Amend Booking, UIAmendBooking
					// Zheng Kai
					break;
				case 3:
					// Show movie listing + Book ticket, UIListingAndBooking
					// Zheng Kai
					break;
				case 4:
					UISalesReporting s = new UISalesReporting();
					s.run();
					break;
				case 5:
					System.out.println("Exiting to the previous level...");
					break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
        }while (choice != 5);
    }
}
