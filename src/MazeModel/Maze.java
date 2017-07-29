package MazeModel;

import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import static MazeModel.MazeFloor.COLUMNS;
import static MazeModel.MazeFloor.MAXLEVEL;
/**
 *
 * @author Alexandre
 */
public class Maze {
    private final MazeFloor[] floor;
    private final MazeCell[][][] allCells;
    private MazeCell currentCell;
    private int currentLevel;
    private MazeCell red;
    private boolean hasChanged;
    private boolean gameOver;
    private final SpriteSheet sheet;
    public static int algorithmID = 0;
    private MazeCell current;
    private MazeCell previous;
    private boolean floorChanged = true;
    public Maze(MazeFloor[] floors) throws IOException{
        floor = floors;
        sheet = SpriteSheet.getInstance();
        sheet.setImage(ImageIO.read(getClass().getResource("/res/colors.png")));
        allCells = MazeBuilder.buildCell(floor, MAXLEVEL, COLUMNS);
        init();
    }
    
    // Initialize the start and end positions
    public final void init(){
        currentLevel = 0;
        // Set up the player's initial position
        previous = current = currentCell = allCells[0][0][0];
        currentCell.assignTile(Tile.ONWHITE, Tile.START);
        
        // The cell the player has to reach
        red = allCells[MAXLEVEL-1][COLUMNS-1][COLUMNS-1];
        red.assignTile(Tile.ON_END, Tile.END);
        runAlgorithm();
    }
    
    // Reset all the tile image to BLACK
    public void resetCell(){
        for (MazeCell[][] levels : allCells) {
            for (MazeCell[] rows : levels) {
                for (MazeCell columns : rows) {
                    columns.resetTile();
                }
            }
        }
    }
    public int[] getCoord(){
        return new int[]{previous.getX(), previous.getY(), current.getX(), current.getY()};
    }
    public Tile[] getPairs(){
         return new Tile[]{previous.getPermImage(), current.getTile()};
    }
    // Move the player based on key input
    public void move(int d){
        // Return if the key is invalid
        if(d != KeyEvent.VK_S &&  
            d != KeyEvent.VK_W && 
            d != KeyEvent.VK_A && 
            d != KeyEvent.VK_D && 
            d != KeyEvent.VK_E && 
            d != KeyEvent.VK_C)
            return;
        int x = currentCell.getX();
        int y = currentCell.getY();
        hasChanged = false;
        char c = (char)d;
        switch(c){
           // GO DOWN
           case 'S':
               if(x+1 >= MazeFloor.COLUMNS)
                   return;
               current = allCells[currentLevel][x+1][y];
               break;
               
           // GO UP
           case 'W':
               if(x-1 < 0)
                   return;
               current = allCells[currentLevel][x-1][y];
               break;
               
           // GO LEFT
           case 'A':
               if(y-1 < 0)
                   return;
               current = allCells[currentLevel][x][y-1];
               break;
               
           // GO UPSTAIRS
           case 'E':
               if(currentLevel + 1 >= MAXLEVEL)
                   return;
               if(currentCell.getPermImageName().equals("pink") || currentCell.getPermImageName().equals("green")){
                   currentLevel++;
                   floorChanged = hasChanged = true;
               }
               current = allCells[currentLevel][x][y];
               break;
          
           // GO DOWNSTAIRS
           case 'C':
               if(currentLevel -1 < 0)
                   return;
               if(currentCell.getPermImageName().equals("orange") || currentCell.getPermImageName().equals("green")){
                   currentLevel--;
                   floorChanged = hasChanged = true;
               }
               current = allCells[currentLevel][x][y];
               break;    
           
           // GO RIGHT 
           default:
               if(y+1 >= COLUMNS)
                   return;
               current = allCells[currentLevel][x][y+1];
               break;
        }
        
        // Prevent stepping on the BLACK square
        if(!current.getPermImageName().equals("black")){
            currentCell.changeImage(currentCell.getPermImage());
                previous = currentCell;
            currentCell = current;
            currentCell.changeImage(Tile.ONWHITE);
           // The next square is the exit
           if(currentCell.getPermImageName().equals("red"))
               gameOver = true;
       }
        // Previous cell should be the current position if player moves to different floor.
        else if(floorChanged){
            floorChanged = false;
            previous = current;
        }
    }
    // Check if the player has reached the end
    public boolean isGameOver(){
        return gameOver;
    }
    
    // Move up or down the floor
    public boolean hasFloorChanged(){
        return hasChanged;
    }
    public Tile[][] getTile(){
        MazeFloor f = floor[currentLevel];
        f.updateFloor();
        return floor[currentLevel].getTile();
    }
    // Return the curent cell the player is on
    public MazeCell getCurrentCell(){
        return currentCell;
    }
    
    // Return the current number of the floor
    public int getLevel(){
        return currentLevel;
    }
    
    // Run prim's algorithm to generate the maze
    public void runAlgorithm(){
        MazeGenerationAlgorithm algorithm = new MazeGenerationAlgorithm();
        switch(algorithmID){
            case 1:
                algorithm.runPrim(allCells, currentCell);
                break;
            default:
                algorithm.runDFS(allCells, currentCell);
        }
    }
    // Show the path from the start to the end
    public void showPath(){
        MazeCell c = red.getPrevious();
        while(!c.getPermImageName().equals("magenta")){
            c.changeImage(Tile.CYAN);
            c = c.getPrevious();
        }
    }
    
}
