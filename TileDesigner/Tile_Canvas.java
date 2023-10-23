import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*********************************************************************
* class: TileCanvas
* description: This class creates and manipulates a 5x5 grid
* that paints Images on the cells.
********************************************************************/
public class Tile_Canvas extends JPanel implements MouseListener
{
    final private int rowCount = 5;//grid row count
    final private int columnCount = 5;//grid column count
    private int selectedImageIndex;//index of cell that will draw the array
    private int[][] drawCells;//array to determine which cells to be filled
    private Image[] tileImageArray;//array to hold images to be drawn
    private Point selectedCell;//Point to determine which cell is to be filled
    private boolean resetGrid;//flag to determine whether grid should be reset

    /*********************************************************************
    * method: TileCanvas()
    * description: This constructor intializes the member variables
    * of the TileCanvas class and adds a MouseListener.
    ********************************************************************/    
    public Tile_Canvas()
    {
        //initialize reset grid flag to false
        resetGrid = false;

        //initialize the 2D array to false
        drawCells = new int[rowCount][columnCount];

        //initialize each element to false
        for(int e = 0; e < rowCount; e++)
        {
            for(int i = 0; i < columnCount; i++)
            {
                drawCells[e][i] = -1;
            }
        }

        //add mouse listener
        addMouseListener(this);
    }//end TileCanvas()

    private void addMouseListener(Tile_Canvas l) {
    }

    /*********************************************************************
    * method: setTileIndex()
    * parameters: int selectedTileIndex
    * description: This setter function sets the selectedImageIndex.
    ********************************************************************/
    public void setTileIndex(int selectedTileIndex)
    {
        selectedImageIndex = selectedTileIndex;
    }//end setTileIndex()

    /*********************************************************************
    * method: setTileArray()
    * parameters: Image[] imageArray
    * description: This setter function sets the tileImage array.
    ********************************************************************/
    public void setTileArray(Image[] imageArray)
    {
        tileImageArray = imageArray;
    }//end setTileArray()

    /*********************************************************************
    * method: clearGrid()
    * description: This function repaints the canvas and sets the reset flag.
    ********************************************************************/
    public void clearGrid()
    {
        resetGrid = true;
        repaint();
    }//end clearGrid()

    /*********************************************************************
    * method: paintComponent()
    * parameters: Graphics g
    * description: This function paints a 5x5 grid and repaints the grid
    * with the images provided to it. It also reset the grid to its 
    * original empty state.
    ********************************************************************/    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        //get dimensions of the grid and each cell
        int width = getWidth();
        int height = getHeight();
        int cellWidth = width / 5;
        int cellHeight = height / 5;

        //draw the 5x5 grid
        for (int y = 0; y <= height; y += cellHeight) 
        {
            g.drawLine(0, y, width, y);
        }
        for (int x = 0; x <= width; x += cellWidth) 
        {
            g.drawLine(x, 0, x, height);
        }
        
        //scan the grid for the cell that needs to be drawn
        for (int i = 0; i < rowCount; i++) 
        {
            for (int j = 0; j < columnCount; j++) 
            {
                //paint the tile in the selected cell
                if (drawCells[i][j] != -1) 
                {
                    //fill the cell with the selected tile image
                    g.drawImage(tileImageArray[drawCells[i][j]], i * cellWidth, j * cellHeight, cellWidth, cellHeight, null);
                }
            }
        }

        //if reset button has been clicked then reset the grid
        if (resetGrid == true)
        {
            for (int i = 0; i < rowCount; i++) 
            {
                for (int j = 0; j < columnCount; j++) 
                {
                    //reset cells to initial states
                    g.drawImage(null, i * cellWidth, j * cellHeight, cellWidth, cellHeight, null);
                    
                    //reset cell state array to empty
                    drawCells[i][j] = -1;
                }
            }

            //reset the flag
            resetGrid = false;         
        }
    }//end paintComponent()

    /**
     * The empty MouseListener functions are here to satisfy the compiler error
     */
	public void mouseExited(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}

    /*********************************************************************
    * method: mousePressed()
    * parameters: MouseEvent me
    * description: This function reacts to a mouse click by calculating
    * the dimensions of the grid cell that that selected and sending that
    * information to the paintComponent function to fill that cell.
    ********************************************************************/
	public void mousePressed(MouseEvent me)
	{
        //get dimensions of the selected cell
        int width = getWidth();
        int height = getHeight();
        int cellWidth = width / 5;
        int cellHeight = height / 5;

        //get the dimensions of the call that was clicked
        Point p = me.getPoint();
        int i = p.x / cellWidth;
        int j = p.y / cellHeight;

        //set cell to be painted to selection index
        drawCells[i][j] = selectedImageIndex;
        
        //paint that cell
        repaint();
	}//end mousePressed()

}//end TileCanvas