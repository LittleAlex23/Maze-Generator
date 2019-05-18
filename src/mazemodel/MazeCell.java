package mazemodel;

import static mazemodel.MazeFloor.COLUMNS;
import static mazemodel.MazeFloor.MAXLEVEL;

import java.util.ArrayList;

public class MazeCell{
    private final ArrayList<MazeCell> neighbor;
    private final int row;
    private final int column;
    private final int level;
    private MazeCell previous;
    private Tile frontImage;
    private Tile realImage;
    private final int ID;
    protected MazeCell(int level, int ID){
        neighbor = new ArrayList<>(4);
        this.level = level;
        this.ID = ID;
        row = ID/COLUMNS;
        column = ID%COLUMNS;
    }
    protected boolean isVertex(){
        return row%2 == 0 && column%2 == 0;
    }
    protected void setImage(Tile image){
        this.frontImage = image;
    }
    protected void resetImage(){
        frontImage = realImage;
    }
    protected void assignTile(Tile front, Tile real){
        this.frontImage = front;
        this.realImage = real;
    }
    protected void assignTile(Tile image){
        this.frontImage = realImage = image;
    }
    protected void resetTile(){
        if(realImage == null || !realImage.equals(Tile.BLACK))
            this.frontImage = realImage = Tile.BLACK;
    }
    
    // Return the list of neighbors of current cell;
    protected ArrayList<MazeCell> getNeighbor(){
        return neighbor;
    }
    public int getX(){
        return row;
    }
    public int getY(){
        return column;
    }
    // Return the level the cell is in
    protected int getLevel(){
        return level;
    }
    
    // For backtracking
    protected void setPrevious(MazeCell previous){
        this.previous = previous;
    }
    
    // Assign the cell the predecessor cell 
    protected MazeCell getPrevious(){
        return previous;
    }
    
    // Assign cell neighbors 
    protected void assignNeighbor(MazeCell[][][] cell){
        // Player at start position can move either to the east or south.
        if(ID == 0 && level == 0){
            neighbor.add(cell[level][row][column+2]);
            neighbor.add(cell[level][row + 2][column]);
            return;
        }
        // NORTH NEIGHBOR
        if(row-2 >= 0)
            neighbor.add(cell[level][row - 2][column]);
        // EAST NEIGHBOR
        if(column+2 < COLUMNS)
            neighbor.add(cell[level][row][column+2]);
        // SOUTH NEIGHBOR
        if(row+2 < COLUMNS)
            neighbor.add(cell[level][row + 2][column]);
        // WEST NEIGHBOR
        if(column-2 >= 0)
            neighbor.add(cell[level][row][column-2]);
        // TOP NEIGHBOR
        if(level+1 < MAXLEVEL)
            neighbor.add(cell[level+1][row][column]);
        // BOTTOM NEIGHBOR
        if(level-1 >= 0)
            neighbor.add(cell[level-1][row][column]);
    }
    public Tile getFrontTile(){
        if(frontImage == null)
            frontImage = realImage = Tile.BLACK;
        return frontImage;
    }
    public String getTileName(){
        if(realImage == null)
            frontImage = realImage = Tile.BLACK;
        return realImage.getName();
    }
    public Tile getRealTile(){
        return realImage;
    }
}
