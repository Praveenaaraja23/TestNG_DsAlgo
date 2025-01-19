package utils;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

public class excelReader {

    private String filePath;

    public excelReader(String filePath) {
        this.filePath = filePath;
    }

    public Object[][] readSheet(String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(new File(filePath));
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            workbook.close();
            throw new IllegalArgumentException("Sheet " + sheetName + " not found in the file: " + filePath);
        }

        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        List<Object[]> dataList = new ArrayList<>();
        for (int i = 1; i <= rowCount; i++) { // Start from the second row
            Row row = sheet.getRow(i);

            if (row == null || isRowEmpty(row)) {
                continue;
            }

            Object[] rowData = new Object[colCount];
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                rowData[j] = getCellValue(cell);
            }

            dataList.add(rowData);
        }

        workbook.close();

        Object[][] data = new Object[dataList.size()][colCount];
        return dataList.toArray(data);
    }

    public Object[][] readSheetWithColumns(String sheetName, List<String> columns) throws IOException {
        FileInputStream fis = new FileInputStream(new File(filePath));
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            workbook.close();
            throw new IllegalArgumentException("Sheet " + sheetName + " not found in the file: " + filePath);
        }

        Row headerRow = sheet.getRow(0);
        if (headerRow == null) {
            workbook.close();
            throw new IllegalArgumentException("No header row found in the sheet: " + sheetName);
        }

        List<Integer> columnIndices = getColumnIndices(headerRow, columns);

        List<Object[]> dataList = new ArrayList<>();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);

            if (row == null || isRowEmpty(row)) {
                continue;
            }

            Object[] rowData = new Object[columnIndices.size()];
            for (int j = 0; j < columnIndices.size(); j++) {
                Cell cell = row.getCell(columnIndices.get(j));
                rowData[j] = getCellValue(cell);
            }
            dataList.add(rowData);
        }

        workbook.close();

        Object[][] data = new Object[dataList.size()][columnIndices.size()];
        return dataList.toArray(data);
    }

    private static boolean isRowEmpty(Row row) {
        for (int j = 0; j < row.getLastCellNum(); j++) {
            Cell cell = row.getCell(j);
            if (cell != null && cell.getCellType() != CellType.BLANK && getCellValue(cell).toString().trim().length() > 0) {
                return false;
            }
        }
        return true;
    }

    private static Object getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    private List<Integer> getColumnIndices(Row headerRow, List<String> columnNames) {
        List<Integer> indices = new ArrayList<>();
        for (Cell cell : headerRow) {
            if (columnNames.contains(cell.getStringCellValue())) {
                indices.add(cell.getColumnIndex());
            }
        }
        return indices;
    }
}
