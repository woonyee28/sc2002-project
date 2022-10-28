package tests;

import models.Staff;
import models.StaffLogin;
import serializers.StaffSerializer;

public class testStaffLogin {
    public static void main(String[] args) {
    int x = StaffLogin.run("testing@gmail.com", "testing");
    System.out.println(x);
    }
    
}
