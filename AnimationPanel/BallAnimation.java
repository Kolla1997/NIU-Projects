import java.util.*;
import java.io.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import javax.swing.border.Border;

/*********************************************************************
* class: BallAnimation
* description: This class creates a JPanel that contains a animation
* panel and a button panel to start/stop the ball bouncing in the
* animation panel.
********************************************************************/
public class BallAnimation extends JPanel
{
    private static JButton startButton,//start button
                            stopButton;//stop button
    private static AnimationPanel animationPanel;//animation panel
    private static JPanel buttonPanel;//flow layout panel for start and stop buttons

    /*********************************************************************
    * method: BallAnimation()
    * description: This constructor creates the right side panel of the
    * Tile Designer app. It adds the animation panel and the button panel
    * to itself. It also listens for button clicks for start and stop.
    ********************************************************************/
    public BallAnimation()
    {
        setLayout(new BorderLayout());

        //create animation panel
        animationPanel = new AnimationPanel();
        add(animationPanel, BorderLayout.CENTER);

        //create start and stop jbuttons
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");

        //create button panel and add start and stop buttons
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(getStartButton());
        buttonPanel.add(getStopButton());

        //add button panel to south of ball animation panel
        add(buttonPanel, BorderLayout.SOUTH);

        //initially have start enabled and stop disabled
        startButton.setEnabled(true);
        stopButton.setEnabled(false);

        //listen for start button to be clicked
        startButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                //disable start button and enable stop upon click
                startButton.setEnabled(false);
                stopButton.setEnabled(true);

                //start the thread
                animationPanel.start();
            }
        });

        //listen to stop button to be clicked
        stopButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                //disable stop button and enable start upon click
                stopButton.setEnabled(false);
                startButton.setEnabled(true);

                //stop the thread
                animationPanel.stop();
            }
        });
    }//end BallAnimation()

    /*********************************************************************
    * method: getStartButton()
    * description: This getter function returns the start button.
    ********************************************************************/
    public JButton getStartButton()
    {
        return startButton;
    }//end getStartButton()

    /*********************************************************************
    * method: getStopButton()
    * description: This getter function returns the stop button.
    ********************************************************************/
    public JButton getStopButton()
    {
        return stopButton;
    }//end getStopButton()

}//end BallAnimation class