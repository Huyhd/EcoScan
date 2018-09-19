package app.creatingminds.ecoscan.utils;

import java.util.concurrent.TimeUnit;

/**
 * Created by tom on 9/19/18.
 */
public final class DateUtils {
    public static String getDayDiff(long timestampFrom, long timestampTo) {
        long diff = timestampTo - timestampFrom;

        return String.valueOf(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
    }
}
