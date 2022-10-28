package tests;

import models.StaffLogin;


public class testStaffLogin {
    public static void main(String[] args) {
    int x = StaffLogin.run("testing@gmail.com", "testing");
    System.out.println(x);
    }
    
}
