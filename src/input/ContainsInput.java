package input;

import java.util.List;

public final class ContainsInput {
    private List<String> actors;
    private List<String> genre;

    /**
     * getter
     * @return actors for filtering
     */
    public List<String> getActors() {
        return actors;
    }

    /**
     * getter
     * @return genres for filtering
     */
    public List<String> getGenre() {
        return genre;
    }
}
