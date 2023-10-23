import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.BorderLayout;

/*********************************************************************
* class: TileDesigner
* description: This class creates a GUI that displays a toolbar,
* canvas object, and a reset button that allows a user to create and
* reset a tile design.
********************************************************************/
public class Tile_Designer extends JFrame
{
    //variables for images
    final private static String[] imagename = { "pat1.gif", "pat2.gif", "pat3.gif", "pat4.gif", "pat5.gif", "pat6.gif", "pat7.gif", "pat8.gif" };
    private static Image[] imAr;//array of Image objects with images
    private static int selectedTileIndex;//index of which tile button was selected

    //swing components
    private static Tile_Designer windowFrame;//JFrame window for application
    private static TileCanvas canvas;//JPanel that paints the tile design grid
    private static JToolBar tileBar;//toolbar with image selections
    private static JMenuBar menuBar;//menubar to hold reset and help options
    private static JMenu menu;//menu to handle reset and help options
    private static JButton[] tileButtons;//array of buttons that hold each image
    private static JButton resetButton;//button that resets the grid
    private static BallAnimation ballAnimation;//JPanel that creates the ball animation

    /*********************************************************************
    * method: TileDesigner()
    * description: This constructor adds the tile bar, the grid canvas,
    * and the reset button to the JFrame. It also listens for hotkeys
    * that can be pressed
    ********************************************************************/
    public Tile_Designer()
    {
        //create canvas panel
        canvas = new TileCanvas();

        //create ball animation
        ballAnimation = new BallAnimation();

        //create left and right panels
        JPanel left = new JPanel(new BorderLayout());

        //add tool bar to top of left panel
        left.add(createTileBar(), BorderLayout.NORTH);

        //add canvas to center of left panel
	    left.add(canvas, BorderLayout.CENTER);

        //add reset button to bottom of left panel
        left.add(createResetButton(), BorderLayout.SOUTH);

        //add left panel to the frame
        getContentPane().add(left, BorderLayout.WEST);

        //add right panel to the frame
        getContentPane().add(ballAnimation);
        
        //create action for reset button for hot key
        Action resetAction = new AbstractAction() 
        {
            public void actionPerformed(ActionEvent ae) 
            {
                // perform the desired action here
                resetButton.doClick();
            }
        };

        //create input map with reset action
        InputMap inputMap = resetButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        //add hot key combination to input map
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK), "resetAction");
        
        //create reset action map with reset button
        ActionMap actionMap = resetButton.getActionMap();

        //add the reset button action to the reset action map
        actionMap.put("resetAction", resetAction);
    }//end TileDesigner()

    /*********************************************************************
    * method: getImageArray()
    * description: This getter function returns an array of Image objects.
    ********************************************************************/
    public static Image[] getImageArray()
    {
        return imAr;
    }//end getImageArray()

    /*********************************************************************
    * method: getSelectedTileIndex()
    * description: This getter function returns the index of the 
    * selected tile button.
    ********************************************************************/
    public static int getSelectedTileIndex()
    {
        return selectedTileIndex;
    }//end getSelectedTileIndex()

    /*********************************************************************
    * method: createMenu()
    * description: This function creates a JMenu that will contain
    * a help item and a reset item in its dropdown options
    ********************************************************************/
    private static JMenu createMenu()
    {
        //create menu dropdown
        menu = new JMenu("Menu");

        //create reset item
        JMenuItem resetItem = new JMenuItem("Reset");

        //add action listener to reset item
        resetItem.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent ae) 
            {
                canvas.clearGrid();
            }
        });

        //add reset item to menu
        menu.add(resetItem);

        //create help item
        JMenuItem helpItem = new JMenuItem("Help");

        //add action listener to help item
        helpItem.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent ae) 
            {
                //display option pane with help information
                JOptionPane.showMessageDialog(windowFrame, 
                "Select a tile above and click on any space in the center grid to create a tile design.\nIf you want to start over, click reset or Ctrl + R to clear the canvas.",
                "Welcome to Tile Designer", 
                JOptionPane.INFORMATION_MESSAGE);
            }
        });

        //add help item to menu
        menu.add(helpItem);

        return menu;
    }//end createMenu()

    /*********************************************************************
    * method: createTileBar()
    * description: This function creates a toolbar that will contain
    * the dropdown menu as well as the tile buttons that the user
    * can select in order to design the canvas
    ********************************************************************/
    private static JToolBar createTileBar()
    {
        //create tile toolbar
        tileBar = new JToolBar();
        tileBar.setFloatable(false);

        //create menu bar
        menuBar = new JMenuBar();

        //add dropdown menu to menu bar
        menuBar.add(createMenu());

        //add menu to tile toolbar
        tileBar.add(menuBar);

        //create array of images
        imAr = new Image[imagename.length];

        //create array of tile buttons
        tileButtons = new JButton[imAr.length];

        //iterate thru arrays to add the images
        for(int i = 0; i < imAr.length; i++)
        {
            //store images into image array
            imAr[i] = (Image) Toolkit.getDefaultToolkit().getImage(imagename[i]);

            //set tile button icon to tile image
            tileButtons[i] = new JButton(new ImageIcon(imAr[i]));

            //listen for user to select a tile button
            tileButtons[i].addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae) 
                { 
                    for (int i = 0; i < tileButtons.length; i++) 
                    {
                        if (ae.getSource() == tileButtons[i]) 
                        {
                            //store selected tile button to index variable
                            selectedTileIndex = i;

                            //pass index to canvas setter
                            canvas.setTileIndex(selectedTileIndex);

                            break;
                        }
                    }
                }           
            });

            //add the tile buttons to the tool bar
            tileBar.add(tileButtons[i]);
        }

        //send image array to canvas
        canvas.setTileArray(imAr);

        return tileBar;
    }//end createTileBar()

    /*********************************************************************
    * method: createResetButton()
    * description: This function creates the button that will reset
    * the grid canvas to its original empty state.
    ********************************************************************/
    private static JButton createResetButton()
    {
        //create reset button
        resetButton = new JButton("Reset");

        resetButton.setPreferredSize(new Dimension(50, 25));

        //listen for user to reset the grid
        resetButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae) 
            { 
                //clear the canvas
                canvas.clearGrid();
            }
        });

        return resetButton;
    }//end createResetButton()

    /*********************************************************************
    * method: createAndShowGUI()
    * description: This function creates the frame which will contain
    * the application components which is a TileDesigner object.
    ********************************************************************/
    private static void createAndShowGUI() 
    {
        //create new window frame
		windowFrame = new Tile_Designer();
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowFrame.setSize(800, 500);
        windowFrame.setTitle("Tile Designer App");
        windowFrame.setVisible(true);

        // windowFrame.setResizable(false);

	}//end createAndShowGUI()

    /*********************************************************************
    * method: main()
    * parameters: String[] args
    * description: This function runs the application by creating the GUI.
    ********************************************************************/
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
                createAndShowGUI();
            }
		});
    }//end main()

}//end TileDesigner class