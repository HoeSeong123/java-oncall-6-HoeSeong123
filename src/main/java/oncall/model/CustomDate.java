package oncall.model;

public class CustomDate {
    private int month;
    private int date;
    private String dayOfWeek;

    public CustomDate(int month, int date, String dayOfWeek) {
        this.month = month;
        this.date = date;
        this.dayOfWeek = dayOfWeek;
    }

    public CustomDate(int month, int date) {
        this.month = month;
        this.date = date;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }

    public boolean equals(CustomDate customDate) {
        return customDate.month == this.month && customDate.date == this.date;
    }

}

