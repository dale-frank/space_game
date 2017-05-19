
/**
 * ************************************
 *
 * Dale Frank CS 3 – 0119 - Java Programming Assignment  SpaceSettings Class
 *
 ***********************************
 */


import java.util.ArrayList;

public class SpaceSettings {

  //starting point outside (x or y)
  //final int ROWS = 4;
  //starting point inside ( x or y) & move points (x, y)
  //final int COLS = 3;
  private static int x, y, mveX, mveY;
  //initial outside positions for -x
  private ArrayList<Integer> posNeg1, posNeg2, posNeg3;
  //initial outside positions for +y
  private ArrayList<Integer> pos4, pos5, pos6;
  //initial outside positions for +x
  private ArrayList<Integer> pos7, pos8, pos9;
  //initial outside positions for -y
  private ArrayList<Integer> posNeg10, posNeg11, posNeg12;
  //intitial and movement settings (first is outside setting)
  private static int[][] settings = {
    //x settings
    {-188, 225, 11, 3},
    {-188, 400, 11, -1},
    {-188, 666, 11, -5},
    //y settings
    {222, -144, 7, 11},
    {550, -144,-2, 11},
    {800, -133, -7, 11},
    //x settings
    {1100, 222, -11, 3},
    {1100, 400, -11, 0},
    {1100, 666, -11, -5},
    //y settings
    {222, 840, 6, -11},
    {550, 840, -2, -11},
    {800, 840, -7, -11},};

  public SpaceSettings(int firstCode, int secCode, int mveXCode, int mveYCode) {
    //defaults = new ArrayList<Integer>();
//    x = settings[2][0];
//    y = settings [2][1];
//    mveX = settings [2][2];
//    mveY = settings [2][3];
    //if(firstCode == 0 || firstCode == 2){
    x = settings[firstCode][0];
    y = settings [firstCode][secCode];
    mveX = settings [firstCode][mveXCode];
    mveY = settings [firstCode][mveYCode];
  //}
//    else{
//      x = settings[secCode][0];
//    y = settings [firstCode][secCode];
//    mveX = settings [firstCode][mveXCode];
//    mveY = settings [firstCode][mveYCode];
//    }
//
 }

//  private int getSettings(int c) {
//    int settings = 0;
//
//    return settings;
//  }
  public  int getX(){
    return x;
  }
  
  public  int getY(){
    return y;
  }
  
  public  int getmveX(){
    return mveX;
  }
  public  int getmveY(){
    return mveY;
  }
  
  public int resetX() {
    x = -188;
    return x;
  }
  
  public int resetY(){
    y = -166;
    return y;
  }
}
