/**
 * 
 */
package pdf;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map.Entry;

import model.MMIngredient;
import model.MMRecipe;
import model.MMRecipeElement;
import view.table.MMWeekMenuTable;

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

	public static final String FOLDER_PDF = "/print/";
	public static final String PREFIX_PDF = "Menu_";
	public static final String EXT_PDF = ".pdf";

	public static final Color DARK_DODGER_BLUE = new Color(10, 124, 235);
	public static final Color DODGER_BLUE = new Color(30, 144, 255);
	public static final Color LIGHT_DODGER_BLUE = new Color(70, 184, 255);
	public static final Color LIGHTER_DODGER_BLUE = new Color(100, 214, 255);
	
	public static final Color LIGHT_GRAY = new Color(170, 170, 170);
	public static final Color LIGHTER_GRAY = new Color(220, 220, 220);

	public static final Font PARAGRAPH_TITLE_FONT = FontFactory.getFont(
			FontFactory.HELVETICA, 18, Font.BOLD, BaseColor.DARK_GRAY);

	public static final Font NORMAL_FONT = FontFactory.getFont(
			FontFactory.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);

	public static final Font BOLD_FONT = FontFactory.getFont(
			FontFactory.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
	
	public static final Font TABLE_HEADER_FONT = FontFactory.getFont(
			FontFactory.HELVETICA, 10, Font.BOLD, BaseColor.WHITE);

	public static final int SHOP_LIST_COL_COUNT = 4;

	private MMWeekMenuTable weekMenuTable;
	private Paragraph weekMenuParagraph;
	private Paragraph shopListParagraph;
	private PdfPTable weekTable;
	private PdfPTable shopTable;

	public MMWeekMenuPdf(MMWeekMenuTable weekMenuTable) {
		super();
		this.weekMenuTable = weekMenuTable;
		preparePdf();
	}

	private String getTarget() throws IOException {
		File configDir = new File("." + FOLDER_PDF);

		if (!configDir.exists()) {
			configDir.mkdirs();
		}

		Calendar today = Calendar.getInstance();

		return configDir.getPath() + File.separator + PREFIX_PDF
				+ today.get(Calendar.MONTH) + "-"
				+ today.get(Calendar.DAY_OF_MONTH) + "-"
				+ today.get(Calendar.YEAR) + EXT_PDF;
	}

	private void preparePdf() {
		// Week menu paragraph
		weekMenuParagraph = new Paragraph("WeekMenu", PARAGRAPH_TITLE_FONT);

		// Week menu table
		weekTable = new PdfPTable(weekMenuTable.getColumnCount());

		for (int col = 0; col < weekMenuTable.getColumnCount(); col++) {
			Paragraph content = new Paragraph(weekMenuTable.getColumnName(col), TABLE_HEADER_FONT);

			PdfPCell cell = new PdfPCell(content);
			cell.setBackgroundColor(new BaseColor(DARK_DODGER_BLUE));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

			weekTable.addCell(cell);
		}

		for (int row = 0; row < weekMenuTable.getRowCount(); row++) {
			for (int col = 0; col < weekMenuTable.getColumnCount(); col++) {

				if (col == 0) {
					
					Paragraph content = new Paragraph(weekMenuTable.getValueAt(row, col).toString(), TABLE_HEADER_FONT);
					PdfPCell cell = new PdfPCell(content);
					
					if (row % 2 == 0) {
						cell.setBackgroundColor(new BaseColor(LIGHT_DODGER_BLUE));
					} else {
						cell.setBackgroundColor(new BaseColor(DODGER_BLUE));
					}
					
					weekTable.addCell(cell);
				} else {

					Paragraph content = new Paragraph(weekMenuTable.getValueAt(row, col).toString(), NORMAL_FONT);
					PdfPCell cell = new PdfPCell(content);
					
					if (row % 2 == 0) {
						cell.setBackgroundColor(new BaseColor(Color.WHITE));
					} else {
						cell.setBackgroundColor(new BaseColor(LIGHTER_GRAY));
					}
					
					weekTable.addCell(cell);
				}
			}
		}

		// Shop list paragraph
		shopListParagraph = new Paragraph("Shop list", PARAGRAPH_TITLE_FONT);

		// Shop list table
		Hashtable<MMIngredient, Float> quantities = new Hashtable<MMIngredient, Float>();

		for (MMRecipe recipe : weekMenuTable.getRecipes()) {
			if (recipe != null) {
				for (MMRecipeElement element : recipe.getElements()) {

					Float quantity = quantities.get(element.getIngredient());

					if (quantity == null) {
						quantity = new Float(0);
					}

					quantity += element.getQuantity();
					quantities.put(element.getIngredient(), quantity);
				}
			}
		}

		shopTable = new PdfPTable(SHOP_LIST_COL_COUNT);

		for (Entry<MMIngredient, Float> entry : quantities.entrySet()) {
			shopTable.addCell(entry.getKey().getShopPoint().getName());
			shopTable.addCell(entry.getValue().toString());
			shopTable.addCell(entry.getKey().getUnit().toString());
			shopTable.addCell(entry.getKey().getName());
		}
	}

	public void writePdf() throws FileNotFoundException, DocumentException,
			IOException {
		PdfWriter
				.getInstance(this, new FileOutputStream(new File(getTarget())));

		this.open();

		weekMenuParagraph.add(weekTable);
		this.add(weekMenuParagraph);
		
		shopListParagraph.add(shopTable);
		this.add(shopListParagraph);

		this.close();
	}
}
