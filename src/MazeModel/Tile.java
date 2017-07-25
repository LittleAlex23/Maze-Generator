package MazeModel;


import java.awt.image.BufferedImage;

/**
 *
 * @author Alexandre
 */
public class Tile {
    private final BufferedImage image;
    private final String name;
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
