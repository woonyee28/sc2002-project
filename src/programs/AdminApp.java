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
