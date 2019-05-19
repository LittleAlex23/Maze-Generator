package mazemodel;

import javax.swing.JPanel;

public class MazeFloor extends JPanel {
	private static final long serialVersionUID = 1L;
	public static int SIZE;
    public static int MAXLEVEL;
    private MazeCell[][] floorCells;
    private final Tile[][] floorImage;
    protected MazeFloor(){
        floorImage = new Tile[SIZE][SIZE];
    }
    public void fillFloor(MazeCell[][] cells){
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
                floorImage[i][j] = floorCells[i][j].getFrontTile();
    }
    // Return the cell images for this floor
    protected Tile[][] getFloorTile(){
        return floorImage;
    }
}
