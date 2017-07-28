package MazeModel;



import java.awt.image.BufferedImage;

/**
 *
 * @author Alexandre
 */
public class SpriteSheet {
    public static SpriteSheet sheet;
    private BufferedImage image;
    private final int SIZE = 36;
    private SpriteSheet(){}
    public static SpriteSheet getInstance(){
        if(sheet == null)
            sheet = new SpriteSheet();
        return sheet;
    }
    public void setImage(BufferedImage image){
        this.image = image;
    }
    public BufferedImage getSubimage(int x, int y){
        return image.getSubimage(x*SIZE, y*SIZE, SIZE, SIZE);
    }
}
