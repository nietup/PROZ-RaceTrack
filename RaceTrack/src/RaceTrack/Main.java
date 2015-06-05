package RaceTrack;

import Controller.Controller;
import Model.Model;
import View.View;

public class Main implements Runnable {
	private boolean run = false;
	private Thread thread;
	
	Model model;
	View view;
	Controller controller;
	Data data;
	Input input;
	
	private void init() {
		model = new Model();
		data = model.initialData(1, 0, 20, 20);
		view = new View("Race Track Ultimate Pro Elite I - The Race Track Saga", data);
		controller = new Controller();
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
	
	/*Contains the main game loop**/
	public void run() {
		init();
		
		// TODO change it so the game loop goes only when player makes move
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		
		while (run) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			
			/**This is the main game loop*/
			if(delta >= 1) {			
				view.update(data);
				controller.collectInput(input);
				model.process(data, input);
				delta = 0;
			}
		}
		stop();
	}
	
	public static void main(String[] args) {
		Main game = new Main();
		game.start();
	}
}
