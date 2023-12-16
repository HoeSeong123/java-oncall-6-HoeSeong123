package oncall.util.validator;

import static oncall.util.message.ExceptionMessage.INVALID_INPUT;

import java.util.List;
import oncall.util.Util;

public class WeekendEmployeeValidator {
    public static void validate(String substring, String input, List<String> weekdayEmployee) {
        WeekdayEmployeeValidator.validate(substring, input);
        validateIsSameEmployee(Util.splitToList(substring, input), weekdayEmployee);
    }

    private static void validateIsSameEmployee(List<String> weekendEmployee, List<String> weekdayEmployee) {
        if (!weekdayEmployee.containsAll(weekendEmployee) || !weekendEmployee.containsAll(weekdayEmployee)) {
            throw new IllegalArgumentException(INVALID_INPUT.get());
        }
    }
}
