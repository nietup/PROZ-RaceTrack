package RaceTrack;

import Controller.Controller;
import Model.Model;
import View.View;

public class Main {
	
	public static void main(String[] args) {
		
		Model model = new Model();
		View view = new View();
		Controller controller = new Controller();
		Data data = model.initialData();
		Input input = null;
		
		while (true) {
			
			view.update(data);
			
			if (data.isFinal())
				break;
			
			input = controller.collectInput();
			data = model.process(input);
		}
	}
}
