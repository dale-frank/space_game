
/**
 * ************************************
 *
 * Dale Frank CS 3 – 0119 - Java Programming Assignment 6 Checking AccountClass
 *
 ***********************************
 */
import java.io.Serializable;
import java.util.ArrayList;

public class CheckingAccount extends Account implements Serializable {

  //private double initialBalance;
  private double LowBalanceChrge;
  private double totalServiceCharge;
  private double transactAmount;
  private static int transCount = 0;
  private static int numAccounts = 0;
  private static boolean transFormCompare = false;
  private boolean tChange;
  private int transCompare = 0;
  public ArrayList<Transaction> transList;

  public CheckingAccount(String accountName, double balance) {

    super(accountName, balance);
    totalServiceCharge = 0;
    transList = new ArrayList<Transaction>();
  }

  // adds a transaction object to the transList
  public void addTrans(Transaction newTrans) {
    transList.add(newTrans);
  }

  public void addCheck(Check newCheck) {
    transList.add(newCheck);
  }

  public void addDeposit(Deposit newDeposit) {
    transList.add(newDeposit);
  }

  public ArrayList getTransList() {

    return transList;
  }

  public void settransCount() {
    transCount++;
  }

  public void settransCount(int count) {
    transCount = count;
  }

  public static void setNumAccounts(int acctCount) {
    numAccounts = acctCount;
  }

  //returns the current value of transCount;
  public boolean getTChange() {

    return tChange;
  }

  public void setTChange(boolean flag) {
    tChange = flag;
  }

  //returns the current value of transCount;
  public int gettransCount() {

    return transCount;
  }

  public void setTransFormCompare(boolean compare) {
    transFormCompare = compare;
  }

  //returns the current value of transCount;
  public boolean gettransFormCompare() {

    return transFormCompare;
  }

  // returns the i-th Transaction object in the list
  public Transaction getTrans(int i) {

    return transList.get(i);
  }

  public void setBalance(double transAmt, int tCode) {
    if (tCode == 2 || tCode == 7) {
      balance += +transAmt;
    } else if (tCode == 1) {
      balance += -transAmt;
    } else if (tCode == 6) {
      balance -= -transAmt;
    }
  }

  public void setLowBalanceCharge(double LowBalCharge) {
    LowBalanceChrge = LowBalCharge;

  }

  public double getLowBalanceCharge() {
    return LowBalanceChrge;
  }

  public void setServiceCharge(int tCode) {
    if (tCode == 1) {
      transactAmount = .15;
    } else if (tCode == 2) {
      transactAmount = .10;
    } else if (tCode == 3) {
      transactAmount = 5;
    } else if (tCode == 4) {
      transactAmount = 10;
    }
  }

  public double getServiceCharge() {
    return transactAmount;
  }

  public void setTotServiceCharge(double currentServiceCharge) {
    totalServiceCharge = currentServiceCharge;
  }

  public double getTotServiceCharge() {
    return totalServiceCharge;
  }

  void setFinBal(double finBal) {
    balance = finBal;
  }
}
