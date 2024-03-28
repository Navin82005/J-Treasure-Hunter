package services;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class WorldData {
    int worldRow, worldCol, worldLevel;

    TileMap[][] worldData;

    public int getWorldLevel() {
        return worldLevel;
    }

    public int getWorldCol() {
        return worldCol;
    }

    public int getWorldRow() {
        return worldRow;
    }

    public TileMap[][] getWorldData() {
        return worldData;
    }

    public WorldData(InputStream inputStream) {
        loadWorldData(inputStream);
    }

    public void loadWorldData(InputStream inputStreamPram) {
        try (InputStream inputStream = inputStreamPram;
                Reader reader = new InputStreamReader(inputStream)) {
            Gson gson = new Gson();
            JsonObject json = gson.fromJson(reader, JsonObject.class);

            worldRow = json.get("row").getAsInt();
            worldCol = json.get("column").getAsInt();
            JsonArray tilesArray = json.get("tiles").getAsJsonArray();
            worldData = new TileMap[worldRow][worldCol];
            JsonArray temp;
            JsonObject tempJsonObject;
            for (int i = 0; i < worldRow; i++) {
                temp = tilesArray.getAsJsonArray().get(i).getAsJsonArray();
                for (int j = 0; j < worldCol; j++) {
                    tempJsonObject = temp.getAsJsonArray().get(j).getAsJsonObject();
                    for (String tileName : tempJsonObject.keySet()) {
                        worldData[i][j] = new TileMap(tileName, tempJsonObject.get(tileName).getAsInt());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
