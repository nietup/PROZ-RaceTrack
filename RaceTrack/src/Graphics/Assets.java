package Graphics;

import java.awt.image.BufferedImage;

/**Project sprite manager, loads images*/
public class Assets {
	private static final int width = 25, height = 25;

	public static BufferedImage playerN,
								playerNE,
								playerE,
								playerSE,
								playerS,
								playerSW,
								playerW,
								playerNW,
								opponentN,
								opponentNE, 
								opponentE,
								opponentSE,
								opponentS,
								opponentSW,
								opponentW,
								opponentNW,
								wall, 
								finish, 
								blank, 
								available,
								red_won,
								blue_won;
	
	public static void init() {
		red_won = ImageLoader.loadImage("/Gfx/red.jpg");
		blue_won = ImageLoader.loadImage("/Gfx/blue.jpg");
		SpriteSheetHandler sprites = new SpriteSheetHandler(ImageLoader.loadImage("/Gfx/SpriteSheet.jpg"));

		playerN = sprites.crop(0, 0, width, height);
		playerNE = sprites.crop(width, 0, width, height);
		playerE = sprites.rotate(0, 0, width, height, 1);
		playerSE = sprites.rotate(width, 0, width, height, 1);
		playerS = sprites.rotate(0, 0, width, height, 2);
		playerSW = sprites.rotate(width, 0, width, height, 2);
		playerW = sprites.rotate(0, 0, width, height, 3);
		playerNW = sprites.rotate(width, 0, width, height, 3);
		opponentN = sprites.crop(0, height, width, height);
		opponentNE = sprites.crop(width, height, width, height);
		opponentE = sprites.rotate(0, height, width, height, 1);
		opponentSE = sprites.rotate(width, height, width, height, 1);
		opponentS = sprites.rotate(0, height, width, height, 2);
		opponentSW = sprites.rotate(width, height, width, height, 2);
		opponentW = sprites.rotate(0, height, width, height, 3);
		opponentNW = sprites.rotate(width, height, width, height, 3);
		blank = sprites.crop(0, 2*height, width, height);
		wall = sprites.crop(width, 2*height, width, height);
		finish = sprites.crop(2*width, 2*height, width, height);
		available = sprites.crop(width, 3*height, width, height);
	}
	
	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}
}
