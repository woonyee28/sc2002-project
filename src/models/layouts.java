package models;

import java.util.*;

public class layouts {
	
	private ArrayList<MovieSeat> seats;
	private int row; 
	private int rowType;
	private int TotalSeats;

	//rowType is Plat-gold,regular class
	public layouts (int rowType, int TotalSeats, int row)
	{
		this.rowType = rowType;
		this.TotalSeats = TotalSeats;
		this.row = row;
		//create array lists of Movieseats
		seats = new ArrayList<MovieSeat>();
		//Create the seats
		createSeats(this.TotalSeats);
	}
	
	public void setRowType(int rowType)
	{
		this.rowType = rowType;
	}

    public int getRowNumber()
	{
		return this.row;
	}
	
	public int getRowType()
	{
		return this.rowType;
	}
	
	public void createSeats(int TotalSeats)
	{
		for (int i = 1; i <= TotalSeats; i++)
		{
			seats.add(new MovieSeat(i));
		}
	}
	
	public ArrayList<MovieSeat> getSeats()
	{
		return seats;
	}
}