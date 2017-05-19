
/**
 * ************************************
 *
 * Dale Frank CS 3 – 0119 - Java Programming Assignment 6 Check Class
 *
 ***********************************
 */
public class Check extends Transaction {

  private int checkNumber; // check number for each check transaction

  public Check(int tId, int tCount, double tAmt, int checkNumber) {
    super(tId, tCount, tAmt);
    this.checkNumber = checkNumber;
  }

  public int getCheckNumber() {
    return checkNumber;
  }

  public void setCheckNumber(int checkNumber) {
    this.checkNumber = checkNumber;
  }
}