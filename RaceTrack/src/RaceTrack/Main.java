package RaceTrack;

import Controller.Controller;
import Model.Model;
import Tools.Data;
import Tools.Input;
import View.View;

public class Main implements Runnable {
	private boolean run = false;
	private Thread thread;
	private int mode;
	
	private Model model;
	private View view;
	private Controller controller;
	private Data data;
	private Input input;
	
	Main(int pMode) {
		mode = pMode;
		model = new Model();
		data = (mode == 0) ?  model.initialData() : model.generateMap(40, 30);
		controller = new Controller();
		view = new View("Race Track Ultimate Pro Elite I - The Race Track Saga", data, controller);
		input = new Input();
	}
	
	public synchronized void start() {
		
		if (run)
			return;
		
		run = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		
		if (!run)
			return;
		
		run = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**Contains the main game loop*/
	public void run() {		
		/**Controling of frames per secound rate*/
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		
		/**The main game loop*/
		while (run) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			
			if (delta >= 1) {
				delta = 0;
				view.update(data);
				controller.collectInput(input);
				model.process(data, input);
			}
		}
		stop();
	}
	
	public static void main(String[] args) {
		Main game = new Main(1);
		game.start();
	}
}
