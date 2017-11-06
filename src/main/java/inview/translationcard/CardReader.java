package inview.translationcard;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CardReader {

    public CardReader() {
    }

    public static List<Card> readCards(String path) {

        List<Card> cardList = new ArrayList<Card>();
        Path dir = Paths.get(path);
        ObjectInputStream oin = null;
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir, "*.ser")) {
            for (Path p : directoryStream) {
                oin = new ObjectInputStream(new FileInputStream(p.toFile()));
                cardList.add((Card) oin.readObject());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        return cardList;
    }
}
