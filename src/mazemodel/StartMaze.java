package mazemodel;

import mazeview.MazeFrame;

import java.io.IOException;

import mazecontroller.Controller;

public class StartMaze {
    public static void main(String[] args) throws IOException{
        final MazeFrame frame = new MazeFrame();
        new Controller(frame, new Maze());
        java.awt.EventQueue.invokeLater(() -> {
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        );
    }
}
