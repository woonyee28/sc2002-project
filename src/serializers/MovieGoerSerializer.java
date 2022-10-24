package serializers;

import java.io.*;
import java.util.ArrayList;

import models.MovieGoer;

public class MovieGoerSerializer {
    private static final String CSV_SEPARATOR = ",";

    public static void writeToMovieGoerCSV(MovieGoer movieGoer)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/movieGoersData.csv", true)));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(movieGoer.getMovieGoersID());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movieGoer.getName());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movieGoer.getEmail());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movieGoer.getAge());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movieGoer.getPasswordHashed());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movieGoer.getMobileNumber());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movieGoer.getTID_List());

            bw.write(oneLine.toString());
            bw.newLine();
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {System.out.printf("'%s' %n", "Unsupported Encoding");}
        catch (FileNotFoundException e){System.out.printf("'%s' %n", "File Not Found"); }
        catch (IOException e){e.printStackTrace();}
    }

    public static ArrayList<MovieGoer> readFromMovieGoerCSV()
    {
        try {
            ArrayList<MovieGoer> movieGoerList = new ArrayList<MovieGoer>();
            File file = new File("database/movieGoersData.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
               tempArr = line.split(CSV_SEPARATOR);
               int movieGoerID = Integer.valueOf(tempArr[0].replaceAll("\\uFEFF", ""));
               String name = tempArr[1];
               String email = tempArr[2];
               int age = Integer.valueOf(tempArr[3]);
               String passwordHashed = tempArr[4];
               int mobileNumber = Integer.valueOf(tempArr[5]);
               String TID_List = tempArr[6];
               
               MovieGoer m = new MovieGoer(movieGoerID,name,email,age,passwordHashed,mobileNumber,TID_List);
               movieGoerList.add(m);
               
            }
            br.close();
            return movieGoerList;
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
 