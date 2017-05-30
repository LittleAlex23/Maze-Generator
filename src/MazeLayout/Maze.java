package MazeLayout;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
/**
 *
 * @author Alexandre
 */
public class Maze {
    private MazeFloor[] floor;
    private MazeCell[] vertex;;
    private MazeCell[][][] allCells;
    private MazeCell currentCell;
    private int currentLevel;
    public static int SIZE;
    private MazeCell red;
    static int maxLevel;
    private boolean hasChanged;
    private boolean gameOver;
    BufferedImage sheetLoader;
    SpriteSheet sheet;
    public Maze(int maxLevel, int SIZE) throws IOException{
        BufferedImageLoader loader = new BufferedImageLoader();
        sheetLoader = loader.loadImage("/res/colors.png");
        sheet = SpriteSheet.getInstance();
        sheet.setImage(sheetLoader);
        Maze.maxLevel = maxLevel;
        Maze.SIZE = SIZE;
        create();
    }
    public final void create(){
        currentLevel = 0;
        vertex = new MazeCell[maxLevel *((SIZE/2) *(SIZE/2) + SIZE)];
        
        // All the squares of every floor
        allCells = new MazeCell[maxLevel][SIZE][SIZE];
        floor = new MazeFloor[maxLevel];
        int count = 0;
        for(int i = 0; i < maxLevel; i++){
            floor[i] = new MazeFloor(i, SIZE);
            allCells[i] = floor[i].getFloorCells();
            for(MazeCell[] rows : allCells[i]){
                for(MazeCell columns : rows)
                    if(columns.isVertex())
                        vertex[count++] = columns;
            }
        }
        
        // Add the cells to be used as nodes of a graph for maze generation
        for(MazeCell s : vertex)
            s.setNeighbor(allCells);
        
        // Set up the player's initial position
        currentCell = vertex[0];
        currentCell.assignTile(sheet.getTile("onWhite"), sheet.getTile("magenta"));
        // The cell the player has to reach
        red = vertex[vertex.length - 1];
        
        red.assignTile(sheet.getTile("onRed"), sheet.getTile("red"));
        runAlgorithm();
    }
    // Move the player based on key input
    public void move(int direction){
        
        // Return if the key is invalid
        if(direction != KeyEvent.VK_S &&  direction != KeyEvent.VK_W && direction != KeyEvent.VK_A
            && direction != KeyEvent.VK_D && direction != KeyEvent.VK_E && direction != KeyEvent.VK_C)
            return;
        
        MazeCell next;
        int x = currentCell.getX();
        int y = currentCell.getY();
        hasChanged = false;
        switch(direction){
            // down
           case 83:
               if(x+1 >= SIZE)
                   return;
               next = allCells[currentLevel][x+1][y];
               break;
               
            // up
           case 87:
               if(x-1 < 0)
                   return;
               next = allCells[currentLevel][x-1][y];
               break;
               
           // left
           case 65:
               if(y-1 < 0)
                   return;
               next = allCells[currentLevel][x][y-1];
               break;
               
            // up
           case 69:
               if(currentLevel + 1 >= maxLevel)
                   return;
               if(currentCell.getName().equals("pink") || currentCell.getName().equals("green")){
                   currentLevel++;
                   hasChanged = true;
               }
               next = allCells[currentLevel][x][y];
               break;
          
           // down
           case 67:
               if(currentLevel -1 < 0)
                   return;
               if(currentCell.getName().equals("orange") || currentCell.getName().equals("green")){
                   currentLevel--;
                   hasChanged = true;
               }
               next = allCells[currentLevel][x][y];
               break;    
           // right
           default:
               if(y+1 >= SIZE)
                   return;
               next = allCells[currentLevel][x][y+1];
               break;
       }
        
        // Prevent stepping on the black square
        if(!next.getName().equals("black")){
            currentCell.changeImage(currentCell.getPermImage());
            currentCell = next;
            currentCell.changeImage(sheet.getTile("onWhite"));
           // The next square is the exit
           if(currentCell.getName().equals("red")){
               System.out.println("ddd");
               gameOver = true;
           }
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
    
    // Return all the floors in the maze
    public MazeFloor[] getFloors(){
        return floor;
    }
    public BufferedImage[][] getImage(){
        MazeFloor f = floor[currentLevel];
        f.generateFloor();
        return f.getImage();
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
        algorithm.runDFS(allCells, currentCell);
        floor[currentLevel].generateFloor();
    }
    
    // Show the path from the start to the end
    public void showPath(){
        MazeCell current = red.getPrevious();
        while(!current.getName().equals("magenta")){
            current.changeImage(sheet.getTile("cyan"));
            current = current.getPrevious();
        }
    }
}
