package Model;

import java.awt.Point;
import java.awt.Rectangle;

import Graphics.Assets;
import RaceTrack.Data;
import RaceTrack.Input;
import TileSystem.Tile;
import Tools.Finish;
import Tools.Tools;

public class Model {
	/**Positions of a car in tiles*/
	int translatedX, translatedY;
	boolean firstTurn;
	
	public Model() {
		translatedX = translatedY = 0;
		firstTurn = true;
	}

	/**This method generates game map*/
	public Data initialData(int startX, int startY, int width, int height, Finish finish) {
		Data data = new Data(startX, startY, width, height, finish);
		
		for (int x = 0; x < data.getMapWidth(); x++)
			for (int y = 0; y < data.getMapHeight(); y++)
				data.setTile(x, y, 0);
		
		return data;
	}
	
	/*This method loads map from file**/
	public Data initialData(String path) {
		int startX, startY, width, height;
		Finish finish = new Finish();
		String file = Tools.loadFileAsString(path);
		String[] mapTile = file.split("\\s+");
		width = Tools.parseInt(mapTile[0]);
		height = Tools.parseInt(mapTile[1]);
		startX = Tools.parseInt(mapTile[2]);
		startY = Tools.parseInt(mapTile[3]);
		finish.first.x = Tools.parseInt(mapTile[4]);
		finish.first.y = Tools.parseInt(mapTile[5]);
		finish.secound.x = Tools.parseInt(mapTile[6]);
		finish.secound.y = Tools.parseInt(mapTile[7]);
		finish.third.x = Tools.parseInt(mapTile[8]);
		finish.third.y = Tools.parseInt(mapTile[9]);
		Data data = new Data(startX, startY, width, height, finish);

		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				data.setTile(x, y, Tools.parseInt(mapTile[(x + y * width) + 10]));
				if (Tools.parseInt(mapTile[(x + y * width) + 10]) == Tile.Type.WALL.ordinal())
					data.addWall(x, y);
			}
		}
		
		data.setTile(finish.first.x, finish.first.y, Tile.Type.FINISH.ordinal());
		data.setTile(finish.secound.x, finish.secound.y, Tile.Type.FINISH.ordinal());
		data.setTile(finish.third.x, finish.third.y, Tile.Type.FINISH.ordinal());
		data.setTile(startX-1, startY, Tile.Type.PLAYER_CAR.ordinal());
		data.setTile(startX+1, startY, Tile.Type.OPPONENT_CAR.ordinal());
		
		return data;
	}
	
	/** This is main game thick 
	 * @param data information about actual map situation, that will be processed
	 * @param input information about player choice*/
	public void process(Data data, Input input) {
		
		if (firstTurn) {
			firstTurn = false;
			computeAvailables(data);
		}
		
		if (!correctInput(data, input))
			return;
		
		data.player.path.add(new Point(data.player.position.x, data.player.position.y));
		data.setTile(data.player.position.x, data.player.position.y, Tile.Type.BLANK.ordinal());
		data.player.position.x = translatedX;
		data.player.position.y = translatedY;
		data.setTile(data.player.position.x, data.player.position.y, Tile.Type.PLAYER_CAR.ordinal());
		data.removeAvailable(translatedX, translatedY);
		
		moveOpponent(data);
		
		computeAvailables(data);
		
		if (data.isFinal())
			endGame();
	}
	
	private void endGame() {
		// TODO Auto-generated method stub
		
	}

	/**This method also checks if the state is final*/
	private void moveOpponent(Data data) {
		//data.setTile(data.opponent.position.x, data.opponent.position.y, Tile.Type.BLANK.ordinal());
		//data.opponent.position.y--;
		//data.setTile(data.opponent.position.x, data.opponent.position.y, Tile.Type.OPPONENT_CAR.ordinal());
	}

	/**This method also checks if the state is final*/
	private void computeAvailables(Data data) {
		Point tmp;
		int availablesCount = data.availablesCount();
		
		for (int x = 0; x < availablesCount; x++) {
			tmp = data.getAvailable();
			data.setTile(tmp.x, tmp.y, Tile.Type.BLANK.ordinal());
			data.removeAvailable();
		}
		tmp = (Point) data.player.path.get(data.player.path.size()-1);
		int playerX = data.player.position.x, playerY = data.player.position.y;
		int baseX = 2*playerX - tmp.x, baseY = 2*playerY - tmp.y;
		
		//TODO check for collisions with wall
		
		if (baseX > data.getMapWidth() || baseX < 0 || baseY > data.getMapHeight() || baseY < 0) {
			data.setFinal();
			return;
		}
		
		Rectangle tmpRectangle;
		int availableFields = 0;
		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				availability_check:
				if (baseX + x < data.getMapWidth() && baseX + x >= 0 && baseY + y < data.getMapHeight() && baseY + y >= 0) {
					for (int i = 0; i < data.wallCount(); i++) {
						tmp = data.getWall(i);
						tmpRectangle = new Rectangle(translateX(tmp.x), translateY(tmp.y), Assets.getWidth(), Assets.getHeight());
						if (tmpRectangle.intersectsLine(translateXcenter(baseX + x), translateYcenter(baseY + y), translateXcenter(playerX), translateYcenter(playerY))) {
							break availability_check;
						}
					}
					if (data.getTileId(baseX + x, baseY + y) == Tile.Type.BLANK.ordinal()) {
						data.setTile(baseX + x, baseY + y, Tile.Type.AVAILABLE.ordinal());
						data.addAvailable(baseX + x, baseY + y);
						availableFields++;
					}
				} 
			}
		}	
		
		if (availableFields == 0) {
			data.setFinal();
			return;
		}
	}
	
	private void translateInput(Input input) {
		translatedX = input.get().x / Assets.getWidth();
		translatedY = input.get().y / Assets.getHeight();
	}
	
	/**Translates input for getting rectangle*/
	private int translateX(int x) {
		return (x * Assets.getWidth());
	}
	
	/**Translates input for getting rectangle*/
	private int translateY(int y) {
		return (y * Assets.getHeight());
	}
	
	/**Translates input for getting tile center*/
	private int translateXcenter(int x) {
		return (x * Assets.getWidth() + (Assets.getWidth() / 2));
	}
	
	/**Translates input for getting tile center*/
	private int translateYcenter(int y) {
		return (y * Assets.getHeight() + (Assets.getHeight()));
	}

	private boolean correctInput(Data data, Input input) {

		if (input == null || input.isNull())
			return false;
		
		translateInput(input);
		
		if(data.getTile(translatedX, translatedY).getId() != Tile.Type.AVAILABLE.ordinal())
			return false;
		
		return true;
	}
}
