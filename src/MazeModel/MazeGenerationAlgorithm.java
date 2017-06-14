package MazeModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Alexandre
 */
public class MazeGenerationAlgorithm {
    private MazeCell[][][] Cell;
    private Stack<MazeCell> stack;
    private Queue<MazeCell> queue;
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
    }
    
    // Helper function for DFS
    public void shuffleCells(MazeCell previous){
        LinkedList<MazeCell> openList;
        MazeCell[] neighbor;
        boolean meetEnd = false;
        while(!stack.isEmpty()){
            neighbor = previous.getNeighbor();
            openList = new LinkedList<>();
            boolean hasNeighbor = false;
            
            // Add all cell's unvisited neighbors to the list;
            for(MazeCell n : neighbor){
                if(!visited[n.getLevel()][n.getX()][n.getY()]){
                    if(!meetEnd || !n.getName().equals("red")){
                        hasNeighbor = true;
                        openList.add(n);
                    }
                }
            }
            
            // If current cell has a neighbor
            if(hasNeighbor){
                Collections.shuffle(openList);
                MazeCell n = openList.get(0);
                stack.push(n);
                
                // Check if the neighbor is not the end of the maze
                if(!n.getName().equals("red"))
                    n.assignTile(sheet.getTile("white"));
                else
                    meetEnd = true;
                // Add neighbor to the list
                n.setPrevious(previous);
                connectVertex(n, previous);
                visited[n.getLevel()][n.getX()][n.getY()] = true;
                previous = n;
            }
            else
                previous = stack.pop();
        }
    }
    
    // Helper function for BFS
    public void shuffleBFS(MazeCell previous){
        LinkedList<MazeCell> openList;
        boolean meetEnd = false;
        boolean isHead = true;
        while(!queue.isEmpty()){
            if(isHead){
                isHead = false;
                previous = queue.poll();
            }
            openList = new LinkedList<>();
            openList.addAll(Arrays.asList(previous.getNeighbor()));
            Collections.shuffle(openList);
            boolean hasNeighbor = false;
            // Add all cell's unvisited neighbors to the list;
            for(MazeCell n : openList){
                if(!visited[n.getLevel()][n.getX()][n.getY()]){
                    if(!meetEnd){
                        hasNeighbor = true;
                        if(!n.getName().equals("red"))
                            n.assignTile(sheet.getTile("white"));
                        else
                            meetEnd = true;
                        n.setPrevious(previous);
                        connectVertex(n, previous);
                        visited[n.getLevel()][n.getX()][n.getY()] = true;
                    }
                }
            }
            
            // If current cell has a neighbor
            if(hasNeighbor)
                queue.addAll(openList);
            previous = queue.poll();
        }
    }
    public void runBFS(MazeCell[][][] cell, MazeCell source){
        this.Cell = cell;
        visited = new boolean [cell.length][cell[0].length][cell[0][0].length];
        queue = new LinkedList<>();
        queue.add(source);
        visited[0][0][0] = true;
        shuffleBFS(source);
    }
    
    
    // Connect the two cells
    public void connectVertex(MazeCell current, MazeCell previous){
        int cX = current.getX();
        int cY = current.getY();
        int cL = current.getLevel();

        int level = cL - previous.getLevel();
        
        // ON THE SAME FLOOR
        if(level == 0){
            int xDiff = cX - previous.getX();
            int yDiff = cY - previous.getY();
            MazeCell middleCell;

            // ON THE SAME ROW
            if(xDiff != 0)
                
                middleCell = (xDiff > 0 ) ? Cell[cL][cX-1][cY] : Cell[cL][cX+1][cY];
            
            // ON THE SAME COLUMN
            else 
                middleCell = (yDiff > 0) ? Cell[cL][cX][cY - 1] : Cell[cL][cX][cY + 1];
            
            current.setPrevious(middleCell);
            middleCell.setPrevious(previous);
            middleCell.assignTile(sheet.getTile("white"));
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
                    previous.assignTile(sheet.getTile("orange"));
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
