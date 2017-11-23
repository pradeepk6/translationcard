package inview.translationcard.cardbuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import inview.translationcard.Card;

import java.io.IOException;
import java.util.List;

public class CardBuilder {

    List cardList;

    public void buildCardListFromCSV(List<String> rows) {
        for (String s : rows) {
            String[] tokens = s.split(",");
            Card card = new Card();
            card.setId(tokens[0]);
            card.setText(tokens[1]);
            card.setDescription(tokens[2]);
            cardList.add(card);

        }
    }

    public void buildCardJsonListFromCSV(List<String> rows) {
        ObjectMapper mapper = new ObjectMapper();

        for (String s : rows) {
            String[] tokens = s.split(",");
            ObjectNode objNode = mapper.createObjectNode();
            objNode.put("Id", tokens[0]);
            objNode.put("Text", tokens[1]);
            objNode.put("Description", tokens[2]);
            cardList.add(objNode);
        }
    }

    public void buildCardFromJsonArray(String jsonArray) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        cardList = mapper.readValue(jsonArray, new TypeReference<List<Card>>() {
        });
    }

    public void buildCardFromJsonList(List<String> jsonRows) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        for (String s : jsonRows) {
            Card card = mapper.readValue(s, Card.class);
            cardList.add(card);
        }
    }

    public List getCardList() {
        return cardList;
    }

}
