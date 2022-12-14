package input;

import site.account.Credentials;

public class ActionInput {
    private String type;
    private String page;
    private String feature;
    private FilterInput filters;
    private Credentials credentials;
    private String startsWith;
    private int count;
    private String objectType;
    private String movie;
    private int rate;

    public String getType() {
        return type;
    }

    public String getPage() {
        return page;
    }

    public String getFeature() {
        return feature;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public int getCount() {
        return count;
    }

    public String getObjectType() {
        return objectType;
    }

    public String getMovie() {
        return movie;
    }

    public int getRate() {
        return rate;
    }

    public FilterInput getFilters() {
        return filters;
    }
}
