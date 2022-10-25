package serializers;

import java.io.*;
import java.util.ArrayList;

import models.Transaction;

public class TransactionSerializer {
    private static final String CSV_SEPARATOR = ",";

    public static void writeToTransactionCSV(Transaction transaction)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/transactionsData.csv", true)));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(transaction.getTID());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(transaction.getMovieGoersID());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(transaction.getBookingDate());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(transaction.getBookingTime());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(transaction.getCinemaCode());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(transaction.getSeatingNum());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(transaction.getPrice());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(transaction.getMovieID());

            bw.write(oneLine.toString());
            bw.newLine();
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {System.out.printf("'%s' %n", "Unsupported Encoding");}
        catch (FileNotFoundException e){System.out.printf("'%s' %n", "File Not Found"); }
        catch (IOException e){e.printStackTrace();}
    }

    public static ArrayList<Transaction> readFromTransactionCSV()
    {
        try {
            ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
            File file = new File("database/transactionsData.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
               tempArr = line.split(CSV_SEPARATOR);
               String TID = tempArr[0];
               int movieGoersID = Integer.valueOf(tempArr[1]);
               String bookingDate = tempArr[2];
               String bookingTime = tempArr[3];
               String cinemaCode = tempArr[4];
               int seatingNum = Integer.valueOf(tempArr[5]);
               double price = Double.valueOf(tempArr[6]);
               int movieID = Integer.valueOf(tempArr[7]);
               Transaction m = new Transaction(TID,movieGoersID,bookingDate,bookingTime,cinemaCode,seatingNum, price, movieID);
               transactionList.add(m);
            }
            br.close();
            return transactionList;
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
