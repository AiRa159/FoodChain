package chain;

public class Report {

    /**
     * Message of report
     */
    private String message;

    /**
     * Constructor for creating a new report
     * @param message
     */
    public Report(String message) {
        this.message = message;
    }

    /**
     * @return report message
     */
    @Override
    public String toString(){
        return this.message + "\n";
    }
}
