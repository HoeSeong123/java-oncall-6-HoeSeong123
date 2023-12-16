package oncall.util.validator;

import static oncall.util.message.ExceptionMessage.INVALID_INPUT;

import java.util.List;
import oncall.util.Util;

public class MonthDayValidator {
    public static void validate(String substring, String input) {
        Validator.validateAvailableForm(substring, input);
        List<String> splitInput = Util.splitToList(substring, input);
        validateSize(splitInput);

        String month = splitInput.get(0);
        Validator.validateBlank(month);
        Validator.validateNumeric(month);
        Validator.validateIntegerRange(month);
        validateMonth(Util.convertToInt(month));

        String day = splitInput.get(1);
        Validator.validateBlank(day);
        validateDay(day);
    }

    private static void validateSize(List<String> input) {
        if (input.size() != 2) {
            throw new IllegalArgumentException(INVALID_INPUT.get());
        }
    }

    private static void validateMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException(INVALID_INPUT.get());
        }
    }

    private static void validateDay(String day) {
        List<String> dayOfWeek = List.of("월", "화", "수", "목", "금", "토", "일");
        if (!dayOfWeek.contains(day)) {
            throw new IllegalArgumentException(INVALID_INPUT.get());
        }
    }
}
