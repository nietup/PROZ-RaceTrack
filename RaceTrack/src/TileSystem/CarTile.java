package TileSystem;

import java.awt.image.BufferedImage;

import Graphics.Assets;
import RaceTrack.Car;

public class CarTile extends Tile {

	public CarTile(int id) {
		super(Assets.playerN, id);
		// TODO Auto-generated constructor stub
	}
	
	/**This method updates cat texture depending on car orientation
	 * it is to be executed in Model*/
	public void updateTexture(Car car) {
		if (car.team == 0)
			switch (car.orientation) {
			case "N":
				texture = Assets.playerN;
				break;
			case "NE":
				texture = Assets.playerNE;
				break;
			case "E":
				texture = Assets.playerN;
				break;
			case "SE":
				texture = Assets.playerNE;
				break;
			case "S":
				texture = Assets.playerN;
				break;
			case "SW":
				texture = Assets.playerNE;
				break;
			case "W":
				texture = Assets.playerN;
				break;
			case "NW":
				texture = Assets.playerNE;
				break;
			}
		else
			switch (car.orientation) {
			case "N":
				texture = Assets.opponentN;
				break;
			case "NE":
				texture = Assets.opponentNE;
				break;
			case "E":
				texture = Assets.opponentN;
				break;
			case "SE":
				texture = Assets.opponentNE;
				break;
			case "S":
				texture = Assets.opponentN;
				break;
			case "SW":
				texture = Assets.opponentNE;
				break;
			case "W":
				texture = Assets.opponentN;
				break;
			case "NW":
				texture = Assets.opponentNE;
				break;
			}
	}
}
