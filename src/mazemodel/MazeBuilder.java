package mazemodel;

import java.io.IOException;

public class MazeBuilder {
    public static Maze buildMaze(int floorCount, int cellSize) throws IOException{
        MazeFloor[] floors = buildFloor(floorCount, cellSize);
        Maze m = new Maze();
        m.setFloor(floors);
        return m;
    }
    
    // Build a floor
    private static MazeFloor[] buildFloor(final int L, final int C){
        MazeFloor.MAXLEVEL = L;
        MazeFloor.COLUMNS = C;
        MazeFloor[] floors = new MazeFloor[L];
        for(int i = 0; i < L; i++)
            floors[i] = new MazeFloor();
        return floors;
    }
    
    // Build a cell
    public static MazeCell[][][] buildCell(MazeFloor[] f, int L, int C){
    	final int index = C>>1;
        MazeCell[] vertex = new MazeCell[L *(index *index + C)];
        MazeCell[][][] cellArray = new MazeCell[L][C][C];
        int count = 0;
        for(int i = 0; i < L; i++){
            for(int j = 0; j < C; j++)
                 for(int k = 0; k < C; k++){
                     cellArray[i][j][k] = new MazeCell(i, j * C + k);
                     if(cellArray[i][j][k].isVertex())
                        vertex[count++] = cellArray[i][j][k];
                 }
            f[i].fillCell(cellArray[i]);
        }
        connectCells(vertex, cellArray);
        return cellArray;
    }
    // Assign neighbors for each cell
    private static void connectCells(MazeCell[] vertex, MazeCell[][][] cells){
        for(MazeCell s : vertex)
            s.assignNeighbor(cells);
    }
}
