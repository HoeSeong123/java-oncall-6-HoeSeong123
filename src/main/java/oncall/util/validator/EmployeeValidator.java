package oncall.util.validator;

import static oncall.util.message.ExceptionMessage.INVALID_INPUT;

import java.util.List;
import oncall.util.Util;

public class EmployeeValidator {
    public static void validate(String substring, String input) {
        Validator.validateBlank(input);
        Validator.validateAvailableForm(substring, input);
        List<String> employee = Util.splitToList(substring, input);
        validateDuplicate(employee);
        validateSize(employee);
        employee.forEach(EmployeeValidator::validateNameSize);
    }

    private static void validateDuplicate(List<String> input) {
        List<String> distinctElements = input.stream()
                .distinct()
                .toList();

        if (input.size() != distinctElements.size()) {
            throw new IllegalArgumentException(INVALID_INPUT.get());
        }
    }

    private static void validateSize(List<String> input) {
        if (input.size() < 5 || input.size() > 35) {
            throw new IllegalArgumentException(INVALID_INPUT.get());
        }
    }

    private static void validateNameSize(String input) {
        if (input.isEmpty() || input.length() > 5) {
            throw new IllegalArgumentException(INVALID_INPUT.get());
        }
    }
}
