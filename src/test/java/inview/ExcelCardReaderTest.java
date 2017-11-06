package inview;

import inview.translationcard.Card;
import inview.translationcard.CardWriter;
import inview.translationcard.ExcelCardReader;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class ExcelCardReaderTest {

    URL fileUrl;

    public ExcelCardReaderTest() {
        //sample date file
        fileUrl = this.getClass().getResource("/CardSampleData.xls");

    }

    @BeforeClass
    public static void setUp() {
    }

    @Test
    public void testRead() {
        List<Card> cardList = ExcelCardReader.read(fileUrl.getPath());
        //System.out.println("cardList.toString() = " + cardList.toString());
        CardWriter.writeCards(cardList);
        assertTrue(cardList.size() == 3);
    }
}
