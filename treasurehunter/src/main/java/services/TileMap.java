package services;

public class TileMap {
    String tileName;

    public TileMap(String tileName, int tileNumber) {
        this.tileName = tileName;
        this.tileNumber = tileNumber;
    }

    public String getTileName() {
        return tileName;
    }

    public void setTileName(String tileName) {
        this.tileName = tileName;
    }

    int tileNumber;

    public int getTileNumber() {
        return tileNumber;
    }

    public void setTileNumber(int tileNumber) {
        this.tileNumber = tileNumber;
    }

    public String toString() {
        return this.tileName + " : " + tileNumber;
    }

}