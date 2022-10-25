package serializers;

import java.io.*;
import java.util.ArrayList;

import models.Sessions;

public class SessionSerializer {
    private static final String CSV_SEPARATOR = ",";

    public static void writeToSessionCSV(Sessions session)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/sessionsData.csv", true)));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(session.getMovieID());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(session.getSessionDate());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(session.getSessionTime());
            oneLine.append(CSV_SEPARATOR);
            String cast = session.getSeatingPlan().toString();
            cast = cast.replace(',', ';').substring(1, cast.length() - 1);
            oneLine.append(cast);
            oneLine.append(CSV_SEPARATOR);

            bw.write(oneLine.toString());
            bw.newLine();
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {System.out.printf("'%s' %n", "Unsupported Encoding");}
        catch (FileNotFoundException e){System.out.printf("'%s' %n", "File Not Found"); }
        catch (IOException e){e.printStackTrace();}
    }
 
    public static ArrayList<Sessions> readFromSessionCSV()
    {
        try {
            ArrayList<Sessions> sessionList = new ArrayList<Sessions>();
            File file = new File("database/sessionsData.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
               tempArr = line.split(CSV_SEPARATOR);
               int movieID = Integer.valueOf(tempArr[0]);
               String sessionDate = tempArr[1];
               String sessionTime = tempArr[2];
               ArrayList<Integer> seatingPlan = new ArrayList<Integer>();
               String[] tempReviewID;
               tempReviewID = tempArr[3].split("; ");
               for (String s: tempReviewID) {           
                seatingPlan.add(Integer.valueOf(s));
               }
               Sessions m = new Sessions(movieID, sessionDate, sessionTime, seatingPlan);
               sessionList.add(m);
               
            }
            br.close();
            return sessionList;
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
