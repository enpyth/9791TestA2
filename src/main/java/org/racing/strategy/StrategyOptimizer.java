package org.racing.strategy;

import org.racing.car.RaceCar;
import org.racing.car.Tyre;
import org.racing.config.ConfigurationManager;
import org.racing.track.Track;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Handles race strategy optimization calculations.
 */
public class StrategyOptimizer {
    private final RaceCar raceCar;
    private final Track track;
    private final int totalLaps;
    private final ConfigurationManager configManager;
    private final double minFuelLevel;
    private final double maxFuelLevel;
    private final double tyreWearThreshold;
    private final double pitStopDuration;
    private final double fuelFillRate;

    public StrategyOptimizer(RaceCar raceCar, Track track, int totalLaps) {
        this.raceCar = raceCar;
        this.track = track;
        this.totalLaps = totalLaps;
        this.configManager = ConfigurationManager.getInstance();
        
        // Load configuration parameters
        this.minFuelLevel = (double) configManager.getConfigValue("strategy", "parameters", "defaultParameters", "minFuelLevel");
        this.maxFuelLevel = (double) configManager.getConfigValue("strategy", "parameters", "defaultParameters", "maxFuelLevel");
        this.tyreWearThreshold = (double) configManager.getConfigValue("strategy", "parameters", "defaultParameters", "tyreWearThreshold");
        this.pitStopDuration = (double) configManager.getConfigValue("strategy", "parameters", "defaultParameters", "pitStopDuration");
        this.fuelFillRate = (double) configManager.getConfigValue("strategy", "parameters", "defaultParameters", "fuelFillRate");
    }

    public RaceStrategy optimizeStrategy() {
        // Get preferred strategy based on track type
        String trackType = track.getType().name();
        String preferredTyreCompound = (String) configManager.getConfigValue(
            "strategy", "trackTypePreferences", trackType, "preferredTyreCompound");
        String fuelStrategy = (String) configManager.getConfigValue(
            "strategy", "trackTypePreferences", trackType, "fuelStrategy");

        // Create initial strategy
        Tyre startingTyre = selectTyre(preferredTyreCompound);
        RaceStrategy.FuelStrategy fuelStrategyEnum = RaceStrategy.FuelStrategy.valueOf(fuelStrategy);
        double initialFuelLoad = calculateInitialFuelLoad(fuelStrategyEnum);
        
        RaceStrategy strategy = new RaceStrategy(initialFuelLoad, startingTyre, fuelStrategyEnum);

        // Calculate pit stops
        List<PitStop> pitStops = calculatePitStops(startingTyre, fuelStrategyEnum);
        for (PitStop pitStop : pitStops) {
            strategy.addPitStop(pitStop);
        }

        // Calculate total time and fuel consumption
        double totalTime = calculateTotalTime(pitStops);
        double fuelConsumption = calculateTotalFuelConsumption(pitStops);

        strategy.setEstimatedTotalTime(totalTime);
        strategy.setEstimatedFuelConsumption(fuelConsumption);

        return strategy;
    }

    private Tyre selectTyre(String compound) {
        // In a real implementation, this would load the tyre from the configuration
        // For now, we'll create a basic tyre
        return new Tyre(
            compound + " Tyre",
            9.0,
            2000.0,
            "RaceTyre Co",
            8.0,
            1.5,
            85.0,
            Tyre.TyreCompound.valueOf(compound)
        );
    }

    private double calculateInitialFuelLoad(RaceStrategy.FuelStrategy fuelStrategy) {
        double baseFuel = track.getLength() * track.getFuelConsumptionRate() * totalLaps;
        return switch (fuelStrategy) {
            case CONSERVATIVE -> baseFuel * 1.1;
            case AGGRESSIVE -> baseFuel * 0.9;
            case BALANCED -> baseFuel;
        };
    }

    private List<PitStop> calculatePitStops(Tyre startingTyre, RaceStrategy.FuelStrategy fuelStrategy) {
        List<PitStop> pitStops = new ArrayList<>();
        double currentFuel = calculateInitialFuelLoad(fuelStrategy);
        Tyre currentTyre = startingTyre;
        int lastPitStopLap = 0;

        for (int lap = 1; lap <= totalLaps; lap++) {
            // Calculate fuel consumption for this lap
            double fuelConsumption = track.getFuelConsumptionRate() * 
                (1 + (lap - lastPitStopLap) * 0.01); // Increase consumption as tyres wear

            // Check if pit stop is needed
            if (needsPitStop(lap, currentFuel, fuelConsumption, currentTyre, lastPitStopLap)) {
                PitStop pitStop = createPitStop(lap, currentFuel, currentTyre);
                pitStops.add(pitStop);
                currentFuel = maxFuelLevel;
                currentTyre = selectTyre("MEDIUM"); // Default to medium tyres for pit stops
                lastPitStopLap = lap;
            }

            currentFuel -= fuelConsumption;
        }

        return pitStops;
    }

    private boolean needsPitStop(int currentLap, double currentFuel, double fuelConsumption,
                               Tyre currentTyre, int lastPitStopLap) {
        // Check fuel level
        if (currentFuel - fuelConsumption < minFuelLevel) {
            return true;
        }

        // Check tyre wear
        int lapsOnTyres = currentLap - lastPitStopLap;
        double tyreWear = currentTyre.getWearRate() * lapsOnTyres;
        if (tyreWear > tyreWearThreshold) {
            return true;
        }

        return false;
    }

    private PitStop createPitStop(int lap, double currentFuel, Tyre currentTyre) {
        double fuelToAdd = maxFuelLevel - currentFuel;
        double duration = pitStopDuration + (fuelToAdd / fuelFillRate);
        
        return new PitStop(
            lap,
            duration,
            selectTyre("MEDIUM"),
            fuelToAdd,
            PitStop.PitStopType.BOTH
        );
    }

    private double calculateTotalTime(List<PitStop> pitStops) {
        double totalTime = 0;
        
        // Calculate racing time
        for (int lap = 1; lap <= totalLaps; lap++) {
            totalTime += track.calculateEstimatedLapTime(
                raceCar.getPerformanceMetrics().getOverallPerformance()
            );
        }

        // Add pit stop time
        for (PitStop pitStop : pitStops) {
            totalTime += pitStop.getDuration();
        }

        return totalTime;
    }

    private double calculateTotalFuelConsumption(List<PitStop> pitStops) {
        double totalFuel = 0;
        
        // Calculate fuel consumption for each lap
        for (int lap = 1; lap <= totalLaps; lap++) {
            totalFuel += track.getFuelConsumptionRate() * 
                (1 + (lap % 10) * 0.01); // Slight increase in consumption as tyres wear
        }

        return totalFuel;
    }
} 