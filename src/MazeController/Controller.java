package MazeController;

import MazeModel.Maze;
import MazeModel.MazeBuilder;
import MazeModel.MazeCell;
import MazeModel.Tile;
import MazeView.MazeFrame;
import java.io.IOException;

public class Controller {
    private final MazeFrame _mazeframe;
    private Maze _maze;
    public Controller(MazeFrame mazeframe, Maze maze){
        _mazeframe = mazeframe;
        _maze = maze;
        init();
    }
    public final void init(){
        _mazeframe.setController(this);
    }
    public void setLevel(int levels) throws IOException{
        _maze = MazeBuilder.buildMaze(levels, 9);
    }
    public Tile[][] getTile(){
        return _maze.getTile();
    }
    public int getLevel(){
        return _maze.getLevel();
    }
    public boolean isGameOver(){
        return _maze.isGameOver();
    }
    public MazeCell getCurrentCell(){
        return _maze.getCurrentCell();
    }
    public Tile[] getPairs(){
         return _maze.getPairs();
    }
    public int[] getCoord(){
        return _maze.getCoord();
    }
    public void move(int d){
        _maze.move(d);
    }
    public void resetCell(){
        _maze.resetCell();
    }
    public boolean hasFloorChanged(){
        return _maze.hasFloorChanged();
    }
    public void showPath(){
        _maze.showPath();
    }
}
