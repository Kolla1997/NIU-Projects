import java.util.*;
import java.io.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*********************************************************************
* class: AnimationPanel
* description: This subclass of JPanel will be used to display the
* animation in a separate background thread using the Runnable
* interface.
********************************************************************/
public class AnimationPanel extends JPanel implements Runnable
{
    private ArrayList<Ball> ballList = new ArrayList<Ball>();//holds ball objects
    private Dimension panelDimensions = null;//dimensions of animation panel
    private Thread animationThread = null;//animation thread

    /*********************************************************************
    * method: start()
    * description: This function creates a new thread if and changes its 
    * state to runnable.
    ********************************************************************/
    public void start()
    {
        //create a new thread
        if(animationThread == null)
        {
            animationThread = new Thread(this);
            animationThread.start();
        }
    }//end start()

    /*********************************************************************
    * method: stop()
    * description: This function terminates the current thread.
    ********************************************************************/
    public void stop()
    {
        //terminate the current thread
        animationThread = null;
    }//end stop()

    /*********************************************************************
    * method: paintComponent()
    * parameters: Graphics g
    * description: This function renders the animation panel and adds
    * several Ball objects to the ArrayList. It then creates and moves
    * each ball in the animation panel.
    ********************************************************************/
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        //create the animation panel
        if(panelDimensions == null)
        {
            //calculate size of animational panel
            panelDimensions = this.getSize();

            //set animation panel background to blue
            setBackground(Color.BLUE);

            //add several balls to the screen
            ballList.add(new Ball(Color.WHITE, 5, panelDimensions.width - 60, panelDimensions.height - 60, 50, -10));
            ballList.add(new Ball(Color.CYAN , 15, panelDimensions.width - 70, panelDimensions.height - 20, -50, 10));
            ballList.add(new Ball(Color.GREEN, 30, panelDimensions.width - 50, panelDimensions.height - 50, -5, -10));
            ballList.add(new Ball(Color.ORANGE, 50, panelDimensions.width - 100, panelDimensions.height - 100, 10, 5));
            ballList.add(new Ball(Color.RED , 60, panelDimensions.width - 150, panelDimensions.height - 150, -5, 5));
            ballList.add(new Ball(Color.MAGENTA , 75, panelDimensions.width - 200, panelDimensions.height - 200, 5, -5));
        }

        //move and draw each ball on the screen
        for(Ball ball : ballList)
        {
            ball.move(panelDimensions);
            ball.draw(g);
        }
    }//end paintComponent()

    /*********************************************************************
    * method: run()
    * description: This function runs a separate background thread until
    * the animation of bouncing balls is stopped.
    ********************************************************************/
    @Override
    public void run()
    {   
        //continue running the animation while the thread is not stopped
        while (Thread.currentThread() == animationThread)
        {
            try 
            {
                //put thread to sleep
                Thread.sleep(100);
            } 
            catch (InterruptedException ie) 
            {
                System.out.println(ie.getMessage());
            }

            //create animation panel
            repaint();
        }
    }//end run()

}//end AnimationPanel class