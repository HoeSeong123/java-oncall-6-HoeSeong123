package oncall.model;

import static oncall.util.message.ExceptionMessage.INVALID_DAY_OF_WEEK;

import java.util.Arrays;
import java.util.List;

public enum DayOfWeek {
    MONDAY(0, "월"),
    TUESDAY(1, "화"),
    WEDNESDAY(2, "수"),
    THURSDAY(3, "목"),
    FRIDAY(4, "금"),
    SATURDAY(5, "토"),
    SUNDAY(6, "일");

    private int number;
    private String name;

    DayOfWeek(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public static int getNumberByName(String name) {
        return Arrays.stream(values())
                .filter(day -> day.name.equals(name))
                .findFirst()
                .map(DayOfWeek::getNumber)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_DAY_OF_WEEK.get()));
    }

    public static List<String> getDayOfWeeks() {
        return Arrays.stream(values())
                .map(DayOfWeek::getName)
                .toList();
    }
}
