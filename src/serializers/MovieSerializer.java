package serializers;

import java.io.*;
import java.util.ArrayList;

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
            String cast = movie.getCasts().toString();
            cast = cast.replace(',', ';').substring(1, cast.length() - 1);
            oneLine.append(cast);
            oneLine.append(CSV_SEPARATOR);
            String reviewsID = movie.getReviewsID().toString();
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
 
    public static ArrayList<Movie> readFromMovieCSV()
    {
        try {
            ArrayList<Movie> movieList = new ArrayList<Movie>();
            File file = new File("database/moviesData.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
               tempArr = line.split(CSV_SEPARATOR);
               int movieID = Integer.valueOf(tempArr[0]);
               String title = tempArr[1];
               String type = tempArr[2];
               String synopsis = tempArr[3];
               int rating = Integer.valueOf(tempArr[4]);
               String showingStatus = tempArr[5];
               String director = tempArr[6];
               ArrayList<String> cast = new ArrayList<String>();
               String[] tempCast;
               tempCast = tempArr[7].split("; ");
               for (String s: tempCast) {           
                cast.add(s);
               }
               ArrayList<Integer> reviewsID = new ArrayList<Integer>();
               String[] tempReviewID;
               tempReviewID = tempArr[8].split("; ");
               for (String s: tempReviewID) {           
                reviewsID.add(Integer.valueOf(s));
               }
               Movie m = new Movie(movieID,title,type,synopsis,rating,showingStatus,director,cast,reviewsID);
               movieList.add(m);
               
            }
            br.close();
            return movieList;
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void overwriteMovieCSV(ArrayList<Movie> aList) {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/moviesData.csv",false)));
			for(Movie movie:aList) {
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
                String cast = movie.getCasts().toString();
                cast = cast.replace(',', ';').substring(1, cast.length() - 1);
                oneLine.append(cast);
                oneLine.append(CSV_SEPARATOR);
                String reviewsID = movie.getReviewsID().toString();
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

    public static void updateMovieFromCSV(int movieID, String title, String type, String synopsis, int rating, String showingStatus, String director, ArrayList<String> cast, ArrayList<Integer> reviewsID) {
	    ArrayList<Movie> aList = MovieSerializer.readFromMovieCSV();
	    int flag =0;
	    for (Movie a:aList) {
		    if (a.getTitle().equals(title)) {
		    	a.setMovieID(movieID);
                a.setType(type);
                a.setSynopsis(synopsis);
                a.setRating(rating);
                a.setShowingStatus(showingStatus);
                a.setDirector(director);
                a.setCast(cast);
                a.setReviewID(reviewsID);
		    	flag=1;
		    	break;
	    	}
	    }
	    if (flag==1){
	        MovieSerializer.overwriteMovieCSV(aList);
	        System.out.println("Movie record, title = " +title+" successfully updated!");
	    } else System.out.println("Movie record, title = " +title+" update unsuccessful!");
    }

    public static void deleteMovieFromCSV(String title) {
        ArrayList<Movie> aList = MovieSerializer.readFromMovieCSV();
        int index=0,flag=0;
        for (Movie a:aList) {
            if (a.getTitle().equals(title)) {
                flag=1;
                break;
            }
            index++;
        }
        if (flag==1) {
            aList.remove(index);
            MovieSerializer.overwriteMovieCSV(aList);
            System.out.println("Movie record, title = " +title+" successfully updated!");
	    } else System.out.println("Movie record, title = " +title+" update unsuccessful!");
    }
}