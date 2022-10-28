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
                    sortByRating();
					break;
				case 2:
                    System.out.println("sortBySales():");
					sortBySales();
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

    public void sortByRating(){
        Map<Double,String> d = new TreeMap<Double,String>(Collections.reverseOrder());
        for (Movie m: MovieSerializer.readFromMovieCSV()) {           
            d.put(m.getRating(),m.getTitle());
        }
        Set set = d.entrySet();
        Iterator i = set.iterator();
        int c=0;
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            System.out.print(me.getValue() + ": ");
            System.out.println(me.getKey());
            c+=1;
            if (c==5){
                break;
            }
        }
    }

    public void sortBySales(){
        Map<Double, Integer> d = new TreeMap<Double,Integer>(Collections.reverseOrder());
        for (Transaction m: TransactionSerializer.readFromTransactionCSV()) {   
            int id = m.getMovieID();  
            Set<Double> prices = d.keySet();      
            if (d.containsValue(id)){
                Double ans=0.0;
                for( Double p : prices ){
                    if( d.get(p) == id){
                        ans = p;
                    }
                }
                Double price = ans+m.getPrice();
                d.remove(ans);
                d.put(price,id);
            }else{
                d.put(m.getPrice(),id);
            }
        }
        Set set = d.entrySet();
        Iterator i = set.iterator();
        int c=0;
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            for (Movie m: MovieSerializer.readFromMovieCSV()) {           
                if (me.getValue().equals(m.getMovieID())){
                    System.out.print(m.getTitle() + ": ");
                    break;
                }
            }
            System.out.println(me.getKey());
            c+=1;
            if (c==5){
                break;
            }
        }
    }
}
