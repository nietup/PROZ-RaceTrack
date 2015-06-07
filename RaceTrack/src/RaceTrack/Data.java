package RaceTrack;

import java.awt.Point;
import java.util.Vector;

import TileSystem.Tile;
import Tools.Finish;

public class Data {
	public Car player 	= null,
			   opponent = null; 
	
	public Finish finish;
	private Vector availables;
	
	private boolean finalState = false;
	private int[][] map;
	private int mapWidth, mapHeight;

	public Data(int x, int y, int pMapWidth, int pMapHeight, Finish pFinish) {
		player = new Car(x - 1, y, 0);
		opponent = new Car(x + 1, y, 1);
		finish = pFinish;
		mapWidth = pMapWidth;
		mapHeight = pMapHeight;
		map = new int[mapWidth][mapHeight];
		availables = new Vector();
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
			return Tile.blankTile;
		
		return tile;
	}
	
	public int getTileId(int x, int y) {
		
		if (x >= mapWidth || y >= mapHeight)	
			return 0;
		
		return Tile.type[map[x][y]].getId();
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
	
	public void addAvailable(int x, int y) {
		availables.addElement(new Point(x, y));
	}
	
	public Point getAvailable() {
		return (Point) availables.get(0);
	}
	
	public int availablesCount() {
		return availables.size();
	}
	
	public void removeAvailable() {
		availables.remove(0);
	}
	
	public void removeAvailable(int x, int y) {
		Point tmp;
		
		for (int i = 0; i < availables.size(); i++) {
			tmp = (Point) availables.get(i);
			if (tmp.x == x && tmp.y == y)
				availables.remove(tmp);
		}
	}
}