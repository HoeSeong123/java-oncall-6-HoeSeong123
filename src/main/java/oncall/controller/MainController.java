package oncall.controller;

import static oncall.util.RetryUtil.read;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oncall.model.CustomCalendar;
import oncall.model.DayOfWeek;
import oncall.util.Util;
import oncall.view.InputView;

public class MainController {
    public void run() {
        CustomCalendar customCalendar = createCustomCalendar();
        Map<String, List<String>> employee = read(this::readEmployee);
    }

    private CustomCalendar createCustomCalendar() {
        List<String> monthAndDay = read(InputView::readMonthAndStartDay);

        return new CustomCalendar(Util.convertToInt(monthAndDay.get(0)), monthAndDay.get(1));
    }

    private Map<String, List<String>> readEmployee() {
        Map<String, List<String>> employee = new HashMap<>();

        List<String> weekdayEmployee = read(InputView::readWeekdayEmployee);
        List<String> weekEndEmployee = InputView.readWeekendEmployee(weekdayEmployee);

        employee.put("평일", weekdayEmployee);
        employee.put("휴일", weekEndEmployee);

        return employee;
    }

}
