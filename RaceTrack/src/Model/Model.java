package Model;

import RaceTrack.Data;
import RaceTrack.Input;

public class Model {
	
	/** This is main game thick 
	 * @param data information about actual map situation, that will be processed
	 * @param input information about player choice*/
	public void process(Data data, Input input) {
		// TODO Auto-generated method stub
	}

	/**This method generates game map*/
	public Data initialData(int startX, int startY, int width, int height) {
		Data data = new Data(startX, startY, width, height);
		
		for (int x = 0; x < data.getMapWidth(); x++)
			for (int y = 0; y < data.getMapHeight(); y++)
				data.setTile(x, y, 0);
		
		data.setTile(startX-1, startY, 3);
		data.setTile(startX+1, startY, 3);
		
		return data;
	}
}
