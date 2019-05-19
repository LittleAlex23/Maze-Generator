package mazemodel;

public class MazeBuilder {
    public static Maze buildMaze(int floorCount, int cellSize){
        MazeFloor[] floors = buildFloor(floorCount, cellSize);
        Maze m = new Maze();
        m.setFloor(floors);
        return m;
    }
    
    // Build a floor
    private static MazeFloor[] buildFloor(final int MAXLEVEL, final int SIZE){
        MazeFloor.MAXLEVEL = MAXLEVEL;
        MazeFloor.SIZE = SIZE;
        MazeFloor[] floors = new MazeFloor[MAXLEVEL];
        for(int i = 0; i < MAXLEVEL; i++)
            floors[i] = new MazeFloor();
        return floors;
    }
    
    // Fill Maze with cells
    public static MazeCell[][][] createMaze(MazeFloor[] floor){
    	final int L = MazeFloor.MAXLEVEL;
    	final int C = MazeFloor.SIZE;
    	final int index = C>>1;
        MazeCell[] vertex = new MazeCell[L *(index*index + C)];
        MazeCell[][][] cell3D = new MazeCell[L][C][C];
        int numOfVertex = 0;
        for(int i = 0; i < L; i++){
            for(int j = 0; j < C; j++)
                 for(int k = 0; k < C; k++){
                     cell3D[i][j][k] = new MazeCell(i, j * C + k);
                     if(cell3D[i][j][k].isVertex())
                        vertex[numOfVertex++] = cell3D[i][j][k];
                 }
            floor[i].fillFloor(cell3D[i]);
        }
        connectToNeighbors(vertex, cell3D);
        return cell3D;
    }
    // Assign neighbors for each cell
    private static void connectToNeighbors(MazeCell[] vertex, MazeCell[][][] cells){
        for(MazeCell s : vertex)
            s.assignNeighbor(cells);
    }
}
