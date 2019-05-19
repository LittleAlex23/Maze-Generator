package mazemodel.algorithm;

import java.util.List;
import java.util.Stack;

import mazemodel.MazeCell;
import mazemodel.Tile;

public abstract class MazeAlgorithm {
	protected MazeCell[][][] cell;
	protected Stack<MazeCell> stack;
	protected boolean[][][] visited;
	protected List<MazeCell> openList;
	protected List<MazeCell> neighbor;
	public abstract void run(MazeCell[][][] cell, MazeCell source);
	private void connect(MazeCell current, MazeCell middleCell ,MazeCell previous) {
    	current.setPrevious(middleCell);
        middleCell.setPrevious(previous);
        middleCell.setFrontTile(Tile.WHITE);
    }
	// Connect the two cells
    protected void connectVertex(MazeCell current, MazeCell previous){
        int cX = current.getX();
        int cY = current.getY();
        int cL = current.getLevel();

        int levelDif = cL - previous.getLevel();
        
        // ON THE SAME FLOOR
        if(levelDif == 0){
            int xDiff = cX - previous.getX();
            int yDiff = cY - previous.getY();
            MazeCell middle;

            // ON THE SAME ROW
            if(xDiff != 0)                
                middle = (xDiff > 0 ) ? cell[cL][cX-1][cY] : cell[cL][cX+1][cY];
            
            // ON THE SAME COLUMN
            else 
                middle = (yDiff > 0) ? cell[cL][cX][cY - 1] : cell[cL][cX][cY + 1];
            
            connect(current, middle, previous);
        }
        else {
            // GOING UP
            if(levelDif == 1){
            	if(previous.getRealTile() == Tile.START)
                	return;
                if(current.getRealTile() == Tile.END)
                    previous.setFrontTile(Tile.UP);
                // Can go either up or down from the current cell
                else if(previous.getRealTile() == Tile.DOWN){
                    current.setFrontTile(Tile.DOWN);
                    previous.setFrontTile(Tile.UP_OR_DOWN);
                }
                // Can only go up
                else if(current.getRealTile() == Tile.WHITE){
                    current.setFrontTile(Tile.DOWN);
                    previous.setFrontTile(Tile.UP);
                }
            }
            
            // GOING DOWN
            else {
               
                if(previous.getRealTile() == Tile.END)
                    return;
                // Can go either up or down from the current cell
                if(previous.getRealTile() == Tile.UP){
                    current.setFrontTile(Tile.UP);
                    previous.setFrontTile(Tile.UP_OR_DOWN);
                }
                // Can only go down
                else if(current.getRealTile() == Tile.WHITE){
                    current.setFrontTile(Tile.UP);
                    previous.setFrontTile(Tile.DOWN);
                }
            }
        }
    }
    
}
