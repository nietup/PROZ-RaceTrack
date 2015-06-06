package Model;

import RaceTrack.Data;
import RaceTrack.Input;
import TileSystem.Tile;
import Tools.Tools;

public class Model {

	/**This method generates game map*/
	public Data initialData(int startX, int startY, int width, int height) {
		Data data = new Data(startX, startY, width, height);
		
		for (int x = 0; x < data.getMapWidth(); x++)
			for (int y = 0; y < data.getMapHeight(); y++)
				data.setTile(x, y, 0);
		
		
		data.setTile(startX-1, startY, Tile.Type.PLAYER_CAR.ordinal());
		data.setTile(startX+1, startY, Tile.Type.OPPONENT_CAR.ordinal());
		data.setTile(3, 2, Tile.Type.FINISH.ordinal());
		data.setTile(2, 1, Tile.Type.PATH.ordinal());
		data.setTile(2, 2, Tile.Type.PATH.ordinal());
		data.setTile(2, 3, Tile.Type.PATH.ordinal());
		data.setTile(2, 4, Tile.Type.PATH.ordinal());
		data.setTile(2, 5, Tile.Type.PATH.ordinal());
		data.setTile(2, 6, Tile.Type.PATH.ordinal());
		data.setTile(12, 4, Tile.Type.WALL.ordinal());
		data.setTile(0, 2, Tile.Type.AVAILABLE.ordinal());
		
		return data;
	}
	
	/*This method loads map from file**/
	public Data initialData(String path) {
		int startX, startY, width, height;
		String file = Tools.loadFileAsString(path);
		String[] mapTile = file.split("\\s+");
		width = Tools.parseInt(mapTile[0]);
		height = Tools.parseInt(mapTile[1]);
		startX = Tools.parseInt(mapTile[2]);
		startY = Tools.parseInt(mapTile[3]);
		Data data = new Data(startX, startY, width, height);

		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				data.setTile(x, y, Tools.parseInt(mapTile[(x + y * width) + 4]));
			}
		}
		
		data.setTile(startX-1, startY, Tile.Type.PLAYER_CAR.ordinal());
		data.setTile(startX+1, startY, Tile.Type.OPPONENT_CAR.ordinal());
		
		return data;
	}
	
	/** This is main game thick 
	 * @param data information about actual map situation, that will be processed
	 * @param input information about player choice*/
	public void process(Data data, Input input) {
		
		if (!correctInput(data, input))
			return;
		
		data.setTile(11-1, 11, Tile.Type.PLAYER_CAR.ordinal());
		System.out.println("wykonujesie");
	}
	
	private boolean correctInput(Data data, Input input) {

		if (input == null || input.isNull())
			return false;
		
		return true;
	}
}
