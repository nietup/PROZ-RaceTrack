package TileSystem;

import java.awt.image.BufferedImage;

import Graphics.Assets;
import Tools.Car;

public class PlayerCarTile extends Tile {

	public PlayerCarTile(int id) {
		super(Assets.playerN, id);
		// TODO Auto-generated constructor stub
	}
	
	/**This method updates cat texture depending on car orientation
	 * it is to be executed in Model*/
	public void updateTexture(Car car) {
			switch (car.orientation) {
			case "N":
				texture = Assets.playerN;
				break;
			case "NE":
				texture = Assets.playerNE;
				break;
			case "E":
				texture = Assets.playerE;
				break;
			case "SE":
				texture = Assets.playerSE;
				break;
			case "S":
				texture = Assets.playerS;
				break;
			case "SW":
				texture = Assets.playerSW;
				break;
			case "W":
				texture = Assets.playerW;
				break;
			case "NW":
				texture = Assets.playerNW;
				break;
			}
	}
}
