package utilities;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import entities.SimFile;

public class Printer {
	public static PrinterJob getPrinterJob(Settings settings) {
		PrinterJob job = PrinterJob.getPrinterJob();
		SimFile printerRenderer = new SimFile(settings, 0, 0, 
				(int)(settings.pageHeightInches * settings.pageDPI), (int)(settings.pageWidthInches * settings.pageDPI));
		
		PageFormat defaultFormat = job.defaultPage();
		defaultFormat.setOrientation(PageFormat.REVERSE_LANDSCAPE);
		defaultFormat.setPaper(getPaper(settings, 0.0, 72));
		
		job.setPageable(createBook(printerRenderer, defaultFormat));
		
		return job;
	}
	
	private static Book createBook(SimFile renderer, PageFormat format) {
		Book book = new Book();
		for (int i = 0; i < renderer.getNumberOfPages(); i++) {
			book.append(renderer, format);
		}
		return book;
	}
	
	private static Paper getPaper(Settings settings, double margin, double dpi) {
		final double startX = margin * dpi;
		final double startY = margin * dpi;
		final double width = (settings.pageWidthInches - 2*margin) * dpi;
		final double height = (settings.pageHeightInches - 2*margin) * dpi;
		
		Paper paper = new Paper();
		paper.setImageableArea(startX, startY, width, height);
		return paper;
	}
}
