//@Author ZK
package models;
public class MovieSeat {
    private int seatID;
    private boolean assigned;
    private int customerId;


    public MovieSeat(int seat_id){
        this.seatID = seat_id;
        this.assigned = false;
        this.customerId =0;
    }

    public int getSeatID(){
        return this.seatID;
    }

    public int getCustomerID(){
        return customerId;
    }

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
