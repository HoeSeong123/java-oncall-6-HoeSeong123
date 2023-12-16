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
import oncall.model.Week;
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
        Map<Week, List<String>> employee = new HashMap<>();

        List<String> weekdayEmployee = read(InputView::readWeekdayEmployee);
        List<String> weekEndEmployee = InputView.readWeekendEmployee(weekdayEmployee);

        employee.put(Week.WEEKDAY, weekdayEmployee);
        employee.put(Week.HOLIDAY, weekEndEmployee);

        return new Employees(employee);
    }

    private List<String> assignEmployee(CustomCalendar customCalendar, Employees employees) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < customCalendar.getCalendar().size(); i++) {
            String employee = findEmployee(customCalendar, employees, i);
            result.add(employee);
        }

        checkDouble(customCalendar, result);

        return result;
    }

    private String findEmployee(CustomCalendar customCalendar, Employees employees, int index) {
        CustomDate date = customCalendar.getDate(index);
        if (isHoliday(date)) {
            return employees.getWeekendEmployee();
        }
        return employees.getWeekdayEmployee();
    }

    private boolean isHoliday(CustomDate date) {
        return !DayOfWeek.isWeekday(date.getDayOfWeek()) || PublicHolidayRepository.isHoliday(date);
    }

    private void checkDouble(CustomCalendar customCalendar, List<String> result) {
        for (int i = 1; i < result.size(); i++) {
            CustomDate customDate = customCalendar.getDate(i);
            String previousEmployee = result.get(i - 1);
            String currentEmployee = result.get(i);

            if (previousEmployee.equals(currentEmployee)) {
                int nextEmployeeIndex = i;
                if (isHoliday(customDate)) {
                    nextEmployeeIndex = customCalendar.findNextHoliday(i);
                }
                if (!isHoliday(customDate)) {
                    nextEmployeeIndex = customCalendar.findNextWeekday(i);
                }
                Collections.swap(result, i, nextEmployeeIndex);
            }
        }
    }
}
