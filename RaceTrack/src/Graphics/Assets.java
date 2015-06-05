package Graphics;

import java.awt.image.BufferedImage;

public class Assets {
	private static final int width = 50, height = 50;

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
								n_s, 
								e_w, 
								nw_se, 
								sw_ne, 
								sw_n, 
								n_se, 
								nw_s, 
								s_ne, 
								nw_e, 
								sw_e,
								w_se, 
								w_ne;
	
	public static void init() {
		SpriteSheetHandler sprites = new SpriteSheetHandler(ImageLoader.loadImage("/Gfx/SpriteSheet.jpg"));

		playerN = sprites.crop(0, 0, width, height);
		playerNE = sprites.crop(width, 0, width, height);
		playerE = sprites.rotate(0, 0, width, height, 1);
		playerSE = sprites.rotate(width, 0, width, height, 1);
		playerS = sprites.rotate(0, 0, width, height, 2);
		playerSW = sprites.rotate(width, 0, width, height, 2);
		playerW = sprites.rotate(0, 0, width, height, 3);
		playerNW = sprites.rotate(width, 0, width, height, 3);
		n_s = sprites.crop(2*width, 0, width, height);
		e_w = sprites.rotate(2*width, 0, width, height, 1);
		opponentN = sprites.crop(0, height, width, height);
		opponentNE = sprites.crop(width, height, width, height);
		opponentE = sprites.rotate(0, height, width, height, 1);
		opponentSE = sprites.rotate(width, height, width, height, 1);
		opponentS = sprites.rotate(0, height, width, height, 2);
		opponentSW = sprites.rotate(width, height, width, height, 2);
		opponentW = sprites.rotate(0, height, width, height, 3);
		opponentNW = sprites.rotate(width, height, width, height, 3);
		sw_n = sprites.crop(2*width, height, width, height); 
		n_se = sprites.flip(2*width, height, width, height, 1);
		nw_s = sprites.flip(2*width, height, width, height, 0);
		s_ne = sprites.rotate(2*width, height, width, height, 2);
		nw_e = sprites.rotate(2*width, height, width, height, 1);
		sw_e = sprites.flip(2*width, height, width, height, 2);
		w_se = sprites.rotate(2*width, height, width, height, 3);
		w_ne = sprites.flip(2*width, height, width, height, 3);
		blank = sprites.crop(0, 2*height, width, height);
		wall = sprites.crop(width, 2*height, width, height);
		finish = sprites.crop(2*width, 2*height, width, height);
		nw_se = sprites.crop(0, 3*height, width, height);
		sw_ne = sprites.flip(0, 3*height, width, height, 1);
	}
	
	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}
}
