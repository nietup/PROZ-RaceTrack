package TileSystem;

import java.awt.image.BufferedImage;

import Graphics.Assets;
import Tools.Car;

public class OpponentCarTile extends Tile {

	public OpponentCarTile(int pId) {
		super(Assets.opponentN, pId);
	}
	
	public void updateTexture(Car car) {
		switch (car.orientation) {
		case "N":
			texture = Assets.opponentN;
			break;
		case "NE":
			texture = Assets.opponentNE;
			break;
		case "E":
			texture = Assets.opponentE;
			break;
		case "SE":
			texture = Assets.opponentSE;
			break;
		case "S":
			texture = Assets.opponentS;
			break;
		case "SW":
			texture = Assets.opponentSW;
			break;
		case "W":
			texture = Assets.opponentW;
			break;
		case "NW":
			texture = Assets.opponentNW;
			break;
		}
	}

}
