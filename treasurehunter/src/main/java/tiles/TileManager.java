package tiles;

import java.util.HashMap;

import services.ReadINIFile;
import services.TileMap;
import services.WorldData;
import treasurehunter.GamePanel;

import java.awt.Graphics2D;

public class TileManager {
    HashMap<String, Tiles[]> tiles = new HashMap<>();
    GamePanel gp;
    int totalTiles;
    int scaleFactor;
    int tileSize;
    ReadINIFile iniReader;
    int worldLevel = 0;
    TileMap[][] worldData;
    int worldRow;
    int worldCol;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        loadImages();
    }

    public void loadImages() {
        tiles.put("grass", new GrassTile(9).getTiles());
        System.out.println(tiles);
        iniReader = new ReadINIFile();
        if (iniReader != null) {
            scaleFactor = Integer.parseInt(iniReader.getData("scaleFactor"));
            tileSize = Integer.parseInt(iniReader.getData("tileSize"));
            worldLevel = Integer.parseInt(iniReader.getData("worldLevel"));
            System.out.println(
                    "scaleFactor : " + scaleFactor + " tileSize : " + tileSize + " " + "worldLevel : " + worldLevel);
        }
        WorldData worldLoader = new WorldData(getClass().getResourceAsStream("resources/worldData/L1.json"));
        worldCol = worldLoader.getWorldCol();
        worldRow = worldLoader.getWorldRow();
        worldData = worldLoader.getWorldData();
        worldLevel = worldLoader.getWorldLevel();
    }

    public void draw(Graphics2D g2) {

        int col = 0, row = 0;
        for (int i = 0; i < worldRow; i++) {
            for (int j = 0; j < worldCol; j++) {
                g2.drawImage(tiles.get(worldData[i][j].getTileName())[worldData[i][j].getTileNumber()].image,
                        col, row, tileSize * scaleFactor,
                        tileSize * scaleFactor,
                        null);
                col += gp.tileSize;
            }
            col = 0;
            row += gp.tileSize;
        }
    }
}
