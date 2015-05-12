package RaceTrack;

public class Data {
	
	public Car player = null,
			   opponent = null; 
	
	private boolean finalState = false;
	
	public Data(int x, int y) {
		
		player = new Car(x - 1, y);
		opponent = new Car(x + 1, y);
	}
	
	public boolean isFinal() {

		return finalState;
	}
	
	public void setFinal() {
		
		finalState = true;
	}

}
