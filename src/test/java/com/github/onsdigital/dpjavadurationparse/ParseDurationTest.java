package com.github.onsdigital.dpjavadurationparse;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.github.onsdigital.dpjavadurationparse.ParseDuration.parseDuration;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for {@link ParseDuration}.
 */
public class ParseDurationTest {

        @Test
        public void durationsWithPT()  {
            // Given
            // a set of valid positive durations in strings, check their associated parsed duration object is correct

            // Positive Durations
            assertEquals(Duration.ofHours(3).plusMinutes(45).plusSeconds(50), parseDuration("PT3h45m50s"));
            assertEquals(Duration.ofSeconds(0), parseDuration("PT0s"));
            assertEquals(Duration.ofSeconds(0), parseDuration("PT0.0s"));
            assertEquals(Duration.ofSeconds(1), parseDuration("PT1s"));
            assertEquals(Duration.ofMillis(50), parseDuration("PT0.050s"));
            assertEquals(Duration.ofSeconds(50), parseDuration("PT50s"));
            assertEquals(Duration.ofMinutes(45), parseDuration("PT45m"));
            assertEquals(Duration.ofHours(45), parseDuration("PT45h"));
            assertEquals(Duration.ofHours(3).plusSeconds(50), parseDuration("PT3h50s"));

            // Given
            // a set of valid negative durations in strings, check their associated parsed duration object is correct

            // Negative Durations
            assertEquals(Duration.ofHours(-0), parseDuration("-PT0s"));
            assertEquals(Duration.ofHours(-3).plusMillis(-50), parseDuration("-PT3h0.050s"));
            assertEquals(Duration.ofMillis(-1), parseDuration("-PT0.001s"));
            assertEquals(Duration.ofSeconds(-50), parseDuration("-PT50s"));
            assertEquals(Duration.ofMinutes(-45), parseDuration("-PT45m"));
            assertEquals(Duration.ofHours(-45), parseDuration("-PT45h"));
            assertEquals(Duration.ofHours(-3).plusSeconds(-50), parseDuration("-PT3h50s"));
        }

        @Test
        public void invalidDurationsWithPT()  {
            // Given
            // a set of invalid durations in strings, check they were not parsed properly.
            assertEquals(null, parseDuration("PT"));
            assertEquals(null, parseDuration("PT3h45m56s345ms"));
            assertEquals(null, parseDuration("PT100µs"));
            assertEquals(null, parseDuration("PT100us"));
            assertEquals(null, parseDuration("PT1ms"));
            assertEquals(null, parseDuration("PT1µs"));
        }

        @Test
        public void durationsWithoutPT()  {
            // Given
            // a set of valid positive durations in strings, check their associated parsed duration object is correct

            // Positive Durations
            assertEquals(Duration.ofHours(3).plusMinutes(45).plusSeconds(50), parseDuration("3h45m50s"));
            assertEquals(Duration.ofSeconds(0), parseDuration("-0s"));
            assertEquals(Duration.ofSeconds(0), parseDuration("0s"));
            assertEquals(Duration.ofSeconds(0), parseDuration("0.0s"));
            assertEquals(Duration.ofHours(0), parseDuration("0.000s")); //Also testing the .equals() within Duration
            assertEquals(Duration.ofSeconds(1), parseDuration("1s"));
            assertEquals(Duration.ofMillis(50), parseDuration("0.050s"));
            assertEquals(Duration.ofSeconds(50), parseDuration("50s"));
            assertEquals(Duration.ofMinutes(45), parseDuration("45m"));
            assertEquals(Duration.ofHours(45), parseDuration("45h"));
            assertEquals(Duration.ofHours(3).plusSeconds(50), parseDuration("3h50s"));

            // Given
            // a set of valid negative durations in strings, check their associated parsed duration object is correct

            // Negative Durations
            assertEquals(Duration.ofHours(-3).plusMillis(-50), parseDuration("-3h0.050s"));
            assertEquals(Duration.ofMillis(-1), parseDuration("-0.001s"));
            assertEquals(Duration.ofHours(-1), parseDuration("-1h"));
            assertEquals(Duration.ofSeconds(-50), parseDuration("-50s"));
            assertEquals(Duration.ofMinutes(-45), parseDuration("-45m"));
            assertEquals(Duration.ofHours(-45), parseDuration("-45h"));
            assertEquals(Duration.ofHours(-3).plusSeconds(-50), parseDuration("-3h50s"));
        }

        @Test
        public void invalidDurationsWithoutPT()  {
            // Given
            // a set of invalid durations in strings, check they were not parsed properly.
            assertEquals(null, parseDuration("1"));
            assertEquals(null, parseDuration("0"));
            assertEquals(null, parseDuration("3h45m56s345ms"));
            assertEquals(null, parseDuration("100µs"));
            assertEquals(null, parseDuration("100us"));
            assertEquals(null, parseDuration("1ms"));
            assertEquals(null, parseDuration("1µs"));

            assertEquals(null, parseDuration("-PT"));
            assertEquals(null, parseDuration("-PT1"));
            assertEquals(null, parseDuration("-PT0"));

            assertEquals(null, parseDuration("0000"));
            assertEquals(null, parseDuration("0001"));
            assertEquals(null, parseDuration("00"));
            assertEquals(null, parseDuration("001"));
            assertEquals(null, parseDuration("01"));
        }

    @Test
    public void returningOptionalNull() {

    }

}