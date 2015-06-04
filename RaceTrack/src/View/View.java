package View;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import Graphics.Assets;
import Graphics.ImageLoader;
import Graphics.SpriteSheetHandler;
import RaceTrack.Data;

public class View {
	private JFrame frame;
	private Canvas canvas;
	private String mTitle;
	private int mWidth, mHeight, frameNo;
	private BufferStrategy bufferStrategy;
	private Graphics graphics;
		
	public View(String title, int width, int height) {
		mTitle = title;
		mWidth = width;
		mHeight = height;
		frameNo = 0;
		
		frame = new JFrame(mTitle);
		frame.setSize(mWidth, mHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(mWidth, mHeight));
		canvas.setMaximumSize(new Dimension(mWidth, mHeight));
		canvas.setMinimumSize(new Dimension(mWidth, mHeight));
		
		frame.add(canvas);
		frame.pack();
		
		Assets.init();		//loading images goes here
	}
	
	/** This is rendering function */
	public void update(Data data) {
		bufferStrategy = canvas.getBufferStrategy();
		
		if (bufferStrategy == null) {
			canvas.createBufferStrategy(3);
			return;
		}
		graphics = bufferStrategy.getDrawGraphics();
		graphics.clearRect(0, 0, mWidth, mHeight); 			//Clear the scene
		//Drawing itself starts here
		graphics.drawImage(Assets.opponentNE, (frameNo)%700-60, 700-(frameNo)%700-60, null);
		//End of drawing
		bufferStrategy.show();
		graphics.dispose();
		frameNo++;
	}
}
