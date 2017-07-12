package MazeModel;



import java.awt.image.BufferedImage;

/**
 *
 * @author Alexandre
 */
public class SpriteSheet {
    public static SpriteSheet sheet;
    private final Tile[] tile = new Tile[15];
    private SpriteSheet(){}
    public static SpriteSheet getInstance(){
        if(sheet == null)
            sheet = new SpriteSheet();
        return sheet;
    }
    public void setImage(BufferedImage image){
        tile[0] = new Tile(image.getSubimage(0, 0, 36, 36), "black");
        tile[1] = new Tile(image.getSubimage(36, 0, 36, 36), "white");
        tile[2] = new Tile(image.getSubimage(72, 0, 36, 36), "red");
        tile[3] = new Tile(image.getSubimage(108, 0, 36, 36), "green");
        tile[4] = new Tile(image.getSubimage(144, 0, 36, 36), "pink");
        tile[5] = new Tile(image.getSubimage(180, 0, 36, 36), "orange");
        tile[6] = new Tile(image.getSubimage(216, 0,36, 36), "magenta");
        tile[7] = new Tile(image.getSubimage(252, 0,36, 36), "cyan");
        tile[8] = new Tile(image.getSubimage(36, 36, 36, 36), "onWhite");
        tile[9] = new Tile(image.getSubimage(72, 36, 36, 36), "onRed");
        tile[10] = new Tile(image.getSubimage(108, 36, 36, 36), "onGreen");
        tile[11] = new Tile(image.getSubimage(144, 36, 36, 36), "onPink");
        tile[12] = new Tile(image.getSubimage(180, 36, 36, 36), "onOrange");
        tile[13] = new Tile(image.getSubimage(216, 36, 36, 36), "onMagenta");
        tile[14] = new Tile(image.getSubimage(252, 36, 36, 36), "onCyan");
    }
    public Tile getTile(String name){
        for (Tile tile1 : tile)
            if (tile1.getName().equals(name)) 
                return tile1;
        return null;
    }
}
