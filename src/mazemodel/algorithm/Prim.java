package mazemodel.algorithm;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import mazemodel.MazeCell;
import mazemodel.Tile;

public class Prim extends MazeAlgorithm{
	private boolean[][][] candidateList;
	
	// Add all unvisited neighbors to openList
	private void addNeighborsToList() {
		for(MazeCell n : neighbor){
            if(!visited[n.getLevel()][n.getX()][n.getY()] && !candidateList[n.getLevel()][n.getX()][n.getY()]) {
                openList.add(n);
                candidateList[n.getLevel()][n.getX()][n.getY()] = true;
            }
        }
	}
	
	// Connect the current cell to a random visited neighbor, if possible
	private void connectToNeighbor(MazeCell current) {
		List<MazeCell> g = current.getNeighbor();
        Collections.shuffle(g);
        for(MazeCell neighbor : g){
            if(visited[neighbor.getLevel()][neighbor.getX()][neighbor.getY()]){
                current.setPrevious(neighbor);
                connectVertex(current, neighbor);
                break;
            }
        }
	}
    public void run(MazeCell[][][] cell, MazeCell source){
        this.cell = cell;
        candidateList = new boolean[cell.length][cell[0].length][cell[0][0].length];
        visited = new boolean[cell.length][cell[0].length][cell[0][0].length];
        openList = new Stack<>();
        MazeCell current;
        openList.add(source);
        while(!openList.isEmpty()){
            // Shuffle the cells
            Collections.shuffle(openList);
            
            // Pick the first element in the list
            current = openList.remove(0);
            
            // Set the current cell set as visited
            visited[current.getLevel()][current.getX()][current.getY()] = true;
            neighbor = current.getNeighbor();
            Collections.shuffle(neighbor);
            
            // Add all unvisited neighbors to openList
            addNeighborsToList();
            
            // Initialize all tiles except the start and end tiles to be white
            if(current.getRealTile() != Tile.END && current.getRealTile() != Tile.START)
                current.setFrontTile(Tile.WHITE);
            
            connectToNeighbor(current);
        }
    }
}
