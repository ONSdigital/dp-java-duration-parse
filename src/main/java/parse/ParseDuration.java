package parse;

import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class ParseDuration {

    /**
     * Checks whether duration starts with string looks like an ISO8601 formatted string, and if not it adds PT.
     * @param stringDuration Checking whether {@link String} starts with the appropriate ISO8601 characters.
     * @return A {@link Duration}
     * Compatible with one or a combination of the following units: h, m, s
     *
     * Example Valid Formats
     *          PT3h45m56s345ms34us
     *          -PT3h45m56s345ms34µs
     *          PT3h56s345ms34µs
     *          -PT3h56s34µs
     *          PT3h34µs
     *          -PT56s
     *
     *          3h45m56s345ms34us
     *          -3h45m56s345ms34µs
     *          3h56s345ms34µs
     *          -3h56s34µs
     *          3h34µs
     *          -56s
     *
     * Example Invalid Formats, resulting in null bring returned.
     *          3h56s345ms34µs34us
     *          34µs3h56s345ms
     *          3h56s345ms34µs*
     */
    /*
        The "PT" prefix on each of the Duration's string representations shown above indicates that the representation is a "period" duration designation ("P") and a "time" designation ("T") per the ISO-8601
     */
    public static Duration parseThisDuration(String stringDuration) {
        // Flag to identify durations in the past
        boolean negative = false;
        // Quality check the quality of the parameter
        if(stringDuration==null
                || (stringDuration.startsWith("PT") && stringDuration.length()<4)                  // e.g. "PR1"
                || (!stringDuration.startsWith("PT") && stringDuration.length()<2)) return null;   // e.g. "1"
        stringDuration = stringDuration.toUpperCase();
        // Check if it starts with a negative.
        if(stringDuration.startsWith("-")) {
            negative = true;
            //remove the negative sign atm to allow it to be added back on later
            stringDuration = stringDuration.substring(1);
        }
        // Check if user hasn't included the 'PT', and if they havn't then add it.
        if (!stringDuration.startsWith("PT")) {
            stringDuration = "PT".concat(stringDuration);
        }
        // Add the negative on if it was negative
        if(negative) {
            stringDuration = "-".concat(stringDuration);
        }
        // Try parse the duration.
        Duration parsedDuration=null;
        try {
            parsedDuration = Duration.parse(stringDuration);
        } catch (DateTimeParseException ex) {
            return null;
        }
        return parsedDuration;
    }


    public static void main(String[] args) {
        Map<String,Duration> validDurations = new HashMap<>();

//        System.out.println("parseDuration(\"PT3h45m56s345ms34us\"); = " + parseDuration("PT3h45m56s345ms34us"));
//        System.out.println("parseDuration(\"PT3h45m56s345ms34us\"); = " + parseDuration("PT3h45m56s345ms"));
//        System.out.println("parseDuration(\"PT3h45m56s345ms34us\"); = " + parseDuration("PT3h45m50s"));
//        System.out.println("parseDuration(\"PT3h45m56s345ms34us\"); = " + parseDuration("3h45m50s"));
//        System.out.println("parseDuration(\"PT3h45m56s345ms34us\"); = " + parseDuration("3h45m50s"));
//        System.out.println("parseDuration(\"PT3h45m56s345ms34us\"); = " + parseDuration("-3h45m50s"));
        System.out.println("parseDuration(\"PT3h45m56s345ms34us\"); = " + parseThisDuration("-3h45m50s"));

//        "PT3h45m56s345ms34us"
//        "-PT3h45m56s345ms34µs"
//        "PT3h56s345ms34µs"
//        "-PT3h56s34µs"
//        "PT3h34µs"
//        "-PT56s"
//
//    if (parseDuration())
//
//
//  "3h45m56s345ms34us"
//             "-3h45m56s345ms34µs"
//             "3h56s345ms34µs"
//             "-3h56s34µs"
//             "3h34µs"
//             "-56s"
//
//
//        this.turn("P1DT1H10M10.5S");
//        Duration.in(DAYS, HOURS, MINUTES).between(start, end); // equivalent alternative for Java 8

//        Duration.parsePeriod("-P18DT2H"); // -18 days and -2 hours (negative)
//        Duration<CalendarUnit> duration2 = Duration.parseCalendarPeriod("P0000-11-04"); // 11 months and 4 days

    }
}
