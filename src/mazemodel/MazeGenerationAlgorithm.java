package mazemodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class MazeGenerationAlgorithm {
    private MazeCell[][][] cell;
    private Stack<MazeCell> stack;
    private boolean[][][] visited;
    
    // Prim's algorithm
    public void runPrim(MazeCell[][][] cell, MazeCell source){
        this.cell = cell;
        visited = new boolean[cell.length][cell[0].length][cell[0][0].length];
        visited[0][0][0] = true;
        boolean[][][] candidateList = new boolean[cell.length][cell[0].length][cell[0][0].length];
        LinkedList<MazeCell> openList = new LinkedList<>();
        List<MazeCell> neighbor;
        MazeCell current;
        openList.addAll(source.getNeighbor());

        boolean toggle = false;
        while(!openList.isEmpty()){
            // Shuffle the cells
            Collections.shuffle(openList);
            
            // Pick the first element in the list
            current = openList.removeFirst();
            
            // Set the current cell set as visited
            visited[current.getLevel()][current.getX()][current.getY()] = true;
            neighbor = current.getNeighbor();
            Collections.shuffle(neighbor);
            
            // Add all unvisited neighbors to openList
            for(MazeCell n : neighbor){
                if(!visited[n.getLevel()][n.getX()][n.getY()] && !candidateList[n.getLevel()][n.getX()][n.getY()]) {
                    openList.add(n);
                    candidateList[n.getLevel()][n.getX()][n.getY()] = true;
                }
            }
            // Prevent the ending cell image to be WHITE
            if(current.getRealTile() != Tile.END)
                current.assignTile(Tile.WHITE);
            
            // Connect the current cell to a random visited neighbor, if possible
            List<MazeCell> g = current.getNeighbor();
            Collections.shuffle(g);
            for(MazeCell n : g){
                if(visited[n.getLevel()][n.getX()][n.getY()]){
                    
                    // Disallow the starting cell to be connected to its top neighbor
                    if((n.getRealTile() == Tile.ON_START && current.getLevel() == 0) || (toggle && 
                            n.getRealTile() != Tile.ON_START)){
                        current.setPrevious(n);
                        connectVertex(current, n);
                        break;
                    }
                }
            }
            toggle = true;
        }
    }
    public void runDFS(MazeCell[][][] cell, MazeCell source){
        this.cell = cell;
        visited = new boolean [cell.length][cell[0].length][cell[0][0].length];
        stack = new Stack<>();
        stack.push(source);
        visited[0][0][0] = true;
        shuffleCells(source);
    }
    // Helper function for DFS
    public void shuffleCells(MazeCell p){
        LinkedList<MazeCell> openList;
        ArrayList<MazeCell> neighbor;
        boolean meetEnd = false;
        while(!stack.isEmpty()){
            neighbor = p.getNeighbor();
            openList = new LinkedList<>();
            boolean hasNeighbor = false;
            
            // Add all cell's unvisited neighbors to the list;
            for(MazeCell n : neighbor){
                if(!visited[n.getLevel()][n.getX()][n.getY()]){
                    if(!meetEnd || n.getRealTile() != Tile.END){
                        hasNeighbor = true;
                        openList.add(n);
                    }
                }
            }
            
            // Current cell has at least one neighbor
            if(hasNeighbor){
                Collections.shuffle(openList);
                MazeCell n = openList.get(0);
                stack.push(n);
                
                // Check if the neighbor is not the end of the maze
                if(n.getRealTile() != Tile.END)
                    n.assignTile(Tile.WHITE);
                else
                    meetEnd = true;
                // Add neighbor to the list
                n.setPrevious(p);
                connectVertex(n, p);
                visited[n.getLevel()][n.getX()][n.getY()] = true;
                p = n;
            }
            else
                p = stack.pop();
        }
    }
    private void connect(MazeCell current, MazeCell middleCell ,MazeCell previous) {
    	current.setPrevious(middleCell);
        middleCell.setPrevious(previous);
        middleCell.assignTile(Tile.WHITE);
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
            if(level == 1){
                if(current.getRealTile() == Tile.END)
                    previous.assignTile(Tile.UP);
                // Can go either up or down from the current cell
                else if(previous.getRealTile() == Tile.DOWN){
                    current.assignTile(Tile.DOWN);
                    previous.assignTile(Tile.UP_OR_DOWN);
                }
                // Can only go up
                else if(current.getRealTile() == Tile.WHITE){
                    current.assignTile(Tile.DOWN);
                    previous.assignTile(Tile.UP);
                }
            }
            
            // GOING DOWN
            else {
                
                if(previous.getRealTile() == Tile.END)
                    return;
                // Can go either up or down from the current cell
                if(previous.getRealTile() == Tile.UP){
                    current.assignTile(Tile.UP);
                    previous.assignTile(Tile.UP_OR_DOWN);
                }
                // Can only go down
                else if(current.getRealTile() == Tile.WHITE){
                    current.assignTile(Tile.UP);
                    previous.assignTile(Tile.DOWN);
                }
            }
        }
    }
}
