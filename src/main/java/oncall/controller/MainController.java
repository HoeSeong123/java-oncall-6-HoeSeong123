package oncall.controller;

import static oncall.util.RetryUtil.read;

import java.util.List;
import oncall.view.InputView;

public class MainController {
    public void run() {
        List<String> monthAndDay = read(InputView::readMonthAndStartDay);
        List<String> weekdayEmployee = read(InputView::readWeekdayEmployee);
    }
}
