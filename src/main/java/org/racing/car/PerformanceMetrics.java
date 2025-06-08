package org.racing.car;

/**
 * Represents the performance metrics of a race car.
 */
public class PerformanceMetrics {
    private double speed; // in km/h
    private double handling; // 0-10 rating
    private double fuelEfficiency; // in km/l
    private double corneringAbility; // 0-10 rating
    private double overallPerformance; // 0-100 rating

    public PerformanceMetrics(double speed, double handling, double fuelEfficiency, double corneringAbility) {
        this.speed = speed;
        this.handling = handling;
        this.fuelEfficiency = fuelEfficiency;
        this.corneringAbility = corneringAbility;
        calculateOverallPerformance();
    }

    private void calculateOverallPerformance() {
        // Weighted calculation of overall performance
        this.overallPerformance = (speed * 0.3) +
                                 (handling * 10 * 0.2) +
                                 (fuelEfficiency * 0.2) +
                                 (corneringAbility * 10 * 0.3);
    }

    // Getters
    public double getSpeed() {
        return speed;
    }

    public double getHandling() {
        return handling;
    }

    public double getFuelEfficiency() {
        return fuelEfficiency;
    }

    public double getCorneringAbility() {
        return corneringAbility;
    }

    public double getOverallPerformance() {
        return overallPerformance;
    }
} 