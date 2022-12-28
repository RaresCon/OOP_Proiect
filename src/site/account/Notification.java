package site.account;

public record Notification(String movieName, String message) {
    public Notification(String movieName, String message) {
        this.movieName = movieName;
        this.message = message;
    }
}
