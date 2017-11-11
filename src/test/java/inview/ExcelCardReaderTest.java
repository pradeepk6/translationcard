package inview;

import inview.translationcard.Card;
import inview.translationcard.ExcelCardReader;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class ExcelCardReaderTest {

    static URL fileUrl;
    static ExcelCardReader excelCardReader;
    static List<Card> cardList;

    public ExcelCardReaderTest() {
        fileUrl = this.getClass().getResource("/CardSampleData.xls");
        excelCardReader = new ExcelCardReader();
        try {
            cardList = excelCardReader.read(fileUrl.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public static void setUp() {
    }

    @Test
    public void testSkipEmptyAndInvalidRows() {
        assertTrue(cardList.size() == 4);
    }

    @Test
    public void testReadCardFromRow() {
        Card card = new Card();
        card.setId("5E");
        card.setText("Начало");
        card.setDescription("Start in Russian");
        assertEquals(cardList.get(2), card);
    }
}
