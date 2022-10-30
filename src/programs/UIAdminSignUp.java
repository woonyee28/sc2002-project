package programs;

import java.util.Scanner;
import models.*;
import serializers.*;

public class UIAdminSignUp {
     private final String menuOptions[] = {
        "Create New Admin",
        "Exit Admin Signup"
    };
    public static void main(String[] args) {
		UIAdminSignUp app = new UIAdminSignUp();
		app.run();
	}

    public void run(){
        int i,choice = -1;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("====================AdminSignUp======================\n");
            for (i = 1; i <= menuOptions.length; i++) {
                System.out.printf("(%d) %s \n", i, menuOptions[i-1]);
            }
			choice = Integer.valueOf(sc.next());
			System.out.println();
            
			
            switch (choice) {
				case 1:
                    adminSignUp();
				case 2:
					System.out.println("Exiting to the previous level...");
					break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
        }while (choice != 2);
    }

    public void adminSignUp(){
		String email=null,name=null,password=null,hashedPassword =null;
		int staffID ,flag=-1;
        
		Scanner sc = new Scanner(System.in);
		System.out.println("Creating new Staff...");
		System.out.println("Please enter staffID:");
		staffID =sc.nextInt();
        System.out.println("Please enter your name:");
        name =sc.next();
        System.out.println("Please enter email:");
        email =sc.next();
        System.out.println("Please enter password:");
        password =sc.next();
        for (Staff s: serializers.StaffSerializer.readFromStaffCSV()){
            if(s.getStaffID()==staffID){
                System.out.print("staffID already exists!");
                flag=1;
            }
            if (s.getEmail().equals(email)) {
                System.out.print("Email already in use!");
                flag =1;
            }
            
        }
        if (flag!=1){
            hashedPassword = String.valueOf(password.hashCode());
            Staff newStaff = new Staff(staffID,name,email,hashedPassword); 
            StaffSerializer.writeToStaffCSV(newStaff);
			System.out.println("New Staff user succesfully added");

        }
    }
}
