package TileSystem;

import java.awt.image.BufferedImage;

import RaceTrack.Car;

public class Tile {
	private enum Way {n_s, e_w, nw_se, sw_ne, sw_n, n_se, nw_s, s_ne, nw_e, sw_e,w_se, w_ne};
	
	public static Tile[] type = new Tile[20];
	public static Tile blankTile = new BlankTile(0);
	public static Tile wallTile = new WallTile(1);
	public static Tile finishTile = new FinishTile(2);
	public static Tile playerCarTile = new PlayerCarTile(3);
	public static Tile opponentCarTile = new OpponentCarTile(4);
	public static Tile pathTile = new PathTile(5);
	public static Tile availableTile = new AvailableTile(6);
	public static enum Type {BLANK, WALL, FINISH, PLAYER_CAR, OPPONENT_CAR, PATH, AVAILABLE};
		
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
