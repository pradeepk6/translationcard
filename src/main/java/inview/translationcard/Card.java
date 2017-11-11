package inview.translationcard;

import java.io.Serializable;

public class Card implements Serializable {

    private static final long serialVersionUID = 7526471155622776147L;
    private String id;
    private String text;
    private String description;
    public static final int MAX_LEN = 100;

    public Card() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (!id.equals(card.id)) return false;
        if (!text.equals(card.text)) return false;
        return description.equals(card.description);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", description='" + description + '\'' +
                ", MAX_LEN=" + MAX_LEN +
                '}';
    }
}
