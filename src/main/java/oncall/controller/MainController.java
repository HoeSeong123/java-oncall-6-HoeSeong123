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
import oncall.model.Employees;
import oncall.repository.PublicHolidayRepository;
import oncall.view.InputView;
import oncall.view.OutputView;

public class MainController {
    public void run() {
        CustomCalendar customCalendar = createCustomCalendar();
        Employees employee = read(this::readEmployee);

        List<String> result = assignEmployee(customCalendar, employee);

        OutputView.printResult(customCalendar, result);
    }

    private CustomCalendar createCustomCalendar() {
        List<String> monthAndDay = read(InputView::readMonthAndStartDay);

        return new CustomCalendar(monthAndDay.get(0), monthAndDay.get(1));
    }

    private Employees readEmployee() {
        Map<String, List<String>> employee = new HashMap<>();

        List<String> weekdayEmployee = read(InputView::readWeekdayEmployee);
        List<String> weekEndEmployee = InputView.readWeekendEmployee(weekdayEmployee);

        employee.put("평일", weekdayEmployee);
        employee.put("휴일", weekEndEmployee);

        return new Employees(employee);
    }

    private List<String> assignEmployee(CustomCalendar customCalendar, Employees employees) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < customCalendar.getCalendar().size(); i++) {
            String employee = findEmployee(customCalendar, employees, i);
            result.add(employee);
        }

        checkDouble(result);

        return result;
    }

    private String findEmployee(CustomCalendar customCalendar, Employees employees, int index) {
        CustomDate date = customCalendar.getCalendar().get(index);
        if (isHoliday(date)) {
            return employees.getWeekendEmployee();
        }
        return employees.getWeekdayEmployee();
    }

    private boolean isHoliday(CustomDate date) {
        return !DayOfWeek.isWeekday(date.getDayOfWeek()) || PublicHolidayRepository.isHoliday(date);
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
