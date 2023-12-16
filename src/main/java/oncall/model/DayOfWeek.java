package oncall.model;

import static oncall.util.message.ExceptionMessage.INVALID_DAY_OF_WEEK;

import java.util.Arrays;

public enum DayOfWeek {
    MONDAY(1, "월"),
    TUESDAY(2, "화"),
    WEDNESDAY(3, "수"),
    THURSDAY(4, "목"),
    FRIDAY(5, "금"),
    SATURDAY(6, "토"),
    SUNDAY(7, "일");

    private int number;
    private String name;

    DayOfWeek(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public static int getNumberByName(String name) {
        return Arrays.stream(values())
                .filter(day -> day.name.equals(name))
                .findFirst()
                .map(DayOfWeek::getNumber)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_DAY_OF_WEEK.get()));
    }
}
