package RaceTrack;

import TileSystem.Tile;

public class Data {
	public Car player 	= null,
			   opponent = null; 
	
	private boolean finalState = false;
	private int[][] map;
	private int mapWidth, mapHeight;

	public Data(int x, int y, int pMapWidth, int pMapHeight, int finishX1, int finishY1, int finishX2, int finishY2, int finishX3, int finishY3) {
		player = new Car(x - 1, y, 0);
		opponent = new Car(x + 1, y, 1);
		mapWidth = pMapWidth;
		mapHeight = pMapHeight;
		map = new int[mapWidth][mapHeight];
	}
	
	public boolean isFinal() {
		return finalState;
	}
	
	public void setFinal() {
		finalState = true;
	}
	
	/**This method is used for rendering in View*/
	public Tile getTile(int x, int y) {
		Tile tile = Tile.type[map[x][y]];
		
		if(tile == null)
			return Tile.finishTile;
		
		return tile;
	}
	
	/**This method is used for map generation as well as cars movement in Model*/
	public void setTile(int x, int y, int type) {
		
		if (x >= mapWidth || y >= mapHeight)	
			return;

		map[x][y] = type;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public void setMap(int[][] pMap) {
		pMap = map;
	}
	
}