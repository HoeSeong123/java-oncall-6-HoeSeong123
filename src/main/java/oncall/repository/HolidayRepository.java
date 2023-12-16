package oncall.repository;

import java.util.List;
import oncall.model.CustomDate;

public class HolidayRepository {
    public static final List<CustomDate> holidays = List.of(
            new CustomDate(1, 1),
            new CustomDate(3, 1),
            new CustomDate(5, 5),
            new CustomDate(6, 6),
            new CustomDate(8, 15),
            new CustomDate(10, 3),
            new CustomDate(10, 9),
            new CustomDate(12, 25)
    );


}
