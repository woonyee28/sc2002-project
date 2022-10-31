package programs;

import java.util.Scanner;

import managers.AdminManager;


public class UIAdminSignUp {
     private final String menuOptions[] = {
        "Create New Admin",
        "Exit Admin Signup"
    };

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
                    AdminManager.createAdmin();
				case 2:
					System.out.println("Exiting to the previous level...");
					break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
        }while (choice != 2);
    }
}
