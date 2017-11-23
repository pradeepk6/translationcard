package inview.translationcard.cardreader;

import com.fasterxml.jackson.databind.ObjectMapper;
import inview.translationcard.Card;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CardReader {

    public List<Card> readJavaSerObjCards(String path) throws
            IOException, ClassNotFoundException {
        Path dir = Paths.get(path);
        List<Card> cardList = new ArrayList<Card>();
        ObjectInputStream oin = null;
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir, "*.ser")) {
            for (Path p : directoryStream) {
                oin = new ObjectInputStream(new FileInputStream(p.toFile()));
                cardList.add((Card) oin.readObject());
            }
        }
        return cardList;
    }

    public List<Card> readJsonCards(String path) throws
            IOException, ClassNotFoundException {
        List<Card> cardList = new ArrayList<Card>();
        Path dir = Paths.get(path);
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir, "*.json")) {
            for (Path p : directoryStream) {
                ObjectMapper mapper = new ObjectMapper();
                Card card = mapper.readValue(new FileReader(p.toFile()), Card.class);
                cardList.add(card);
            }
        }
        return cardList;
    }
}
