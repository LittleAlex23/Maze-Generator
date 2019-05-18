package mazemodel;

import static mazemodel.MazeFloor.COLUMNS;
import static mazemodel.MazeFloor.MAXLEVEL;

import java.io.IOException;
import javax.imageio.ImageIO;

import mazemodel.keyboard.Hotkey;

public class Maze {
    private MazeFloor[] floor;
    private MazeCell[][][] cell;
    private MazeCell previous;
    private int currentLevel;
    private MazeCell endCell;
    private boolean hasChanged;
    private boolean gameOver;
    private SpriteSheet sheet;
    public static int algorithmID;
    private MazeCell current;
    private MazeCell dirty;
    private MazeCell[] pair = new MazeCell[2];
    private boolean floorChanged = true;
    public void setFloor(MazeFloor[] floor) throws IOException{
        this.floor = floor;
        sheet = SpriteSheet.getInstance();
        sheet.setImage(ImageIO.read(getClass().getResource("/res/colors.png")));
        cell = MazeBuilder.buildCell(floor, MAXLEVEL, COLUMNS);
        init();
    }
    // Initialize the start and end positions
    public final void init(){
        currentLevel = 0;
        
        // Set up the player's initial position
        dirty = current = previous = cell[0][0][0];
        previous.assignTile(Tile.ON_WHITE, Tile.START);
        dirty.setImage(Tile.START);
        // The cell the player has to reach
        endCell = cell[MAXLEVEL-1][COLUMNS-1][COLUMNS-1];
        endCell.assignTile(Tile.ON_END, Tile.END);
        runAlgorithm();
    }
    
    // Reset all the tile image to BLACK
    public void resetCell(){
        for (MazeCell[][] levels : cell) {
            for (MazeCell[] rows : levels) {
                for (MazeCell columns : rows) {
                    columns.resetTile();
                }
            }
        }
        init();
    }
	public MazeCell[] getPairs(){
		pair[0] = dirty;
		pair[1] = current;
		return pair;
	}
    public boolean onBlack(int x, int y) {
    	return cell[currentLevel][x][y].getRealTile() == Tile.BLACK;
    }
    // Move the player based on key input
    public void move(int d){
        int x = previous.getX();
        int y = previous.getY();
        hasChanged = false;
        char direction = (char)d;
        switch(direction){
           case Hotkey.LEFT:
               if(y-1 >= 0 && !onBlack(x,y-1))
            	   current = cell[currentLevel][x][y-1];
               break;
               
           case Hotkey.DOWN_STAIRS:
               if(currentLevel -1 < 0)
                   return;
               if(previous.getRealTile() == Tile.DOWN || previous.getRealTile() == Tile.UP_OR_DOWN){
                   currentLevel--;
                   floorChanged = hasChanged = true;
               }
               current = cell[currentLevel][x][y];
               break;  
               
           case Hotkey.UP_STAIRS:
               if(currentLevel + 1 >= MAXLEVEL)
                   return;
               if(previous.getRealTile() == Tile.UP || previous.getRealTile() == Tile.UP_OR_DOWN){
                   currentLevel++;
                   floorChanged = hasChanged = true;
               }
               current = cell[currentLevel][x][y];
               break;
               
           case Hotkey.DOWN:
               if(x+1 < MazeFloor.COLUMNS && !onBlack(x+1, y))
            	   current = cell[currentLevel][x+1][y];
               break;
               
           case Hotkey.UP:
               if(x-1 >= 0 && !onBlack(x-1, y))
            	   current = cell[currentLevel][x-1][y];
               break;  
               
           case Hotkey.RIGHT:
               if(y+1 < COLUMNS && !onBlack(x, y+1))
            	   current = cell[currentLevel][x][y+1];
               break;
           // Return if key is invalid
           default:
               return;
        }
        
    	dirty = previous;
    	dirty.setImage(previous.getRealTile());
    	current.setImage(Tile.ON_WHITE);
        previous = current;
       // The next square is the exit
       if(current.getRealTile() == Tile.END)
           gameOver = true;
       
        // Previous cell should be the current position if player moves to different floor.
        else if(floorChanged){
            floorChanged = false;
            dirty = current;
        }
    }
    
    public boolean isGameOver(){
        return gameOver;
    }
    
    public boolean hasFloorChanged(){
        return hasChanged;
    }
    public Tile[][] getTile(){
        MazeFloor f = floor[currentLevel];
        f.updateFloor();
        return floor[currentLevel].getTile();
    }
    
    public MazeCell getCurrentCell(){
        return current;
    }
    
    public int getLevel(){
        return currentLevel;
    }
    
    // Run prim's algorithm to generate the maze
    public void runAlgorithm(){
        MazeGenerationAlgorithm algorithm = new MazeGenerationAlgorithm();
        switch(algorithmID){
            case 1:
                algorithm.runPrim(cell, previous);
                break;
            default:
                algorithm.runDFS(cell, previous);
        }
    }
    // Show the path from the start to the end
    public void showPath(){
        MazeCell c = endCell.getPrevious();
        while(c != null){
            c.setImage(Tile.SOLUTION);
            c = c.getPrevious();
        }
    }
    
}
