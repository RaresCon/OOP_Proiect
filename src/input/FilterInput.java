package input;

public final class FilterInput {
    private SortInput sort;
    private ContainsInput contains;

    /**
     * getter
     * @return the sorting filters
     */
    public SortInput getSort() {
        return sort;
    }

    /**
     * getter
     * @return the containing filters
     */
    public ContainsInput getContains() {
        return contains;
    }
}
