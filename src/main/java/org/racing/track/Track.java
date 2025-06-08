package org.racing.track;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a racing track with its specific characteristics.
 */
public class Track {
    public enum TrackType {
        TECHNICAL,
        SPEED,
        MIXED
    }

    @JsonProperty("name")
    private final String name;
    
    @JsonProperty("length")
    private final double length;
    
    @JsonProperty("corners")
    private final int corners;
    
    @JsonProperty("type")
    private final TrackType type;
    
    @JsonProperty("defaultWeather")
    private final WeatherCondition defaultWeather;
    
    @JsonProperty("averageSpeed")
    private final double averageSpeed;
    
    @JsonProperty("gripLevel")
    private final double gripLevel;
    
    @JsonProperty("description")
    private final String description;

    private WeatherCondition currentWeather;

    @JsonCreator
    public Track(
        @JsonProperty("name") String name,
        @JsonProperty("length") double length,
        @JsonProperty("corners") int corners,
        @JsonProperty("type") TrackType type,
        @JsonProperty("defaultWeather") WeatherCondition defaultWeather,
        @JsonProperty("averageSpeed") double averageSpeed,
        @JsonProperty("gripLevel") double gripLevel,
        @JsonProperty("description") String description
    ) {
        this.name = name;
        this.length = length;
        this.corners = corners;
        this.type = type;
        this.defaultWeather = defaultWeather;
        this.currentWeather = defaultWeather;
        this.averageSpeed = averageSpeed;
        this.gripLevel = gripLevel;
        this.description = description;
    }

    public void setWeather(WeatherCondition weather) {
        this.currentWeather = weather;
    }

    public WeatherCondition getCurrentWeather() {
        return currentWeather;
    }

    public String getName() {
        return name;
    }

    public double getLength() {
        return length;
    }

    public int getCorners() {
        return corners;
    }

    public TrackType getType() {
        return type;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public double getGripLevel() {
        return gripLevel;
    }

    public String getDescription() {
        return description;
    }

    public WeatherCondition getWeatherCondition() {
        return currentWeather;
    }

    public double getFuelConsumptionRate() {
        // Basic fuel consumption calculation based on track characteristics
        return (length * corners * 0.1) / averageSpeed;
    }

    public double calculateEstimatedLapTime(double carPerformance) {
        // Basic lap time calculation based on track length, corners, and car performance
        double baseTime = (length * 60) / averageSpeed; // Convert to seconds
        double cornerPenalty = corners * 2.0; // 2 seconds per corner
        double performanceFactor = 1.0 - (carPerformance / 1000.0); // Scale performance impact
        
        return baseTime + cornerPenalty * performanceFactor;
    }
} 