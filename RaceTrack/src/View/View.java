package View;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import Controller.Controller;
import Graphics.Assets;
import Graphics.ImageLoader;
import Graphics.SpriteSheetHandler;
import TileSystem.Tile;
import Tools.Data;
import Tools.Input;
import Tools.Positions;

public class View {
	private JFrame frame;
	private Canvas canvas;
	private String title;
	private int width, height;
	private BufferStrategy bufferStrategy;
	private Graphics graphics;
		
	/**@param controller is required to connect mouse listener with canvas
	 * @param data contains all information required to display the scene*/
	public View(String pTitle, Data data, Controller controller) {
		title = pTitle;
		width = data.getMapWidth() * Assets.getWidth();
		height = data.getMapHeight() * Assets.getHeight();
		
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
		canvas.setFocusable(false);
		canvas.addMouseListener(controller);
		
		frame.add(canvas);
		frame.pack();
		
		
		Assets.init();		//loading images goes here
		
		update(data);
	}
	
	private int translateX(int x) {
		return ((x * Assets.getWidth()) + (Assets.getWidth() / 2));
	}
	
	private int translateY(int y) {
		return ((y * Assets.getHeight()) + (Assets.getHeight() / 2));
	}
	
	/** This is rendering function */
	public void update(Data data) {		
		bufferStrategy = canvas.getBufferStrategy();
		
		if (bufferStrategy == null) {
			canvas.createBufferStrategy(2);
			return;
		}
		graphics = bufferStrategy.getDrawGraphics();
		graphics.clearRect(0, 0, width, height); 			//Clear the scene

		/**Car textures must be updated because of their possible rotation*/
		Tile.playerCarTile.updateTexture(data.player);
		Tile.opponentCarTile.updateTexture(data.opponent);
		
		for (int y = 0; y < data.getMapHeight(); y++)
			for (int x = 0; x < data.getMapWidth(); x++)
				graphics.drawImage(data.getTile(x, y).getTexture(), Assets.getWidth()*x, Assets.getHeight()*y, null);
		
		/**Drawing vehicles paths*/
		Point tmp = null, tmpPrev;
		int i;
		graphics.setColor(Color.RED);
		for (i = 0; i + 1 < data.player.path.size(); i++) {
			tmpPrev = (Point) data.player.path.get(i);
			tmp = (Point) data.player.path.get(i + 1);
			graphics.drawLine(translateX(tmpPrev.x), translateY(tmpPrev.y), translateX(tmp.x), translateY(tmp.y));
		}
		if (tmp != null)
			graphics.drawLine(translateX(tmp.x), translateY(tmp.y), translateX(data.player.position.x), translateY(data.player.position.y));
		
		tmp = null;
		graphics.setColor(Color.BLUE);
		for (i = 0; i + 1 < data.opponent.path.size(); i++) {
			tmpPrev = (Point) data.opponent.path.get(i);
			tmp = (Point) data.opponent.path.get(i + 1);
			graphics.drawLine(translateX(tmpPrev.x), translateY(tmpPrev.y), translateX(tmp.x), translateY(tmp.y));
		}
		if (tmp != null)
			graphics.drawLine(translateX(tmp.x), translateY(tmp.y), translateX(data.opponent.position.x), translateY(data.opponent.position.y));
		
		/**Information about the winner*/
		if (data.isFinal()) {
			if (data.getWhoWon() == 0)
				graphics.drawImage(Assets.red_won, 10, 10, null);
			else
				graphics.drawImage(Assets.blue_won, 10, 10, null);
		}
		
		bufferStrategy.show();
		graphics.dispose();
	}
}
