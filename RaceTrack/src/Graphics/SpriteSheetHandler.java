package Graphics;

import java.awt.image.BufferedImage;

public class SpriteSheetHandler {
	private BufferedImage mSheet;
	
	public SpriteSheetHandler(BufferedImage sheet) {
		mSheet = sheet;
	}
	
	public BufferedImage crop(int x, int y, int width, int height){
		return mSheet.getSubimage(x, y, width, height);
	}
}
