package tiles;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GrassTile extends Tiles {
    private Tiles[] tiles;
    private int numberOfTiles;

    public GrassTile(int numberOfTiles) {
        this.numberOfTiles = numberOfTiles;
        loadImages();
    }

    public Tiles[] getTiles() {
        return tiles;
    }

    public void loadImages() {
        String imagePath;
        this.tiles = new Tiles[numberOfTiles];
        for (int i = 0; i < numberOfTiles; i++) {
            this.tiles[i] = new Tiles();
            imagePath = "images/grass/" + i + ".png";
            // getClass().getResourceAsStream(imagePath));
            try {
                BufferedImage image = ImageIO.read(getClass().getResourceAsStream(imagePath));
                this.tiles[i].image = image;
            } catch (IOException e) {
                System.out.println("Error in Loading Grass Tiles: " + imagePath);
            }
        }
    }
}
