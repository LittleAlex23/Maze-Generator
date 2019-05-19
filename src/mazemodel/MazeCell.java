package mazemodel;

import static mazemodel.MazeFloor.SIZE;
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
    	this.level = level;
        this.ID = ID;
        neighbor = new ArrayList<>(6);
        row = ID/SIZE;
        column = ID%SIZE;
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
    public void setFrontTile(Tile image){
        this.frontImage = realImage = image;
    }
    protected void resetTile(){
        if(realImage == null || !realImage.equals(Tile.BLACK))
            this.frontImage = realImage = Tile.BLACK;
    }
    
    public ArrayList<MazeCell> getNeighbor(){
        return neighbor;
    }
    public int getX(){
        return row;
    }
    public int getY(){
        return column;
    }
    
    public int getLevel(){
        return level;
    }
    
    // For backtracking
    public void setPrevious(MazeCell previous){
        this.previous = previous;
    }
    
    // Assign the cell the predecessor cell 
    public MazeCell getPrevious(){
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
        if(column+2 < SIZE)
            neighbor.add(cell[level][row][column+2]);
        // SOUTH NEIGHBOR
        if(row+2 < SIZE)
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
