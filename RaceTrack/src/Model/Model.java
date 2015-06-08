package Model;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

import Graphics.Assets;
import TileSystem.Tile;
import Tools.Car;
import Tools.Data;
import Tools.Finish;
import Tools.Input;
import Tools.Positions;
import Tools.Tools;

public class Model {
	/**Positions of a car in tiles*/
	int translatedX, translatedY;
	boolean firstTurn;
	/**false - red car turn
	 * true - blue car turn*/
	boolean whoseTurn;
	
	public Model() {
		translatedX = translatedY = 0;
		firstTurn = true;
		whoseTurn = false;
	}
	
	/*This method loads map from specified file**/
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
	
	/*This method loads map from random file**/
	public Data initialData() {
		int startX, startY, width, height;
		Finish finish = new Finish();
		Random rand = new Random();
		String path = "Assets/Maps/Map" + (rand.nextInt(3)+1) + ".lvl";
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
		
		move(data, input);
		whoseTurn = !whoseTurn;
		computeAvailables(data);
	}
	
	/**This method also checks if the state is final*/
	private void move(Data data, Input input) {
		Car actual;
		
		if (whoseTurn == false)
			actual = data.player;
		else
			actual = data.opponent;
		
		actual.path.add(new Point(actual.position.x, actual.position.y));
		data.setTile(actual.position.x, actual.position.y, Tile.Type.BLANK.ordinal());
		actual.position.x = translatedX;
		actual.position.y = translatedY;
		data.setTile(actual.position.x, actual.position.y, (whoseTurn) ? Tile.Type.OPPONENT_CAR.ordinal() : Tile.Type.PLAYER_CAR.ordinal());
		data.removeAvailable(translatedX, translatedY);
		

		int playerX = actual.position.x, playerY = actual.position.y;
		Point tmp = (Point) actual.path.get(actual.path.size()-1);
		
		/**Check for crossing the finish*/
		Positions tmpFinish = data.finish.first;
		Rectangle tmpRectangle = new Rectangle(translateX(tmpFinish.x), translateY(tmpFinish.y), Assets.getWidth(), Assets.getHeight());
		if (tmpRectangle.intersectsLine(translateXcenter(playerX), translateYcenter(playerY), translateXcenter(tmp.x), translateYcenter(tmp.y))) {
			endGame(!whoseTurn, data);
		}
		tmpFinish = data.finish.secound;
		tmpRectangle = new Rectangle(translateX(tmpFinish.x), translateY(tmpFinish.y), Assets.getWidth(), Assets.getHeight());
		if (tmpRectangle.intersectsLine(translateXcenter(playerX), translateYcenter(playerY), translateXcenter(tmp.x), translateYcenter(tmp.y))) {
			endGame(!whoseTurn, data);
		}
		tmpFinish = data.finish.third;
		tmpRectangle = new Rectangle(translateX(tmpFinish.x), translateY(tmpFinish.y), Assets.getWidth(), Assets.getHeight());
		if (tmpRectangle.intersectsLine(translateXcenter(playerX), translateYcenter(playerY), translateXcenter(tmp.x), translateYcenter(tmp.y))) {
			endGame(!whoseTurn, data);
		}
		
		/**Car rotations*/
		int baseX = playerX - tmp.x, baseY = playerY - tmp.y;
		if (baseX == 0) {
			if (baseY >= 0)
				actual.orientation = "S";
			else
				actual.orientation = "N";
			return;
		}
		if (baseY == 0) {
			if (baseX >= 0)
				actual.orientation = "E";
			else
				actual.orientation = "W";
			return;
		}
		if (baseX >= 0) {
			if (baseY >= 0)
				actual.orientation = "SE";
			else
				actual.orientation = "NE";
			return;
		}
		if (baseX < 0 ) {
			if (baseY >= 0)
			actual.orientation = "SW";
			else
				actual.orientation = "NW";
		}		
	}

	private void endGame(boolean who, Data data) {
		data.setFinal();
		
		if (who)
			data.setWhoWon(0);
		else
			data.setWhoWon(1);
			
	}

	/**This method also checks if the state is final*/
	private void computeAvailables(Data data) {
		Car actual;
		
		if (whoseTurn == false)
			actual = data.player;
		else
			actual = data.opponent;
		
		
		Point tmp;
		int availablesCount = data.availablesCount();
		/**Clearing previous availables*/
		for (int x = 0; x < availablesCount; x++) {
			tmp = data.getAvailable();
			data.setTile(tmp.x, tmp.y, Tile.Type.BLANK.ordinal());
			data.removeAvailable();
		}
		
		/**in case of crossing the finish line*/
		if (data.isFinal())
			return;
		
		tmp = (Point) actual.path.get(actual.path.size()-1);
		int playerX = actual.position.x, playerY = actual.position.y;
		/**Adding (previous position, actual position) vector to players actual position*/
		int baseX = 2*playerX - tmp.x, baseY = 2*playerY - tmp.y;
		
		Rectangle tmpRectangle;
		int availableFields = 0;
		for (int y = -1; y < 2; ++y) {
			for (int x = -1; x < 2; ++x) {
				availability_check:
				if (baseX + x < data.getMapWidth() && baseX + x >= 0 && baseY + y < data.getMapHeight() && baseY + y >= 0) {
					for (int i = 0; i < data.wallCount(); i++) {
						tmp = data.getWall(i);
						tmpRectangle = new Rectangle(translateX(tmp.x)+3, translateY(tmp.y)+3, Assets.getWidth()-6, Assets.getHeight()-6);
						if (tmpRectangle.intersectsLine(translateXcenter(playerX), translateYcenter(playerY), translateXcenter(baseX + x), translateYcenter(baseY + y))) {
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
			endGame(whoseTurn, data);
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
		return (y * Assets.getHeight() + (Assets.getHeight() / 2));
	}

	private boolean correctInput(Data data, Input input) {

		if (input == null || input.isNull())
			return false;
		
		translateInput(input);
		
		if(data.getTile(translatedX, translatedY).getId() != Tile.Type.AVAILABLE.ordinal()) {
			if (data.getTile(translatedX, translatedY).getId() != Tile.Type.FINISH.ordinal()) {
				return false;
			} else {
				/**Check if finish is in range*/
				Car actual;
				
				if (whoseTurn == false)
					actual = data.player;
				else
					actual = data.opponent;
				
				Point tmp = (Point) actual.path.get(actual.path.size()-1);
				int playerX = actual.position.x, playerY = actual.position.y;
				/**Adding (previous position, actual position) vector to players actual position*/
				int baseX = 2*playerX - tmp.x, baseY = 2*playerY - tmp.y;
				
				if (translatedX > baseX + 1 || translatedX < baseX - 1 || translatedY > baseY + 1 || translatedY < baseY - 1)
					return false;
			}
		}
		
		return true;
	}
}
