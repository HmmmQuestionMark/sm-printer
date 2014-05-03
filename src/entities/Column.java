package entities;

import java.awt.Graphics;

import utilities.Settings;

public class Column extends Container {
	private final static int COLUMN_MARGIN = 10;
	
	private models.Measure[] measures; 
	private Hold[] currentHolds;
	
	public Column(Settings settings, models.Measure[] measures, int x, int y, int width, int height) {
		super(settings, x, y, width, height);
		
		this.measures = measures;
		generateObjects();
	}
	
	private void generateObjects() {
		if (measures[0] != null) {
			models.SimFileLine exampleLine = measures[0].getLines().get(0);
			currentHolds = new Hold[exampleLine.getSteps().length];
		} else {
			currentHolds = new Hold[0];
		}
		
		double measureHeight = (double)(height - 2 * COLUMN_MARGIN) / measures.length;
		double currentY = y + COLUMN_MARGIN;
		
		children = new Entity[measures.length];
		for (int i = 0; i < measures.length; i++) {
			children[i] = new Measure(settings, measures[i], currentHolds, 
					x + COLUMN_MARGIN, (int)currentY, width - 2 * COLUMN_MARGIN, (int)measureHeight);
			currentY += measureHeight;
		}
	}

	@Override
	public void draw(Graphics g) {
		drawChildren(g);
	}
	
	@Override
	public void drawMidground(Graphics g) {
		drawChildrenMidground(g);
	}
	
	@Override
	public void drawBackground(Graphics g) {
		highlightRegion(g, settings.columnColor);
		outlineRegion(g, settings.columnOutlineColor);
		drawChildrenBackground(g);
	}
}
