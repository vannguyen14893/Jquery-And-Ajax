package com.example.demo.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Comment;
import com.example.demo.entity.User;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class ExcelGenerator {
	@Autowired
	private CommentRepository repository;
	@Autowired
	private UserRepository userRepository;

	public static ByteArrayInputStream customersToExcel(List<Comment> comments) throws IOException {
		String[] COLUMNs = { "Id", "Message", "ParentId", "Email" };
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper createHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("Comments");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			// Row for Header
			Row headerRow = sheet.createRow(0);

			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}

			// CellStyle for Age
			CellStyle ageCellStyle = workbook.createCellStyle();
			ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

			int rowIdx = 1;
			for (Comment comment : comments) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(comment.getId());
				row.createCell(1).setCellValue(comment.getMessage());
				row.createCell(2).setCellValue(comment.getParentId());
				row.createCell(3).setCellValue(comment.getUser().getEmail());
				// Cell ageCell = row.createCell(2);
				// ageCell.setCellValue(comment.getParentId());
				// ageCell.setCellStyle(ageCellStyle);
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public void readExcel(String excelFilePath) {
		final int COLUMN_INDEX_MESSAGE = 0;
		final int COLUMN_INDEX_PARENT_ID = 1;
		final int COLUMN_INDEX_EMAIL = 2;
		try {
			File file = new File(excelFilePath);
			InputStream inputStream = new FileInputStream(file);
			Workbook workbook = getWorkbook(inputStream, excelFilePath);
			// get sheet
			Sheet sheet = workbook.getSheetAt(0);
			// get all row
			Iterator<Row> iterator = sheet.iterator();
			while (iterator.hasNext()) {
				Row nextRow = (Row) iterator.next();
				if (nextRow.getRowNum() == 0) {
					continue;
				}
				// Gets all cell
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				// Read cells and set value for book object
				Comment comment = new Comment();
				while (cellIterator.hasNext()) {
					// Read cell
					Cell cell = (Cell) cellIterator.next();
					Object cellValue = getCellValue(cell);
					if (cellValue == null || cellValue.toString().isEmpty()) {
						continue;
					}
					int columnIndex = cell.getColumnIndex();
					switch (columnIndex) {
					case COLUMN_INDEX_MESSAGE:
						comment.setMessage((String) getCellValue(cell));
						break;
					case COLUMN_INDEX_PARENT_ID:
						Double parentId = Double.valueOf((Double) getCellValue(cell));
						comment.setParentId(parentId.intValue());						
						break;
					case COLUMN_INDEX_EMAIL:
						Double userId = Double.valueOf((Double) getCellValue(cell));
						User user = userRepository.getOne(userId.intValue());
						comment.setUser(user);
						break;
					default:
						break;
					}
				}
				repository.save(comment);
			}

		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Object getCellValue(Cell cell) {
		@SuppressWarnings("deprecation")
		CellType cellType = cell.getCellTypeEnum();
		Object cellValue = null;
		switch (cellType) {
		case BOOLEAN:
			cellValue = cell.getBooleanCellValue();
			break;
		case FORMULA:
			Workbook workbook = cell.getSheet().getWorkbook();
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			cellValue = evaluator.evaluate(cell).getNumberValue();
			break;
		case NUMERIC:
			cellValue = cell.getNumericCellValue();
			break;
		case STRING:
			cellValue = cell.getStringCellValue();
			break;
		case _NONE:
		case BLANK:
		case ERROR:
			break;
		default:
			break;
		}

		return cellValue;
	}

	private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
		Workbook workbook = null;
		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}
}
