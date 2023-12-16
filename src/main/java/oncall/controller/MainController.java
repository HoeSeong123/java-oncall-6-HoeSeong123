package oncall.controller;

import static oncall.util.RetryUtil.read;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oncall.model.CustomCalendar;
import oncall.model.CustomDate;
import oncall.model.DayOfWeek;
import oncall.repository.PublicHolidayRepository;
import oncall.view.InputView;
import oncall.view.OutputView;

public class MainController {
    public void run() {
        CustomCalendar customCalendar = createCustomCalendar();
        Map<String, List<String>> employee = read(this::readEmployee);

        List<String> result = assignEmployee(customCalendar, employee);

        OutputView.printResult(customCalendar, result);
    }

    private CustomCalendar createCustomCalendar() {
        List<String> monthAndDay = read(InputView::readMonthAndStartDay);

        return new CustomCalendar(monthAndDay.get(0), monthAndDay.get(1));
    }

    private Map<String, List<String>> readEmployee() {
        Map<String, List<String>> employee = new HashMap<>();

        List<String> weekdayEmployee = read(InputView::readWeekdayEmployee);
        List<String> weekEndEmployee = InputView.readWeekendEmployee(weekdayEmployee);

        employee.put("평일", weekdayEmployee);
        employee.put("휴일", weekEndEmployee);

        return employee;
    }

    private List<String> assignEmployee(CustomCalendar customCalendar, Map<String, List<String>> employee) {
        List<String> result = new ArrayList<>();
        List<String> weekdayEmployee = employee.get("평일");
        List<String> weekendEmployee = employee.get("휴일");
        int weekdayIndex = 0;
        int weekendIndex = 0;

        for (int i = 0; i < customCalendar.getCalendar().size(); i++) {
            CustomDate date = customCalendar.getCalendar().get(i);

            if (DayOfWeek.isWeekday(date.getDayOfWeek())) {
                result.add(weekdayEmployee.get(weekdayIndex));
                weekdayIndex = (weekdayIndex + 1) % weekdayEmployee.size();
                continue;
            }
            if (!DayOfWeek.isWeekday(date.getDayOfWeek()) || PublicHolidayRepository.isHoliday(date)) {
                result.add(weekendEmployee.get(weekendIndex));
                weekendIndex = (weekendIndex + 1) % weekendEmployee.size();
            }
        }

        checkDouble(result);

        return result;
    }

    private void checkDouble(List<String> result) {
        for (int i = 0; i < result.size() - 1; i++) {
            String previousEmployee = result.get(i);
            String currentEmployee = result.get(i + 1);

            if (previousEmployee.equals(currentEmployee)) {
                Collections.swap(result, i + 1, i + 2);
            }
        }
    }
}
