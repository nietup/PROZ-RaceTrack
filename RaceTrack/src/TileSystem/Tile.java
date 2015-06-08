package TileSystem;

import java.awt.image.BufferedImage;

import Tools.Car;

/**Contains all types of tiles*/
public class Tile {	
	public static Tile[] type = new Tile[20];
	public static Tile blankTile = new BlankTile(0);
	public static Tile wallTile = new WallTile(1);
	public static Tile finishTile = new FinishTile(2);
	public static Tile playerCarTile = new PlayerCarTile(3);
	public static Tile opponentCarTile = new OpponentCarTile(4);
	public static Tile availableTile = new AvailableTile(5);
	public static enum Type {BLANK, WALL, FINISH, PLAYER_CAR, OPPONENT_CAR, AVAILABLE};
		
	protected BufferedImage texture;
	protected final int id;
		
	public Tile(BufferedImage pTexture, int pId) {
		texture = pTexture;
		id = pId;
		
		type[id] = this;
	}
		
	public int getId(){
		return id;
	}
		
	public BufferedImage getTexture() {
		return texture;
	}

	public void updateTexture(Car car){}
}
