package Model;

import Graphics.Assets;
import RaceTrack.Data;
import RaceTrack.Input;
import TileSystem.Tile;
import Tools.Tools;

public class Model {
	/**Positions of a car in tiles*/
	int translatedX, translatedY;
	
	public Model() {
		translatedX = translatedY = 0;
	}

	/**This method generates game map*/
	public Data initialData(int startX, int startY, int width, int height, int finishX1, int finishY1, int finishX2, int finishY2, int finishX3, int finishY3) {
		Data data = new Data(startX, startY, width, height, finishX1, finishY1, finishX2, finishY2, finishX3, finishY3);
		
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
		int startX, startY, width, height, finishX1, finishY1, finishX2, finishY2, finishX3, finishY3;
		String file = Tools.loadFileAsString(path);
		String[] mapTile = file.split("\\s+");
		width = Tools.parseInt(mapTile[0]);
		height = Tools.parseInt(mapTile[1]);
		startX = Tools.parseInt(mapTile[2]);
		startY = Tools.parseInt(mapTile[3]);
		finishX1 = Tools.parseInt(mapTile[4]);
		finishY1 = Tools.parseInt(mapTile[5]);
		finishX2 = Tools.parseInt(mapTile[6]);
		finishY2 = Tools.parseInt(mapTile[7]);
		finishX3 = Tools.parseInt(mapTile[8]);
		finishY3 = Tools.parseInt(mapTile[9]);
		Data data = new Data(startX, startY, width, height, finishX1, finishY1, finishX2, finishY2, finishX3, finishY3);//robisz kurwe z kodu

		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				data.setTile(x, y, Tools.parseInt(mapTile[(x + y * width) + 10]));
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
		
		data.setTile(data.player.position.x, data.player.position.y, Tile.Type.BLANK.ordinal());
		data.player.position.x = translatedX;
		data.player.position.y = translatedY;
		data.setTile(data.player.position.x, data.player.position.y, Tile.Type.PLAYER_CAR.ordinal());
		
		computeAvailables(data);
		moveOpponent(data);
		
		if (data.isFinal())
			endGame();
	}
	
	private void endGame() {
		// TODO Auto-generated method stub
		
	}

	/**This method also checks if the state is final*/
	private void moveOpponent(Data data) {
		data.setTile(data.opponent.position.x, data.opponent.position.y, Tile.Type.BLANK.ordinal());
		data.opponent.position.y--;
		data.setTile(data.opponent.position.x, data.opponent.position.y, Tile.Type.OPPONENT_CAR.ordinal());
	}

	/**This method also checks if the state is final*/
	private void computeAvailables(Data data) {
		// TODO Auto-generated method stub
		
	}

	private void translateInput(Input input) {
		translatedX = input.get().x / Assets.getWidth();
		translatedY = input.get().y / Assets.getHeight();
	}

	private boolean correctInput(Data data, Input input) {

		if (input == null || input.isNull())
			return false;
		
		translateInput(input);
		
		if(data.getTile(translatedX, translatedY).getId() != Tile.Type.BLANK.ordinal())
			return false;
		
		return true;
	}
}
