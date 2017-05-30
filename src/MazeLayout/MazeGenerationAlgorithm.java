package MazeLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 *
 * @author Alexandre
 */
public class MazeGenerationAlgorithm {
    private MazeCell[][][] squares;
    private ArrayList<MazeCell> openList;
    private Stack<MazeCell> stack;
    private SpriteSheet sheet;
    private boolean[][][] visited;
    public MazeGenerationAlgorithm(){
        sheet = SpriteSheet.getInstance();
    }
    public void runDFS(MazeCell[][][] squares, MazeCell source){
        this.squares = squares;
        visited = new boolean [squares.length][squares[0].length][squares[0][0].length];
        stack = new Stack<>();
        visited[0][0][0] = true;
        mixNeighbor(source);
        while(!stack.isEmpty())
            mixNeighbor(stack.pop());
    }
    public void mixNeighbor(MazeCell previous){
        MazeCell[] neighbor;
        while(true){
            neighbor = previous.getNeighbor();
            openList = new ArrayList<>();
            
            // Add all cell's unvisited neighbors to the list;
            for(MazeCell n : neighbor){
                if(!visited[n.getLevel()][n.getX()][n.getY()]){
                    if(!n.getName().equals("red"))
                        n.assignTile(sheet.getTile("white"));
                    openList.add(n);
                    n.setPrevious(previous);
                    connectVertex(n, previous);
                    visited[n.getLevel()][n.getX()][n.getY()] = true;
                }
            }
            
            // Backtrack until the next cell has an unvisited neighbor or
            // the stack is empty
            if(openList.isEmpty() && !stack.isEmpty())
                previous = stack.pop();
            else
                break;
        }
        // Shuffle the neighbors
        Collections.shuffle(openList);
        for(MazeCell sq : openList)
            stack.push(sq);
    }
    public void runAlgorithm(MazeCell[][][] squares, MazeCell source){
        this.squares = squares;
        openList = new ArrayList<>();
        
        addToOpenList(source);
        visited[0][0][0] = true;
        
        while(!openList.isEmpty()){
            Collections.shuffle(openList);
            addToOpenList(openList.get(0));
        }
    }
    private void addToOpenList(MazeCell previous){
        openList.remove(previous);
        MazeCell[] neighbors = previous.getNeighbor();
        for(MazeCell n: neighbors){
            if(!visited[n.getLevel()][n.getX()][n.getY()]){
                openList.add(n);
                n.setPrevious(previous);
                visited[n.getLevel()][n.getX()][n.getY()]= true;
                n.assignTile(sheet.getTile("white"));
                connectVertex(n, previous);
            }
        }
    }
    
    // Connect the two cells
    public void connectVertex(MazeCell current, MazeCell previous){
        int currentX = current.getX();
        int currentY = current.getY();
        int currentL = current.getLevel();

        int level = currentL - previous.getLevel();
        if(level == 0){
            int row = currentX - previous.getX();
            int column = currentY - previous.getY();
            MazeCell colorSquare;

            if(row != 0){
                if(row > 0)
                    // SOUTH
                    colorSquare = squares[currentL][currentX-1][currentY];
                else
                    // NORTH
                    colorSquare = squares[currentL][currentX+1][currentY];
            }
            else {
                // EAST
                if(column > 0)
                    colorSquare = squares[currentL][currentX][currentY - 1];
                // WEST
                else
                    colorSquare = squares[currentL][currentX][currentY + 1];
            }
            current.setPrevious(colorSquare);
            colorSquare.setPrevious(previous);
            colorSquare.assignTile(sheet.getTile("white"));
        }
        else {
            // GOING UP
            if(level == 1){
                if(previous.getName().equals("orange")){
                    current.assignTile(sheet.getTile("orange"));
                    previous.assignTile(sheet.getTile("green"));
                }
                else if(current.getName().equals("white")){
                    current.assignTile(sheet.getTile("orange"));
                    previous.assignTile(sheet.getTile("pink"));
                }
                else if(!previous.getName().equals("red"))
                    previous.assignTile(sheet.getTile("pink"));
            }
            
            // GOING DOWN
            else{
                if(previous.getName().equals("pink")){
                    current.assignTile(sheet.getTile("pink"));
                    previous.assignTile(sheet.getTile("green"));
                }
                
                else if(current.getName().equals("white")){
                    current.assignTile(sheet.getTile("pink"));
                    previous.assignTile(sheet.getTile("orange"));
                
                }
                else if(!previous.getName().equals("red"))
                   previous.assignTile(sheet.getTile("orange"));
            }
        }
    }
}
