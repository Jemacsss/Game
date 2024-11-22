package main;

import object.OBJ_Chest;
import object.OBJ_Key;
import object.OBJ_Potion;
import object.SuperObject;

import java.util.ArrayList;
import java.util.Random;


public class AssetSetter {
	
	Panel gp;
	Random random = new Random();
	
	public AssetSetter(Panel gp) {
		this.gp = gp;
	}
	
	
	
	public void setObject() {
	    // Place 1 Chest
	    gp.obj[0] = new OBJ_Chest();
	    placeObject(gp.obj[0]);

	    // Place 1 Key
	    gp.obj[1] = new OBJ_Key();
	    placeObject(gp.obj[1]);

	    // Place 3 Potions
	    for (int i = 2; i < 5; i++) {
	        gp.obj[i] = new OBJ_Potion();
	        placeObject(gp.obj[i]);
	    }
	}
	
	private void placeObject(SuperObject obj) {
	    ArrayList<int[]> validPositions = new ArrayList<>();

	    // Scan the entire map for valid positions
	    for (int col = 0; col < gp.worldWidth / gp.tileSize; col++) {
	        for (int row = 0; row < gp.worldHeight / gp.tileSize; row++) {
	            int x = col * gp.tileSize;
	            int y = row * gp.tileSize;

	            if (!isPositionOccupied(x, y)) {
	                validPositions.add(new int[]{x, y});
	            }
	        }
	    }

	    // Choose a random valid position
	    if (!validPositions.isEmpty()) {
	        int[] pos = validPositions.get(random.nextInt(validPositions.size()));
	        obj.worldX = pos[0];
	        obj.worldY = pos[1];
	    } else {
	        System.out.println("No valid positions available for object placement.");
	    }
	}
	
	private boolean isTileWalkable(int x, int y) {
	    int col = x / gp.tileSize;
	    int row = y / gp.tileSize;
	    int tileNum = gp.tileM.mapTileNum[col][row];

	    // Return true only if the tile is walkable (no collision)
	    return !gp.tileM.tile[tileNum].collision;
	}

	private boolean isPositionOccupied(int x, int y) {
	    // Check if any object occupies this position
	    for (SuperObject obj : gp.obj) {
	        if (obj != null && obj.worldX == x && obj.worldY == y) {
	            return true;
	        }
	    }

	    // Check if the position is on the player's starting position
	    if (x == gp.player.worldX && y == gp.player.worldY) {
	        return true;
	    }

	    // Check if the tile is walkable
	    return !isTileWalkable(x, y);
	}
	
//	public void debugCollisionMap() {
//	    for (int row = 0; row < gp.tileM.mapTileNum[0].length; row++) {
//	        for (int col = 0; col < gp.tileM.mapTileNum.length; col++) {
//	            int tileNum = gp.tileM.mapTileNum[col][row];
//	            System.out.print(tileM.tile[tileNum].collision ? "X " : ". ");
//	        }
//	        System.out.println();
//	    }
//	}
	
}
