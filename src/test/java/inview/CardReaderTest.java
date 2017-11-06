package inview;

import inview.translationcard.Card;
import inview.translationcard.CardReader;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CardReaderTest {

    URL resourcesDirectory;

    public CardReaderTest() {
        //fileUrl = this.getClass().getResource("/1A.ser");
        File resourcesDirectory = new File("src/test/resources");
    }

    @Test
    public void testCardRead() {
        List<Card> cardList = CardReader.readCards("src/test/resources");
        //System.out.println("cardList = " + cardList);
        Card card = cardList.get(0);
        assertTrue(cardList.size() == 3);
        assertTrue(card.getId().equals("1A"));
    }
}
