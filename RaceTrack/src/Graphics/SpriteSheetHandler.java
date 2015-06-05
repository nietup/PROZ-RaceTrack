package Graphics;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class SpriteSheetHandler {
	private BufferedImage mSheet;
	
	public SpriteSheetHandler(BufferedImage sheet) {
		mSheet = sheet;
	}
	
	public BufferedImage crop(int x, int y, int width, int height) {
		return mSheet.getSubimage(x, y, width, height);
	}
	
	/**@param angle is clockwise rotation multiplied by 90 degrees*/
	public BufferedImage rotate(int x, int y, int width, int height, int angle) {
		BufferedImage result = mSheet.getSubimage(x, y, width, height);
		AffineTransform transform = new AffineTransform();
		transform.rotate(angle*(Math.PI/2), result.getWidth() / 2, result.getHeight() / 2);
		AffineTransformOp transformOp = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		result = transformOp.filter(result, null);
		    
		return result;
	}
	
	/**@param axis can get 0 (horizontal flip), 1 (vertical flip), 2 (bot left - top right flip) or 3 (top left - bot right flip)*/
	public BufferedImage flip(int x, int y, int width, int height, int axis) {
		BufferedImage result = null;
		AffineTransform transform;
		AffineTransformOp transformOp;
		
		switch(axis) {
		case 0:
			result = mSheet.getSubimage(x, y, width, height);
			transform = AffineTransform.getScaleInstance(1, -1);
			transform.translate(0, -result.getWidth(null));
			transformOp = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		    result = transformOp.filter(result, null);
			break;
		case 1:
			result = mSheet.getSubimage(x, y, width, height);
			transform = AffineTransform.getScaleInstance(-1, 1);
			transform.translate(-result.getWidth(null), 0);
			transformOp = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		    result = transformOp.filter(result, null);
			break;
		case 2:
			result = rotate(x, y, width, height, 1);
			transform = AffineTransform.getScaleInstance(1, -1);
			transform.translate(0, -result.getWidth(null));
			transformOp = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		    result = transformOp.filter(result, null);
			break;
		case 3:
			result = rotate(x, y, width, height, 1);
			transform = AffineTransform.getScaleInstance(-1, 1);
			transform.translate(-result.getWidth(null), 0);
			transformOp = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		    result = transformOp.filter(result, null);
			break;
		}
		
		return result;
	}
}
