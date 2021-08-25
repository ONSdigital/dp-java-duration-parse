# dp-java-duration-parse
Used by Java applications to parse durations in two formats.  Both are not case sensitive.  Example valid formats are:
- `PT0s` which is equivalent to `0s`
- `PT1s` which is equivalent to `1s`
- `PT0.050s` which is equivalent to `0.05s`
- `PT50S` which is equivalent to `50s`
- `PT45m` which is equivalent to `45m`
- `PT45H` which is equivalent to `45h`
- `PT3h50S` which is equivalent to `3h50s`

Formats beginning with PT are the Java ISO8601 standard.  The equivalent format is one of the formats available in `timer.Duration` in GO, and this is the format in use within ONSDigital application.