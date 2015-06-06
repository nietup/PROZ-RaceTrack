package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

import RaceTrack.Data;
import RaceTrack.Input;

public class Controller implements MouseListener{
	private boolean newInput;
	private Input input;
	
	public Controller() {
		newInput = false;
		input = new Input();
	}
	
	public void collectInput(Input pInput) {
		
		if (newInput == false) {
			pInput.set(0, 0);
			return;
		}
		pInput.set(input);
		newInput = false;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		newInput = true;
		input.set(arg0.getPoint());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}
