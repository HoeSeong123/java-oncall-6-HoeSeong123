package oncall.repository;

import java.util.ArrayList;
import java.util.List;
import oncall.model.DayOfWeek;
import oncall.model.Month;

public class CalendarRepository {
    public static final List<String> calendar = new ArrayList<>();

    public static void createCalendar(int month, String dayOfWeek) {
        List<String> dayOfWeeks = DayOfWeek.getDayOfWeeks();
        int index = DayOfWeek.getNumberByName(dayOfWeek);

        for (int i = 0; i < Month.getMaxDateByMonth(month); i++) {
            calendar.add(dayOfWeeks.get(index));
            index = (index + 1) % 7;
        }
    }
}
