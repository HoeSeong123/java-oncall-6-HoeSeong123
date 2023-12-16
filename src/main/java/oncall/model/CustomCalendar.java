package oncall.model;

import java.util.ArrayList;
import java.util.List;

public class CustomCalendar {
    private List<String> calendar;

    public CustomCalendar(int month, String dayOfWeek) {
        createCalendar(month, dayOfWeek);
    }

    public void createCalendar(int month, String dayOfWeek) {
        List<String> dayOfWeeks = DayOfWeek.getDayOfWeeks();
        int index = DayOfWeek.getNumberByName(dayOfWeek);

        for (int i = 0; i < Month.getMaxDateByMonth(month); i++) {
            calendar.add(dayOfWeeks.get(index));
            index = (index + 1) % 7;
        }
    }
}
