/**
 * ************************************
 *
 * Dale Frank CS 3 – 0119 - Java Programming Assignment 6 Calculate Class
 *
 ***********************************
 */
public class Calculate {

  private String defaultAst, explodeAst;
  private String worthImg;
  private int xAxis, yAxis, moveX, moveY;
  private int worth;
  int delay;

  public Calculate(int x, int y, int mvX, int mvY, int val, int del) {
    xAxis = getRand();
    yAxis = y;
    moveX = mvX;
    moveY = mvY;
    worth = val;
    delay = del;
  }
  
public int getRand(){
  int rand=0;
  int highLow;
  highLow = (int)(Math.random()*2);
  if(highLow == 0){
      
    } 
  return rand;  
}
  
  
  //   public int getRand() {
//    int low = -20;
//    int high = 1220;
//    int rand =low +  (int)(Math.random()*(high));
//    //int rand = (int) (Math.random() * max);
//    return rand;
//  }
  
}
