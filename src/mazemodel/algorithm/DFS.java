package mazemodel.algorithm;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import mazemodel.MazeCell;
import mazemodel.Tile;

public class DFS extends MazeAlgorithm{
	public void run(MazeCell[][][] cell, MazeCell source){
        this.cell = cell;
        visited = new boolean [cell.length][cell[0].length][cell[0][0].length];
        stack = new Stack<>();
        stack.push(source);
        visited[0][0][0] = true;
        useDFS(source);
    }
    // Helper function for DFS
    public void useDFS(MazeCell current){
        boolean meetEnd = false;
        while(!stack.isEmpty()){
            neighbor = current.getNeighbor();
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
                    n.setFrontTile(Tile.WHITE);
                else
                    meetEnd = true;
                // Add neighbor to the list
                n.setPrevious(current);
                connectVertex(n, current);
                visited[n.getLevel()][n.getX()][n.getY()] = true;
                current = n;
            }
            else
                current = stack.pop();
        }
    }
}
