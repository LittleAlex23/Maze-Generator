package MazeModel;

import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Alexandre
 */
public class MazeFloor extends JPanel {
    private final MazeCell[][] floorCells;
    private final BufferedImage[][] floorImage;
    protected MazeFloor(int level, int COLUMNS){
        floorImage = new BufferedImage[COLUMNS][COLUMNS];
        floorCells = new MazeCell[COLUMNS][COLUMNS];
        
        // initialize the cells
        for(int i = 0; i < COLUMNS; i++)
            for(int j = 0; j < COLUMNS; j++)
                floorCells[i][j] = new MazeCell(level, i * COLUMNS + j);
    }
    
    // Return the cells for this floor
    protected MazeCell[][] getFloorCells(){
        return floorCells;
    }
    
    // Generate the cells in this floor 
    protected void generateFloor(){
        for(int i = 0; i < floorCells.length; i++)
            for(int j = 0; j < floorCells[i].length; j++)
                floorImage[i][j] = floorCells[i][j].getCurrentImage().getImage();
    }
    // Return the colors of the cell for this floor
    protected BufferedImage[][] getImage(){
        return floorImage;
    }
}
