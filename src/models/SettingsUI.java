package models;
import serializers.HolidaySerializer;
import java.util.ArrayList;
import java.util.*;

public class SettingsUI {
    private ArrayList<Holiday> hList;

    public SettingsUI(){
        this.hList=HolidaySerializer.readFromHolidayCSV();
    }
    
    public boolean checkExistenceName(String name){
        boolean exists =false;
        for (Holiday h:this.hList){
            if (h.getName().equals(name)){
                exists =true;
                break;
            }
        }
        return exists;
    }

    public static void createNewHoliday(){
        SettingsUI create = new SettingsUI();
        String name = null,date =null;
        Scanner sc =new Scanner(System.in).useDelimiter("\n");
        System.out.println("Adding Holiday...");
        System.out.println("Please enter name of Holiday:");
        if(sc.hasNext()){
            name =sc.next();
        }
        if(!create.checkExistenceName(name)){
            System.out.println("Please enter date of new Holiday (dd-mm-yyy):");
            if(sc.hasNext()){
                date =sc.next();
            }
           Holiday newHol = new Holiday(name,date);
           HolidaySerializer.writeToHolidayCSV(newHol);
           System.out.println(name+ " Holiday succesfully created!");
        }else System.out.println(name + " Holiday creation unsuccesful!");
    }

    public static void updateHoliday(){
        SettingsUI update =new SettingsUI();
        String name=null,date=null;
        Scanner sc =new Scanner(System.in).useDelimiter("\n");
        System.out.println("Updating Holiday...");
        System.out.println("Please enter name of Holiday to be updated:");
        if(sc.hasNext()){
            name =sc.next();
        }
        if(update.checkExistenceName(name)){
            System.out.println("Please enter new date of Holiday (dd-mm-yyy):");
            if(sc.hasNext()){
                date =sc.next();
            }
          HolidaySerializer.updateHolidayFromCSV(name, date);
        }else System.out.println(name + " Holiday does not exists!");
    } 

    public static void deleteHoliday(){
        SettingsUI delete = new SettingsUI();
        String name = null;
        Scanner sc =new Scanner(System.in).useDelimiter("\n");
        System.out.println("Updating Holiday...");
        System.out.println("Please enter name of Holiday to be deleted:");
        if(sc.hasNext()){
            name =sc.next();
        }
        if(delete.checkExistenceName(name)){
          HolidaySerializer.deleteHolidayFromCSV(name);
        }else System.out.println(name + " Holiday does not exists!");
    }

    public static void printHolList(){
        for (Holiday h:HolidaySerializer.readFromHolidayCSV() ){
            h.toString();
            System.out.println(h);
        }

    }





}
