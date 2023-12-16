package oncall.model;

import java.util.ArrayList;
import java.util.List;

public class CalendarMaker {

    public static List<String> createCalendar(int month, String dayOfWeek) {
        List<String> calendar = new ArrayList<>();
        List<String> dayOfWeeks = DayOfWeek.getDayOfWeeks();
        int index = DayOfWeek.getNumberByName(dayOfWeek);

        for (int i = 0; i < Month.getMaxDateByMonth(month); i++) {
            calendar.add(dayOfWeeks.get(index));
            index = (index + 1) % 7;
        }

        return calendar;
    }
}
