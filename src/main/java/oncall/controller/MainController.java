package oncall.controller;

import static oncall.util.RetryUtil.read;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oncall.model.CustomCalendar;
import oncall.model.CustomDate;
import oncall.model.DayOfWeek;
import oncall.repository.HolidayRepository;
import oncall.view.InputView;

public class MainController {
    public void run() {
        CustomCalendar customCalendar = createCustomCalendar();
        Map<String, List<String>> employee = read(this::readEmployee);

        assignEmployee(customCalendar, employee);
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

    private void assignEmployee(CustomCalendar customCalendar, Map<String, List<String>> employee) {
        List<String> result = new ArrayList<>();
        List<String> weekdayEmployee = employee.get("평일");
        List<String> weekendEmployee = employee.get("휴일");
        int weekdayIndex = 0;
        int weekendIndex = 0;

        for (CustomDate dayOfWeek : customCalendar.getCalendar()) {
            if (HolidayRepository.isHoliday(dayOfWeek)) {
                result.add(weekendEmployee.get(weekendIndex));
                weekendIndex = (weekendIndex + 1) % weekendEmployee.size();
                continue;
            }

            if (DayOfWeek.isWeekday(dayOfWeek.getDayOfWeek())) {
                result.add(weekdayEmployee.get(weekdayIndex));
                weekdayIndex = (weekdayIndex + 1) % weekdayEmployee.size();
                continue;
            }
            if (!DayOfWeek.isWeekday(dayOfWeek.getDayOfWeek())) {
                result.add(weekendEmployee.get(weekendIndex));
                weekendIndex = (weekendIndex + 1) % weekendEmployee.size();
            }
        }

        System.out.println(result);
    }
}
