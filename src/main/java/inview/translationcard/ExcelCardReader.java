package inview.translationcard;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ExcelCardReader {
    public ExcelCardReader() {
    }

    public static List<Card> read(String filePath) {
        List<Card> cardList = null;
        try {
            Workbook wb = WorkbookFactory.create(new File(filePath));
            Sheet sheet = wb.getSheetAt(0);
            List<String> rowList = null;

            if (sheet == null) return null;
            else rowList = readSheet(sheet);
            //convert list of rows into list of Cards
            cardList = ExcelCardReader.buildCard(rowList);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InvalidFormatException ife) {
            ife.printStackTrace();
        } catch (ParseException pe) {
            pe.printStackTrace();
            return null;
        }
        return cardList;
    }

    //utility method to read a sheet into a List of comma
    //seperated rows
    public static List<String> readSheet(Sheet sheet) throws ParseException {
        if (sheet == null) throw new NullPointerException();
        List<String> rowList = new ArrayList<>();
        // Skip first row of Headers
        int rowStart = sheet.getFirstRowNum() + 1;
        int rowEnd = sheet.getLastRowNum() + 1;
        //System.out.println("rowEnd = " + rowEnd);
        String rowStr = null;
        for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
            Row r = sheet.getRow(rowNum);
            if (r == null) {
                continue;
            }
            int MY_MINIMUM_COLUMN_COUNT = 4;
            int lastColumn = Math.max(r.getLastCellNum(), MY_MINIMUM_COLUMN_COUNT);
            String cellContent = "";
            for (int cn = 0; cn < lastColumn; cn++) {
                //cellContent = "";
                Cell c = r.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                if (c == null) {
                    cellContent += "";
                } else {
                    if (c.getCellTypeEnum() == CellType.STRING)
                        cellContent += c.getStringCellValue();
                    else if (c.getCellTypeEnum() == CellType.NUMERIC)
                        cellContent += ((int) c.getNumericCellValue());
                }
                //keep appending the seperator but for the last col
                if (cn < lastColumn - 1) cellContent += ",";
            }
            //System.out.println("cellContent = " + cellContent);
            rowList.add(cellContent);
        }
        return rowList;
    }

    /*
    Takes in pre-validated comma-delimited rows and converts them into a list of Card objects.
    Values in each row correspond respectively to id,text,description and max_len
    @throws NullPointerException if cardRows is null
     */
    public static List<Card> buildCard(List<String> cardRows) {
        if (cardRows == null) throw new NullPointerException();
        List<Card> cardList = new ArrayList<Card>();
        String[] tokens;
        Card card = null;
        for (String row : cardRows) {
            tokens = row.split(",");
            card = new Card();
            card.setId(tokens[0]);
            card.setText(tokens[1]);
            card.setDescription(tokens[2]);
            card.setMax_len(Integer.valueOf(tokens[3]));
            cardList.add(card);
        }
        return cardList;
    }

}
