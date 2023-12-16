package oncall.view;

import static oncall.util.message.GlobalMessage.COMMA;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import oncall.util.Util;
import oncall.util.validator.MonthDayValidator;
import oncall.util.validator.WeekdayEmployeeValidator;
import oncall.util.validator.WeekendEmployeeValidator;

public class InputView {
    private InputView() {
    }

    public static List<String> readMonthAndStartDay() {
        System.out.print(Message.INPUT_MONTH_START_DAY.message);
        String input = Console.readLine();
        MonthDayValidator.validate(COMMA.get(), input);

        return Util.splitToList(COMMA.get(), input);
    }

    public static List<String> readWeekdayEmployee() {
        System.out.print(Message.INPUT_WEEKDAY_EMPLOYEE.message);
        String input = Console.readLine();
        WeekdayEmployeeValidator.validate(COMMA.get(), input);

        return Util.splitToList(COMMA.get(), input);
    }

    public static List<String> readWeekendEmployee(List<String> weekdayEmployee) {
        System.out.print(Message.INPUT_WEEKEND_EMPLOYEE.message);
        String input = Console.readLine();
        WeekendEmployeeValidator.validate(COMMA.get(), input, weekdayEmployee);

        return Util.splitToList(COMMA.get(), input);
    }

    private enum Message {
        INPUT_MONTH_START_DAY("비상 근무를 배정할 월과 시작 요일을 입력하세요> "),
        INPUT_WEEKDAY_EMPLOYEE("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> "),

        INPUT_WEEKEND_EMPLOYEE("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");


        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}
