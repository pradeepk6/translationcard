package inview;

import inview.translationcard.Card;
import inview.translationcard.CardReader;
import org.junit.BeforeClass;
import org.junit.Test;

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
        cardList = CardReader.readCards("src/test/resources");
    }
    @Test
    public void testReadAllCardsinDir() {
        assertTrue(cardList.size() == 3);
    }
}
