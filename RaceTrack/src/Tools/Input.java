package Tools;

import java.awt.Point;

public class Input {
	private int x, y;
	
	public Input() {
		x = 0;
		y = 0;
	}

	public void set(Point point) {
		x = (int) point.getX();
		y = (int) point.getY();
	}
	
	public void set(int pX, int pY) {
		x = pX;
		y = pY;
	}
	
	public void set(Input input) {
		set(input.get());
	}
	
	public Point get() {
		return new Point(x, y);
	}
	
	public boolean isNull() {
		
		if (x == 0 && y == 0)
			return true;
		
		return false;
	}
}
