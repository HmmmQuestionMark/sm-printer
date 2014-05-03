package utilities;

import java.awt.Color;

import models.StepFile;
import models.StepFileDifficultyMap;

public class Settings {
	public int measuresPerColumn = 3;
	public int columnsPerPage = 4;
	public boolean horizontalOrientation = true;
	public boolean hideLeadingAndTrailingWhiteSpace = true;
	
	public StepFile stepFile;
	public StepFileDifficultyMap difficulty;
	
	public double pageWidthInches = 8.5;
	public double pageHeightInches = 11;
	public double pageDPI = 96;
	
	public Color pageColor = null;
	public Color pageOutlineColor = Color.BLACK;
	public Color columnColor = null;
	public Color columnOutlineColor = Color.BLACK;
	public Color measureColor = null;
	public Color measureOutlineColor = Color.BLACK;
	
	public int getMeasuresPerPage() {
		return measuresPerColumn * columnsPerPage;
	}
	
	public int getNumberOfPages() {
		int measuresPerPage = measuresPerColumn * columnsPerPage;
		if (hideLeadingAndTrailingWhiteSpace) {
			return (int)Math.ceil((double)difficulty.getNumberOfMeasuresTrimmed() / measuresPerPage); 
		}
		return (int)Math.ceil((double)difficulty.getNumberOfMeasures() / measuresPerPage); 
	}
	
	public Settings() {
		
	}
}
