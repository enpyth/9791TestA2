package org.racing.strategy;

import org.racing.car.Tyre;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a complete race strategy including pit stops and fuel management.
 */
public class RaceStrategy {
    private List<PitStop> pitStops;
    private double initialFuelLoad;
    private Tyre startingTyre;
    private FuelStrategy fuelStrategy;
    private double estimatedTotalTime;
    private double estimatedFuelConsumption;

    public enum FuelStrategy {
        CONSERVATIVE,
        BALANCED,
        AGGRESSIVE
    }

    public RaceStrategy(double initialFuelLoad, Tyre startingTyre, FuelStrategy fuelStrategy) {
        this.pitStops = new ArrayList<>();
        this.initialFuelLoad = initialFuelLoad;
        this.startingTyre = startingTyre;
        this.fuelStrategy = fuelStrategy;
    }

    public void addPitStop(PitStop pitStop) {
        pitStops.add(pitStop);
    }

    public void setEstimatedTotalTime(double estimatedTotalTime) {
        this.estimatedTotalTime = estimatedTotalTime;
    }

    public void setEstimatedFuelConsumption(double estimatedFuelConsumption) {
        this.estimatedFuelConsumption = estimatedFuelConsumption;
    }

    // Getters
    public List<PitStop> getPitStops() {
        return pitStops;
    }

    public double getInitialFuelLoad() {
        return initialFuelLoad;
    }

    public Tyre getStartingTyre() {
        return startingTyre;
    }

    public FuelStrategy getFuelStrategy() {
        return fuelStrategy;
    }

    public double getEstimatedTotalTime() {
        return estimatedTotalTime;
    }

    public double getEstimatedFuelConsumption() {
        return estimatedFuelConsumption;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Race Strategy:\n");
        sb.append(String.format("Starting Tyre: %s\n", startingTyre.getName()));
        sb.append(String.format("Initial Fuel Load: %.2f liters\n", initialFuelLoad));
        sb.append(String.format("Fuel Strategy: %s\n", fuelStrategy));
        sb.append(String.format("Estimated Total Time: %.2f seconds\n", estimatedTotalTime));
        sb.append(String.format("Estimated Fuel Consumption: %.2f liters\n", estimatedFuelConsumption));
        sb.append("\nPit Stops:\n");
        for (PitStop pitStop : pitStops) {
            sb.append(pitStop.toString()).append("\n");
        }
        return sb.toString();
    }
} 