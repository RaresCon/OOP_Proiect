package site;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import site.account.Account;
import site.account.Credentials;
import site.movies.Movie;

import java.util.List;

public class Utility {
    public static ArrayNode movieListOutput(List<Movie> currentMovies) {
        ArrayNode movieListOutput = JsonNodeFactory.instance.arrayNode();

        currentMovies.forEach((movie -> movieListOutput.add(movieOutput(movie))));

        return movieListOutput;
    }

    private static ObjectNode movieOutput(Movie movie) {
        ObjectNode movieOutput = JsonNodeFactory.instance.objectNode();
        ArrayNode genresOutput = JsonNodeFactory.instance.arrayNode();
        ArrayNode actorsOutput = JsonNodeFactory.instance.arrayNode();
        ArrayNode countriesBanned = JsonNodeFactory.instance.arrayNode();

        movie.getGenres().forEach(genresOutput::add);
        movie.getActors().forEach(actorsOutput::add);
        movie.getBannedCountries().forEach(countriesBanned::add);

        movieOutput.put("name", movie.getName());
        movieOutput.put("year", movie.getYear());
        movieOutput.put("duration", movie.getDuration());

        movieOutput.replace("genres", genresOutput);
        movieOutput.replace("actors", actorsOutput);
        movieOutput.replace("countriesBanned", genresOutput);
        movieOutput.put("numLikes", movie.getNumLikes());
        movieOutput.put("rating", movie.getRating());
        movieOutput.put("numRatings", movie.getRatings().size());

        return movieOutput;
    }

    public static ObjectNode userOutput(Account user) {
        ObjectNode userOutput = JsonNodeFactory.instance.objectNode();

        userOutput.replace("credentials", credsOutput(user.getCreds()));
        userOutput.put("tokensCount", user.getTokensCount());
        userOutput.put("numFreePremiumMovies", user.getNumFreePremiumMovies());
        userOutput.replace("purchasedMovies", movieListOutput(user.getPurchasedMovies()));
        userOutput.replace("watchedMovies", movieListOutput(user.getWatchedMovies()));
        userOutput.replace("likedMovies", movieListOutput(user.getLikedMovies()));
        userOutput.replace("ratedMovies", movieListOutput(user.getRatedMovies()));

        return userOutput;
    }

    private static ObjectNode credsOutput(Credentials creds) {
        ObjectNode credsOutput = JsonNodeFactory.instance.objectNode();

        credsOutput.put("name", creds.getName());
        credsOutput.put("password", creds.getPasswordHash());
        credsOutput.put("accoutType", creds.getAccountType());
        credsOutput.put("country", creds.getCountry());
        credsOutput.put("balance", creds.getBalance());

        return credsOutput;
    }
}
