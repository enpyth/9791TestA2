package org.racing.car;

import org.racing.Component;

/**
 * Represents a racing tyre with specific performance characteristics.
 */
public class Tyre extends Component {
    private double gripLevel; // 0-10 rating
    private double wearRate; // percentage per lap
    private double temperatureRange; // optimal temperature range
    private TyreCompound compound;

    public enum TyreCompound {
        SOFT,
        MEDIUM,
        HARD
    }

    public Tyre(String name, double weight, double cost, String manufacturer,
                double gripLevel, double wearRate, double temperatureRange, TyreCompound compound) {
        super(name, weight, cost, manufacturer);
        this.gripLevel = gripLevel;
        this.wearRate = wearRate;
        this.temperatureRange = temperatureRange;
        this.compound = compound;
    }

    @Override
    public double calculatePerformance() {
        // Performance calculation based on grip and wear rate
        // Higher grip and lower wear rate means better performance
        return (gripLevel * 0.7) + ((10 - wearRate) * 0.3);
    }

    @Override
    public String getComponentType() {
        return "Tyre";
    }

    // Getters
    public double getGripLevel() {
        return gripLevel;
    }

    public double getWearRate() {
        return wearRate;
    }

    public double getTemperatureRange() {
        return temperatureRange;
    }

    public TyreCompound getCompound() {
        return compound;
    }
} 