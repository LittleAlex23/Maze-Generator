package MazeLayout;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author Alexandre
 */
public class MazeGenerationAlgorithm {
    private MazeCell[][][] Cell;
    private Stack<MazeCell> stack;
    private SpriteSheet sheet;
    private boolean[][][] visited;
    public MazeGenerationAlgorithm(){
        sheet = SpriteSheet.getInstance();
    }
    public void runDFS(MazeCell[][][] cell, MazeCell source){
        this.Cell = cell;
        visited = new boolean [cell.length][cell[0].length][cell[0][0].length];
        stack = new Stack<>();
        stack.push(source);
        visited[0][0][0] = true;
        shuffleCells(source);
        
 //       while(!stack.isEmpty())
    //        shuffleCells(stack.pop());
    }
    public void shuffleCells(MazeCell previous){
        LinkedList<MazeCell> openList;
        MazeCell[] neighbor;
        while(!stack.isEmpty()){
            neighbor = previous.getNeighbor();
            openList = new LinkedList<>();
            boolean isAvailable = false;
            
            // Add all cell's unvisited neighbors to the list;
            for(MazeCell n : neighbor){
                if(!visited[n.getLevel()][n.getX()][n.getY()]){
                    isAvailable = true;
                    openList.add(n);
                }
            }
            if(isAvailable){
                Collections.shuffle(openList);
                for(MazeCell n : openList)
                    stack.push(n);
                MazeCell n = stack.pop();
                isAvailable = true;
                // Check if the neighbor is not the end of the maze
                if(!n.getName().equals("red"))
                    n.assignTile(sheet.getTile("white"));

                // Add neighbor to the list
                n.setPrevious(previous);
                connectVertex(n, previous);

                visited[n.getLevel()][n.getX()][n.getY()] = true;
                stack.add(n);
            }
            // Backtrack until the next cell has an unvisited neighbor or
            // the stack is empty
            if(!isAvailable)
                previous = stack.pop();
        }
    }
    
    // Connect the two cells
    public void connectVertex(MazeCell current, MazeCell previous){
        int currentX = current.getX();
        int currentY = current.getY();
        int currentL = current.getLevel();

        int level = currentL - previous.getLevel();
        
        // ON THE SAME FLOOR
        if(level == 0){
            int row = currentX - previous.getX();
            int column = currentY - previous.getY();
            MazeCell previousCell;

            // ON THE SAME COLUMN
            if(row != 0){
                if(row > 0)
                    // SOUTH
                    previousCell = Cell[currentL][currentX-1][currentY];
                else
                    // NORTH
                    previousCell = Cell[currentL][currentX+1][currentY];
            }
            
            // ON THE SAME ROW
            else {
                // EAST
                if(column > 0)
                    previousCell = Cell[currentL][currentX][currentY - 1];
                // WEST
                else
                    previousCell = Cell[currentL][currentX][currentY + 1];
            }
            current.setPrevious(previousCell);
            previousCell.setPrevious(previous);
            previousCell.assignTile(sheet.getTile("white"));
        }
        else {
            // GOING UP
            if(level == 1){
                
                // Can go either up or down from the current cell
                if(previous.getName().equals("orange")){
                    current.assignTile(sheet.getTile("orange"));
                    previous.assignTile(sheet.getTile("green"));
                }
                
                // Can only go up
                else if(current.getName().equals("white")){
                    current.assignTile(sheet.getTile("orange"));
                    previous.assignTile(sheet.getTile("pink"));
                }
                
                // Can't go up to the end cell
                else if(!previous.getName().equals("red"))
                    previous.assignTile(sheet.getTile("pink"));
            }
            
            // GOING DOWN
            else{
                
                // Can go either up or down from the current cell
                if(previous.getName().equals("pink")){
                    current.assignTile(sheet.getTile("pink"));
                    previous.assignTile(sheet.getTile("green"));
                }
                
                // Can only go down
                else if(current.getName().equals("white")){
                    current.assignTile(sheet.getTile("pink"));
                    previous.assignTile(sheet.getTile("orange"));
                
                }
            }
        }
    }
}
