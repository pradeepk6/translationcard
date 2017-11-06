package inview.translationcard;

import java.io.Serializable;

public class Card implements Serializable {

    private static final long serialVersionUID = 7526471155622776147L;
    private String id;
    private String text;
    private String description;
    private int max_len;

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

    public int getMax_len() {
        return max_len;
    }

    public void setMax_len(int max_len) {
        this.max_len = max_len;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", description='" + description + '\'' +
                ", max_len=" + max_len +
                '}';
    }
}
