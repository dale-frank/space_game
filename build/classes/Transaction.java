
/**
 * ************************************
 *
 * Dale Frank CS 3 – 0119 - Java Programming Assignment 6 Transaction Class
 *
 ***********************************
 */

import java.io.Serializable;


public class Transaction implements Serializable {

  protected int transNumber;
  protected int transId;
  protected int checkNum;
  protected double transAmt;
  protected double serviceAmt;
  protected double lowBalanceCharge;

  public Transaction(int number, int id, double amount) {
    transNumber = number;
    transId = id;
    transAmt = amount;
  }

  public int getTransNumber() {
    return transNumber;
  }

  public int getTransId() {
    return transId;
  }

  public double getTransAmount() {
    return transAmt;
  }

  public double getServiceCharge() {
    return serviceAmt;
  }

  public double getLowBalanceCharge() {
    return lowBalanceCharge;
  }
}
