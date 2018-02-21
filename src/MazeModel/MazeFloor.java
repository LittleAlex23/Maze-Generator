package MazeModel;

import javax.swing.JPanel;

public class MazeFloor extends JPanel {
    public static int COLUMNS;
    public static int MAXLEVEL;
    private MazeCell[][] floorCells;
    private final Tile[][] floorImage;
    protected MazeFloor(){
        floorImage = new Tile[COLUMNS][COLUMNS];
    }
    public void fillCells(MazeCell[][] cells){
        floorCells = cells;
    }
    // Return the cells for this floor
    protected MazeCell[][] getFloorCells(){
        return floorCells;
    }
    // update the current status floor 
    protected void updateFloor(){
        for(int i = 0; i < floorCells.length; i++)
            for(int j = 0; j < floorCells[i].length; j++)
                floorImage[i][j] = floorCells[i][j].getTile();
    }
    // Return the cell images for this floor
    protected Tile[][] getTile(){
        return floorImage;
    }
}
