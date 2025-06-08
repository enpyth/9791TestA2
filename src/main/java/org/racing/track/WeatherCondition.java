package org.racing.track;

/**
 * Represents different weather conditions that can affect racing.
 */
public enum WeatherCondition {
    DRY("Dry conditions, optimal for racing"),
    WET("Wet conditions, requires wet weather tyres"),
    INTERMEDIATE("Mixed conditions, suitable for intermediate tyres");

    private final String description;

    WeatherCondition(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
} 