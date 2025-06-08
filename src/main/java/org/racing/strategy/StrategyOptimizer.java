package org.racing.strategy;

import org.racing.car.RaceCar;
import org.racing.car.Tyre;
import org.racing.config.ConfigurationManager;
import org.racing.track.Track;
import org.racing.track.WeatherCondition;
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
    private final double fuelCapacity;

    public StrategyOptimizer(RaceCar raceCar, Track track, int totalLaps) {
        this(raceCar, track, totalLaps, 100.0); // Default fuel capacity
    }

    public StrategyOptimizer(RaceCar raceCar, Track track, int totalLaps, double fuelCapacity) {
        this.raceCar = raceCar;
        this.track = track;
        this.totalLaps = totalLaps;
        this.fuelCapacity = fuelCapacity;
        this.configManager = ConfigurationManager.getInstance();
        
        // Load configuration parameters
        this.minFuelLevel = (double) configManager.getConfigValue("strategy", "parameters", "defaultParameters", "minFuelLevel");
        this.maxFuelLevel = fuelCapacity;
        this.tyreWearThreshold = (double) configManager.getConfigValue("strategy", "parameters", "defaultParameters", "tyreWearThreshold");
        this.pitStopDuration = (double) configManager.getConfigValue("strategy", "parameters", "defaultParameters", "pitStopDuration");
        this.fuelFillRate = (double) configManager.getConfigValue("strategy", "parameters", "defaultParameters", "fuelFillRate");
    }

    public RaceStrategy optimizeStrategy() {
        // Get preferred strategy based on track type and weather
        String trackType = track.getType().name();
        WeatherCondition weather = track.getWeatherCondition();
        
        // Get tyre compound preference based on weather
        String preferredTyreCompound = getPreferredTyreCompound(trackType, weather);
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

    private String getPreferredTyreCompound(String trackType, WeatherCondition weather) {
        if (weather != null) {
            return (String) configManager.getConfigValue(
                "strategy", "weatherPreferences", weather.name(), "preferredTyreCompound");
        }
        return (String) configManager.getConfigValue(
            "strategy", "trackTypePreferences", trackType, "preferredTyreCompound");
    }

    @SuppressWarnings("unchecked")
    private Tyre selectTyre(String compound) {
        // Convert compound to proper format (e.g., "SOFT" -> "Soft Compound")
        String tyreKey = compound.charAt(0) + compound.substring(1).toLowerCase() + " Compound";
        Map<String, Object> tyreConfig = (Map<String, Object>) configManager.getConfigValue("car", "tyres", tyreKey);
        if (tyreConfig == null) {
            throw new IllegalArgumentException("Tyre compound not found in configuration: " + tyreKey);
        }
        return new Tyre(
            tyreKey,
            ((Number) tyreConfig.get("weight")).doubleValue(),
            ((Number) tyreConfig.get("price")).doubleValue(),
            (String) tyreConfig.get("manufacturer"),
            ((Number) tyreConfig.get("grip")).doubleValue(),
            ((Number) tyreConfig.get("wearRate")).doubleValue(),
            ((Number) tyreConfig.get("durability")).doubleValue(),
            Tyre.TyreCompound.valueOf(compound)
        );
    }

    private double calculateInitialFuelLoad(RaceStrategy.FuelStrategy fuelStrategy) {
        double baseFuel = track.getLength() * track.getFuelConsumptionRate() * totalLaps;
        double calculatedFuel = switch (fuelStrategy) {
            case CONSERVATIVE -> baseFuel * 1.1;
            case AGGRESSIVE -> baseFuel * 0.9;
            case BALANCED -> baseFuel;
        };
        return Math.min(calculatedFuel, fuelCapacity);
    }

    private List<PitStop> calculatePitStops(Tyre startingTyre, RaceStrategy.FuelStrategy fuelStrategy) {
        List<PitStop> pitStops = new ArrayList<>();
        double currentFuel = calculateInitialFuelLoad(fuelStrategy);
        Tyre currentTyre = startingTyre;
        int lastPitStopLap = 0;
        double totalFuelConsumed = 0;

        for (int lap = 1; lap <= totalLaps; lap++) {
            // Calculate fuel consumption for this lap
            double fuelConsumption = track.getFuelConsumptionRate() * 
                (1 + (lap - lastPitStopLap) * 0.01); // Increase consumption as tyres wear
            totalFuelConsumed += fuelConsumption;

            // Check if pit stop is needed
            if (needsPitStop(lap, currentFuel, fuelConsumption, currentTyre, lastPitStopLap)) {
                double fuelToAdd = Math.min(maxFuelLevel - currentFuel, fuelCapacity - currentFuel);
                PitStop pitStop = createPitStop(lap, currentFuel, currentTyre, fuelToAdd);
                pitStops.add(pitStop);
                currentFuel = maxFuelLevel;
                // Select next tyre based on remaining laps and track conditions
                currentTyre = selectNextTyre(lap, totalLaps);
                lastPitStopLap = lap;
            }

            currentFuel -= fuelConsumption;
        }

        return pitStops;
    }

    private Tyre selectNextTyre(int currentLap, int totalLaps) {
        int remainingLaps = totalLaps - currentLap;
        String trackType = track.getType().name();
        WeatherCondition weather = track.getWeatherCondition();

        // For wet conditions, always use wet tyres
        if (weather == WeatherCondition.WET) {
            return selectTyre("SOFT"); // Using soft as wet tyres
        }

        // For remaining laps less than 10, use soft tyres for better grip
        if (remainingLaps <= 10) {
            return selectTyre("SOFT");
        }

        // For remaining laps between 10 and 20, use medium tyres
        if (remainingLaps <= 20) {
            return selectTyre("MEDIUM");
        }

        // For longer stints, use hard tyres
        return selectTyre("HARD");
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

    private PitStop createPitStop(int lap, double currentFuel, Tyre currentTyre, double fuelToAdd) {
        double duration = pitStopDuration + (fuelToAdd / fuelFillRate);
        
        return new PitStop(
            lap,
            duration,
            selectNextTyre(lap, totalLaps),
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