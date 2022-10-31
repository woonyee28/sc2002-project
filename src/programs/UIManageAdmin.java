package programs;
import java.util.ArrayList;
import java.util.Scanner;

import managers.AdminManager;
import models.*;
import serializers.*;

public class UIManageAdmin {
    private int adminID;

    public UIManageAdmin(int adminID){
        this.adminID=adminID;
    }

    private final String menuOptions[] = {
        "Print Admin List",
        "Update Admin",
        "Delete Admin",
        "Exit ManageAdmin"
    };

    public int getAdminID(){
        return this.adminID;
    }


    public void run(){
        int i,choice = -1;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("====================ManageAdmin======================\n");
            for (i = 1; i <= menuOptions.length; i++) {
                System.out.printf("(%d) %s \n", i, menuOptions[i-1]);
            }
			choice = Integer.valueOf(sc.next());
			System.out.println();
			
            switch (choice) {
				case 1:
                    // Prints admin list
                    AdminManager.printAdminList();
                    break;
                case 2:
                    // Update admin(change pass/email)
                    AdminManager.updateAdminPassword();
                    break;
                case 3:
                    // Delete admin
                   AdminManager.deleteAdmin(this.adminID);
                    break;
				case 4:
					System.out.println("Exiting to the previous level...");
					break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
        }while (choice != 4);
    }
}
