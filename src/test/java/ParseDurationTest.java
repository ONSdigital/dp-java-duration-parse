import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

//import static ParseDuration.parseDuration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static parse.ParseDuration.parseThisDuration;

public class ParseDurationTest {

    @Test
    public void durationsWithPT()  {
        // Given
        // a set of valid positive durations in strings, check their associated parsed duration object is correct

        // Positive Durations
        assertEquals(Duration.ofHours(3).plusMinutes(45).plusSeconds(50),parseThisDuration("PT3h45m50s"));
        assertEquals(Duration.ofSeconds(0),parseThisDuration("PT0s"));
        assertEquals(Duration.ofSeconds(0),parseThisDuration("PT0.0s"));
        assertEquals(Duration.ofSeconds(1),parseThisDuration("PT1s"));
        assertEquals(Duration.ofMillis(50),parseThisDuration("PT0.050s"));
        assertEquals(Duration.ofSeconds(50),parseThisDuration("PT50s"));
        assertEquals(Duration.ofMinutes(45),parseThisDuration("PT45m"));
        assertEquals(Duration.ofHours(45),parseThisDuration("PT45h"));
        assertEquals(Duration.ofHours(3).plusSeconds(50),parseThisDuration("PT3h50s"));

        // Given
        // a set of valid negative durations in strings, check their associated parsed duration object is correct

        // Negative Durations
        assertEquals(Duration.ofHours(-0),parseThisDuration("-PT0s"));
        assertEquals(Duration.ofHours(-3).plusMillis(-50),parseThisDuration("-PT3h0.050s"));
        assertEquals(Duration.ofMillis(-1),parseThisDuration("-PT0.001s"));
        assertEquals(Duration.ofSeconds(-50),parseThisDuration("-PT50s"));
        assertEquals(Duration.ofMinutes(-45),parseThisDuration("-PT45m"));
        assertEquals(Duration.ofHours(-45),parseThisDuration("-PT45h"));
        assertEquals(Duration.ofHours(-3).plusSeconds(-50),parseThisDuration("-PT3h50s"));
    }

    @Test
    public void invalidDurationsWithPT()  {
        // Given
        // a set of invalid durations in strings, check they were not parsed properly.
        assertEquals(null,parseThisDuration("PT"));
        assertEquals(null,parseThisDuration("PT3h45m56s345ms"));
        assertEquals(null,parseThisDuration("PT100µs"));
        assertEquals(null,parseThisDuration("PT100us"));
        assertEquals(null,parseThisDuration("PT1ms"));
        assertEquals(null,parseThisDuration("PT1µs"));
    }

    @Test
    public void durationsWithoutPT()  {
        // Given
        // a set of valid positive durations in strings, check their associated parsed duration object is correct

        // Positive Durations
        assertEquals(Duration.ofHours(3).plusMinutes(45).plusSeconds(50),parseThisDuration("3h45m50s"));
        assertEquals(Duration.ofSeconds(0),parseThisDuration("-0s"));
        assertEquals(Duration.ofSeconds(0),parseThisDuration("0s"));
        assertEquals(Duration.ofSeconds(0),parseThisDuration("0.0s"));
        assertEquals(Duration.ofHours(0),parseThisDuration("0.000s")); //Also testing the .equals() within Duration
        assertEquals(Duration.ofSeconds(1),parseThisDuration("1s"));
        assertEquals(Duration.ofMillis(50),parseThisDuration("0.050s"));
        assertEquals(Duration.ofSeconds(50),parseThisDuration("50s"));
        assertEquals(Duration.ofMinutes(45),parseThisDuration("45m"));
        assertEquals(Duration.ofHours(45),parseThisDuration("45h"));
        assertEquals(Duration.ofHours(3).plusSeconds(50),parseThisDuration("3h50s"));

        // Given
        // a set of valid positive durations in strings, check their associated parsed duration object is correct

        // Negative Durations
        assertEquals(Duration.ofHours(-3).plusMillis(-50),parseThisDuration("-3h0.050s"));
        assertEquals(Duration.ofMillis(-1),parseThisDuration("-0.001s"));
        assertEquals(Duration.ofHours(-1),parseThisDuration("-1h"));
        assertEquals(Duration.ofSeconds(-50),parseThisDuration("-50s"));
        assertEquals(Duration.ofMinutes(-45),parseThisDuration("-45m"));
        assertEquals(Duration.ofHours(-45),parseThisDuration("-45h"));
        assertEquals(Duration.ofHours(-3).plusSeconds(-50),parseThisDuration("-3h50s"));
    }

    @Test
    public void invalidDurationsWithoutPT()  {
        // Given
        // a set of invalid durations in strings, check they were not parsed properly.
        assertEquals(null,parseThisDuration("1"));
        assertEquals(null,parseThisDuration("0"));
        assertEquals(null,parseThisDuration("3h45m56s345ms"));
        assertEquals(null,parseThisDuration("100µs"));
        assertEquals(null,parseThisDuration("100us"));
        assertEquals(null,parseThisDuration("1ms"));
        assertEquals(null,parseThisDuration("1µs"));


        assertEquals(null,parseThisDuration("-PT"));
        assertEquals(null,parseThisDuration("-PT1"));
        assertEquals(null,parseThisDuration("-PT0"));

        assertEquals(null, parseThisDuration("0000")); 
        assertEquals(null, parseThisDuration("0001")); 
        assertEquals(null, parseThisDuration("00")); 
        assertEquals(null, parseThisDuration("001")); 
        assertEquals(null, parseThisDuration("01")); 
    }
}
