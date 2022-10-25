import java.util.ArrayList;

import javax.sql.rowset.RowSetFactory;

public class Theatre {
    // private MovieSeat[][] seat = new MovieSeat[50];

    private int numEmptySeat;
    private static int NUMOFSEAT = 50;
    private int row;
    // private ArrayList<MovieSeat> seats;
    private int theatreNumber;
    private ArrayList<layouts> rows;
    

    //Each Theatre have fixed number of seats, able to assign/unassign seats
    // using from MovieSeat structure.

    public Theatre(int theatreNumber)
    {
        this.theatreNumber = theatreNumber;
        rows = new ArrayList<layouts>();
    }

    public void createRows(int rowType, int TotalSeats, int row )
    {
        //for every  row, create total seats
        for (int i=1; i<=row; i++)
        {
            rows.add(new layouts(rowType,TotalSeats,i));
        }
        this.row += row;
    }
    public int getNumRow()
    {
        return this.row;
    }

	public ArrayList<layouts> getRows()
	{
		return rows;
	}

    public void printSeatPlan()
	{
		System.out.println();
        int maxSeatsInRow = 0;
        // System.out.println("test" + this.getRows().size());
        // for (layouts row : getRows())
        // {
        // 	if (row.getSeats().size() > maxSeatsInRow)
        // 	{
        // 		maxSeatsInRow = row.getSeats().size();
        // 	}	     
        // }
        // maxSeatsInRow = this.getRows().size();
        // System.out.print("   |");
        // for (int i=1; i <= maxSeatsInRow; i++)
        // {
        // 	System.out.print(" " + i);
        // }
        // System.out.print("\n");
        // System.out.print("----");
        // for (int i=1; i <= maxSeatsInRow; i++)
        // {
        // 	if (i>9) {
        // 		System.out.print("---");
        // 	}
        // 	else {
        // 		System.out.print("--");
        // 	}
        // }
        System.out.print("\n");
        int count =0;
        for (layouts row : getRows())
        {
            // for proper indentation as <=9 is single digit
        	if (row.getRowNumber()>9) { 
             
                System.out.print(row.getRowNumber() + " | ");
        	}
        	else {
        		System.out.print(row.getRowNumber() + "  | ");
        	}
            // for every seat in the row
        	for (MovieSeat seat : row.getSeats())
        	{
        		
        		if (!seat.isOccupied()) {
                     //if print by numbers, use the next line
                    System.out.print(seat.getSeatID() + " ");
        			// System.out.print("O" + " " ); 
        		}
        		else {
                   
        			System.out.print("X ");
                    
        		}
        	}
            System.out.println("");
        }
        System.out.println("");
	}
}
