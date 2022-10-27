package serializers;

import java.io.*;
import java.util.ArrayList;

import models.Cinemas;

public class CinemaSerializer {
    private static final String CSV_SEPARATOR = ",";

    public static void writeToCinemaCSV(Cinemas cinema)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/cinemasData.csv", true)));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(cinema.getCinemaCode());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(cinema.getCinemaClass());
            oneLine.append(CSV_SEPARATOR);
            String cast = cinema.getSeatingPlan().toString();
            cast = cast.replace(',', ';').substring(1, cast.length() - 1);
            oneLine.append(cast);
            oneLine.append(CSV_SEPARATOR);
            String reviewsID = cinema.getSessionsID().toString();
            reviewsID = reviewsID.replace(',', ';').substring(1, reviewsID.length() - 1);
            oneLine.append(reviewsID);

            bw.write(oneLine.toString());
            bw.newLine();
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {System.out.printf("'%s' %n", "Unsupported Encoding");}
        catch (FileNotFoundException e){System.out.printf("'%s' %n", "File Not Found"); }
        catch (IOException e){e.printStackTrace();}
    }
 
    public static ArrayList<Cinemas> readFromCinemaCSV()
    {
        try {
            ArrayList<Cinemas> cinemaList = new ArrayList<Cinemas>();
            File file = new File("database/cinemasData.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
               tempArr = line.split(CSV_SEPARATOR);
               String cinemaCode = tempArr[0];
               String cinemaClass = tempArr[1];
               ArrayList<Integer> seatingPlan = new ArrayList<Integer>();
               String[] tempReviewID;
               tempReviewID = tempArr[2].split("; ");
               for (String s: tempReviewID) {           
                seatingPlan.add(Integer.valueOf(s));
               }
               ArrayList<String> sessionsID = new ArrayList<String>();
               String[] tempCast;
               tempCast = tempArr[3].split("; ");
               for (String s: tempCast) {           
                sessionsID.add(s);
               }
               Cinemas m = new Cinemas(cinemaCode,cinemaClass,seatingPlan,sessionsID);
               cinemaList.add(m);
               
            }
            br.close();
            return cinemaList;
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }


public static void overwriteCinemasCSV(ArrayList<Cinemas> aList) {
    try {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/cinemasData.csv",false)));
        for(Cinemas cinema:aList) {
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(cinema.getCinemaCode());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(cinema.getCinemaClass());
            oneLine.append(CSV_SEPARATOR);
            String cast = cinema.getSeatingPlan().toString();
            cast = cast.replace(',', ';').substring(1, cast.length() - 1);
            oneLine.append(cast);
            oneLine.append(CSV_SEPARATOR);
            String reviewsID = cinema.getSessionsID().toString();
            reviewsID = reviewsID.replace(',', ';').substring(1, reviewsID.length() - 1);
            oneLine.append(reviewsID);

            bw.write(oneLine.toString());
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
    catch (UnsupportedEncodingException e) {System.out.printf("'%s' %n", "Unsupported Encoding");}
    catch (FileNotFoundException e){System.out.printf("'%s' %n", "File Not Found"); }
    catch (IOException e){e.printStackTrace();}

}

public static void updateCinemasFromCSV(String cinemaCode, String cinemaClass, ArrayList<Integer> seatingPlan, ArrayList<String> sessionsID) {
    ArrayList<Cinemas> aList = CinemaSerializer.readFromCinemaCSV();
    int flag =0;
    for (Cinemas a:aList) {
        if (a.getCinemaCode().equals(cinemaCode)) {
            a.setCinemaClass(cinemaClass);
            a.setSeatingPlan(seatingPlan);
            a.setSessionsID(sessionsID);
            flag=1;
            break;
        }
    }
    if (flag==1){
        CinemaSerializer.overwriteCinemasCSV(aList);
        System.out.println("Cinema record, cinemaCode = " +cinemaCode+" successfully updated!");
    } else System.out.println("Cinema record, cinemaCode = " +cinemaCode+" update unsuccessful!");
}

public static void deleteCinemasFromCSV(String cinemaCode) {
    ArrayList<Cinemas> aList = CinemaSerializer.readFromCinemaCSV();
    int index=0,flag=0;
    for (Cinemas a:aList) {
        if (a.getCinemaCode().equals(cinemaCode)) {
            flag=1;
            break;
        }
        index++;
    }
    if (flag==1) {
        aList.remove(index);
        CinemaSerializer.overwriteCinemasCSV(aList);
        System.out.println("Cinema record, cinemaCode = " +cinemaCode+" successfully updated!");
    } else System.out.println("Cinema record, cinemaCode = " +cinemaCode+" update unsuccessful!");
}
}