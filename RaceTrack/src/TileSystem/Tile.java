package TileSystem;

import java.awt.image.BufferedImage;

public class Tile {
	//STATIC STUFF HERE
	
		public static Tile[] type = new Tile[5];
		public static Tile blankTile = new BlankTile(0);
		public static Tile wallTile = new WallTile(1);
		public static Tile finishTile = new FinishTile(2);
		public static Tile carTile = new CarTile(3);
		public static Tile pathTile = new PathTile(4);
		
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
}
