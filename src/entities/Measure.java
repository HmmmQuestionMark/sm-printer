package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import utilities.Resources;
import utilities.Settings;

public class Measure extends Container {
	protected models.Measure measure; 
	protected Hold[] currentHolds;
	
	public Measure(Settings settings, models.Measure measure, Hold[] currentHolds, int x, int y, int width, int height) {
		super(settings, x, y, width, height);
		
		this.measure = measure;
		this.currentHolds = currentHolds;
		
		generateChildren();
	}
	
	private void generateChildren() {
		if (measure != null) {
			List<models.SimFileLine> lines = measure.getLines();
			children = new Entity[lines.size()];
			
			double currentY = y;
			double lineHeight = (double)height / children.length;
			
			for (int i = 0; i < children.length; i++) {
				children[i] = new Line(settings, lines.get(i), currentHolds,
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
		highlightRegion(g, settings.measureColor);
		drawChildrenBackground(g);
		
		if (measure != null) {
			g.setColor(Color.BLACK);
			g.setFont(Resources.getInstance().pageHeader);
			g.drawString(measure.getMeasureNumber() + "", x, y + 5);
		}
	}
	
}
