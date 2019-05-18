package mazemodel;


import java.awt.image.BufferedImage;

/**
 *
 * @author Alexandre
 */
public enum Tile {
    BLACK(SpriteSheet.getInstance().getSubimage(0, 0), "black"),
    WHITE(SpriteSheet.getInstance().getSubimage(1, 0), "white"),
    END(SpriteSheet.getInstance().getSubimage(2, 0), "red"),
    UP_OR_DOWN(SpriteSheet.getInstance().getSubimage(3, 0), "green"),
    UP(SpriteSheet.getInstance().getSubimage(4, 0), "pink"),
    DOWN(SpriteSheet.getInstance().getSubimage(5, 0), "orange"),
    START(SpriteSheet.getInstance().getSubimage(6, 0), "magenta"),
    SOLUTION(SpriteSheet.getInstance().getSubimage(7, 0), "cyan"),
    ON_WHITE(SpriteSheet.getInstance().getSubimage(1, 1), "onWhite"),
    ON_END(SpriteSheet.getInstance().getSubimage(2, 1), "onRed"),
    ON_UP_OR_DOWN(SpriteSheet.getInstance().getSubimage(3, 1), "onGreen"),
    ON_UP(SpriteSheet.getInstance().getSubimage(4, 1), "onPink"),
    ON_DOWN(SpriteSheet.getInstance().getSubimage(5, 1), "onOrange"),
    ON_START(SpriteSheet.getInstance().getSubimage(6, 1), "onMagenta"),
    ON_SOLUTION(SpriteSheet.getInstance().getSubimage(7, 1), "onCyan");
    private final BufferedImage image;
    private final String name;
    Tile(BufferedImage image, String name){
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
