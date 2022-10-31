package programs;

import java.util.Scanner;

import com.apple.laf.resources.aqua;

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
        int adminID=0;
        

        do{
            System.out.println("====================MoblimaMainApp======================\n");
            System.out.println("Are you a:\n\t[1] Moblima Member\t[2] New User\t[3] Moblima Staff");
            choice = Integer.valueOf(sc.next());
            System.out.println();
            switch (choice) {
				case 1:
                    if(MemberManager.logIn()==1){
                        MemberApp m = new MemberApp();
                        m.run();
                    }
					break;
				case 2:
					MemberManager.signUp();
					break;
				case 3:
                    adminID = AdminManager.logIn();
                    if(adminID!=-1){
                        AdminApp a = new AdminApp(adminID);
                        a.run();
                    }
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
}