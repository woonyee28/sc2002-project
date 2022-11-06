//@Author ZK
package models;
public class MovieSeat {
    private int seatID;
    private boolean assigned;
    private int customerId;

    /**
     * Initialise the variables of an instance of MovieSeat class
     * @param seat_id
     */
    public MovieSeat(int seat_id){
        this.seatID = seat_id;
        this.assigned = false;
        this.customerId =0;
    }

    /**
     * returns seat number
     * @return
     */
    public int getSeatID(){
        return this.seatID;
    }

    /**returns customerID occupying the seat */
    public int getCustomerID(){
        return customerId;
    }

    /**
     * checks if a seat is occupied or not
     * @return
     */
    public boolean isOccupied(){
        return assigned;
    }

    public void assign()
    {
        assigned = true;
    }

    public void assign(int cust_id){
        this.customerId = cust_id;
        assigned = true;
    }

    public void unAssign(){
        this.assigned = false;
        this.customerId = 0;
    }
    
}
