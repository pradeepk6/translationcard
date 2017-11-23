package inview.cardreader;

import inview.translationcard.Card;
import inview.translationcard.cardreader.CardReader;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CardReaderTest {

    URL resourcesDirectory;
    static List<Card> cardList;

    public CardReaderTest() {
    }

    @BeforeClass
    public static void setUp() {
        try {
            CardReader cardReader = new CardReader();
            cardList = cardReader.readJavaSerObjCards("src/test/resources");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
    @Test
    public void testReadAllCardsinDir() {
        assertTrue(cardList.size() == 3);
    }
}
