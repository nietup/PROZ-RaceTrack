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
	private String title;
	private int width, height, frameNo;
	private BufferStrategy bufferStrategy;
	private Graphics graphics;
		
	public View(String pTitle, Data data) {
		title = pTitle;
		width = data.getMapWidth() * Assets.getWidth();
		height = data.getMapHeight() * Assets.getHeight();
		frameNo = 0;
		
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
		graphics.clearRect(0, 0, width, height); 			//Clear the scene
		//Drawing itself starts here
		for(int y = 0; y < data.getMapHeight(); y++){
			for(int x = 0; x < data.getMapWidth(); x++) {
				graphics.drawImage(data.getTile(x, y).getTexture(), Assets.getWidth()*x, Assets.getHeight()*y, null);
			}
		}
		//End of drawing
		bufferStrategy.show();
		graphics.dispose();
		frameNo++;
	}
}
