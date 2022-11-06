package programs;

import java.util.Scanner;

import managers.AmendBookingManager;

public class UIAmendBooking {
    private int adminOrmember; // 1 == admin, 0 == member, -1 == Guest
    private int id; // -1 == Member

    public UIAmendBooking(int id, int adminOrmember){
        this.id = id;
        this.adminOrmember = adminOrmember;
    }


    protected String menuOptions[] = {
        "Cancel Booking",
        "Exit Amend Booking Page"
    };


    public void run(){
        int choice = -1;

        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("====================AdminSignUp======================\n");
            for (int i = 1; i <= menuOptions.length; i++) {
                System.out.printf("(%d) %s \n", i, menuOptions[i-1]);
            }
			choice = Integer.valueOf(sc.next());
			System.out.println();
            switch (choice) {
				case 1:
                    AmendBookingManager abm = new AmendBookingManager(id, adminOrmember);
                    abm.run();
				case 2:
					System.out.println("Program exiting...");
					break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
        }while (choice != 2);
        ;
    }
}
