package site.pages;

public enum Actions {

    LOGIN {
        @Override
        public void executeAction() {

        }
    };

    /**
     *
     * @param affectedRow
     * @return
     */
    public abstract void executeAction();
}
