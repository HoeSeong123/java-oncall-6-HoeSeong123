package oncall.util.message;

public enum ExceptionMessage {

    INVALID_INPUT("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    INVALID_DAY_OF_WEEK("존재하지 않는 요일입니다.");

    public static final String BASE_MESSAGE = "[ERROR] %s";
    private final String message;

    ExceptionMessage(String message) {
        this.message = String.format(BASE_MESSAGE, message);
    }

    public String get() {
        return message;
    }
}
