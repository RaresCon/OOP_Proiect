package input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties({"genre"})
public class ContainsInput {
    private List<String> actors;
    private List<String> genre;

    public List<String> getActors() {
        return actors;
    }
    public List<String> getGenres() {
        return genre;
    }
}
