package com.github.onsdigital.dpjavadurationparse;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
            assertEquals(Duration.ofHours(3).plusMinutes(45).plusSeconds(50), parseDuration("PT3h45m50s").get());
            assertEquals(Duration.ofSeconds(0), parseDuration("PT0s").get());
            assertEquals(Duration.ofSeconds(0), parseDuration("PT0.0s").get());
            assertEquals(Duration.ofSeconds(1), parseDuration("PT1s").get());
            assertEquals(Duration.ofMillis(50), parseDuration("PT0.050s").get());
            assertEquals(Duration.ofSeconds(50), parseDuration("PT50s").get());
            assertEquals(Duration.ofMinutes(45), parseDuration("PT45m").get());
            assertEquals(Duration.ofHours(45), parseDuration("PT45h").get());
            assertEquals(Duration.ofHours(3).plusSeconds(50), parseDuration("PT3h50s").get());

            // Given
            // a set of valid negative durations in strings, check their associated parsed duration object is correct

            // Negative Durations
            assertEquals(Duration.ofHours(-0), parseDuration("-PT0s").get());
            assertEquals(Duration.ofHours(-3).plusMillis(-50), parseDuration("-PT3h0.050s").get());
            assertEquals(Duration.ofMillis(-1), parseDuration("-PT0.001s").get());
            assertEquals(Duration.ofSeconds(-50), parseDuration("-PT50s").get());
            assertEquals(Duration.ofMinutes(-45), parseDuration("-PT45m").get());
            assertEquals(Duration.ofHours(-45), parseDuration("-PT45h").get());
            assertEquals(Duration.ofHours(-3).plusSeconds(-50), parseDuration("-PT3h50s").get());
        }

        @Test
        public void invalidDurationsWithPT()  {
            // Given
            // a set of invalid durations in strings, check they were not parsed properly.
            assertFalse(parseDuration("PT3h45m56s345ms").isPresent());
            assertFalse(parseDuration("PT3h45m56s345ms").isPresent());
            assertFalse(parseDuration("PT100µs").isPresent());
            assertFalse(parseDuration("PT100us").isPresent());
            assertFalse(parseDuration("PT1ms").isPresent());
            assertFalse(parseDuration("PT1µs").isPresent());
            assertFalse(parseDuration("PT").isPresent());
        }

        @Test
        public void durationsWithoutPT()  {
            // Given
            // a set of valid positive durations in strings, check their associated parsed duration object is correct

            // Positive Durations
            assertEquals(Duration.ofHours(3).plusMinutes(45).plusSeconds(50), parseDuration("3h45m50s").get());
            assertEquals(Duration.ofSeconds(0), parseDuration("-0s").get());
            assertEquals(Duration.ofSeconds(0), parseDuration("0s").get());
            assertEquals(Duration.ofSeconds(0), parseDuration("0.0s").get());
            assertEquals(Duration.ofHours(0), parseDuration("0.000s").get()); //Also testing the .equals() within Duration
            assertEquals(Duration.ofSeconds(1), parseDuration("1s").get());
            assertEquals(Duration.ofMillis(50), parseDuration("0.050s").get());
            assertEquals(Duration.ofSeconds(50), parseDuration("50s").get());
            assertEquals(Duration.ofMinutes(45), parseDuration("45m").get());
            assertEquals(Duration.ofHours(45), parseDuration("45h").get());
            assertEquals(Duration.ofHours(3).plusSeconds(50), parseDuration("3h50s").get());

            // Given
            // a set of valid negative durations in strings, check their associated parsed duration object is correct

            // Negative Durations
            assertEquals(Duration.ofHours(-3).plusMillis(-50), parseDuration("-3h0.050s").get());
            assertEquals(Duration.ofMillis(-1), parseDuration("-0.001s").get());
            assertEquals(Duration.ofHours(-1), parseDuration("-1h").get());
            assertEquals(Duration.ofSeconds(-50), parseDuration("-50s").get());
            assertEquals(Duration.ofMinutes(-45), parseDuration("-45m").get());
            assertEquals(Duration.ofHours(-45), parseDuration("-45h").get());
            assertEquals(Duration.ofHours(-3).plusSeconds(-50), parseDuration("-3h50s").get());
        }

        @Test
        public void invalidDurationsWithoutPT()  {
            // Given
            // a set of invalid durations in strings, check they were not parsed properly.
            assertFalse( parseDuration("1").isPresent());
            assertFalse( parseDuration("0").isPresent());
            assertFalse( parseDuration("3h45m56s345ms").isPresent());
            assertFalse( parseDuration("100µs").isPresent());
            assertFalse( parseDuration("100us").isPresent());
            assertFalse( parseDuration("1ms").isPresent());
            assertFalse( parseDuration("1µs").isPresent());

            assertFalse( parseDuration("-PT").isPresent());
            assertFalse( parseDuration("-PT1").isPresent());
            assertFalse( parseDuration("-PT0").isPresent());

            assertFalse( parseDuration("0000").isPresent());
            assertFalse( parseDuration("0001").isPresent());
            assertFalse( parseDuration("00").isPresent());
            assertFalse( parseDuration("001").isPresent());
            assertFalse( parseDuration("01").isPresent());

            assertFalse(parseDuration(null).isPresent());
            assertFalse(parseDuration("").isPresent());
        }

        @Test
        public void testForInstatiation() {
            // Given
            // the class ParseDuration, ensure that it cannot be instatiated, and throws an exception.
            try {
                Class c=Class.forName("com.github.onsdigital.dpjavadurationparse.ParseDuration");
                Constructor con=c.getDeclaredConstructor();
                con.setAccessible(true);
                con.newInstance();
            } catch (InvocationTargetException ex) {
                Throwable targetException = ex.getTargetException();
                //Ensure that the reflected exception is an instance of IllegalStateException
                assertInstanceOf(IllegalStateException.class, targetException);
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
                fail("Expected InvocationTargetException not IllegalAccessException");
            } catch (InstantiationException ex) {
                ex.printStackTrace();
                fail("Expected InvocationTargetException not InstantiationException");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                fail("Expected InvocationTargetException not ClassNotFoundException");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                fail("Expected InvocationTargetException not NoSuchMethodException");
            }
        }

}