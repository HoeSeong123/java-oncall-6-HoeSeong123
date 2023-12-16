package oncall.model;

public enum Week {

    WEEKDAY("평일"),
    HOLIDAY("휴일");

    private String name;

    Week(String name) {
        this.name = name;
    }
}
