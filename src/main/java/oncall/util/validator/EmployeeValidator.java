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
    }

    private static void validateDuplicate(List<String> input) {
        List<String> distinctElements = input.stream()
                .distinct()
                .toList();

        if (input.size() != distinctElements.size()) {
            throw new IllegalArgumentException(INVALID_INPUT.get());
        }
    }
}
