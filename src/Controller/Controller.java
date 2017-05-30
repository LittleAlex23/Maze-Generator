package Controller;

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
        
        // Resize the GUI based on he number of cells
        frame.expand(Maze.SIZE+2);
        frame.updateLevel(maze.getLevel());
        frame.updateStat(maze.getImage());
        
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
                    frame.updateStat(maze.getImage());
                    
                    if(maze.isGameOver()){
                        frame.removeKeyListener(this);
                        JOptionPane.showMessageDialog(frame, "Game Over");
                    }
                }
        });
    }
}
