package oncall.view;

import static oncall.util.message.GlobalMessage.SPACE;

import java.util.List;
import oncall.model.CustomCalendar;
import oncall.model.CustomDate;
import oncall.model.DayOfWeek;
import oncall.repository.PublicHolidayRepository;

public class OutputView {
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
        sb.append(customDate.getMonth() + "월 ");
        sb.append(customDate.getDate() + "일");
        if (PublicHolidayRepository.isHoliday(customDate) && DayOfWeek.isWeekday(customDate.getDayOfWeek())) {
            sb.append("(휴일)");
        }

        sb.append(SPACE.get() + customDate.getDayOfWeek());
        sb.append(SPACE.get() + employee);

        return sb.toString();
    }
}