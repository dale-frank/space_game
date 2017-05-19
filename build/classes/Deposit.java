/**
 * ************************************
 *
 * Dale Frank CS 3 – 0119 - Java Programming Assignment 6 Deposit Class
 *
 ***********************************
 */

public class Deposit extends Transaction
{
    private double check; // check deposit amount
    private double cash; // check deposit amount
    
 
    public Deposit(int tId, int tCount, double tAmt, double check, double cash) {
        super(tId, tCount, tAmt);
        this.check = check;
        this.cash = cash;
    }
 
    public double getCashAmount() {
        return cash;
    }
 
    public void setCashAmount(int cash) {
        this.cash = cash;
    }
    public double getCheckAmount() {
        return check;
    }
 
    public void setCheckAmount(int check) {
        this.check = check;
    }
}