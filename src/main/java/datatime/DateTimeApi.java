package datatime;

import org.junit.Test;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

public class DateTimeApi {


    /**
     * LocalDate、LocalTime、LocalDateTime
     */
    @Test
    public void Test1(){
        LocalDateTime ldt1 = LocalDateTime.now();
        System.out.println(ldt1);

        LocalDateTime ldt2 = LocalDateTime.of(2019, 1, 2, 3, 4, 5);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt1.plusYears(3);
        System.out.println(ldt3);

        LocalDateTime ldt4 = ldt1.minusHours(4);
        System.out.println(ldt4);

        System.out.println(ldt1.getYear());
        System.out.println(ldt1.getMonthValue());
        System.out.println(ldt1.getDayOfMonth());
        System.out.println(ldt1.getHour());
        System.out.println(ldt1.getMinute());
        System.out.println(ldt1.getSecond());
        System.out.println(ldt1.getNano());
    }

    /**
     * instant：时间戳（与1970年1月1日所经历的毫秒值）
     */
    @Test
    public void Test2(){
        // 默认使用 UTC 时区
        Instant instant1 = Instant.now();
        System.out.println(instant1);/* 2019-08-20T06:40:45.303Z */

        OffsetDateTime odt = instant1.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt);/* 2019-08-20T14:40:45.303+08:00 */

        System.out.println(instant1.getNano());
        System.out.println(instant1.toEpochMilli());

        Instant instant2 = Instant.ofEpochSecond(5);
        System.out.println(instant2);/* 1970-01-01T00:00:05Z */

        Instant instant3 = Instant.ofEpochMilli(5);
        System.out.println(instant3);
    }


    /* Duration: 计算两个时间间隔 */
    @Test
    public void Test3() throws InterruptedException {
        Instant instant1 = Instant.now();
        Thread.sleep(1000);
        Instant instant2 = Instant.now();

        Duration duration1 = Duration.between(instant1, instant2);
        System.out.println(duration1);
        System.out.println(duration1.getSeconds());
        System.out.println(duration1.toMillis());
        System.out.println(duration1.getNano());

        LocalDate ld1 = LocalDate.now();
        LocalDate ld2 = LocalDate.of(2010, 2, 2);
        Period period1 = Period.between(ld2, ld1);
        System.out.println(period1);
        System.out.println(period1.getYears());
        System.out.println(period1.getMonths());
        System.out.println(period1.getDays());
    }


    @Test
    public void Test4(){
        LocalDateTime ldt1 = LocalDateTime.now();
        System.out.println(ldt1);

        LocalDateTime ldt2 = ldt1.withDayOfMonth(10);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt1.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        System.out.println(ldt3);

        LocalDateTime ldt4 = ldt1.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
        System.out.println(ldt4);

        // 自定义：下一个工作日
        LocalDateTime ldt5 = ldt1.with(temporal -> {
            LocalDateTime ldt = (LocalDateTime) temporal;
            DayOfWeek dow = ldt.getDayOfWeek();
            if (dow.equals(DayOfWeek.FRIDAY)) {
                return ldt.plusDays(3);
            } else if (dow.equals(DayOfWeek.SATURDAY)) {
                return ldt.plusDays(2);
            } else {
                return ldt.plusDays(1);
            }
        });

        System.out.println(ldt5);
    }

    @Test
    public void Test5(){
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(ldt);
        System.out.println(zdt);
    }
}
