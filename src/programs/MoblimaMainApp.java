package programs;

import java.util.Scanner;

import managers.*;
import models.*;
import serializers.*;

public class MoblimaMainApp{
    public static void main(String[] args) {
		MoblimaMainApp app = new MoblimaMainApp();
		app.run();
	}

    public void run(){
        int choice = -1;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("====================MoblimaMainApp======================\n");
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
        MemberApp m = new MemberApp();
        m.run();
    }
    
    public void memberSignUp(){
        // Create Account then go back to memberLogIn
    }

    public void adminLogIn(){
        // Verify Password then call AdminApp
        AdminApp m = new AdminApp();
        m.run();
    }
}