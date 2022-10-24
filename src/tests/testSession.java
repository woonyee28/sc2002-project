package tests;

import java.util.ArrayList;

import models.Sessions;
import serializers.SessionSerializer;

public class testSession {
    public static void main(String[] args) {
        ArrayList<Integer> id = new ArrayList<Integer>();
        id.add(11);
        id.add(21);
        Sessions session = new Sessions(0,"20221119","2000",id);
        SessionSerializer.writeToSessionCSV(session);
        for (Sessions m: SessionSerializer.readFromSessionCSV()) {           
            m.toString();
            System.out.println(m); 
        }
    }
}
