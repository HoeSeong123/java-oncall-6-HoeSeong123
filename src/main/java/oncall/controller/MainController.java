package oncall.controller;

import static oncall.util.RetryUtil.read;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oncall.model.DayOfWeek;
import oncall.util.Util;
import oncall.view.InputView;

public class MainController {
    public void run() {
        List<Integer> monthAndDay = readMonthAndDay();
        Map<String, List<String>> employee = read(this::readEmployee);
    }

    private List<Integer> readMonthAndDay() {
        List<Integer> monthAndDay = new ArrayList<>();
        List<String> monthAndDayStr = read(InputView::readMonthAndStartDay);

        monthAndDay.add(Util.convertToInt(monthAndDayStr.get(0)));
        monthAndDay.add(DayOfWeek.getNumberByName(monthAndDayStr.get(1)));

        return monthAndDay;
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
