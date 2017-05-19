
/**
 * ************************************
 *
 * Dale Frank CS 3 – 0119 - Java Programming Assignment 5 Asteroid Class
 *
 ***********************************
 */


import javax.swing.ImageIcon;


public class Asteroid {

  private ImageIcon defaultAst, explodeAst;
  //private String worthImg;
  private  int xAxis, yAxis, moveX, moveY;
  private int value;
  private static String[][] asteroids = {
    {"images/blueDownRight.png", "images/blueExp.gif"},
    {"images/fireLeft.png", "images/explode.gif"},
    {"images/redLeftUp.png", "images/explode.gif"},
    {"images/blueTopRight.png", "images/blueExp.gif"},
    {"images/firetTopDown.png", "images/explode.gif"},
    {"images/AsteroidUpRight.png", "images/explode.gif"},
    {"images/Asteroid1.png", "images/explode.gif"},
    {"images/blueRightSide.png", "images/whiteBlueExp.gif"},
    {"images/orangeLeftUp.png", "images/explode.gif"},
    {"images/blueUpRight.png", "images/blueExp.gif"},
    {"images/asteroidUp.png", "images/explode.gif"},
    {"images/redUpLeft.png", "images/explode.gif"},};
  private static int[] valStore = {20, 35, 20, 35, 20, 35, 20, 35, 20, 35, 20, 35,};
  private static ImageIcon astExplode;
  SpaceSettings settings;
  
//  int delay;

  public Asteroid(int num) {
    defaultAst = new ImageIcon(asteroids[num][0]);
    //explodeAst = asteroids [num][1];
    astExplode = new ImageIcon(asteroids[num][1]);
    value = valStore[num];
    settings = new SpaceSettings(num, 1, 2, 3);
    xAxis = settings.getX();
    yAxis = settings.getY();
    moveX = settings.getmveX();
    moveY = settings.getmveY();
    

  }

  public ImageIcon getAst() {
    return defaultAst;
  }

  public ImageIcon getExplode() {
    return astExplode;
  }
  
  public int getXAxis() {
    return xAxis;
  }
  
  public int getYAxis() {
    return yAxis;
  }
  
  public int getmoveX() {
    return moveX;
  }
  
  public int getmoveY() {
    return moveY;
  }

  public int getValue() {
    return value;
  }
}
