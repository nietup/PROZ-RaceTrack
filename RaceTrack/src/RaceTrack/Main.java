package RaceTrack;

import Controller.Controller;
import Model.Model;
import View.View;

public class Main implements Runnable{
	
	private boolean run = false;
	private Thread thread;
	
	Model model;
	View view;
	Controller controller;
	Data data;
	Input input;
	
	private void init() {
		
		model = new Model();
		view = new View("Race Track Ultimate Pro Elite I - The Race Track Saga", 700, 700);
		controller = new Controller();
		data = model.initialData();
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
		}
		
		catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		init();
		
		while (run) {
			
			view.update(data);								//render
			input = controller.collectInput();
			data = model.process(input);					//tick
		}
		
		stop();
	}
	
	public static void main(String[] args) {
		
		Main game = new Main();
		game.start();
	}
}
