package Tools;

import java.awt.Point;
import java.util.Vector;

public class Car {
	/**base possible horizontal and vertical move vaule (+/- 1); positive values towards right*/
	public int x_velocity, y_velocity;
	public Positions position = null;
	/**the history of a car's movement*/
	public Vector path;
	/**actual vehicle's orientation
	 * possible valuse are:
	 * "N"
	 * "NE"
	 * "E"
	 * ...etc.*/
	public String orientation;
	public int team;
	
	public Car(int x, int y, int pTeam) {
		position = new Positions(x, y);
		x_velocity = y_velocity = 0;
		path = new Vector();
		path.add(new Point(x,y));
		orientation = "N";
		team = pTeam;
	}
}
