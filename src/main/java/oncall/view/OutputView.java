package oncall.view;

import static oncall.util.message.GlobalMessage.SPACE;

import java.util.List;
import oncall.model.CustomCalendar;
import oncall.model.CustomDate;
import oncall.model.DayOfWeek;
import oncall.repository.PublicHolidayRepository;

public class OutputView {
    private static final String MONTH = "월";
    private static final String DATE = "일";
    private static final String HOLIDAY = "(휴일)";

    private OutputView() {
    }

    public static void printExceptionMessage(String message) {
        System.out.println(message);
    }

    public static void printResult(CustomCalendar customCalendar, List<String> result) {
        List<CustomDate> customDates = customCalendar.getCalendar();
        System.out.println(result.size());
        for (int i = 0; i < result.size() - 1; i++) {
            System.out.println(formatOutput(customDates.get(i), result.get(i)));
        }
    }

    private static String formatOutput(CustomDate customDate, String employee) {
        StringBuilder sb = new StringBuilder();
        sb.append(customDate.getMonth() + MONTH + SPACE.get());
        sb.append(customDate.getDate() + DATE);
        if (PublicHolidayRepository.isHoliday(customDate) && DayOfWeek.isWeekday(customDate.getDayOfWeek())) {
            sb.append(HOLIDAY);
        }

        sb.append(SPACE.get() + customDate.getDayOfWeek());
        sb.append(SPACE.get() + employee);

        return sb.toString();
    }

}