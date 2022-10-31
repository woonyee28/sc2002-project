package programs;

import java.util.Scanner;

import managers.SalesManager;

public class UISalesReporting {
    private final String menuOptions[] = {
        "Sort By Rating",
        "Sort By Sales",
        "Show Reviews",
        "Exit Sales Reporting"
    };

    public void run(){
        int choice = -1;

        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("====================UI Sales Reporting======================");
            int i;
            for (i = 1; i <= menuOptions.length; i++) {
                System.out.printf("(%d) %s \n", i, menuOptions[i-1]);
            }
            choice = Integer.valueOf(sc.next());
			
            switch (choice) {
				case 1:
                    System.out.println("sortByRating():");
                    SalesManager.sortByRating();
					break;
				case 2:
                    System.out.println("sortBySales():");
					SalesManager.sortBySales();
					break;
                case 3:
                    System.out.println("showReview()");
                    SalesManager.showReview();
                    break;
				case 4:
					System.out.println("Program exiting...");
					break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
        }while (choice != 4);
        sc.close();
    }
}
