package datatime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateForatThreadLocal {

    private static final ThreadLocal<DateFormat> df = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    public static Date convert(String source) throws ParseException {
        return df.get().parse(source);
    }
}
