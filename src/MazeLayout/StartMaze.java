package MazeLayout;

import java.io.IOException;
import Controller.Controller;
import java.util.Scanner;
/**
 *
 * @author Alexandre
 */
public class StartMaze {
    public static void main(String[] args) throws IOException{
        int levels;
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("how many level do you want? (must between 5 and 10,000)");
            while(true){
                try{
                    levels = input.nextInt();
                    if(5 < levels && levels < 10000)
                        break;
                    else
                        System.out.println("too big");
                }
                catch(java.util.InputMismatchException e){
                    System.out.println("Invalid");
                    input.next();
                }
            }
        }
        
        System.out.println("Generating maze...");
        final MazeFrame frame = new MazeFrame();
        final Maze maze = new Maze(levels, 15);
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Controller(frame, maze);
                frame.setVisible(true);
            }
        });
    }
}
