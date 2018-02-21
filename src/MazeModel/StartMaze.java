package MazeModel;

import MazeController.Controller;
import java.io.IOException;
import MazeView.MazeFrame;

public class StartMaze {
    public static void main(String[] args) throws IOException{
        final MazeFrame frame = new MazeFrame();
        new Controller(frame, new Maze());
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
