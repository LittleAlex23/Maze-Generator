package MazeModel;


import java.awt.image.BufferedImage;

/**
 *
 * @author Alexandre
 */
public class Tile {
    private BufferedImage image;
    private String name;
    protected Tile(BufferedImage image, String name){
        this.image = image;
        this.name = name;
    }
    public BufferedImage getImage(){
        return image;
    }
    public String getName(){
        return name;
    }
}
