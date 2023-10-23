import java.util.*;
import java.io.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*********************************************************************
* class: Ball
* description: This class represents a single ball in the animation. 
********************************************************************/
public class Ball
{
    private Color ballColor;//color of ball
    private int ballRadius,//radius of ball
                xCoord,//x coordinate of ball center
                yCoord,//y coordinate of ball center
                dx,//horizontal direction of ball
                dy;//vertical direction of ball
    
    /*********************************************************************
    * method: Ball()
    * parameters: Color color, int radius, int x, int y, int xDirection,
    * int yDirection
    * description: This constructor intializes the ball object to its 
    * data members
    ********************************************************************/
    public Ball(Color color, int radius, int x, int y, int xDirection, int yDirection)
    {
        ballColor = color;
        ballRadius = radius;
        xCoord = x;
        yCoord = y;
        dx = xDirection;
        dy = yDirection;
    }//end Ball constructr

    /*********************************************************************
    * method: move()
    * parameters: Dimension panel
    * description: This function determines the direction that a ball
    * moves based on its current location
    ********************************************************************/
    public void move(Dimension panel)
    {
        //reverse the horizontal and vertical directions of the ball according to their position
        //on the animation panel
        if((xCoord <= ballRadius) || (xCoord >= (panel.getWidth() - ballRadius)))
        {
            dx = -dx;
        }
        if((yCoord <= ballRadius) || (yCoord >= (panel.getHeight() - ballRadius)))
        {
            dy = -dy;
        }

        //add dx to xCoord and dy to yCoord
        xCoord += dx;
        yCoord += dy;
    }//end move()

    /*********************************************************************
    * method: draw()
    * parameters: Graphics g
    * description: This function draws the Ball object using the designated
    * color and dimensions
    ********************************************************************/
    public void draw(Graphics g)
    {
        //set ball color
        g.setColor(ballColor);

        //draw circle
        g.fillOval(xCoord - ballRadius, yCoord - ballRadius, ballRadius * 2, ballRadius * 2);
    }//end draw()

}//end Ball class