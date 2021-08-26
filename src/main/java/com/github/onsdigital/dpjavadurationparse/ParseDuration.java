package com.github.onsdigital.dpjavadurationparse;

import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class ParseDuration {

    private ParseDuration() {
        throw new IllegalStateException("ParseDuration is a static Utility class.  No instantiation possible.x");
    }

    /**
     * Parse's Duration in either ISO8601 or format's similar to: 3h56s345ms.
     * Throws DateTimeParseException if stringDuration is null or if the parse wasn't successful.
     *
     * The "PT" prefix on each of the ISO8601's durations example below, where P is
     * a "period" duration designation ("P") and a "time" designation ("T") per the ISO-8601Checks whether duration
     * starts with string looks like an ISO8601 formatted string, and if not it adds PT.
     *
     * @param stringDuration Checking whether {@link String} starts with the appropriate ISO8601 characters.
     * @return A {@link Duration}
     * Compatible with one or a combination of the following units: h, m, s
     *
     * Example Valid Formats
     *          PT3h45m56s345ms
     *          -PT3h45m56s345ms
     *          PT3h56s345ms
     *          -PT3h56s
     *          PT3h
     *          -PT56s
     *
     *          3h45m56s345ms34us
     *          -3h45m56s345ms34µs
     *          3h56s345ms34µs
     *          -3h56s34µs
     *          3h34µs
     *          -56s
     *
     * Example Invalid Formats, resulting in DateTimeParseException being thrown.
     *          3h56s345ms34µs34us
     *          34µs3h56s345ms
     *          3h56s345ms34µs*
     */
    public static Duration parseDuration(String stringDuration) {
        // Flag to identify durations in the past
        boolean negative = false;
        // Quality check the quality of the parameter
        if(
            stringDuration==null
            || (stringDuration.startsWith("PT") && stringDuration.length()<4) // e.g. "PR1"
            || (!stringDuration.startsWith("PT") && stringDuration.length()<2) // e.g. "1
        )
        {
            throw new DateTimeParseException("The string was of the wrong format", stringDuration, 0);
        }
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
        return Duration.parse(stringDuration);
    }
}
