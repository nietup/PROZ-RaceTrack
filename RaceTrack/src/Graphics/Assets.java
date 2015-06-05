package Graphics;

import java.awt.image.BufferedImage;

public class Assets {
	private static final int width = 50, height = 50;

	public static BufferedImage playerN,
								playerNE,
								opponentN,
								opponentNE, 
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
		opponentN = sprites.crop(0, height, width, height);
		opponentNE = sprites.crop(width, height, width, height);
		blank = sprites.crop(0, 2*height, width, height);;
	}
	
	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}
}
