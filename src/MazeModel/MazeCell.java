package MazeModel;

import java.util.ArrayList;
import static MazeModel.MazeFloor.COLUMNS;
import static MazeModel.MazeFloor.MAXLEVEL;

/**
 *
 * @author Alexandre
 */
public class MazeCell{
    private final ArrayList<MazeCell> neighbor;
    private final int row;
    private final int column;
    private final int level;
    private MazeCell previous;
    private Tile image;
    private Tile permImage;
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
    protected void changeImage(Tile image){
        this.image = image;
    }
    protected void resetImage(){
        image = permImage;
    }
    protected void assignTile(Tile image, Tile perm){
        this.image = image;
        this.permImage = perm;
    }
    protected void assignTile(Tile image){
        this.image = permImage = image;
    }
    protected void resetTile(){
        if(permImage == null || !permImage.equals(Tile.BLACK))
            this.image = permImage = Tile.BLACK;
    }
    
    // Return the list of neighbors of current cell;
    protected ArrayList<MazeCell> getNeighbor(){
        return neighbor;
    }
    protected int getX(){
        return row;
    }
    protected int getY(){
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
    protected Tile getTile(){
        if(image == null)
            image = permImage = Tile.BLACK;
        return image;
    }
    public String getPermImageName(){
        if(permImage == null)
            image = permImage = Tile.BLACK;
        return permImage.getName();
    }
    protected Tile getPermImage(){
        return permImage;
    }
}
