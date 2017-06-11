package MazeLayout;

import java.io.IOException;
import Controller.Controller;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
/**
 *
 * @author Alexandre
 */
public class StartMaze {
    public static void main(String[] args) throws IOException, UnsupportedLookAndFeelException, InstantiationException, ClassNotFoundException, IllegalAccessException{
        
        int levels;
        while(true){
            try{
                 levels = Integer.parseInt( JOptionPane.showInputDialog(
                    "how many level do you want? \n (must between 5 and 10,000)"));
                if(2 < levels && levels < 10000)
                    break;
                else
                    System.out.println("too big");
            }
            catch(java.util.InputMismatchException | java.lang.NumberFormatException e){
                System.out.println("Invalid");
            }
        }
        
        System.out.println("Generating maze...");
        
        final MazeFrame frame = new MazeFrame();
        final Maze maze = new Maze(levels, 7);
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Controller(frame, maze);
                frame.setVisible(true);
            }
        });
    }
}
