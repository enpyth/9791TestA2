package org.racing.strategy;

import org.racing.car.Tyre;

/**
 * Represents a pit stop event during a race.
 */
public class PitStop {
    private int lapNumber;
    private double duration;
    private Tyre newTyre;
    private double fuelAmount;
    private PitStopType type;

    public enum PitStopType {
        TYRE_CHANGE,
        FUEL_REFILL,
        BOTH
    }

    public PitStop(int lapNumber, double duration, Tyre newTyre, double fuelAmount, PitStopType type) {
        this.lapNumber = lapNumber;
        this.duration = duration;
        this.newTyre = newTyre;
        this.fuelAmount = fuelAmount;
        this.type = type;
    }

    // Getters
    public int getLapNumber() {
        return lapNumber;
    }

    public double getDuration() {
        return duration;
    }

    public Tyre getNewTyre() {
        return newTyre;
    }

    public double getFuelAmount() {
        return fuelAmount;
    }

    public PitStopType getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("Pit Stop on Lap %d: %s (Duration: %.2f seconds, Fuel: %.2f liters)",
                lapNumber, type, duration, fuelAmount);
    }
} 