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

public class UIBookHistAndReview {
    private int movieGoerID;

    public UIBookHistAndReview(int movieGoerID){
        this.movieGoerID = movieGoerID;
    }

    private final String menuOptions[] = {
        "Show Booking History",
        "Input Review And Rating",
        "Exit to previous level"
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
                    System.out.println("showBookingHistory():");
                    BookingHistManager.showBookingHistory(this.movieGoerID);
					break;
				case 2:
                    System.out.println("inputReviewAndRating():");
					BookingHistManager.inputReviewAndRating(this.movieGoerID);
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
