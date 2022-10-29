package programs;
import java.util.ArrayList;
import java.util.Scanner;
import models.*;
import serializers.*;

public class UIManageAdmin {

    private final String menuOptions[] = {
        "Print Admin List",
        "Update Admin",
        "Delete Admin",
        "Exit ManageAdmin"
    };
    public static void main(String[] args) {
		UIManageAdmin app = new UIManageAdmin();
		app.run();
	}

    public void run(){
        int i,choice = -1;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("====================ManageAdmin======================\n");
            for (i = 1; i <= menuOptions.length; i++) {
                System.out.printf("(%d) %s \n", i, menuOptions[i-1]);
            }
			choice = Integer.valueOf(sc.next());
			System.out.println();
			
            switch (choice) {
				case 1:
                    // Prints admin list
                    printAdminList();
                    break;
                case 2:
                    // Update admin(change pass/email)
                    updateAdmin();
                    break;
                case 3:
                    // Delete admin
                    deleteAdmin();
                    break;
				case 4:
					System.out.println("Exiting to the previous level...");
					break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
        }while (choice != 4);
    }

    public void printAdminList(){
        for(Staff s:StaffSerializer.readFromStaffCSV()){
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(s.getStaffID());
            oneLine.append(",");
            oneLine.append(s.getName());
            oneLine.append(",");
            oneLine.append(s.getEmail());
            System.out.println(oneLine);
        }

    }

    public void updateAdmin(){
        String name,email,pass;
        int staffID;
        int flag =-1,index=-1;
        ArrayList<Staff> sList = new ArrayList<Staff>();
        sList = serializers.StaffSerializer.readFromStaffCSV();
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Updating Staff...");
		System.out.println("Please enter id of Staff to be updated:");
        staffID = sc.nextInt();
        System.out.println("Please enter name of Staff to be updated:");
        name = sc.next();
        for (Staff s: sList){
            if (s.getName().equals(name) && s.getStaffID()==staffID){
                flag =1;
                index = sList.indexOf(s);
            }
        }
        
        if (flag==1 && index!=-1){
            System.out.println("Please enter new email:");
            email=sc.next();
            System.out.println("Please enter new password:");
            pass = sc.next();
            serializers.StaffSerializer.updateStaffFromCSV(staffID, name, email, String.valueOf(pass.hashCode()));

        } else System.out.println("staffID/name does not exists!");


        
    }

    public void deleteAdmin(){
		String name =null;
		int staffID =0;
        int flag =-1;
		Scanner sc = new Scanner(System.in).useDelimiter("\n");
		System.out.println("Deleting Staff...");
		System.out.println("Please enter id of Staff to be deleted:");
		staffID = sc.nextInt();
        System.out.println("Please enter name of Staff to be deleted:");
        name = sc.next();
        for(Staff s: serializers.StaffSerializer.readFromStaffCSV()){
             if (s.getStaffID()==staffID && s.getName().equals(name)){
                flag =1;
                StaffSerializer.deleteStaffFromCSV(staffID, name);
            }
        } 
        if (flag!=-1) System.out.println("staffID/name does not exists!");
    }
    
    
    
}
