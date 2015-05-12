package View;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import RaceTrack.Data;

public class View {

	private JFrame frame;
	private Canvas canvas;
	private String mTitle;
	private int mWidth, mHeight;
	
	public View(String title, int width, int height) {
		
		mTitle = title;
		mWidth = width;
		mHeight = height;
		
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		
		frame.add(canvas);
		frame.pack();
	}
	
	public void update(Data data) {
		// TODO Auto-generated method stub
		
	}

}
