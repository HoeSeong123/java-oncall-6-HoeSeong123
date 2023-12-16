package oncall.util.validator;

import static oncall.util.message.ExceptionMessage.INVALID_INPUT;

import java.util.Objects;
import java.util.regex.Pattern;

public class Validator {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+");

    public static void validateBlank(String value) {
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException(INVALID_INPUT.get());
        }
    }

    public static void validateNumeric(String value) {
        if (!isNumber(value)) {
            throw new IllegalArgumentException(INVALID_INPUT.get());
        }
    }

    public static void validateIntegerRange(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_INPUT.get());
        }
    }

    public static void validateAvailableForm(String substring, String input) {
        if (isDuplicateSubstring(substring, input)
                || isStartWithSubstring(substring, input)
                || isEndWithSubstring(substring, input)) {
            throw new IllegalArgumentException(INVALID_INPUT.get());
        }
    }

    private static boolean isDuplicateSubstring(String substring, String input) {
        return containsDuplicateSubstring(substring, input);
    }

    private static boolean isStartWithSubstring(String substring, String input) {
        return input.startsWith(substring);
    }

    private static boolean isEndWithSubstring(String substring, String input) {
        return input.endsWith(substring);
    }

    private static boolean isNumber(String value) {
        return NUMBER_PATTERN.matcher(value).matches();
    }

    private static boolean containsDuplicateSubstring(String substring, String input) {
        String doubleSubstring = substring.repeat(2);
        return input.contains(doubleSubstring);
    }
}
