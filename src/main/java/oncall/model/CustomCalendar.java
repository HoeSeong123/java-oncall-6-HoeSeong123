package oncall.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import oncall.util.Util;

public class CustomCalendar {
    private List<CustomDate> calendar;

    public CustomCalendar(String month, String dayOfWeek) {
        createCalendar(Util.convertToInt(month), dayOfWeek);
    }

    public void createCalendar(int month, String dayOfWeek) {
        calendar = new ArrayList<>();
        List<String> dayOfWeeks = DayOfWeek.getDayOfWeeks();
        int index = DayOfWeek.getNumberByName(dayOfWeek);

        for (int i = 0; i < Month.getMaxDateByMonth(month); i++) {
            calendar.add(new CustomDate(month, i + 1, dayOfWeeks.get(index)));
            index = (index + 1) % 7;
        }
    }

    public List<CustomDate> getCalendar() {
        return Collections.unmodifiableList(calendar);
    }
}
