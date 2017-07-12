package Controller;

import MazeModel.Maze;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import MazeLayout.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Alexandre
 */
public class Controller {
    public Controller(final MazeFrame frame, final Maze maze){
        
        // Initialize the maze
        frame.expand(maze.getSize()+2, maze.getMaxlevel());
        frame.updateLevel(maze.getLevel());
        frame.updateStat(maze.getImage(), "start");
        
        // Register the key lister and handle a key event.
        frame.addKeyListener(new KeyAdapter(){
                @Override
                public void keyPressed(KeyEvent e){
                    if(e.getKeyCode() == KeyEvent.VK_Q)
                        maze.showPath();
                    else if(e.getKeyCode() == KeyEvent.VK_R){
                        frame.updateLevel(maze.getLevel());
                        maze.create();
                    }
                    maze.move(e.getKeyCode());
                    
                    // Check for certain states
                    if(maze.hasFloorChanged())
                        frame.updateLevel(maze.getLevel());
                    frame.updateStat(maze.getImage(), maze.getCurrentCell().getName());
                    
                    // Toggle game over
                    if(maze.isGameOver()){
                        frame.removeKeyListener(this);
                        JOptionPane.showMessageDialog(frame, "Game Over");
                    }
                }
        });
    }
}
