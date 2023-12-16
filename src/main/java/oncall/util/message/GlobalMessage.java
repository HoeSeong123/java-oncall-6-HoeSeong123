package oncall.util.message;

public enum GlobalMessage {

    NEW_LINE(System.lineSeparator()),
    BLANK_AND_NEW_LINE(System.lineSeparator().repeat(2)),
    BAR("|"),
    HYPHEN("-"),
    COMMA(","),
    SPACE(" "),
    NO_SPACE(""),
    COLON(":"),
    SQUARE_BRACKETS_START("["),
    SQUARE_BRACKETS_END("]");

    private final String message;

    GlobalMessage(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }
}