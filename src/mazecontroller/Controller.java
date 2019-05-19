package mazecontroller;

import mazemodel.Maze;
import mazemodel.MazeBuilder;
import mazemodel.MazeCell;
import mazemodel.Tile;
import mazeview.MazeFrame;

import java.io.IOException;

public class Controller {
    private  MazeFrame _mazeframe;
    private Maze _maze;
    public Controller(MazeFrame mazeframe, Maze maze){
        _mazeframe = mazeframe;
        _maze = maze;
        init();
    }
    public final void init(){
        _mazeframe.setController(this);
    }
    public void setLevel(int levels){
        _maze = MazeBuilder.buildMaze(levels, 9);
    }
    public Tile[][] getTile(){
        return _maze.getFloorTile();
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
    public MazeCell[] getPairs(){
         return _maze.getPairs();
    }
    public void move(char d){
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
