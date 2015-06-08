package Tools;

/**Contains information about 3 finish line tiles*/
public class Finish {
	public Positions first;
	public Positions secound;
	public Positions third;
	
	public Finish() {
		first = new Positions(0, 0);
		secound = new Positions(0, 0);
		third = new Positions(0, 0);
	}
}
