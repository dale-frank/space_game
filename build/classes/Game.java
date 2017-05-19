/**
 * ************************************
 *
 * Dale Frank CS 3 – 0119 - Java Programming Assignment 6 Game Class
 *
 ***********************************
 */


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Game extends JPanel {

  private static Integer score = 0;
  //private static final int MAXAST = 2;
  private static int newAstCtr = 0;
  private int delay = 197;
  private final int WIDTH = 1100, HEIGHT = 800;
  private final int JUMP = 15;  // increment for image movement
  private final int IMAGE_SIZE = 35;
  //Placement starting points
  private final int STARTX = 899, STARTY = 66, SCOREX = 822, SCOREY = 44, ASTAMOUNTX = 877, ASTAMOUNTY = 188, INSTRUCTIONSX = 44, INSTRUCTIONSY = 522;
  private final int ASTAMOUNT = 50, EARTHAMOUNT = 500;
  private static String asteroidPic = "images/Asteroid.png", asteroidPic2 = "images/Asteroid.png";
  private static String asteroidExplode = "images/explode.gif", asteroidExplode2 = "images/astExplode.gif";
  private static String earthExplode = "images/earthexploding.gif";
  private static String earth = "images/earth.jpg";
  //private static ImageIcon image;
  private static ImageIcon earthExploding, earthInSpace, ast, ast2, astExplode, astExplode2, earthExplosion, asteroidBlowingUP, asteroid1;
  private static ImageIcon moonImg;
  boolean earthExploded = false, astExploded = false;
  private Timer timer;
  public static ImageIcon up, down, right, left, currentSup, start, stop, exitbut, startStopImg, sunImg, resetImg;
  public static int supX, supY, earthx, earthy, astx, asty, moveX, moveY, supChoice, offPageX = -222, offPageY = -222;
  private static SpaceSettings settings;
  private Rectangle startStopBound, exitBound, chandikaBound, supermanBound, andromedaBound, superWomanBound, fatSuperheroBound, spidermanBound, dogBound;
  private static int[] astX, astY;
  private static int[] astxAMove, astyAMove;
  private static Asteroid[] astArray;
  private static ImageIcon[] astImg;
  private static boolean stopped = true, firstTime = true;
  private static long startTime = System.currentTimeMillis();
  private static long earthTime, astTime;
  private static AsteroidListener astListener;
  private static boolean resetScr, preGame = true, postGame = false, astHit = false, earthHit = false;
  public static ImageIcon fatSuperhero, spiderman, superman, superWoman, andromeda, dog, chandika, openText, openText1;

  public Game() {
    astListener = new AsteroidListener();
    timer = new Timer(delay, astListener);
    addMouseListener(new StartStop());
    addKeyListener(new KeyLis());
    //required
    setFocusable(true);

    chandika = new ImageIcon("images/superhero/chandika.png");
    superman = new ImageIcon("images/superhero/superman.png");
    fatSuperhero = new ImageIcon("images/superhero/SuperUpsideDownChloe1.png");
    spiderman = new ImageIcon("images/superhero/spiderman3.png");
    andromeda = new ImageIcon("images/superhero/LexiSuperLeg1.png");
    superWoman = new ImageIcon("images/superhero/superWoman2.png");
    dog = new ImageIcon("images/superhero/superDog.png");

    openText = new ImageIcon("images/text.gif");
    openText1 = new ImageIcon("images/textRestart.gif");

    resetScr = false;
    astArray = new Asteroid[12];
    astImg = new ImageIcon[12];
    astX = new int[12];
    astY = new int[12];
    astxAMove = new int[12];
    astyAMove = new int[12];

    start = new ImageIcon("images/startSmall.jpg");
    stop = new ImageIcon("images/stopSmall.jpg");
    exitbut  = new ImageIcon("images/exit.jpg");
    sunImg = new ImageIcon("images/sun1.jpg");
    moonImg = new ImageIcon("images/moonSmall.jpg");
    startStopImg = new ImageIcon("images/moonSmall.jpg");

    //resetImg = new ImageIcon("reset1.gif");
    int randNm = getRand();
    settings = new SpaceSettings(randNm, 1, 2, 3);
    addAst(0);
    setGame(resetScr);
    clearGame(stopped);

    setPreferredSize(new Dimension(WIDTH, HEIGHT));

    setBackground(Color.black);
    setForeground(Color.red);
    setFont(new Font("Helvetica", Font.BOLD, 24));
    timer.start();


  }

  public void paintComponent(Graphics page) {
    super.paintComponent(page);
    if (preGame == true) {
      chandikaBound = new Rectangle(844, 311, chandika.getIconHeight(), chandika.getIconWidth());
      supermanBound = new Rectangle(888, 555, superman.getIconHeight(), superman.getIconWidth());
      andromedaBound = new Rectangle(688, 555, andromeda.getIconHeight(), andromeda.getIconWidth());
      superWomanBound = new Rectangle(488, 555, superWoman.getIconHeight(), superWoman.getIconWidth());

      fatSuperheroBound = new Rectangle(266, 555, fatSuperhero.getIconHeight(), fatSuperhero.getIconWidth());
      dogBound = new Rectangle(33, 566, dog.getIconHeight(), dog.getIconWidth());
      spidermanBound = new Rectangle(33, 311, spiderman.getIconHeight(), spiderman.getIconWidth());
      startStopBound = new Rectangle(STARTX, STARTY, offPageX, offPageY);

      chandika.paintIcon(this, page, 844, 311);
      superman.paintIcon(this, page, 888, 555);
      andromeda.paintIcon(this, page, 688, 555);
      superWoman.paintIcon(this, page, 488, 555);
      fatSuperhero.paintIcon(this, page, 266, 555);
      dog.paintIcon(this, page, 33, 555);
      spiderman.paintIcon(this, page, 33, 311);
      sunImg.paintIcon(this, page, 22, 22);
      moonImg.paintIcon(this, page, 333, 222);
      earthInSpace.paintIcon(this, page, earthx, earthy);
         
      if (postGame == false) {
        openText.paintIcon(this, page, 377, 0);
      }

      if (postGame == true) {
        String One = Integer.toString(score);
        page.drawString("Your total is $" + One + ".00", SCOREX, SCOREY);
        openText1.paintIcon(this, page, 311, 0);
        exitbut.paintIcon(this, page, STARTX, STARTY);
      exitBound = new Rectangle(STARTX, STARTY, exitbut.getIconHeight() + 33, startStopImg.getIconWidth() + 33);
      }
    } else {
      sunImg.paintIcon(this, page, 22, 22);
      moonImg.paintIcon(this, page, 333, 222);
      earthInSpace.paintIcon(this, page, earthx, earthy);
      currentSup.paintIcon(this, page, supX, supY);
      startStopBound = new Rectangle(STARTX, STARTY, startStopImg.getIconHeight() + 33, startStopImg.getIconWidth() + 33);
      startStopImg.paintIcon(this, page, STARTX, STARTY);
      String One = Integer.toString(score);
      page.drawString("Your total is $" + One + ".00", SCOREX, SCOREY);
      if (firstTime == true) {
        int moveAmountY = INSTRUCTIONSY;
        page.drawString("Use the arrow keys to move your superhero through space and intercept the asteroids.", INSTRUCTIONSX, moveAmountY);
//        moveAmountY += 33;
//        page.drawString("Each superhero has unique super powers. ", INSTRUCTIONSX, moveAmountY);
        moveAmountY += 33;
        page.drawString("Each superhero has unique super powers. Try to utilize these special powers to blow up ", INSTRUCTIONSX, moveAmountY);
        moveAmountY += 33;
        page.drawString("the asteroids before they destroy the world.", INSTRUCTIONSX, moveAmountY);
        moveAmountY += 44;
        page.drawString("Each asteroid destroyed earns $50.00. You will get a bonus of $50.00 if you hit radiation  ", INSTRUCTIONSX, moveAmountY);
        moveAmountY += 33;
        page.drawString("fragments after it explodes. If an asteroid destroys the world, you lose $500.00. Watch out,", INSTRUCTIONSX, moveAmountY);
        moveAmountY += 33;
        page.drawString("as asteroid radiation can also blow up the world if exploding too close the the earth!", INSTRUCTIONSX, moveAmountY);
        moveAmountY += 44;
        page.drawString("You can select a new superhero at anytime by clicking 'Stop' and making a new selection.", INSTRUCTIONSX, moveAmountY);
        moveAmountY += 33;
        page.drawString("Press 'Start' to save the world. Good luck!", INSTRUCTIONSX, moveAmountY);
      }

      if (astHit == true) {
        //gets time to compare to when action occurred
        long time = getTime();
        long astElapsedTime = time - astTime;
        if (astElapsedTime < 2000) {
          page.drawString("Asteroid Hit!", ASTAMOUNTX, ASTAMOUNTY);
          //page.drawString("Account credited", ASTAMOUNTX - 22, ASTAMOUNTY + 33);
        }
      }
      if (earthHit == true) {
        //gets time to compare to when action occurred
        long eTime = getTime();
        long eElapsedTime = eTime - earthTime;
        if (eElapsedTime < 2000) {
          page.drawString("Earth Destroyed!", ASTAMOUNTX, ASTAMOUNTY +22);
        }
      }


      for (int ct = 0; ct <= newAstCtr; ct++) {
        astImg[ct].paintIcon(this, page, astX[ct], astY[ct]);
      }
    }
  }

  private class AsteroidListener implements ActionListener {

    public void actionPerformed(ActionEvent event) {
//      boolean newGame = true;
//      openActWin();
//      timer.stop();
      long earthElapsedTime, astElapsedTime;
      if (stopped == false) {
        for (int ct = 0; ct <= newAstCtr; ct++) {
          astX[ct] += astxAMove[ct];
          astY[ct] += astyAMove[ct];
        }
        findDistance();
        if (earthExploded == true) {
          earthElapsedTime = System.currentTimeMillis() - earthTime;
          if (earthElapsedTime >= 2000) {
            resetAst();
            addAst(0);
            clearGame(stopped);
            earthExploded = false;
            repaint();
          }
        }
        if (astExploded == true) {
          astElapsedTime = System.currentTimeMillis() - astTime;
          if (astElapsedTime >= 2000) {
            int addDelay = 30;
            int defaultDel = 233;
            switch (score) {
              case 100:
                delay -= addDelay;
                timer.setDelay(delay);
                break;
              case 250:
                delay -= addDelay;
                timer.setDelay(delay);
                break;
              case 500:
                delay -= addDelay;
                timer.setDelay(delay);
                break;
              case 850:
                delay -= addDelay;
                timer.setDelay(delay);
                break;
              case 1250:
                delay -= addDelay;
                timer.setDelay(delay);
                break;
              case 1600:
                delay -= addDelay;
                timer.setDelay(delay);
                break;
              case 2500:
                delay -= addDelay;
                timer.setDelay(delay);
                break;
              default:
                timer.setDelay(delay);

            }
            //            if (score == 1) {
//              //addAst(MAXAST);
//              delay -=  200;
//              timer.setDelay(delay);
//             if (score == 2){
//               delay -=  220;
//              timer.setDelay(delay);
//            } 
//             if (score == 3){
//               delay -=  260;
//              timer.setDelay(delay);
//            } 
//           }
            //newAstCtr = 0;
            resetAst();
            addAst(0);
            astExploded = false;
          }
        }
        repaint();
      }
    }
  }

  public void openActWin() {
    AccountWindow window = new AccountWindow();
  }

  public void calcBoundary() {
    for (int ct = 0; ct <= newAstCtr; ct++) {
      int widthADD = WIDTH + 220;
      int heightADD = HEIGHT + 50;
      //x or y coordinate outside of boundary
      if (astX[ct] > widthADD || astX[ct] < -50 || astY[ct] > heightADD || astY[ct] < -50) {
        astX[ct] = astArray[ct].getXAxis();
        astY[ct] = astArray[ct].getYAxis();
        astImg[ct] = astArray[ct].getAst();
      }
    }
  }

  public void resetAst() {

    for (int ct = 0; ct <= newAstCtr; ct++) {
      astArray[ct] = null;
    }
    newAstCtr = 0;
  }

  public static void addAst(int astCount) {
    int randNm;
    //int x = 0;
    for (int ct = 0; ct <= astCount; ct++) {
      randNm = getRand();

      astArray[ct] = new Asteroid(randNm);
      astImg[ct] = astArray[ct].getAst();
      astX[ct] = astArray[ct].getXAxis();
      astY[ct] = astArray[ct].getYAxis();
      astxAMove[ct] = astArray[ct].getmoveX();
      astyAMove[ct] = astArray[ct].getmoveY();

    }
    //sets counter to determine when to add asteroids
    newAstCtr = astCount;
  }

  public static void setGame(boolean reset) {
    earthExploding = new ImageIcon(earthExplode);
    if (reset == true) {
      score = 0;
    }
  }

  public void setSup(int choice) {
    preGame = false;
    switch (supChoice) {
      case 1:
        up = new ImageIcon("images/superhero/smallSup/chandikaUp.png");
        down = new ImageIcon("images/superhero/smallSup/chandikaDown.png");
        left = new ImageIcon("images/superhero/smallSup/chandikaLeft.png");
        right = new ImageIcon("images/superhero/smallSup/chandikaRight.png");
        currentSup = up;
        break;
      case 2:
        up = new ImageIcon("images/superhero/smallSup/supermanUp.png");
        down = new ImageIcon("images/superhero/smallSup/supermanDown.png");
        left = new ImageIcon("images/superhero/smallSup/supermanLeft.png");
        right = new ImageIcon("images/superhero/smallSup/supermanRight.png");
        currentSup = up;
        break;
      case 3:
        up = new ImageIcon("images/superhero/smallSup/LexiSuperLegUp.png");
        down = new ImageIcon("images/superhero/smallSup/LexiSuperLegDown.png");
        left = new ImageIcon("images/superhero/smallSup/LexiSuperLegLeft.png");
        right = new ImageIcon("images/superhero/smallSup/LexiSuperLegRight.png");
        currentSup = up;
        break;
      case 4:
        up = new ImageIcon("images/superhero/smallSup/superWomUp.gif");
        down = new ImageIcon("images/superhero/smallSup/superWomDown.gif");
        left = new ImageIcon("images/superhero/smallSup/superWomLeft.gif");
        right = new ImageIcon("images/superhero/smallSup/superWomRight.gif");
        currentSup = up;
        break;
      case 5:
        up = new ImageIcon("images/superhero/smallSup/SuperUpsideDownChloeUp.png");
        down = new ImageIcon("images/superhero/smallSup/SuperUpsideDownChloeDown.png");
        left = new ImageIcon("images/superhero/smallSup/SuperUpsideDownChloeLeft.png");
        right = new ImageIcon("images/superhero/smallSup/SuperUpsideDownChloeRight.png");
        currentSup = up;
        break;
      case 6:
        up = new ImageIcon("images/superhero/smallSup/spidermanUp.png");
        down = new ImageIcon("images/superhero/smallSup/spidermanDown.png");
        left = new ImageIcon("images/superhero/smallSup/spidermanLeft.png");
        right = new ImageIcon("images/superhero/smallSup/spidermanRight.png");
        currentSup = up;
        break;
      case 7:
        up = new ImageIcon("images/superhero/smallSup/dogUp.png");
        down = new ImageIcon("images/superhero/smallSup/dogDown.png");
        left = new ImageIcon("images/superhero/smallSup/dogLeft.png");
        right = new ImageIcon("images/superhero/smallSup/dogRight.png");
        currentSup = up;
        break;
    }
  }

  public static void clearGame(boolean strtStp) {
    earthInSpace = new ImageIcon(earth);
    currentSup = up;
    //supX = 135;
    supX = 488;
    //superhero placement, y axis
    //supY = 35;
    supY = 288;
    //earth placement x
    earthx = 488;
    //earth placement y
    earthy = 350;
    //currentSup = up;
    if (strtStp == true) {
      startStopImg = start;
    } else {
      startStopImg = stop;
    }
  }

  public static int getRand() {
    int rand = (int) (Math.random() * (12));
    return rand;
  }
  
  public static int getAmt(){
    return score;
  }

  public int[] getImgCenter(ImageIcon image, int xVal, int yVal) {

    int centerX;
    int centerY;
    int height;
    int width;
    int[] center = new int[2];
    height = image.getIconHeight();
    width = image.getIconWidth();
    centerX = xVal + width / 2;
    centerY = yVal + height / 2;
    center[0] = centerX;
    center[1] = centerY;
    return center;
  }

  public void findDistance() {
    double distSupAst, distErthAst;
    int[] centerAst;
    int[] centerEarth;
    int[] centerSup = new int[2];
    long expTime = System.currentTimeMillis();
    for (int ct = 0; ct <= newAstCtr; ct++) {
      centerEarth = getImgCenter(earthInSpace, earthx, earthy);
      centerAst = getImgCenter(astImg[ct], astX[ct], astY[ct]);
      centerSup = getImgCenter(currentSup, supX, supY);
      distErthAst = calcDistance(centerEarth, centerAst);
      distSupAst = calcDistance(centerAst, centerSup);
      if (distSupAst > 0 && distSupAst < 44) {
        String url = "file:sound/explode.wav";
        astImg[ct] = astArray[ct].getExplode();
        //adjustment for exploding gifs
        astX[ct] += moveX - 123;
        astY[ct] += moveY - 100;
        score += 50;
        astExploded = true;
        astHit = true;
        //gets time when action occurs
        startTime = System.currentTimeMillis();
        //global variable to store time
        astTime = startTime;
        try {
          Sound.playSound(url);
        } catch (InterruptedException ie) {
          System.out.println(ie);
        }
      }
      if (distErthAst > 0 && distErthAst < 33) {
        String url = "file:sound/explode.wav";
        earthHit = true;
        astImg[ct] = astArray[ct].getExplode();
//        astX[ct] += moveX - 123;
//        astY[ct] += moveY - 99;
        earthInSpace = earthExploding;
        earthExploded = true;
        startTime = System.currentTimeMillis();
        earthTime = startTime;
        resetAst();
        earthx += moveX - 93;
        earthy += moveY - 80;
        score -= 500;
        try {
          Sound.playSound(url);
        } catch (InterruptedException ie) {
          System.out.println(ie);
        }
      }
    }
    repaint();
  }

  public static long getTime() {
    long time;
    time = System.currentTimeMillis();
    return time;
  }
  
  public static long getScore() {
    return score;
  }

  public double calcDistance(int[] ctr1, int[] ctr2) {
    double distance;
    distance = Math.sqrt((ctr1[0] - ctr2[0]) * (ctr1[0] - ctr2[0]) + (ctr1[1] - ctr2[1]) * (ctr1[1] - ctr2[1]));
    return distance;
  }

  public class KeyLis implements KeyListener {

    public void keyPressed(KeyEvent event) {
      switch (event.getKeyCode()) {
        case KeyEvent.VK_UP:
          currentSup = up;
          supY -= JUMP;
          break;
        case KeyEvent.VK_DOWN:
          currentSup = down;
          supY += JUMP;
          break;
        case KeyEvent.VK_LEFT:
          currentSup = left;
          supX -= JUMP;
          break;
        case KeyEvent.VK_RIGHT:
          currentSup = right;
          supX += JUMP;
          break;
      }
    }

    public void keyTyped(KeyEvent event) {
    }

    public void keyReleased(KeyEvent event) {
    }
  }

  private class StartStop extends MouseAdapter {

    public void mouseClicked(MouseEvent event) {

      int xClickOnButton = event.getX();
      int ylickOnButton = event.getY();

      if (chandikaBound.contains(xClickOnButton, ylickOnButton)) {
        supChoice = 1;
        setSup(supChoice);
        repaint();

      }
      else if (supermanBound.contains(xClickOnButton, ylickOnButton)) {
        supChoice = 2;
        setSup(supChoice);
        repaint();
      }
      else if (andromedaBound.contains(xClickOnButton, ylickOnButton)) {
        supChoice = 3;
        setSup(supChoice);
        repaint();
      }
      else if (superWomanBound.contains(xClickOnButton, ylickOnButton)) {
        supChoice = 4;
        setSup(supChoice);
        repaint();
      }
      else if (fatSuperheroBound.contains(xClickOnButton, ylickOnButton)) {
        supChoice = 5;
        setSup(supChoice);
        repaint();
      }
      else if (spidermanBound.contains(xClickOnButton, ylickOnButton)) {
        //JOptionPane.showMessageDialog(null, "Click!");
        supChoice = 6;
        setSup(supChoice);
        repaint();
      }

      else if (dogBound.contains(xClickOnButton, ylickOnButton)) {
        supChoice = 7;
        setSup(supChoice);
        repaint();
      }
      else if (startStopBound.contains(xClickOnButton, ylickOnButton)) {
        if (stopped == true) {
          String url = "file:sound/gameMusic.wav";
          try {

            Sound.playMusic(url);
          } catch (InterruptedException ie) {
            System.out.println(ie);
          }
          timer.start();
          //game is stopped and now starting        
          startStopImg = stop;
          stopped = false;
          resetAst();
          addAst(0);
          setGame(resetScr);
          firstTime = false;
        } else if (stopped == false) {
          //game is going and now stopping
          Sound.stopMusic();
          clearGame(stopped);
          startStopImg = start;
          resetScr = true;
          for (int ct = 0; ct <= newAstCtr; ct++) {
            astX[ct] = settings.resetX();
            astY[ct] += settings.resetY();
          }
          preGame = true;
          postGame = true;
          repaint();
          timer.stop();
          stopped = true;
        }
      }
     else if (exitBound.contains(xClickOnButton, ylickOnButton)) {
        //System.exit(0x0);
       JavaA6Superheroes.gameEnd(score); 
       score = 0;
      }
    }
  }
}
