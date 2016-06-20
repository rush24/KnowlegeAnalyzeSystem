package edu.fjnu.util;

import java.awt.Color;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Chapter;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * PDF工具
 * 
 * @author Li
 *
 */
public class PDFUtil {
	private final Color black = Color.black; // 黑色
	private final Color blue = Color.blue; // 蓝色
	private final Color red = Color.red; // 红色
	private final Color grey = Color.darkGray; // 深灰色

	private final int bold = Font.BOLD; // 黑体
	private final int normal = Font.NORMAL; // 常规
	private final int italic = Font.ITALIC; // 斜体
	private final int bolditalic = Font.BOLDITALIC; // 斜黑体

	private float setting = 24; // 首行缩进参数

	/**
	 * 转化成为汉字
	 * 
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static Font ChineseFron() throws DocumentException, IOException {
		BaseFont bfSong = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
		Font fontSong = new Font(bfSong, 10, Font.BOLD);

		return fontSong;
	}

	public static Font ChineseFron(int size) throws DocumentException, IOException {
		BaseFont bfSong = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
		Font fontSong = new Font(bfSong, size, Font.BOLD);

		return fontSong;
	}

	/**
	 * 同段落汉字转化
	 * 
	 * @param text
	 * @param fontsize
	 * @param fonStyle
	 * @param color
	 * @return
	 * @throws DocumentException
	 * @throws Exception
	 */
	public static Paragraph convertParToChinese(String text, int fontsize, int fonStyle, Color color)
			throws DocumentException, Exception {
		BaseFont baseFontChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font chineseFont = new Font(baseFontChinese, fontsize, fonStyle, color);
		Paragraph graph = new Paragraph(text, chineseFont);
		return graph;
	}

	/**
	 * 同行汉字转化
	 * 
	 * @param text
	 * @param fontsize
	 * @param fontStyle
	 * @param color
	 * @return
	 * @throws DocumentException
	 * @throws Exception
	 */
	public static Chunk convertChunkByChinese(String text, int fontsize, int fontStyle, Color color)
			throws DocumentException, Exception {
		BaseFont baseFontChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font chineseFont = new Font(baseFontChinese, fontsize, fontStyle, color);
		Chunk chunk = new Chunk(text, chineseFont);
		return chunk;
	}

	/**
	 * 创建一个PDF文件
	 * 
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public Document createDocument(String filename) throws Exception {
		// 创建一个Document对象
		Document doc = new Document(PageSize.A3, 40, 40, 40, 40);
		// 蒋健一个书写器PdfWriter
		PdfWriter.getInstance(doc, new FileOutputStream(filename));

		// 页头
		HeaderFooter header = new HeaderFooter(new Phrase("第九组", ChineseFron()), false);
		header.setBorder(2);
		header.setAlignment(Element.ALIGN_LEFT);
		doc.setHeader(header);

		// 页尾
		HeaderFooter footer = new HeaderFooter(new Phrase("第 ", ChineseFron()), new Phrase(" 页", ChineseFron()));
		footer.setAlignment(Element.ALIGN_CENTER);
		footer.setBorder(1);
		doc.setFooter(footer);
		return doc;
	}

	/**
	 * 插入图片
	 * 
	 * @param imgPath
	 * @throws Exception
	 */
	public void InsertImg(Document doc, String imgPath) throws Exception {
		Image img = Image.getInstance(imgPath);
		// img.scaleAbsolute(150, 150);
		img.setAlignment(Element.ALIGN_CENTER);
		doc.add(img);
	}

	/**
	 * 插入段落
	 * 
	 * @param doc
	 * @param text
	 * @throws Exception
	 */
	public void InsertParagh(Document doc, String text) throws Exception {
		Paragraph par = new Paragraph();
		par.add(convertChunkByChinese(text, 15, normal, black));
		par.setFirstLineIndent(setting);
		doc.add(par);
	}

	public void InsertParagh(Document doc, String text, int size) throws Exception {
		Paragraph par = new Paragraph();
		par.add(convertChunkByChinese(text, size, normal, black));
		par.setAlignment(Element.ALIGN_MIDDLE);
		doc.add(par);
	}

	/**
	 * 模块标题设计
	 * 
	 * @param doc
	 * @param text
	 * @throws DocumentException
	 * @throws Exception
	 */
	public void InsertModuleTitle(Document doc, String text) throws DocumentException, Exception {
		Paragraph enter = new Paragraph("\n"); // 回车
		doc.add(enter);
		Paragraph title = new Paragraph(convertParToChinese(text, 30, bold, black));
		title.setAlignment(Element.ALIGN_CENTER);
		doc.add(title);
	}

	/**
	 * 插入章节标题
	 * 
	 * @param doc
	 * @param text
	 * @throws Exception
	 */
	public void InsertTitle(Document doc, String text) throws Exception {
		Paragraph parFront = new Paragraph("\n\n");
		Paragraph title = new Paragraph(convertParToChinese(text, 24, bold, black));
		Paragraph parBcak = new Paragraph("\n");
		doc.add(parFront);
		doc.add(title);
		doc.add(parBcak);
	}

	public void InsertSubhead(Document doc, String text) throws Exception {
		Paragraph title = new Paragraph(convertParToChinese(text, 18, bold, black));
		doc.add(title);
	}

	/**
	 * 制作PDF的封面
	 * 
	 * @param doc
	 * @param imgpath
	 * @param name
	 * @throws BadElementException
	 * @throws Exception
	 * @throws IOException
	 */
	public void makePDFCover(Document doc, String name, String other)
			throws BadElementException, Exception, IOException {

		String rootPath = FileTools.getApplicationRootPath();
		PDFUtil util = new PDFUtil();
		Paragraph centrePar = convertParToChinese(name, 30, bold, black);
		centrePar.setAlignment(Element.ALIGN_CENTER);
		Paragraph par = new Paragraph("\n\n\n\n\n\n\n\n\n\n\n\n");
		doc.add(par);

		util.InsertImg(doc, rootPath + "imag/" + "学立方logo.png");
		doc.add(centrePar);
		Paragraph subPar = convertParToChinese(other, 20, normal, black);
		subPar.setAlignment(Element.ALIGN_CENTER);
		doc.add(subPar);
		doc.newPage();

	}

	/**
	 * 插入的表格
	 * 
	 * @param args
	 * @throws Exception
	 */

	// , List<String> list, Color color
	public void InsertTable(Document doc, float[] widths, float totalwidth, List<String[]> list, int textSize,
			int front, Color color) throws Exception {
		PdfPTable table = new PdfPTable(widths.length);
		table.setTotalWidth(totalwidth);
		table.setWidths(widths);
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.get(i).length; j++) {
				if (null == list.get(i)[j]) {
					list.get(i)[j] = "     ";
				}
				PdfPCell cell = new PdfPCell();
				Paragraph para = new Paragraph(convertChunkByChinese(list.get(i)[j], textSize, front, color));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPhrase(para);
				table.addCell(cell);
			}
		}

		doc.add(table);
	}

	public void InsertTable(Document doc, float[] widths, float totalwidth, List<String[]> list, int textSize,
			Color color) throws Exception {
		PdfPTable table = new PdfPTable(widths.length);
		table.setTotalWidth(totalwidth);
		table.setWidths(widths);
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.get(i).length; j++) {
				if (null == list.get(i)[j]) {
					list.get(i)[j] = "     ";
				}
				PdfPCell cell = new PdfPCell();
				Paragraph para = new Paragraph(convertChunkByChinese(list.get(i)[j], textSize, Font.NORMAL, color));
				cell.setPhrase(para);
				table.addCell(cell);
			}
		}

		doc.add(table);
	}

	public static void main(String[] args) throws Exception {
		PDFUtil pdf = new PDFUtil();
		Document doc = pdf.createDocument("D:/test.pdf");
		doc.open();
		pdf.makePDFCover(doc, "关联分析报告", null);
		pdf.InsertTitle(doc, "ssssssss");
		doc.close();

	}
}
