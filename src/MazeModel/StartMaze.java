package MazeModel;

import MazeController.Controller;
import java.io.IOException;
import MazeView.MazeFrame;
/**
 *
 * @author Alexandre
 */
public class StartMaze {
    public static void main(String[] args) throws IOException{
        final MazeFrame frame = new MazeFrame();
        Maze maze = new Maze();
        Controller c = new Controller(frame, maze);
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
