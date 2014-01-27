package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import utilities.Settings;

public class Measure extends Container {
	protected models.Measure measure; 
	protected Hold[] currentHolds;
	
	public Measure(models.Measure measure, Hold[] currentHolds, int x, int y, int width, int height) {
		super(x, y, width, height);
		
		this.measure = measure;
		this.currentHolds = currentHolds;
		
		generateChildren();
	}
	
	private void generateChildren() {
		if (measure != null) {
			List<models.StepLine> lines = measure.getLines();
			children = new Entity[lines.size()];
			
			double currentY = y;
			double lineHeight = (double)height / children.length;
			
			for (int i = 0; i < children.length; i++) {
				children[i] = new Line(lines.get(i), currentHolds,
						x, (int)currentY, width, (int)lineHeight);
				currentY += lineHeight;
			}
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
		highlightRegion(g, Settings.measureColor);
		drawChildrenBackground(g);		
	}
	
}
