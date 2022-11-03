package tests;

import models.Staff;
import serializers.StaffSerializer;

public class testStaff {
    public static void main(String[] args) {
        String pw = "testing";
        StaffSerializer ss = new StaffSerializer();
        Staff staff = new Staff(1,"Testing","testing@gmail.com",String.valueOf(pw.hashCode()));
        ss.writeToCSV(staff);
        for (Staff m: ss.readFromCSV()) {           
            m.toString();
            System.out.println(m); 
        }
    }
}
