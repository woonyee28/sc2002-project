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

    }
    
    public static void modifySession(){

    }
}
