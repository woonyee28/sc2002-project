package serializers;

import java.io.*;
import java.util.ArrayList;

import models.Cineplexes;

public class CineplexSerializer {
    private static final String CSV_SEPARATOR = ",";

    public static void writeToCineplexesCSV(Cineplexes cineplexes)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/cineplexesData.csv", true)));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(cineplexes.getCineplexCode());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(cineplexes.getName());
            oneLine.append(CSV_SEPARATOR);
            String cast = cineplexes.getCinemasCode().toString();
            cast = cast.replace(',', ';').substring(1, cast.length() - 1);
            oneLine.append(cast);

            bw.write(oneLine.toString());
            bw.newLine();
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {System.out.printf("'%s' %n", "Unsupported Encoding");}
        catch (FileNotFoundException e){System.out.printf("'%s' %n", "File Not Found"); }
        catch (IOException e){e.printStackTrace();}
    }
 
    public static ArrayList<Cineplexes> readFromCineplexesCSV()
    {
        try {
            ArrayList<Cineplexes> cineplexesList = new ArrayList<Cineplexes>();
            File file = new File("database/cineplexesData.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
               tempArr = line.split(CSV_SEPARATOR);
               String cineplexCode = tempArr[0];
               String name = tempArr[1];
               ArrayList<String> cinemasCode = new ArrayList<String>();
               String[] tempCast;
               tempCast = tempArr[2].split("; ");
               for (String s: tempCast) {           
                cinemasCode.add(s);
               }
               Cineplexes m = new Cineplexes(cineplexCode,name,cinemasCode);
               cineplexesList.add(m);
               
            }
            br.close();
            return cineplexesList;
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }  
}
