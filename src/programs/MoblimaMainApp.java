package programs;

import java.util.Scanner;

import managers.*;
import models.*;
import serializers.*;

public class MoblimaMainApp{
    public static void main(String[] args) {
		MoblimaMainApp app = new MoblimaMainApp();
        System.out.println("Welcome to MOBLIMA Booking System!");
		app.run();
	}

    public void run(){
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Are you a:\n\t[1] Moblima Member\t[2] New User\t[3] Moblima Staff");

        do{
            choice = Integer.valueOf(sc.next());
			System.out.println();
            switch (choice) {
				case 1:
                    memberLogIn();
					break;
				case 2:
					memberSignUp();
					break;
				case 3:
					adminLogIn();
					break;
				case 4:
					System.out.println("Program exiting...");
					break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
        }while (choice != 4);
    }
    public void memberLogIn(){
        // Verify Password then call MemberApp
        Scanner input = new Scanner(System.in);
        System.out.println("Please key in your email ID:");
        String email = input.nextLine();
        System.out.println("Please key in your password:");
        String password = input.nextLine();

    }
    
    public void memberSignUp(){
        // Create Account then go back to memberLogIn
    }

    public void adminLogIn(){
        // Verify Password then call AdminApp
        Scanner input = new Scanner(System.in);
        System.out.println("Please key in your email ID:");
        String email = input.nextLine();
        System.out.println("Please key in your password:");
        String password = input.nextLine();

        StaffLogin user = new StaffLogin();
        boolean emailCheck = user.checkExistenceEmail(email);
        if (emailCheck==true){
            boolean passCheck = user.checkPassword(email, password);
            while (passCheck==false && x){
                System.out.println("Wrong Password! Please key in the correct password:");
                password = input.nextLine();
            }
            if (passCheck==true)
        }
    }
}