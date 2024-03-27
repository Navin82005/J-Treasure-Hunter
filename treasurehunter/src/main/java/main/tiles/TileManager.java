package main.tiles;

import java.util.HashMap;

import main.services.ReadINIFile;
import treasurehunter.GamePanel;

import java.awt.Graphics2D;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class TileManager {
    HashMap<String, Tiles[]> tiles = new HashMap<>();
    GamePanel gp;
    int totalTiles;
    int scaleFactor;
    int tileSize;
    ReadINIFile iniReader;
    int worldLevel = 0;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        loadImages();
    }

    public void loadImages() {
        tiles.put("grass", new GrassTile(8).getTiles());
        System.out.println(tiles);
        iniReader = new ReadINIFile();
        if (iniReader != null) {
            scaleFactor = Integer.parseInt(iniReader.getData("scaleFactor"));
            tileSize = Integer.parseInt(iniReader.getData("tileSize"));
            worldLevel = Integer.parseInt(iniReader.getData("worldLevel"));
            System.out.println(
                    "scaleFactor : " + scaleFactor + " tileSize : " + tileSize + " " + "worldLevel : " + worldLevel);
        }

        try (InputStream inputStream = getClass().getResourceAsStream("/resources/worldData/L1.json");
                Reader reader = new InputStreamReader(inputStream)) {
            // Gson gson = new Gson();
            // JsonObject json = gson.fromJson(reader, JsonObject.class);
            // Process the JSON object as needed
        } catch (Exception e) {
            System.out.println("Error Loading World Data: " + e.getMessage());
        }

    }

    public void draw(Graphics2D g2) {
        int col = 0, row = 0;
        for (int i = 0; i < gp.maxScreenRow; i++) {
            for (int j = 0; j < gp.maxScreenCol; j++) {
                g2.drawImage(tiles.get("grass")[4].image, col, row, tileSize * scaleFactor, tileSize * scaleFactor,
                        null);
                col += gp.tileSize;
            }
            col = 0;
            row += gp.tileSize;
        }
    }
}
