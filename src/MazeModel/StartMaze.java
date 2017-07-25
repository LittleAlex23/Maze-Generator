package MazeModel;

import java.io.IOException;
import MazeView.MazeFrame;
/**
 *
 * @author Alexandre
 */
public class StartMaze {
    public static void main(String[] args) throws IOException{
        final MazeFrame frame = new MazeFrame();
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);
            }
        });
    }
}
