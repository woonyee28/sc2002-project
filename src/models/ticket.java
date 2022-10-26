//@Author ZK
package models;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;  


public class ticket {
    MovieGoer_zk customer;
    MovieName movie;
    
    private double price;
    private String ticketType; // indicates what seating class

    private String ticketTime;
    private int transactionID;
    // public String cinema; // which cinema is it
    private int rowNo;
    private int seatNo;


    public ticket( MovieGoer_zk cust_id,  MovieName movie){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy"); 
        LocalDateTime now = LocalDateTime.now();
        this.customer =  cust_id;
        // this.ticketType = type;
        this.movie = movie;
        // ticketTime = time;

        this.ticketTime = dtf.format(now);
    }

    public double calculatePrice(){
        // Platinum - baseprice * 2 - type 3
        // Gold - baseprice * 1.5  - type 2
        //Regular - base price - type 1
        double baseprice = 8;
        // double price =0;

        if(movie.getTheatre().getRows().get(rowNo).getRowType()==1)
        {
            return baseprice;
        }
        else if (movie.getTheatre().getRows().get(rowNo).getRowType()==2)
        {
            return baseprice * 1.5;
        }
        else
        {
            return baseprice * 2;
        }
    }

    public void setSeatNumber(int seatNo)
    {
        this.seatNo = seatNo;
    }
    public  void setRowNumber(int rowNo)
    {
        this.rowNo = rowNo;

    }

    public MovieGoer_zk getMoviegoer()
    {
        return customer;
    }

    // public double getSeatPrice()
    // {
    //     return price;
    // }

    public String getTicketTime()
    {
        return ticketTime;
    }

    public MovieName getMovieName()
    {
        return movie;
    }

    // public String getType()
    // {
    //     return ticketType;
    // }

    // public int getSeatNum()
    // {
    //     return seatNo;
    // }

    //if do not want to return, can just print succesful messages
    public boolean reserveSeat(int rowSelected, int colSelected)
    {
        //seat is occupied.
        if(movie.getTheatre().getRows().get(rowSelected).getSeats().get(colSelected).isOccupied())
        {
            return false;
        }
        else
        {
            movie.getTheatre().getRows().get(rowSelected).getSeats().get(colSelected).assign();
            setSeatNumber(colSelected);
            setRowNumber(rowSelected);
            return true;
        }
    }

    public boolean unreserveSeat()
    {
        movie.getTheatre().getRows().get(rowNo).getSeats().get(seatNo).unAssign();
        return true;
    }

}