package inview.legacycardreader;

import inview.translationcard.Card;
import inview.translationcard.cardwriter.CardWriter;
import inview.translationcard.legacycardreader.ExcelCardReader;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
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

    @Test
    public void testNew() throws IOException {
        //CardWriter cardWriter = new CardWriter();
        //System.out.println("Present Project Directory : " + System.getProperty("user.dir"));
        CardWriter cardWriter = new CardWriter();
        cardWriter.writeCardsAsJson(cardList);
    }
}
