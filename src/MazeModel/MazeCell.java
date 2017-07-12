package MazeModel;

/**
 *
 * @author Alexandre
 */
public class MazeCell{
    private MazeCell[] neighbor;
    private boolean isVertex;
    private final int row;
    private final int column;
    private final int level;
    private MazeCell previous;
    private Tile image;
    private Tile permImage;
    private final int ID;
    static int SIZE;
    static int MAXLEVEL;
    protected MazeCell(int level, int ID){
        image = permImage = SpriteSheet.getInstance().getTile("black");
        this.level = level;
        this.ID = ID;
        row = ID/SIZE;
        column = ID%SIZE;
        
        if(row%2 == 0 && column%2 == 0)
            isVertex =true;
    }
    protected boolean isVertex(){
        return isVertex;
    }
    protected void changeImage(Tile image){
        this.image = image;
    }
    protected void assignTile(Tile image, Tile perm){
        this.image = image;
        this.permImage = perm;
    }
    protected void assignTile(Tile image){
        this.image = permImage = image;
    }
    
    // Return the list of neighbors of current cell;
    protected MazeCell[] getNeighbor(){
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
    protected void setNeighbor(MazeCell[][][] grid){
        if(ID == 0 && level == 0){
            neighbor = new MazeCell[2];
            neighbor[0] = grid[level][row][column+2];
            neighbor[1] = grid[level][row + 2][column];
            return;
        }
        
        int count = 0;
        if(row-2 >= 0)
            count++;
        // EAST NEIGHTBOR
        if(column+2 < SIZE)
            count++;
        // SOUTH NEIGHBOR
        if(row+2 < SIZE)
            count++;
        // WEST NEIGHBOR
        if(column-2 >= 0)
            count++;
        // TOP NEIGHBOR
        if(level+1 < MAXLEVEL)
            count++;
        // BOTTOM NEIGHBOR
        if(level-1 >= 0)
            count++;
        neighbor = new MazeCell[count];
        
        count = 0;
        if(row-2 >= 0)
            neighbor[count++] =grid[level][row - 2][column];
        // EAST NEIGHTBOR
        if(column+2 < SIZE)
            neighbor[count++] = grid[level][row][column+2];
        // SOUTH NEIGHBOR
        if(row+2 < SIZE)
            neighbor[count++] = grid[level][row + 2][column];
        // WEST NEIGHBOR
        if(column-2 >= 0)
            neighbor[count++] = grid[level][row][column-2];
        // TOP NEIGHBOR
        if(level+1 < MAXLEVEL)
            neighbor[count++] = grid[level+1][row][column];
        // BOTTOM NEIGHBOR
        if(level-1 >= 0)
            neighbor[count++] = grid[level-1][row][column];
    }
    protected Tile getImage(){
        return image;
    }
    public String getName(){
        return permImage.getName();
    }
    protected Tile getPermImage(){
        return permImage;
    }
}
