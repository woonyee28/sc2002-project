package tests;

import models.Transaction;
import serializers.TransactionSerializer;

public class testTransaction {
    public static void main(String[] args) {
        TransactionSerializer ts = new TransactionSerializer();
        Transaction transaction = new Transaction("BBB202210232031",1,"20221020","2100","BBB",3,17.0,0);
        ts.writeToCSV(transaction);
        for (Transaction m: ts.readFromCSV()) {           
            m.toString();
            System.out.println(m); 
        }
    }
}
