package tests;

import models.Transaction;
import serializers.TransactionSerializer;

public class testTransaction {
    public static void main(String[] args) {
        Transaction movie = new Transaction("BBB202210232031",1,"20221020","2100","BBB",3,17.0,0);
        TransactionSerializer.writeToTransactionCSV(movie);
        for (Transaction m: TransactionSerializer.readFromTransactionCSV()) {           
            m.toString();
            System.out.println(m); 
        }
    }
}
