
/**
 * ************************************
 *
 * Dale Frank CS 3 – 0119 - Java Programming Assignment 6 Choices Frame Class
 *
 ***********************************
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

import javax.swing.*;

public class ChoicesFrame extends WindowEventListen {

  public static final int WINDOWWIDTH = 1100;
  public static final int WINDOWHEIGHT = 800;
  private JMenu fileMenu, accountMenu, transMenu, gameMenu;
  private JMenuItem readFile, writeFile, addAcct, listTrans, listDep, listCheck, listGame, findAcct, listAcct, getBal, addTrans, startGame;
  private JMenuBar menBar;
  private JLabel input;
  public static JFrame frame;
  private static MenuBar MenuBar;
  public static Account accountHolder;
  public static CheckingAccount TransCheckAccount;
  public static Vector<CheckingAccount> accountList;
  public static Vector<Account> acctList;
  public static String filename;
  String enterBalance, message;
  String customerName;
  int transactionCode;
  double transactionAmount;
  double initialBalance;
  public static boolean changed = false;

  public ChoicesFrame(String windowTitle) {
    super(windowTitle);
    //setSize(WINDOWWIDTH, WINDOWHEIGHT);
    //setPreferredSize(new Dimension(WINDOWWIDTH, WINDOWHEIGHT));
    setLocation(55, 0);
    //setBackground(Color.black);
    setForeground(Color.red);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    fileMenu = new JMenu("File");

    MenuListen menuLis = new MenuListen();

    readFile = new JMenuItem("Read from file");
    readFile.addActionListener(menuLis);
    fileMenu.add(readFile);

    writeFile = new JMenuItem("Write to file");
    writeFile.addActionListener(menuLis);
    fileMenu.add(writeFile);

    accountMenu = new JMenu("Account");

    addAcct = new JMenuItem("Add new account");
    addAcct.addActionListener(menuLis);
    accountMenu.add(addAcct);

    listTrans = new JMenuItem("List acccount transactions");
    listTrans.addActionListener(menuLis);
    accountMenu.add(listTrans);

    listCheck = new JMenuItem("List all checks");
    listCheck.addActionListener(menuLis);
    accountMenu.add(listCheck);

    listDep = new JMenuItem("List all deposits");
    listDep.addActionListener(menuLis);
    accountMenu.add(listDep);
    
    listGame = new JMenuItem("List game winnings/losses");
    listGame.addActionListener(menuLis);
    accountMenu.add(listGame);

    findAcct = new JMenuItem("Find an account");
    findAcct.addActionListener(menuLis);
    accountMenu.add(findAcct);

    listAcct = new JMenuItem("List Accounts");
    listAcct.addActionListener(menuLis);
    accountMenu.add(listAcct);
    
    getBal = new JMenuItem("Get Balance");
    getBal.addActionListener(menuLis);
    accountMenu.add(getBal);

    transMenu = new JMenu("Transactions");
    addTrans = new JMenuItem("Add transactions");
    addTrans.addActionListener(menuLis);
    transMenu.add(addTrans);

    gameMenu = new JMenu("Superheroes");
    startGame = new JMenuItem("Save the World");
    startGame.addActionListener(menuLis);
    gameMenu.add(startGame);


    menBar = new JMenuBar();
    menBar.add(fileMenu);
    menBar.add(accountMenu);
    menBar.add(transMenu);
    menBar.add(gameMenu);
    setJMenuBar(menBar);


    accountList = new Vector<CheckingAccount>();
  }

  private class MenuListen implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
      double balance;
      int typeOfList;
      String source = event.getActionCommand();

      if (source.equals("Read from file")) {
        //clears text area on main window
        AccountWindow.transListArea.setText("");
        readFile();
      } else if (source.equals("Write to file")) {
        AccountWindow.transListArea.setText("");
        writeFile();
        //List all transactions
      } else if (source.equals("Add new account")) {
        AccountWindow.transListArea.setText("");
        newAccount();
      } else if (source.equals("List acccount transactions")) {
        AccountWindow.transListArea.setText("");
        typeOfList = 1;
        listTransactions(typeOfList);
        Game startGame = new Game();
      } else if (source.equals("List all deposits")) {
        AccountWindow.transListArea.setText("");
        typeOfList = 3;
        listTransactions(typeOfList);
      } else if (source.equals("List all checks")) {
        AccountWindow.transListArea.setText("");
        typeOfList = 2;
        listTransactions(typeOfList);
      } else if (source.equals("List game winnings/losses")) {
        typeOfList = 6;
        AccountWindow.transListArea.setText("");
        listTransactions(typeOfList);
      }else if (source.equals("Get Balance")) {
        AccountWindow.transListArea.setText("");
        showBalance();
      }
      else if (source.equals("Find an account")) {
        AccountWindow.transListArea.setText("");
        findAccounts();
      }else if (source.equals("List Accounts")) {
        listAccounts();
      } else if (source.equals("Add transactions")) {
        AccountWindow.transListArea.setText("");
        runTransaction();
      } else if (source.equals("Save the World")) {
        AccountWindow.transListArea.setText("");
        startGame();
        //ChoicesFrame.runGameTrans(0);
      }
    }
  }

  public static void frameHide() {
    AccountWindow.frame.setVisible(false);
  }

  public static void frameShow() {
    AccountWindow.frame.setVisible(true);
  }

  public static void startGame() {  
    if(TransCheckAccount != null){
    JavaA6Superheroes.gameStart();
    frameHide();
    }else {
      AccountWindow.transListArea.setText("\n\n    You must select an account from a file or start a new account before you can save the world.");
    }
  }
  
  public static boolean endGame() {
    boolean gameEnd;
    gameEnd = true;
    return gameEnd;
  }
  


  public static void newAccount() {
    String acctName;
    double acctBal;
    acctName = enterName();
    acctBal = enterBalance(false);
    //accountHolder = new Account(acctName, acctBal);
    TransCheckAccount = new CheckingAccount(acctName, acctBal);
    accountList.add(TransCheckAccount);
    CheckingAccount.setNumAccounts(accountList.size());
    TransCheckAccount.settransCount(0);

    AccountWindow.transListArea.setText("\n\n    Account created for " + acctName);

  }

  public static void listAccounts() {
    String lineOutput = "";
    String name;
    String printList;
    lineOutput += "\n    ";
    lineOutput += "Current Accounts: \n";
    // create List
    List<String> sortList = new ArrayList<String>();
    for (Account list : accountList) {
      name = list.getName();
      sortList.add(name);
    }
    Collections.sort(sortList);
    printList = sortList.toString();
    String formatted = printList.replace(",", "\n   ");
    String formatted1 = formatted.replace("[", "    ");
    String formatted2 = formatted1.replace("]", "");
    lineOutput += formatted2;
    AccountWindow.transListArea.setText(lineOutput);
  }

  public static void findAccounts() {
    String name, displayNm;
    String searchNm = JOptionPane.showInputDialog("Enter the account name:");
    CheckingAccount c = TransCheckAccount;
    Account a = (Account) c;
    //for each to get all accounts/names
    for (Account list : accountList) {
      name = list.getName();
      if (name.equals(searchNm)) {
        TransCheckAccount = (CheckingAccount) list;
        ArrayList transList = TransCheckAccount.getTransList();
        int size = TransCheckAccount.transList.size();
        TransCheckAccount.settransCount(size);
        AccountWindow.transListArea.setText("\n    Found account for " + searchNm);

        break;
      } else {
        AccountWindow.transListArea.setText("\n    No account found for " + searchNm);
      }
    }
  }
  
  public static int setGameCode(){
    int gameCd = 0;
    return gameCd;
  }
  

  //function for running user entered transactions
  public static void runTransaction() {
    int counter = 0;
    int transCompareCt = 0;
    int transactionCode;
    int serviceCode = 3, lowBalCode = 4, below0Code = 5;
    int id;
    int ckNumber = 0;
    int gameAmt = 0;
    double transactionAmount = 0;
    double finalBalance;
    double balance;
    double serviceCharge, lowBalanceChrg, belowZeroChrg;
    double totalServCharge = 0, grandTotalServCharge;
    double cash = 0, chk = 0;
    double[] depositArray;
    boolean gameCode;
    String message;
    int confirm;
    String typeTrans;
    balance = getBalance();
    transactionCode = getTransCode();
    //if transaction code is check or dep
    if (transactionCode != 0) {
      changed = true;
      //TransCheckAccount.setTChange(true);
      if (transactionCode == 1) {
        ckNumber = getCheckNum();
        transactionAmount = getTransAmt();
        setBalance(transactionAmount, transactionCode);
        balance = getBalance();
      }
      if (transactionCode == 2) {
        typeTrans = "deposit";
        depositArray = getDepBreakdown();
        cash = depositArray[0];
        chk = depositArray[1];
        transactionAmount = depositArray[2];
        setBalance(transactionAmount, transactionCode);
        balance = getBalance();
      }
      serviceCharge = getTransactionCharge(transactionCode);
      lowBalanceChrg = getLowBalCharge();
      belowZeroChrg = getBelowZeroServChrge();
      totalServCharge = getTotalServChrg(totalServCharge);
      setTotalServChrg(totalServCharge);
      grandTotalServCharge = totalServCharge + serviceCharge + lowBalanceChrg + belowZeroChrg;
      totalServCharge = serviceCharge + lowBalanceChrg + belowZeroChrg;
      setTotalServChrg(grandTotalServCharge);
      balance += -totalServCharge;
      setBalance(balance);
      id = getIDNum();
      if (transactionCode == 1) {
        setCheckTransaction(transactionCode, id, transactionAmount, ckNumber);
      }
      if (transactionCode == 2) {
        setDepositTransaction(transactionCode, id, transactionAmount, cash, chk);
      }
      setIDnum();
      id = getIDNum();
      //Sets service charge for dep/chck
      setTransaction(serviceCode, id, serviceCharge);
      setIDnum();
      if (lowBalanceChrg > 0) {
        id = getIDNum();
        setTransaction(lowBalCode, id, lowBalanceChrg);
        setIDnum();
      }
      if (belowZeroChrg > 0) {
        id = getIDNum();
        setTransaction(below0Code, id, belowZeroChrg);
        setIDnum();
      }
      displayCurrentTrans(ckNumber, balance, transactionCode, transactionAmount, serviceCharge, lowBalanceChrg, grandTotalServCharge);
      runTransaction();
    }
    if (transactionCode == 0) {
      setFinalMess();
    }
  }
  
  //function for running user entered transactions
  public static void runGameTrans(double gameAmt) {   
    int counter = 0;
    int transactionCode;
    int serviceCode = 3, lowBalCode = 4, below0Code = 5;
    int id;
    int ckNumber = 0;
    double transactionAmount = 0;
    double finalBalance;
    double balance;
    double serviceCharge, lowBalanceChrg, belowZeroChrg;
    double totalServCharge = 0, grandTotalServCharge;
    double cash = 0, chk = 0;
    double[] depositArray;
    boolean gameCode;
    String message;
    int confirm;
    String typeTrans;
    if(gameAmt != 0){
    //balance = getBalance();
    //transactionCode = getTransCode();
    //if transaction code is check or dep
    //if (transactionCode != 0) {
      changed = true;
      //TransCheckAccount.setTChange(true);
      //if (transactionCode == 1) {
        //ckNumber = getCheckNum();
        transactionAmount = gameAmt;
        if(gameAmt < 0){
          transactionCode = 6;
        }else{
         transactionCode = 7;        
        }
        setBalance(transactionAmount, transactionCode);
        balance = getBalance();
      //}
//      if (transactionCode == 2) {
//        typeTrans = "deposit";
//        depositArray = getDepBreakdown();
//        cash = depositArray[0];
//        chk = depositArray[1];
//        transactionAmount = depositArray[2];
//        setBalance(transactionAmount, transactionCode);
//        balance = getBalance();
//      }
      serviceCharge = getTransactionCharge(transactionCode);
      lowBalanceChrg = getLowBalCharge();
      belowZeroChrg = getBelowZeroServChrge();
      totalServCharge = getTotalServChrg(totalServCharge);
      setTotalServChrg(totalServCharge);
      grandTotalServCharge = totalServCharge + serviceCharge + lowBalanceChrg + belowZeroChrg;
      totalServCharge = serviceCharge + lowBalanceChrg + belowZeroChrg;
      setTotalServChrg(grandTotalServCharge);
      balance += -totalServCharge;
      setBalance(balance);
      id = getIDNum();
      //if (transactionCode == 1) {
        //setCheckTransaction(transactionCode, id, transactionAmount, ckNumber);
      //}
      //if (transactionCode == 3) {
        setTransaction(transactionCode, id, transactionAmount);
      //}
      setIDnum();
      id = getIDNum();
      //Sets service charge for dep/chck
      setTransaction(serviceCode, id, serviceCharge);
      setIDnum();
      if (lowBalanceChrg > 0) {
        id = getIDNum();
        setTransaction(lowBalCode, id, lowBalanceChrg);
        setIDnum();
      }
      if (belowZeroChrg > 0) {
        id = getIDNum();
        setTransaction(below0Code, id, belowZeroChrg);
        setIDnum();
      }
      displayCurrentTrans(ckNumber, balance, transactionCode, transactionAmount, serviceCharge, lowBalanceChrg, grandTotalServCharge);
      //runTransaction();
   // }
    //if (transactionCode == 0) {
      //setFinalMess();
    //}
    }
   else {
      balance = getBalance();
      NumberFormat frmt = NumberFormat.getCurrencyInstance();
      JOptionPane.showMessageDialog(null, "There were no game changes in your account. \nYour account balance is still " + frmt.format(balance));
  }
  }

  public static String enterName() {
    String custName = JOptionPane.showInputDialog("Enter the account name:");
    return custName;
  }

  public static double enterBalance(boolean entryText) {
    String enterBalance;
    double initialBalance;
    //  input initial balance from the user
    if (entryText == true) {
      enterBalance = JOptionPane.showInputDialog("You are beginning a new set of entries from a newly opened file. \nEnter the starting balance again:");
    } else {
      enterBalance = JOptionPane.showInputDialog("Enter your intial balance:");
    }
    //parse balance from text entry
    initialBalance = Double.parseDouble(enterBalance);
    return initialBalance;
  }

  //balance for check/dep + serve
  public static void setBalance(double transAmt, int tCode) {
    TransCheckAccount.setBalance(transAmt, tCode);
  }

  //balance for xtra service charges
  public static void setBalance(double bal) {
    TransCheckAccount.setBalance(bal);
  }

  public static double getBalance() {
    double balance;
    balance = TransCheckAccount.getBalance();
    return balance;
  }
  
  
  public static void showBalance() {
    double balance;
    balance = TransCheckAccount.getBalance();
    //return balance;
    NumberFormat frmt = NumberFormat.getCurrencyInstance();
    AccountWindow.transListArea.setText("\n    Balance: " + frmt.format(balance));
  }

  //helper for getting transaction code
  public static int getTransCode() {
    String enterTransCode;
    int transCode;
    String display;
    display = "Transaction Codes:\n";
    display += "0 -> Exit\n";
    display += "1 -> Check\n";
    display += "2 -> Deposit\n\n";
    display += "Enter the transaction code:";
    //get transaction code from user
    enterTransCode = JOptionPane.showInputDialog(null, display);
    transCode = Integer.parseInt(enterTransCode);
    return transCode;
  }

  //helper to get transaction amount
  public static double getTransAmt() {
    String enterAmount;
    double transAmount;
    enterAmount = JOptionPane.showInputDialog("Enter the check transaction amount:");
    transAmount = Double.parseDouble(enterAmount);
    //AccountWindow.frame.setVisible(false);
    return transAmount;
  }

  public static double[] getDepBreakdown() {
    String cash, check, total;
    double[] deposit;  //deposit amounts array
    deposit = new double[3];  //cash, check, total
    //frame.setVisible(false);
    JTextField cashEnter = new JTextField("");
    JTextField checkEnter = new JTextField("");
    JTextField totalEnter = new JTextField("");
    JPanel panel = new JPanel(new GridLayout(0, 1));

    panel.add(new JLabel("Cash"));
    panel.add(cashEnter);

    panel.add(new JLabel("Check"));
    panel.add(checkEnter);
    panel.add(new JLabel(" Total"));
    panel.add(totalEnter);

    //set focus on first text field
    cashEnter.addAncestorListener(new SetFocus());
    //Show input box with entries for cash, check, total
    int result = JOptionPane.showConfirmDialog(null, panel, "OK/Cancel",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    //text version of cash from entry
    cash = cashEnter.getText();
    //no entry = 0
    if ("".equals(cash)) {
      //cash
      deposit[0] = 0;
    } else {
      //get double version
      deposit[0] = Double.parseDouble(cash);
    }
    check = checkEnter.getText();
    if ("".equals(check)) {
      deposit[1] = 0;
    } else {
      deposit[1] = Double.parseDouble(check);
    }
    total = totalEnter.getText();
    deposit[2] = Double.parseDouble(total);
    //validate cash + check = total     
    while (deposit[0] + deposit[1] != deposit[2]) {
      JOptionPane.showMessageDialog(null, "The total deposit does not equal the cash + check amounts. \n Please learn how to add and then enter the amounts again.");
      cashEnter.setText("");
      checkEnter.setText("");
      totalEnter.setText("");
      result = JOptionPane.showConfirmDialog(null, panel, "OK/Cancel",
              JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
      //text version of cash from entry
      cash = cashEnter.getText();
      //no entry = 0
      if ("".equals(cash)) {
        //cash
        deposit[0] = 0;
      } else {
        //get double version
        deposit[0] = Double.parseDouble(cash);
      }
      check = checkEnter.getText();
      if ("".equals(check)) {
        deposit[1] = 0;
      } else {
        deposit[1] = Double.parseDouble(check);
      }
      total = totalEnter.getText();
      deposit[2] = Double.parseDouble(total);
    }


    return deposit;
  }

  public static int getCheckNum() {
    String num;
    int ckNum;
    num = JOptionPane.showInputDialog("Enter the check number:");
    ckNum = Integer.parseInt(num);
    return ckNum;
  }

  public static double getCkAmt() {
    double checkAmt = 0;
    int tcaCount, index, tCode;
    tcaCount = TransCheckAccount.gettransCount();
    for (index = 0; index < tcaCount; index++) {
      Transaction trans = TransCheckAccount.getTrans(index);
      tCode = trans.getTransNumber();
      if (tCode == 1) {
        checkAmt += trans.transAmt;
      }
    }
    return checkAmt;
  }

  public static double getDepAmt() {
    double depAmt = 0;
    int tcaCount, index, tCode;
    tcaCount = TransCheckAccount.gettransCount();
    for (index = 0; index < tcaCount; index++) {
      Transaction trans = TransCheckAccount.getTrans(index);
      tCode = trans.getTransNumber();
      if (tCode == 2) {
        depAmt += trans.transAmt;
      }
    }
    return depAmt;
  }

  public static double getTransactionCharge(int code) {
    double transactionCharge = 0;
    if (code == 1 || code == 3|| code == 6) {
      transactionCharge = .15;
    } else if (code == 2 || code == 4 || code == 7) {
      transactionCharge = .10;
    }
    return transactionCharge;
  }

  //charge for balance below $500
  public static double getLowBalCharge() {
    double lowBalCharge = 0;
    double amount;
    double balance;
    double getLowBalChrge;
    int index, tcaCount;
    balance = TransCheckAccount.getBalance();
    getLowBalChrge = TransCheckAccount.getLowBalanceCharge();
    tcaCount = TransCheckAccount.gettransCount();
    for (index = 0; index < tcaCount; index++) {
      Transaction trans = TransCheckAccount.getTrans(index);
      amount = trans.getTransAmount();
      if (amount == 5) {
        getLowBalChrge = 1;
      }
    }

    if (balance < 500 && getLowBalChrge == 0) {
      lowBalCharge = 5;
      TransCheckAccount.setLowBalanceCharge(lowBalCharge);
    }
    return lowBalCharge;
  }

//charge for balance below $0
  public static double getBelowZeroServChrge() {
    double belowZeroServChrge = 0;
    double balance;
    balance = TransCheckAccount.getBalance();
    if (balance < 0) {
      belowZeroServChrge = 10;
    }
    return belowZeroServChrge;
  }

  public static void setTotalServChrg(double totSerCharge) {
    TransCheckAccount.setTotServiceCharge(totSerCharge);
  }

  public static double getTotalServChrg(double totSerCharge) {
    totSerCharge = TransCheckAccount.getTotServiceCharge();
    return totSerCharge;
  }

  //get total of service charges at ending of session
  public static double getAllServChrgs() {
    int tcaCount;
    int index;
    double allServChrgs = 0;
    tcaCount = TransCheckAccount.gettransCount();
    for (index = 0; index < tcaCount; index++) {
      //get array of transactions
      Transaction trans = TransCheckAccount.getTrans(index);
      //these are service charges on transactions
      if (trans.getTransNumber() == 3 || trans.getTransNumber() == 4 || trans.getTransNumber() == 5) {
        allServChrgs = allServChrgs + trans.getTransAmount();
      }
    }
    return allServChrgs;
  }

  //sets deposit transaction
  public static void setDepositTransaction(int transCode, int id, double transAmout, double cashDep, double checkDep) {
    Deposit depositTrans = new Deposit(transCode, id, transAmout, cashDep, checkDep);
    TransCheckAccount.addDeposit(depositTrans);
  }

  //sets check transaction
  public static void setCheckTransaction(int transCode, int id, double transAmout, int ckNum) {
    Check checkTrans = new Check(transCode, id, transAmout, ckNum);
    TransCheckAccount.addCheck(checkTrans);
  }

  //sets each service transaction: code 3 service
  public static void setTransaction(int number, int id, double transAmout) {
    Transaction newTrans = new Transaction(number, id, transAmout);
    TransCheckAccount.addTrans(newTrans);
  }

  //sets transaction ID numbers
  public static void setIDnum() {
    TransCheckAccount.settransCount();
  }

  public static int getIDNum() {
    int idNum;
    idNum = TransCheckAccount.gettransCount();
    return idNum;
  }

  //message after transaction
  public static void displayCurrentTrans(int ckNumber, double balance, int enterNewCode, double transAmout, double transServChrge, double lowBalCharge, double totSerCharge) {
    String message;
    NumberFormat frmt = NumberFormat.getCurrencyInstance();
    //start message for dialog box to show user
    message = TransCheckAccount.toString();
    message += "Transaction: ";
    if (enterNewCode == 1) {
      message += "Check #" + ckNumber + " in amount of ";
    } else if(enterNewCode == 2) {
      message += "Deposit in amount of ";
    }
    else if (enterNewCode == 6){
      //if(transAmout < 0){
      message += "Game losses in the amount of ";
     // }
     // else{
        //message += "Game winnings in the amount of ";
      //}
    }
    else{
        message += "Game winnings in the amount of ";
      }
    message += frmt.format(transAmout) + "\n";
    message += "Current Balance: " + frmt.format(balance) + "\n";
    message += "Service Charge: ";
    if (enterNewCode == 1) {
      message += "Check --- Charge " + frmt.format(transServChrge) + "\n";
    } else if ((enterNewCode == 2)) {
      message += "Deposit --- Charge " + frmt.format(transServChrge) + "\n";
    }
    else{
      if(transAmout < 0){
        message += "Game Losses --- Charge " + frmt.format(transServChrge) + "\n";
      }
      else{
        message += "Game Winnings --- Charge " + frmt.format(transServChrge) + "\n";
      }
    }
    if (lowBalCharge > 0) {
      message += "Service Charge: Below $500 --- Charge " + frmt.format(lowBalCharge) + "\n";
      //turns off message for future
    }
    if (balance < 50) {
      message += "Warning: Balance Below $50.00" + "\n";
    }
    if (balance < 0) {
      message += "Service Charge: Below $0 --- Charge $10.00" + "\n";
      // $10 for balance below 0
    }
    message += "Total Service Charge: " + frmt.format(totSerCharge) + "\n";
    //shows message to user
    JOptionPane.showMessageDialog(null, message);
    //AccountWindow.frame.setVisible(true);

  }

  //final transaction message
  public static void displayFinalMess(double currentBal, double servCharge, double finalBal) {
    String message;
    NumberFormat frmt = NumberFormat.getCurrencyInstance();
    message = TransCheckAccount.toString();
    message += "Transaction: End \n";
    //message += "Current Balance: " + frmt.format(currentBal) + "\n";
    message += "Total Service Charge: " + frmt.format(servCharge) + "\n";
    message += "Final Balance: " + frmt.format(finalBal) + "\n";
    JOptionPane.showMessageDialog(null, message);
  }

  //list all transactions
  public static void listTransactions(int typeList) {
    //Check checkT = null;
    int checkCount = 0;
    int checkNm = 0;
    boolean checkEntered = true;
    int tNum;
    int tcaCount;
    int linecount = 0;
    int index;
    double bal, amount, servChrge, totSCharge, lowBalChrge;
    double cashDep, checkDep;
    String cashString, checkString;
    String total = "Total ";
    String cash = "Cash";
    String check = "Check";
    String emptyS = "   ";
    String TransNumber = "TransNumber", type = "Type", Amount = "Amount", checkNum = "Check #", depos = "Deposit";
    int num;
    int id;
    String name;
    Transaction trans;
    //get index for current account
    int indexOf = accountList.indexOf(TransCheckAccount);
    TransCheckAccount = accountList.elementAt(indexOf);
    name = TransCheckAccount.name;
    bal = getBalance();
    String lineOutput = "\n", amountString, typeTrans = "";
    if (TransCheckAccount.gettransCount() == 0) {
     // String lineO = "No transactions have been posted.";
      //JOptionPane.showMessageDialog(null, lineO);
      lineOutput += "    No transactions have been posted.";
      AccountWindow.transListArea.setText(lineOutput);
      return;
    }  
    NumberFormat formatMoney = NumberFormat.getCurrencyInstance();
     //Top area account name listing
    totSCharge = getAllServChrgs();
    lineOutput += String.format("    Account:  " + name + "\n");
     //Top area balance listing
    lineOutput += String.format("    Balance:  " + formatMoney.format(bal) + "\n");
    if (typeList == 1) {
      //Top area service charge listing
      lineOutput += String.format("    Service Charge:  " + formatMoney.format(totSCharge) + "\n\n");
      
      lineOutput += String.format("    List of all transactions: \n\n");
      lineOutput += "    ";
      //Heading above all listings
      lineOutput += String.format("%-33s %-22s %15s ", TransNumber, type, Amount) + "\n";
      lineOutput += "    -----------------------------------------------------------------------------\n";
    }
    //Heading for checks
    if (typeList == 2) {
      lineOutput += String.format("\n    List of all Checks\n\n");
      lineOutput += "    ";
      lineOutput += String.format("%-33s %-22s %15s", TransNumber, checkNum, Amount) + "\n";
      lineOutput += "    -----------------------------------------------------------------------------\n";
    }
    //heading for deposits
    if (typeList == 3) {
      lineOutput += "    ";
      lineOutput += String.format("\n    List of all Deposits \n\n");
      lineOutput += "    ";
      lineOutput += String.format("%-33s %-22s %15s ", TransNumber, type, Amount) + "\n";
    }
    //heading for Game Win/Loss
    if (typeList == 6) {
      lineOutput += "    ";
      lineOutput += String.format("\n    List of all game winnings/losses \n\n");
      lineOutput += "    ";
      lineOutput += String.format("%-55s %15s ", TransNumber, Amount) + "\n";
      lineOutput += "    -----------------------------------------------------------------------\n";
    }
    tcaCount = TransCheckAccount.gettransCount();  //get number of transactions  
    for (index = 0; index < tcaCount; index++) {
      //get each Transaction object
      trans = TransCheckAccount.getTrans(index);
      amount = trans.getTransAmount();
      amountString = formatMoney.format(amount);
      DecimalFormat frmt = new DecimalFormat("0.00");
      frmt.format(amount);
      tNum = trans.getTransNumber();
      id = trans.getTransId();
      lowBalChrge = trans.getLowBalanceCharge();
      servChrge = trans.getServiceCharge();
      if (typeList == 1) {
        if (tNum == 1) {
          typeTrans = "Check";
          lineOutput += "    ";
          lineOutput += String.format("%-33d %-22s %15s %n", id, typeTrans, amountString);
        } else if (tNum == 2) {
          typeTrans = "Deposit";
          //adds space at beginning of line
          lineOutput += "    ";
          lineOutput += String.format("%-33d %-22s %15s %n", id, typeTrans, amountString);
        } else if (tNum == 3 || tNum == 4 || tNum == 5) {
          if (amount > 0) {
            typeTrans = "Svc. Chrg.";
            //adds space at beginning of line
            //fix formatting for double digits on id
            lineOutput += "    ";
            lineOutput += String.format("%-33d %-22s %15s %n", id, typeTrans, amountString);
          }
          //listing for game winning/losses
        }else if (tNum == 6 || tNum == 7) {
          if (amount > 0){
            typeTrans = "Game Winnings";
          }else{
            typeTrans = "Game Losses";
            //-amount gets rid of () for negative amount
            amountString = formatMoney.format(-amount);
          }                                      
          //adds space at beginning of line
           //fix formatting for double digits on id
            lineOutput += "    ";
            lineOutput += String.format("%-33d %-22s %15s %n", id, typeTrans, amountString);
        }
      }//display game wins/losses
      else if((tNum == 6 || tNum == 7) && typeList == 6){
        lineOutput += "    ";
        lineOutput += String.format("%-55d %15s %n", id, amountString);
        
      }
      //display check listing only
      else if (typeList == 2 && tNum == 1) {
        Check c = (Check) trans;
        checkNm = c.getCheckNumber();
        //adds space at beginning of line
        lineOutput += "    ";
        lineOutput += String.format("%-33d %-22d %15s %n", id, checkNm, amountString);
      } //display deposit listing only     
      else if (typeList == 3 && tNum == 2) {
        Deposit dep = (Deposit) trans;
        cashDep = dep.getCashAmount();
        checkDep = dep.getCheckAmount();
        cashString = formatMoney.format(cashDep);
        checkString = formatMoney.format(checkDep);
        //for top line, then don't repeat
        if (linecount == 0) {
          lineOutput += "    -----------------------------------------------------------------------------\n";
        }
        linecount++;
        //adds spaces before line
        lineOutput += "    ";
        lineOutput += String.format("%-33d %-44s", id, depos) + "\n";
        //line items listings
        lineOutput += String.format("%-55s %-8s %11s\n", emptyS, cash, cashString);
        lineOutput += String.format("%-55s %-8s %11s\n", emptyS, check, checkString);
        lineOutput += String.format("%-55s %-8s %11s\n", emptyS, total, amountString);
        //bottom line
        lineOutput += "    -----------------------------------------------------------------------------\n";
      }
    }
    AccountWindow.transListArea.setText(lineOutput);
  }

  public static void changeMessage() {
    String message;
    int confirm;
    message = "You have made changes to your account that have not been saved \n ";
    message += "Would you like to save these changes creating a new account?";
    confirm = JOptionPane.showConfirmDialog(null, message);
    if (confirm == JOptionPane.YES_OPTION) {
      writeFile();
    }
  }

  public static void readFile() {
    String fileNme;
    String message;
    int confirm;
    int tranCount = 0;
    double transactionAmount, serviceAmount, balance;
    if (changed == true) {
      message = "You are about to open a file that will overwrite ";
      message += "any transactions you have made in this session.\n";
      message += "Would you like to save these changes to another file before opening the file?";
      confirm = JOptionPane.showConfirmDialog(null, message);
      if (confirm == JOptionPane.YES_OPTION) {
        writeFile();
        JOptionPane.showMessageDialog(null, "Changes have been saved. Open file to start new transactions.");
        fileNme = getFile(2);
      } else {
        fileNme = getFile(2);
      }
    } else {
      fileNme = getFile(2);
    }
    try {
      FileInputStream inStream = new FileInputStream(fileNme);
      ObjectInputStream in = new ObjectInputStream(inStream);
      accountList = (Vector<CheckingAccount>) in.readObject();
    } catch (ClassNotFoundException e) {
    } catch (IOException e) {
    }
    CheckingAccount.setNumAccounts(accountList.size());
    TransCheckAccount = accountList.elementAt(0);
    ArrayList transList = TransCheckAccount.getTransList();
    int size = TransCheckAccount.transList.size();
    TransCheckAccount.settransCount(size);
    changed = false;



    AccountWindow.transListArea.setText("\n     Accounts read. Current account is. " + TransCheckAccount.name);
  }

  public static void writeFile() {
    String file = getFile(1);
    try {
      FileOutputStream fOutStream = new FileOutputStream(file);
      ObjectOutputStream oOutStream = new ObjectOutputStream(fOutStream);
      oOutStream.writeObject(accountList);
      oOutStream.close();
    } catch (IOException e) {
    }
    changed = false;
    AccountWindow.transListArea.setText("\n     Accounts written.");
  }

  public static String getFile(int fileCode) {
    int fileChoose;
    String file = "";
    JFileChooser chooseFile = new JFileChooser();
    if (fileCode == 1) {
      fileChoose = chooseFile.showSaveDialog(null);
    } else {
      fileChoose = chooseFile.showOpenDialog(null);
    }
    if (fileChoose == JFileChooser.APPROVE_OPTION) {
      File getfile = chooseFile.getSelectedFile();
      file = getfile.getPath();
      if(file.endsWith(".dat"))
        JOptionPane.showMessageDialog(null, " dat.");
      else
        JOptionPane.showMessageDialog(null, " no dat.");
        
    }
    return file;
  }

  public static void windowClose() {
    double allServChrg;
    double finBal;
    double currBal;
    allServChrg = getAllServChrgs();
    currBal = getBalance();
    finBal = currBal;
    TransCheckAccount.setFinBal(finBal);
    setBalance(finBal);
    saveChanges();
    displayFinalMess(currBal, allServChrg, finBal);
    System.exit(0x0);
  }

  public static void setFinalMess() {
    double allServChrg;
    double finBal;
    double currBal;
    allServChrg = getAllServChrgs();
    currBal = getBalance();
    finBal = currBal;
    TransCheckAccount.setFinBal(finBal);
    setBalance(finBal);
    //saveChanges();
    displayFinalMess(currBal, allServChrg, finBal);
  }

  public static boolean getChange() {

    return changed;
  }

  public static void saveChanges() {
    String mes;
    int conf;
    if (getChange() == true) {
      mes = "You have made changes to the accounts.\n";
      mes += "Would you like to save these changes?";
      conf = JOptionPane.showConfirmDialog(null, mes);
      if (conf == JOptionPane.YES_OPTION) {
        writeFile();
      }
    }
  }
}
