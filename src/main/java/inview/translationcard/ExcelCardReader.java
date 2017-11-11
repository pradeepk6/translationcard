package inview.translationcard;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelCardReader {

    private static List<Card> cardList;
    private static int CARD_COL_COUNT = 4;

    public ExcelCardReader() {
    }

    public List<Card> read(String filePath) throws Exception {
        cardList = new ArrayList<>();
        try (Workbook wb = WorkbookFactory.create(new File(filePath))) {
            Sheet sheet = wb.getSheetAt(0);
            cardList = readSheet(sheet);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return cardList;
    }

    //read a sheet into a List of Card objects
    public List<Card> readSheet(Sheet sheet) {
        if (sheet == null) throw new NullPointerException();
        // Skip first row of Headers
        int rowStart = sheet.getFirstRowNum() + 1;
        int rowEnd = sheet.getLastRowNum() + 1;
        Card card;
        Row r;

        for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
            r = sheet.getRow(rowNum);
            //if row is null or blank
            if (r == null) {
                continue;
            }
            //set lastcell to be traversed for rows with varying num of cols
            int lastColumn = Math.min(r.getLastCellNum(), CARD_COL_COUNT);
            if (isRowBlank(r, lastColumn)) {
                continue;
            }
            card = new Card();

            for (int cn = 0; cn < lastColumn; cn++) {
                Cell c = r.getCell(cn, Row.MissingCellPolicy.RETURN_NULL_AND_BLANK);
                if (cn == 0) { //process col 'ID'
                    if (c.getCellTypeEnum() == CellType.STRING)
                        card.setId(c.getStringCellValue());
                    else if (c.getCellTypeEnum() == CellType.NUMERIC)
                        card.setId(String.valueOf((int) (c.getNumericCellValue())));
                    else {
                        //id is assumed to be mandaorty
                        //and so skip the row as id cannot be read
                        card = null;
                        break;
                    }
                } else if (cn == 1) { //process col 'TEXT'
                    if (c.getCellTypeEnum() == CellType.STRING)
                        card.setText(c.getStringCellValue());
                } else if (cn == 2) { //process col 'DESCRIPTION'
                    if (c.getCellTypeEnum() == CellType.STRING)
                        card.setDescription(c.getStringCellValue());
                    else if (c.getCellTypeEnum() == CellType.BLANK)
                        card.setDescription("");
                } else if (cn == 3) { //process col 'LENGTH'
                    //length has been delcared as a constant in class 'Card' and
                    // so no need to process it.
                    //if (c.getCellTypeEnum() == CellType.NUMERIC)
                    //card.setMax_len( (int)c.getNumericCellValue() );
                }
            }
            if (card != null) cardList.add(card);
        }
        return cardList;
    }

    //check if all cols in the row are blank
    private boolean isRowBlank(Row r, int colCount) {
        boolean bool = true;
        for (int i = 0; i < colCount; i++) {
            Cell cell = r.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            if (!(cell.getCellTypeEnum() == CellType.BLANK))
                return false;
        }
        return bool;
    }

}
