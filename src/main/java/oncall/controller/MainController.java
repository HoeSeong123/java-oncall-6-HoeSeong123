package oncall.controller;

import static oncall.util.RetryUtil.read;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oncall.model.CalendarMaker;
import oncall.model.DayOfWeek;
import oncall.util.Util;
import oncall.view.InputView;

public class MainController {
    public void run() {
        List<String> customCalendar = createCustomCalendar();
        Map<String, List<String>> employee = read(this::readEmployee);

        assignEmployee(customCalendar, employee);
    }

    private List<String> createCustomCalendar() {
        List<String> monthAndDay = read(InputView::readMonthAndStartDay);

        return CalendarMaker.createCalendar(Util.convertToInt(monthAndDay.get(0)), monthAndDay.get(1));
    }

    private Map<String, List<String>> readEmployee() {
        Map<String, List<String>> employee = new HashMap<>();

        List<String> weekdayEmployee = read(InputView::readWeekdayEmployee);
        List<String> weekEndEmployee = InputView.readWeekendEmployee(weekdayEmployee);

        employee.put("평일", weekdayEmployee);
        employee.put("휴일", weekEndEmployee);

        return employee;
    }

    private void assignEmployee(List<String> customCalendar, Map<String, List<String>> employee) {
        List<String> result = new ArrayList<>();
        List<String> weekdayEmployee = employee.get("평일");
        List<String> weekendEmployee = employee.get("휴일");
        int weekdayIndex = 0;
        int weekendIndex = 0;

        for (String dayOfWeek : customCalendar) {
            if (DayOfWeek.isWeekday(dayOfWeek)) {
                result.add(weekdayEmployee.get(weekdayIndex));
                weekdayIndex = (weekdayIndex + 1) % weekdayEmployee.size();
            }
            if (!DayOfWeek.isWeekday(dayOfWeek)) {
                result.add(weekendEmployee.get(weekendIndex));
                weekendIndex = (weekendIndex + 1) % weekendEmployee.size();
            }
        }

        System.out.println(result);
    }
}
