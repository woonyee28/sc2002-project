package managers;

import models.*;
import serializers.*;
import java.util.*;

public class SessionManager {
    public static void printAllSessions(){
        for (Sessions m: SessionSerializer.readFromSessionCSV()) {           
            System.out.println(m.toString()); 
        }
    }

    public static void createNewSession(){
        MovieManager.printAllMovies();
        Scanner ii = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Movie Id: ");
        int movieID = ii.nextInt();
        System.out.println("Enter Session Date YYYYMMDD: ");
        String sessionDate = sc.nextLine();
        System.out.println("Enter Session Time HHMM: ");
        String sessionTime = sc.nextLine();
        ArrayList<Integer> seatingPlan = new ArrayList<Integer>();
        seatingPlan.add(-1);
        Sessions sess = new Sessions(movieID, sessionDate, sessionTime, seatingPlan);
        SessionSerializer.writeToSessionCSV(sess);
    }
    
    public static void modifySession(){

    }
}

