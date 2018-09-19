package app.creatingminds.ecoscan.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by tom on 9/15/18.
 */
public final class FormatUtils {
    private static final DateFormat NORMAL_DATE_FORMATTER = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
    private static final DateFormat SHORT_MONTH_DATE_FORMATTER = new SimpleDateFormat("dd MMM", Locale.US);

    /**
     * Format timestamp to date string with format yyyy/MM/dd
     *
     * @param timestamp
     * @return
     */
    public static String formatDate(long timestamp) {
        return NORMAL_DATE_FORMATTER.format(timestamp);
    }

    public static String formatDateShortMonth(long timestamp) {
        return SHORT_MONTH_DATE_FORMATTER.format(timestamp);
    }

    public static long convertDateToTimestamp(String date) {
        try {
            return NORMAL_DATE_FORMATTER.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Convert string with space to "_"
     *
     * @param input
     * @return
     */
    public static String stringify(String input) {
        input = input.toLowerCase();
        return input.replace(" ", "_");
    }

    public static String capitalizeWords(String input) {
        StringBuilder sb = new StringBuilder(input);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }
}
