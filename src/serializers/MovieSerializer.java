package serializers;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import models.Movie;

public class MovieSerializer{

    private static final String CSV_SEPARATOR = ",";

    public static void writeToMovieCSV(Movie movie)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/moviesData.csv", true)));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(movie.getMovieID());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movie.getTitle());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movie.getType());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movie.getSynopsis());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movie.getRating());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movie.getShowingStatus());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movie.getDirector());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movie.getCasts());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movie.getReviewsID());

            bw.write(oneLine.toString());
            bw.newLine();
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){System.out.printf("'%s' %n", "File Not Found"); }
        catch (IOException e){}
    }
}