package inview.translationcard.cardwriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import inview.translationcard.Card;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class CardWriter {

    String outputDir = System.getProperty("user.dir") + "CardArchive";

    public void writeCardsAsJavaSerObjs(List<Card> cardList) throws IOException {
        for (Card card : cardList) {
            try (ObjectOutputStream ous =
                         new ObjectOutputStream(new FileOutputStream(outputDir + card.getId() + ".ser"))) {
                ous.writeObject(card);
            }
        }
    }

    public void writeCardsAsJson(List<Card> cardList) throws IOException {
        for (Card card : cardList) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(outputDir + card.getId() + ".json"), card);
        }
    }
}
