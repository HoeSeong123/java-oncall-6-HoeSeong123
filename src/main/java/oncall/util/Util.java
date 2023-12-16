package oncall.util;

import static oncall.util.message.GlobalMessage.NO_SPACE;
import static oncall.util.message.GlobalMessage.SPACE;
import static oncall.util.message.GlobalMessage.SQUARE_BRACKETS_END;
import static oncall.util.message.GlobalMessage.SQUARE_BRACKETS_START;

import java.util.Arrays;
import java.util.List;

public class Util {

    public static String removeSpace(String input) {
        return input.replaceAll(SPACE.get(), NO_SPACE.get());
    }

    public static String removeDelimiters(String input) {
        return input.replace(SQUARE_BRACKETS_START.get(), NO_SPACE.get())
                .replace(SQUARE_BRACKETS_END.get(), NO_SPACE.get());
    }

    public static List<String> splitToList(String delimiter, String input) {
        return Arrays.asList(Util.removeSpace(input).split(delimiter));
    }

    public static List<String> formatProductInfo(String delimiter, String input) {
        return Util.splitToList(delimiter, Util.removeDelimiters(Util.removeSpace(input)));
    }

    public static List<Integer> convertToInt(List<String> input) {
        return input.stream()
                .map(Util::convertToInt)
                .toList();
    }

    public static int convertToInt(String input) {
        return Integer.parseInt(input);
    }

    private Util() {
    }
}