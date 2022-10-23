package models;

public class Transaction {
    private String TID;
    private int movieGoersID;
    private String bookingDate;
    private String bookingTime;
    private String cinemaCode;
    private int seatingNum;
    private double price;
    private int movieID;

    public Transaction(String TID, int movieGoersID, String bookingDate, String bookingTime, String cinemaCode, int seatingNum, double price, int movieID ){
        this.TID = TID;
        this.movieGoersID = movieGoersID;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.cinemaCode = cinemaCode;
        this.seatingNum = seatingNum;
        this.price = price;
        this.movieID = movieID;
    }

    // get methods
    public String getTID(){
        return this.TID;
    }

    public int getMovieGoersID(){
        return this.movieGoersID;
    }

    public String getBookingDate(){
        return this.bookingDate;
    }

    public String getBookingTime(){
        return this.bookingTime;
    }

    public String getCinemaCode(){
        return this.cinemaCode;
    }

    public int getSeatingNum(){
        return this.seatingNum;
    }

    public double getPrice(){
        return this.price;
    }

    public int getMovieID(){
        return this.movieID;
    }

    // set methods

    public void setTID(String TID){
        this.TID = TID;
    }

    public void setMovieGoersID(int movieGoersID){
        this.movieGoersID = movieGoersID;
    }

    public void setBookingDate(String bookingDate){
        this.bookingDate = bookingDate;
    }

    public void setBookingTime(String bookingTime){
        this.bookingTime = bookingTime;
    }

    public void setCinemaCode(String cinemaCode){
        this.cinemaCode = cinemaCode;
    }

    public void setSeatingNum(int seatingNum){
        this.seatingNum = seatingNum;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setMovieID(int movieID){
        this.movieID = movieID;
    }

    // Generate String representation of the Object
    private static final String CSV_SEPARATOR = ",";
    public String toString()
    {
        StringBuffer oneLine = new StringBuffer();
        oneLine.append(this.getTID());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getMovieGoersID());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getBookingDate());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getBookingTime());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getCinemaCode());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getSeatingNum());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getPrice());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getMovieID());

        return oneLine.toString();
    }
}
