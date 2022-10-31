package programs;

import java.util.Scanner;

import managers.*;
import models.*;
import serializers.*;

public class AdminApp {

	private int adminID;
	private final String menuOptions[] = {
        "Amend Booking",
        "Show Movie Listing",
        "Show Sales Report",
        "Create New Movie Listing",
		"Configure System Settings",
		"Create New Admin",
		"Manage Admins",
		"Exit Admin App"
	};

	public AdminApp(int adminID){
		this.adminID=adminID;
	}

	public int getAdminID(){
		return this.adminID;
	}


    public void run(){
        int i,choice = -1;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("====================AdminApp======================\n");
			for (i = 1; i <= menuOptions.length; i++) {
                System.out.printf("(%d) %s \n", i, menuOptions[i-1]);
            }
			choice = Integer.valueOf(sc.next());
			System.out.println();
			
            switch (choice) {
				case 1:
                    // Amend Booking, UIAmendBooking
					// Zheng Kai
					break;
				case 2:
					// Show movie listing + Book ticket, UIListingAndBooking
					// Zheng Kai
					break;
				case 3:
					UISalesReporting s = new UISalesReporting();
					s.run();
					break;
                case 4:
					// Create new movie listing / new session / change movie details, UINewListingSession
					// Woon Yee
					break;
                case 5:
					// Config system setting, UIConfigSystem
					UIConfigSystem configSystem = new UIConfigSystem();
					configSystem.run();
					break;
                case 6:
					// sign up admin, UIAdminSignUp
					UIAdminSignUp adminSignUp = new UIAdminSignUp();
					adminSignUp.run();
                case 7:
					// manage admin, UIManageAdmin
					UIManageAdmin manageAdmin = new UIManageAdmin(this.adminID);
					manageAdmin.run();
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
