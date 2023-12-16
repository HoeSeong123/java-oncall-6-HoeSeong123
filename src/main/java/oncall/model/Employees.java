package oncall.model;

import java.util.List;
import java.util.Map;

public class Employees {
    private Map<Week, List<String>> employees;
    private int weekdayIndex;
    private int weekendIndex;

    public Employees(Map<Week, List<String>> employees) {
        this.employees = employees;
        weekdayIndex = 0;
        weekendIndex = 0;
    }

    public String getWeekdayEmployee() {
        List<String> weekdayEmployee = employees.get(Week.WEEKDAY);
        String employee = weekdayEmployee.get(weekdayIndex);
        weekdayIndex = (weekdayIndex + 1) % weekdayEmployee.size();

        return employee;
    }

    public String getWeekendEmployee() {
        List<String> weekendEmployee = employees.get(Week.HOLIDAY);
        String employee = weekendEmployee.get(weekendIndex);
        weekendIndex = (weekendIndex + 1) % weekendEmployee.size();

        return employee;
    }
}
