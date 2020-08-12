package frolov.excelFromPro100.dataSource;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.WorkbookUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Builder {

    HSSFWorkbook book;
    int startRow = -1;

    public Builder(String filePath) throws Exception {
        book = new HSSFWorkbook(new FileInputStream(filePath));
        HSSFSheet sheet = book.getSheetAt(0);

        outerLoop:
        for (Row row : sheet) {
            for (Cell cell : row) {
                if(cell.toString().equals("{row}")) {
                    startRow = cell.getRowIndex();
                    break outerLoop;
                }
            }
        }

        if(startRow == -1) {
            throw new IllegalStateException("Wrong file format");
        }
    }

    public HSSFWorkbook save(ArrayList<Material> materials) throws IOException {
        int materialIndex = 0;
        for(Material material : materials) {

            HSSFSheet s = book.cloneSheet(0);
            book.setSheetName(++materialIndex, WorkbookUtil.createSafeSheetName(material.getName()));

            for (Row row : s) {
                if(row.getRowNum() != startRow) {
                    for (Cell cell : row) {
                        if(cell.toString().equals("{material}")) {
                            cell.setCellValue(material.getName());
                        }
                    }
                }
            }
            ArrayList<Line> items = material.getItems();

            // added empty rows
            s.shiftRows(startRow, startRow + items.size(), items.size(), true, false);
            Row templateRow = s.getRow(items.size() + startRow);

            int rowIndex = startRow;
            for(int i = 0; i < items.size(); i++) {
                Row row = s.createRow(rowIndex);
                for(Cell cell : templateRow) {
                    Cell c = row.createCell(cell.getColumnIndex());
                    c.setCellValue(items.get(i).parse(cell.toString()));
                }

                rowIndex++;
            }

            s.removeRow(templateRow);
        }

        book.removeSheetAt(0);
        return book;
        //book.write(new FileOutputStream(outputPath));
    }

    public HSSFWorkbook save(ArrayList<Material> materials, Map<String, String> map) throws IOException {

        if(map != null) {
            for (Material material : materials) {
                if (map.containsKey(material.getName())) {
                    material.setName(map.get(material.getName()));
                }
            }
        }

        return save(materials);
    }
}
