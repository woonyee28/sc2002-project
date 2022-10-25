package tests;
import models.Staff;
import models.StaffUI;
import serializers.StaffSerializer;

public class testStaffUI {
    public static void main(String[] args) {
        StaffUI.createNewStaff();
        StaffUI.printStaffList();
        StaffUI.deleteStaff();
       StaffUI.printStaffList();
    }

    
}
