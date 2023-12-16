package oncall.controller;

import static oncall.util.RetryUtil.read;

import oncall.view.InputView;

public class MainController {
    public void run() {
        read(InputView::readMonthAndStartDay);
    }
}
