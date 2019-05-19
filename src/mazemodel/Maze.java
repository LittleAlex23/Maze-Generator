package mazemodel;

import static mazemodel.MazeFloor.SIZE;
import static mazemodel.MazeFloor.MAXLEVEL;

import java.io.IOException;
import javax.imageio.ImageIO;

import mazemodel.algorithm.DFS;
import mazemodel.algorithm.MazeAlgorithm;
import mazemodel.algorithm.Prim;
import mazemodel.keyboard.Hotkey;

public class Maze {
    private MazeFloor[] floor;
    private MazeCell[][][] cell3D;
    private MazeCell[] pair = new MazeCell[2];
    private MazeCell previous;
    private MazeCell endCell;
    private MazeCell current;
    private MazeCell dirty;
    private int currentLevel;
    private boolean hasFloorChanged;
    private boolean gameOver;
    private boolean isOnDifferentFloor = true;
    private SpriteSheet sheet;
    public static int algorithmID;
    public void setFloor(MazeFloor[] floor){
        this.floor = floor;
        sheet = SpriteSheet.getInstance();
        try {
			sheet.setImage(ImageIO.read(getClass().getResource("/res/colors.png")));
			cell3D = MazeBuilder.createMaze(floor);
	        init();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    // Initialize the start and end positions
    public final void init(){
        currentLevel = 0;
        
        // Set up the player's initial position
        dirty = current = previous = cell3D[0][0][0];
        previous.assignTile(Tile.ON_WHITE, Tile.START);
        dirty.setImage(Tile.START);
        // The cell the player has to reach
        endCell = cell3D[MAXLEVEL-1][SIZE-1][SIZE-1];
        endCell.assignTile(Tile.ON_END, Tile.END);
        runAlgorithm();
    }
    
    // Reset all the tile image to BLACK
    public void resetCell(){
        for (MazeCell[][] levels : cell3D) {
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
    	return cell3D[currentLevel][x][y].getRealTile() == Tile.BLACK;
    }
    // Move the player based on key input
    public void move(char d){
        int x = previous.getX();
        int y = previous.getY();
        hasFloorChanged = false;
        char direction = d;
        switch(direction){
           case Hotkey.LEFT:
               if(y-1 >= 0 && !onBlack(x,y-1))
            	   current = cell3D[currentLevel][x][y-1];
               break;
               
           case Hotkey.DOWN_STAIRS:
               if(currentLevel -1 < 0)
                   return;
               if(previous.getRealTile() == Tile.DOWN || previous.getRealTile() == Tile.UP_OR_DOWN){
                   currentLevel--;
                   isOnDifferentFloor = hasFloorChanged = true;
               }
               current = cell3D[currentLevel][x][y];
               break;  
               
           case Hotkey.UP_STAIRS:
               if(currentLevel + 1 >= MAXLEVEL)
                   return;
               if(previous.getRealTile() == Tile.UP || previous.getRealTile() == Tile.UP_OR_DOWN){
                   currentLevel++;
                   isOnDifferentFloor = hasFloorChanged = true;
               }
               current = cell3D[currentLevel][x][y];
               break;
               
           case Hotkey.DOWN:
               if(x+1 < MazeFloor.SIZE && !onBlack(x+1, y))
            	   current = cell3D[currentLevel][x+1][y];
               break;
               
           case Hotkey.UP:
               if(x-1 >= 0 && !onBlack(x-1, y))
            	   current = cell3D[currentLevel][x-1][y];
               break;  
               
           case Hotkey.RIGHT:
               if(y+1 < SIZE && !onBlack(x, y+1))
            	   current = cell3D[currentLevel][x][y+1];
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
       
        // Previous cell x,y coordinate is the same if player moves to different floor.
        else if(isOnDifferentFloor){
            isOnDifferentFloor = false;
            dirty = current;
        }
    }
    
    public boolean isGameOver(){
        return gameOver;
    }
    
    public boolean hasFloorChanged(){
        return hasFloorChanged;
    }
    public Tile[][] getFloorTile(){
        MazeFloor f = floor[currentLevel];
        f.updateFloor();
        return floor[currentLevel].getFloorTile();
    }
    
    public MazeCell getCurrentCell(){
        return current;
    }
    
    public int getLevel(){
        return currentLevel;
    }
    
    // Run prim's algorithm to generate the maze
    public void runAlgorithm(){
        MazeAlgorithm algorithm;
        switch(algorithmID){
            case 1:
                algorithm = new Prim();
                break;
            default:
                algorithm = new DFS();
        }
        algorithm.run(cell3D, previous);
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
