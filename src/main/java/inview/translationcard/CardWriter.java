package inview.translationcard;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class CardWriter {

    public CardWriter() {
    }

    public static void writeCards(List<Card> cardList) {
        try {
            for (Card card : cardList) {
                try (ObjectOutputStream ous =
                             new ObjectOutputStream(new FileOutputStream(card.getId() + ".ser"))) {
                    ous.writeObject(card);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
