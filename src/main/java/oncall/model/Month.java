package oncall.model;

import static oncall.util.message.ExceptionMessage.INVALID_MONTH;

import java.util.Arrays;

public enum Month {

    JANUARY(1, 31),
    FEBRUARY(2, 28),
    MARCH(3, 31),
    APRIL(4, 30),
    MAY(5, 31),
    JUNE(6, 30),
    JULY(7, 31),
    AUGUST(8, 31),
    SEPTEMBER(9, 30),
    OCTOBER(10, 31),
    NOVEMBER(11, 30),
    DECEMBER(12, 31);

    private int month;
    private int maxDate;

    Month(int month, int maxDate) {
        this.month = month;
        this.maxDate = maxDate;
    }

    public int getMaxDate() {
        return maxDate;
    }

    public static int getMaxDateByMonth(int month) {
        return Arrays.stream(values())
                .filter(value -> value.month == month)
                .findFirst()
                .map(Month::getMaxDate)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_MONTH.get()));

    }
}
