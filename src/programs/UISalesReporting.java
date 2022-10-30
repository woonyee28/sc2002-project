package programs;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import managers.*;
import models.*;
import serializers.*;
import managers.*;

public class UISalesReporting {
    private final String menuOptions[] = {
        "Sort By Rating",
        "Sort By Sales",
        "Exit Sales Reporting"
    };
    public static void main(String[] args) {
		UISalesReporting s = new UISalesReporting();
		s.run();
	}

    public void run(){
        int choice = -1;
        System.out.println("====================UI Sales Reporting======================");
        int i;
        for (i = 1; i <= menuOptions.length; i++) {
            System.out.printf("(%d) %s \n", i, menuOptions[i-1]);
        }
        Scanner sc = new Scanner(System.in);

        do{
			
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
					System.out.println("Program exiting...");
					break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
        }while (choice != 3);
    }
}
