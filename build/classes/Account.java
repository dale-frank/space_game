/**
 * ************************************
 *
 * Dale Frank CS 3 – 0119 - Java Programming Assignment 6 Account Class
 *
 ***********************************
 */

import java.io.Serializable;

public class Account implements Serializable
{
    protected String name; // The person who owns the account
    protected double balance;// do not define this in CheckingAccount class
 
    public Account(String acctName, double initBalance){
             balance = initBalance;
             name = acctName;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
    
 
    public double getBalance() {
        return balance;
    }
 
    public void setBalance(double balance) {
        this.balance = balance;
        
    }
    
    public String toString(){
      String acctName = new String(name + "'s account-\n");
      
      return acctName;
    }
}
