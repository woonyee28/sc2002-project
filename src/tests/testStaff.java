package tests;

import models.Staff;
import serializers.StaffSerializer;

public class testStaff {
    public static void main(String[] args) {
        String pw = "testing";
        Staff staff = new Staff(1,"Testing","testing@gmail.com",String.valueOf(pw.hashCode()));
        StaffSerializer.writeToStaffCSV(staff);
        for (Staff m: StaffSerializer.readFromStaffCSV()) {           
            m.toString();
            System.out.println(m); 
        }
    }
}
