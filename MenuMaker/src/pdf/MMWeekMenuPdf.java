/**
 * 
 */
package pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import view.table.MMShopListTable;
import view.table.MMWeekMenuTable;
import view.table.model.MMShopListTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author cmaurice2
 * 
 */
public class MMWeekMenuPdf extends Document {

	public static final String FOLDER_PDF = "print/";
	public static final String PREFIX_PDF = "Menu_";
	public static final String EXT_PDF = ".pdf";

	public static final Font PARAGRAPH_TITLE_FONT = FontFactory.getFont(
			FontFactory.HELVETICA, 18, Font.BOLD, BaseColor.DARK_GRAY);

	public static final Font NORMAL_FONT = FontFactory.getFont(
			FontFactory.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);

	public static final Font BOLD_FONT = FontFactory.getFont(
			FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.DARK_GRAY);

	public static final Font TABLE_HEADER_FONT = FontFactory.getFont(
			FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);

	private MMWeekMenuTable weekMenuTable;
	private MMShopListTable shopListTable;
	private Paragraph weekMenuParagraph;
	private Paragraph shopListParagraph;
	private PdfPTable weekTable;
	private PdfPTable shopTable;

	private File targetFile;

	public MMWeekMenuPdf(MMWeekMenuTable weekMenuTable,
			MMShopListTable shopListTable) {
		super();
		this.weekMenuTable = weekMenuTable;
		this.shopListTable = shopListTable;
		preparePdf();
	}

	private String getTarget() throws IOException {
		File configDir = new File(FOLDER_PDF);

		if (!configDir.exists()) {
			configDir.mkdirs();
		}

		Calendar today = Calendar.getInstance();

		return configDir.getPath() + File.separator + PREFIX_PDF
				+ (today.get(Calendar.MONTH) + 1) + "-"
				+ today.get(Calendar.DAY_OF_MONTH) + "-"
				+ today.get(Calendar.YEAR) + EXT_PDF;
	}

	public File getTargetFile() {
		return targetFile;
	}

	private void preparePdf() {
		// Week menu paragraph
		weekMenuParagraph = new Paragraph("WeekMenu", PARAGRAPH_TITLE_FONT);

		// Week menu table
		weekTable = new PdfPTable(weekMenuTable.getColumnCount());

		for (int col = 0; col < weekMenuTable.getColumnCount(); col++) {
			Paragraph content = new Paragraph(weekMenuTable.getColumnName(col),
					TABLE_HEADER_FONT);

			PdfPCell cell = new PdfPCell(content);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

			weekTable.addCell(cell);
		}

		for (int row = 0; row < weekMenuTable.getRowCount(); row++) {
			for (int col = 0; col < weekMenuTable.getColumnCount(); col++) {

				if (col == 0) {

					Paragraph content = new Paragraph(weekMenuTable.getValueAt(
							row, col).toString(), TABLE_HEADER_FONT);
					PdfPCell cell = new PdfPCell(content);

					weekTable.addCell(cell);
				} else {

					Paragraph content = new Paragraph(weekMenuTable.getValueAt(
							row, col).toString(), NORMAL_FONT);
					PdfPCell cell = new PdfPCell(content);

					weekTable.addCell(cell);
				}
			}
		}
		
		weekTable.setWidthPercentage(100);
		
		try {
			weekTable.setWidths(new float[] {2,4,4,1,4});
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		// Shop list paragraph
		shopListParagraph = new Paragraph("Shop list", PARAGRAPH_TITLE_FONT);

		// Shop list table
		shopTable = new PdfPTable(shopListTable.getColumnCount());

		for (int col = 0; col < shopListTable.getColumnCount(); col++) {
			Paragraph content = new Paragraph(shopListTable.getColumnName(col),
					TABLE_HEADER_FONT);

			PdfPCell cell = new PdfPCell(content);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

			shopTable.addCell(cell);
		}

		for (int row = 0; row < shopListTable.getRowCount(); row++) {
			for (int col = 0; col < shopListTable.getColumnCount(); col++) {

				if (col == 0) {

					Paragraph content = new Paragraph(shopListTable.getValueAt(
							row, col).toString(), TABLE_HEADER_FONT);
					PdfPCell cell = new PdfPCell(content);

					shopTable.addCell(cell);
				} else {

					Paragraph content;

					if (col == MMShopListTableModel.COL_TOTAL) {
						Double total = new Double((Double)shopListTable.getValueAt(row,
								col));
						
						String formattedTotal;
						
						if((total - total.intValue()) == 0){
							formattedTotal = Integer.toString(total.intValue());
						}
						else{
							formattedTotal = total.toString();
						}
						
						content = new Paragraph(formattedTotal, NORMAL_FONT);
						
					} else {
						content = new Paragraph(shopListTable.getValueAt(row,
								col).toString(), NORMAL_FONT);
					}

					PdfPCell cell = new PdfPCell(content);

					shopTable.addCell(cell);
				}
			}
		}
		
		shopTable.setWidthPercentage(100);
		
		try {
			shopTable.setWidths(new float[] {2,1,1,4});
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public void writePdf() throws FileNotFoundException, DocumentException,
			IOException {

		targetFile = new File(getTarget());

		PdfWriter.getInstance(this, new FileOutputStream(targetFile));

		this.open();

		weekMenuParagraph.add(weekTable);
		this.add(weekMenuParagraph);

		this.newPage();

		shopListParagraph.add(shopTable);
		this.add(shopListParagraph);

		this.close();
	}
}
