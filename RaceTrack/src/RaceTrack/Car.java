package RaceTrack;

import java.util.Vector;

public class Car {
	
	public int
		x_position,		//absolute position on map
		y_position,
		x_velocity,		//base possible horizontal move vaule (+/- 1); positive values towards right
		y_velocity;		//base possible vertical move vaule (+/- 1); positive values towards bottom
	
	public Vector path = new Vector();
	
	public Car(int x, int y) {
		
		x_position = x;
		y_position = y;
		x_velocity = y_velocity = 0;
	}
}
