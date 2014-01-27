package entities;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Entity implements Drawable {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	public Entity(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	protected void highlightRegion(Graphics g, Color c) {
		if (c != null) {
			g.setColor(c);
			g.fillRect(x, y, width, height);
		}
	}
	
	protected void outlineRegion(Graphics g, Color c) {
		if (c != null) {
			g.setColor(c);
			g.drawRect(x,  y, width, height);
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
