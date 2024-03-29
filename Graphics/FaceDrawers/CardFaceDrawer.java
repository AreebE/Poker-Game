import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/*
  This class is designed to draw the face of EVERY card, not just 
  the face cards. This also means it will draw the symbols
  that would represent the suit of the card
*/
public abstract class CardFaceDrawer {

  public static final int BASIC_SYMBOL = -1;
  
  /* Gets whether the card is meant to be red or black
  */
  abstract public Color getColor();

  /* Draws the symbol representing the card's suit
  */
  abstract void drawSymbol(Graphics2D canvas, int size);

  /* Draws the Jack card. It loks like a crown with three points and has 
   * one gem (the symbol of the suit)
   * 
   *  @param  g   the canvas to draw on 
   *
   *  @param  s   the square this drawing is located in 
  */
  private void drawJack(Graphics2D g, int s){
    g.setColor(Color.YELLOW);
    int[] xCords = new int[7];
    int[] yCords = new int[7];
    int x = 0;
    for(int i = 0; i < 4; i++){
      xCords[i] = x;
      x += s/4;
      if(i%2 == 0){
        yCords[i] = 0;
      }
      else{
        yCords[i] = s/3;
      }
    }
    xCords[4] = s;
    yCords[4] = 0;
    xCords[5] = s;
    yCords[5] = s;
    xCords[6] = 0;
    yCords[6] = s;

    g.fillPolygon(xCords, yCords, 7);
    g.setColor(getColor());
    g.drawPolygon(xCords, yCords, 7);
    g.translate((s/16) + (s/3), s/2);
    drawSymbol(g, s/4);
    // canvas.setColor(getColor());
    // canvas.fillRect(0, 0, size, size);
  }

/* Draws the Queen card. It loks like a crown with four points and has 
   * two gems (the symbol of the suit) 
   * 
   *  @param  g   the canvas to draw on 
   *
   *  @param  s   the square this drawing is located in 
  */
  private void drawQueen(Graphics2D g, int s){
    g.setColor(Color.YELLOW);
    int[] xCords = new int[9];
    int[] yCords = new int[9];
    int x = 0;
    for(int i = 0; i < 7; i++){
      if(i == 6){
        xCords[i] += s;
      } 
      else{
        xCords[i] = x;
      }
      x =  x + (s/6);
      if(i%2 == 0){
        yCords[i] = 0;
       }
      else{
        yCords[i] = s/3;
       }
    }
      xCords[7] = s;
      xCords[8]  = 0;
      yCords[7] = s;
      yCords[8] = s;
    
      g.fillPolygon(xCords, yCords, 9);
      g.setColor(getColor());
      g.drawPolygon(xCords, yCords, 9);


      // Drawing gems
      g.translate(s/16, 2*(s/3));
      drawSymbol(g, s/4);
      g.translate(s/3 + s/3 - s/20, 0);
      drawSymbol(g, s/4);

  }


  /* Draws the King card. It loks like a crown with five points and has 
   * three gems (the symbol of the suit)
   * 
   *  @param  g   the canvas to draw on 
   *
   *  @param  s   the square this drawing is located in 
  */
  private void drawKing(Graphics2D g, int s){
    // canvas.setColor(Color.YELLOW);
    g.setColor(Color.YELLOW);
    int [] xCords = new int[11];
    int [] yCords = new int[11];
    int x = 0;
    for(int i = 0; i < 8; i++){
      xCords[i] = x;
      x = x+(s/8);
      if(i%2 == 0){
        yCords[i] = 0;
      }
      else{
        yCords[i] = s/3;
      }
    }
    xCords[8] = s;
    xCords[9] = s;
    xCords[10] = 0;
    yCords[8] = 0;
    yCords[9] = s;
    yCords[10] = s;

    g.fillPolygon(xCords, yCords, 11);
    g.setColor(getColor());
    g.drawPolygon(xCords, yCords, 11);
    // canvas.setColor(getColor());
    // canvas.fillRect(0, 0, size, size);
    
    // Drawing gems
    g.translate(s/16, 2*(s/3));
    drawSymbol(g, s/4);
    g.translate(s/3, -s/4);
    drawSymbol(g, s/4);
    g.translate(s/3, s/4);
    drawSymbol(g, s/4);
  }

  /* Draws the shape the program asks for, based on the value of the card
  *
  * @param    canvas      the place where the drawing will be drawn on
  *
  * @param    x           the x-coordinate of the upper left corner of the 
  *                       drawing 
  *
  * @param    y           the y-coordinate of the upper left corner of the 
  *                       drawing
  *
  * @param    size          the 'box' that the drawing would be placed in 
  *
  * @param    isReversed    a boolean that says if this shape should be 
  *                           reversed 
  *
  * @param    value         the value of the card
  */
  public void drawShape(Graphics2D canvas, int x, int y, int size, boolean isReversed, int value){
    AffineTransform oldConditions = canvas.getTransform();
    if (isReversed){
      canvas.translate(size + x, size + y);
      canvas.rotate(Math.PI);
    }
    else {
      canvas.translate(x, y);
    }
    switch(value){
      case 13:
        drawKing(canvas, size);
        break;
      case 12:
        drawQueen(canvas, size);
        break;
      case 11:
        drawJack(canvas, size);
        break;
      default:
        drawSymbol(canvas, size);
        break;
    }
    canvas.setTransform(oldConditions);
  }
}