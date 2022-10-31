package managers;

import models.*;
import serializers.*;
import java.util.*;

public class AdminManager implements logIn{
    private ArrayList<Staff> sList;

    public AdminManager(){
        this.sList =StaffSerializer.readFromStaffCSV();
    }

    public ArrayList<Staff> getList(){
        return this.sList;
    }

    @Override
    public boolean checkPassword(String email, String hashedPassword){
        boolean correct = false;
        if (checkExistenceEmail(email)){
                for (Staff s: sList){
                    if (s.getEmail().equals(email) && s.getPasswordHashed().equals(hashedPassword)){
                        correct = true;
                    }
                }
        }


        return correct;
    }

    @Override
    public boolean checkExistenceEmail(String email){
        boolean exists = false;
        for (Staff s: this.sList){
            if (s.getEmail().equals(email)){
                exists = true;
                break;
            }
        }

        return exists;
    }

    public boolean checkExistenceName(String name){
        boolean exists =false;
        for (Staff s:this.sList){
            if(s.getName().equals(name)){
                exists =true;
                break;
            }
        }
        return exists;
    }

    public String checkName(String email){
        String name = email;
        for (Staff s: this.sList){
            if (s.getEmail().equals(email)){
                name = s.getName();
                break;
            }
        }
        return name;
    }

    public int checkExistenceID(){
        int largest = 0;
		for (Staff s:this.sList) {
			if (largest < s.getStaffID()) {
				largest = s.getStaffID();
			}
		}
        return largest;

    }

    public int getStaffID(String email){
        int staffID =-1;
        for (Staff s : this.sList){
            if(s.getEmail().equals(email)){
                staffID= s.getStaffID();
                break;
            }
        }
        return staffID;

    }

    public boolean checkStaffID(int staffID){
        boolean exists =false;
        for(Staff s:this.sList){
            if(s.getStaffID()==staffID){
                exists =true;
                break;            
            }
        }
        return exists;
    }

    public static int logIn(){
        AdminManager login = new AdminManager();
        Scanner input = new Scanner(System.in);
        System.out.println("Please key in your email:");
        String email = input.nextLine();
        System.out.println("Please key in your password:");
        String password = input.nextLine();
        if(!login.checkExistenceEmail(email)){
			System.out.println("Email does not exist!");
			return -1;
		}
		else {
			
			if(login.checkPassword(email,String.valueOf(password.hashCode()))){
				String name = login.checkName(email);
                System.out.println("Logging in...");
				System.out.println("Welcome "+ name);
				return login.getStaffID(email);
			}
			else {
				System.out.println("Wrong password!");
				return -1;
			}
		}
    }

    

    public static int createAdmin(){
        AdminManager create = new AdminManager();
        int staffID = 0;
        String name=null;
        String email=null;
        String password = null;
        String passwordHashed = null;
     
		
        System.out.println("--------- Create an admin account ---------");
        Scanner input1 = new Scanner(System.in);
        System.out.println("Please enter your email:");
        email = input1.nextLine();

        if (!create.checkExistenceEmail(email)){
            staffID = create.checkExistenceID() + 1;
            System.out.println("Please enter your name:");
            name = input1.nextLine();

            Scanner input2 = new Scanner(System.in); 
            System.out.println("Please enter your password:");
            password = input2.nextLine();
            
            passwordHashed = String.valueOf(password.hashCode());

            Staff newStaff = new Staff(staffID, name, email, passwordHashed);
            serializers.StaffSerializer.writeToStaffCSV(newStaff);
            System.out.println("Admin account successfully created.");
            return 1;
        }
        else{
            System.out.println("Account already exists!");
        }
        return 0;
		
    }

    public static void printAdminList(){
        AdminManager print = new AdminManager();
        System.out.println("--------- Admin list ---------");
        for (Staff s:print.getList()){
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(s.getStaffID());
            oneLine.append(",");
            oneLine.append(s.getName());
            oneLine.append(",");
            oneLine.append(s.getEmail());
            System.out.println(oneLine);
        }
    }

    public static void updateAdminPassword(){
        String email=null,password,passwordHashed=null,name;
        int staffID;
        AdminManager update = new AdminManager();
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);

        System.out.println("--------- Update admin password ---------");
        System.out.println("Please enter your staffID:");
        staffID=input1.nextInt();

        if(update.checkStaffID(staffID)){
            System.out.println("Please enter admin name:");
            name=input2.nextLine();

            if(update.checkExistenceName(name)){
                System.out.println("Please enter email:");
                email=input2.nextLine();

                if (update.checkExistenceEmail(email)){
                    System.out.println("Please enter new password:");
                    password=input2.nextLine();

                    passwordHashed = String.valueOf(password.hashCode());
                    serializers.StaffSerializer.updateStaffFromCSV(staffID, name, email, passwordHashed);

                }else System.out.println("Admin email does not found!");
            }else System.out.println("Admin name does not found!");
        }else System.out.println("Admin ID does not found!");
    }

    public static void deleteAdmin(int accountHolderID){
        int staffID;
        String name,confirm;
        AdminManager delete = new AdminManager();
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);

        System.out.println("--------- Delete admin account ---------");
        System.out.println("Please enter your staffID:");
        staffID=input1.nextInt();

        if (delete.checkStaffID(staffID)){
            if(accountHolderID!=staffID){
                System.out.println("Please enter admin name:");
                name=input2.nextLine();
                if (delete.checkExistenceName(name)){
                    System.out.println("Press y/n to confirm deletion ");
                    confirm =input2.next();
                    if(confirm.equals("y")){
                        serializers.StaffSerializer.deleteStaffFromCSV(staffID, name);
                    } else System.out.println("Deletion aborted.");
                }else System.out.println("Admin name not found.");
            }else System.out.println("Error! Cannot delete currently logged in admin!");
        }else System.out.println("Admin ID not found.");
    }
    
}
