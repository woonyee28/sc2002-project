package programs;

import java.util.Scanner;

import managers.*;
import models.*;
import serializers.*;

public class MemberApp {
    public static void main(String[] args) {
		MemberApp mApp = new MemberApp();
		mApp.run();
	}

    public void run(){
        int choice = -1;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("You are in MemberApp\n");
			choice = Integer.valueOf(sc.next());
			System.out.println();
			
            switch (choice) {
                case 1:
                    // Booking History + review rating, UIBookHistAndReview
                    break;
				case 2:
                    // Amend Booking, UIAmendBooking
					break;
				case 3:
					// Show movie listing + Book ticket, UIListingAndBooking
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