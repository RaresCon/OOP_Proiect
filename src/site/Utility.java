package site;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import site.account.Account;
import site.account.Credentials;
import site.movies.Movie;

import java.util.ArrayList;
import java.util.List;

public final class Utility {
    private Utility() {
    }

    /**
     * function to return the standard response in case of successful request or error
     * @param site the current site session
     * @param responseCode the response code given by the function that calls this
     * @return the response
     */
    public static ObjectNode response(final Database site, final ResponseCodes responseCode) {
        ObjectNode response = JsonNodeFactory.instance.objectNode();

        if (responseCode.equals(ResponseCodes.OK)) {
            response.put("error", (String) null);
            response.put("currentMoviesList", Utility.movieListOutput(site.getCurrentMoviesList()));
            response.put("currentUser", Utility.userOutput(site.getCurrentUser()));
        } else {
            response.put("error", "Error");
            response.replace("currentMoviesList", movieListOutput(new ArrayList<>()));
            response.put("currentUser", (String) null);
        }

        return response;
    }

    /**
     * function to transform a list of movies into JSON output
     * @param currentMovies the list of movies to be transformed
     * @return the JSON format output
     */
    public static ArrayNode movieListOutput(final List<Movie> currentMovies) {
        ArrayNode movieListOutput = JsonNodeFactory.instance.arrayNode();

        currentMovies.forEach((movie -> movieListOutput.add(movieOutput(movie))));

        return movieListOutput;
    }

    /**
     * function to transform a single movie into JSON output
     * @param movie the movie to be transformed
     * @return the JSON format output
     */
    private static ObjectNode movieOutput(final Movie movie) {
        ObjectNode movieOutput = JsonNodeFactory.instance.objectNode();
        ArrayNode genresOutput = JsonNodeFactory.instance.arrayNode();
        ArrayNode actorsOutput = JsonNodeFactory.instance.arrayNode();
        ArrayNode countriesBannedOutput = JsonNodeFactory.instance.arrayNode();

        movie.getGenres().forEach(genresOutput::add);
        movie.getActors().forEach(actorsOutput::add);
        movie.getCountriesBanned().forEach(countriesBannedOutput::add);

        movieOutput.put("name", movie.getName());
        movieOutput.put("year", movie.getYear());
        movieOutput.put("duration", movie.getDuration());

        movieOutput.replace("genres", genresOutput);
        movieOutput.replace("actors", actorsOutput);
        movieOutput.replace("countriesBanned", countriesBannedOutput);
        movieOutput.put("numLikes", movie.getNumLikes());
        movieOutput.put("rating", movie.getRating());
        movieOutput.put("numRatings", movie.getRatings().size());

        return movieOutput;
    }

    /**
     * function to transform a user into JSON output
     * @param user the user to be transformed
     * @return the JSON format output
     */
    public static ObjectNode userOutput(final Account user) {
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

    /**
     * function to transform credentials into JSON output
     * @param creds the credentials to be transformed
     * @return the JSON format output
     */
    private static ObjectNode credsOutput(final Credentials creds) {
        ObjectNode credsOutput = JsonNodeFactory.instance.objectNode();

        credsOutput.put("name", creds.getName());
        credsOutput.put("password", creds.getPassword());
        credsOutput.put("accountType", creds.getAccountType());
        credsOutput.put("country", creds.getCountry());
        credsOutput.put("balance", String.valueOf(creds.getBalance()));

        return credsOutput;
    }
}
