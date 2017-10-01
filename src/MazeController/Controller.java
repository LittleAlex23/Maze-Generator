package MazeController;

import MazeModel.Maze;
import MazeModel.MazeBuilder;
import MazeView.MazeFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JTextField;

/**
 *
 * @author Alexandre
 */
public class Controller {
    private final MazeFrame _mazeframe;
    private Maze _maze;
    public Controller(MazeFrame mazeframe, Maze maze){
        _mazeframe = mazeframe;
        _maze = maze;
        JTextField levelField = _mazeframe.getLevelField();
        levelField.addActionListener(new ButtonListener());
    }
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField levelField = _mazeframe.getLevelField();
            try{
                int levels = Integer.parseInt(levelField.getText());
                if(5 <= levels && levels <= 10000){
                    System.out.println("Generating maze...");
                    _maze = MazeBuilder.buildMaze(levels, 9);
                    _mazeframe.expand(11, levels);
                    _mazeframe.updateLevel(_maze.getLevel());
                    _mazeframe.updateStat(_maze.getTile(), "start");
                    _mazeframe.addKeyListener(new KeyList());
                }
                else
                    System.out.println("Not within range");
            }
            catch(NumberFormatException ex){
                levelField.setText("");
            } catch (IOException ex) {
            }
        }
    }
     private class KeyList extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            int keyCode = e.getKeyCode();
            
            // Show the path to the exit
            if(keyCode == KeyEvent.VK_Q){
                _maze.showPath();
                _mazeframe.updateStat(_maze.getTile(), _maze.getCurrentCell().getPermImageName());
                return;
            }
            
            // Player resets the maze
            else if(keyCode == KeyEvent.VK_R){
                _mazeframe.updateLevel(_maze.getLevel());
                _maze.resetCell();
                _maze.init();
                _mazeframe.updateStat(_maze.getTile(), _maze.getCurrentCell().getPermImageName());
            }
            
            // The player moves around the maze
            _maze.move(keyCode);

            // Check for certain states after the player moves
            if(_maze.hasFloorChanged()){
                _mazeframe.updateLevel(_maze.getLevel());
                _mazeframe.updateStat(_maze.getTile(), _maze.getCurrentCell().getPermImageName());
            }
            else
                _mazeframe.move(_maze.getCurrentCell().getPermImageName(), _maze.getPairs(), _maze.getCoord());
            // Toggle game over
            if(_maze.isGameOver()){
                _mazeframe.setGame();
                _mazeframe.removeKeyListener(this);
            }
        }
    }
}
