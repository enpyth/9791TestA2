package org.racing.simulation;

import org.racing.car.*;
import org.racing.config.ConfigurationManager;
import org.racing.strategy.RaceStrategy;
import org.racing.strategy.StrategyOptimizer;
import org.racing.track.Track;
import org.racing.track.WeatherCondition;
import java.util.Map;


public class RacingSimulation {
    private String carName;
    private String engineName;
    private String tyreName;
    private String aeroKitName;
    private String trackName;
    private int totalLaps;
    private WeatherCondition weather;
    private double fuelCapacity;

    public RacingSimulation(String carName, String engineName, String tyreName, String aeroKitName, String trackName, int totalLaps, WeatherCondition weather) {
        this(carName, engineName, tyreName, aeroKitName, trackName, totalLaps, weather, 100.0); // Default fuel capacity
    }

    public RacingSimulation(String carName, String engineName, String tyreName, String aeroKitName, String trackName, int totalLaps, WeatherCondition weather, double fuelCapacity) {
        this.carName = carName;
        this.engineName = engineName;
        this.tyreName = tyreName;
        this.aeroKitName = aeroKitName;
        this.trackName = trackName;
        this.totalLaps = totalLaps;
        this.weather = weather;
        this.fuelCapacity = fuelCapacity;
    }

    private static final ConfigurationManager configManager = ConfigurationManager.getInstance();

    public RaceStrategy run() {
        
        // Create car components from configuration
        Engine engine = createEngineFromConfig(engineName);
        Tyre tyres = createTyreFromConfig(tyreName);
        AerodynamicKit aeroKit = createAeroKitFromConfig(aeroKitName);

        // Create race car
        RaceCar raceCar = new RaceCar(carName, engine, tyres, aeroKit);

        // Load and configure track
        Track track = configManager.loadTrack(trackName);
        track.setWeather(weather);

        // Create and run strategy optimizer
        StrategyOptimizer optimizer = new StrategyOptimizer(raceCar, track, totalLaps, fuelCapacity);
        RaceStrategy strategy = optimizer.optimizeStrategy();
        return strategy;
    }

    @SuppressWarnings("unchecked")
    private static Engine createEngineFromConfig(String engineName) {
        Map<String, Object> engineConfig = (Map<String, Object>) configManager.getConfigValue("car", "engines", engineName);
        return new Engine(
            engineName,
            ((Number) engineConfig.get("weight")).doubleValue(),
            ((Number) engineConfig.get("price")).doubleValue(),
            (String) engineConfig.get("manufacturer"),
            ((Number) engineConfig.get("power")).doubleValue(),
            ((Number) engineConfig.get("fuelEfficiency")).doubleValue(),
            ((Number) engineConfig.get("reliability")).doubleValue(),
            Engine.EngineType.valueOf((String) engineConfig.get("type"))
        );
    }

    @SuppressWarnings("unchecked")
    private static Tyre createTyreFromConfig(String tyreName) {
        Map<String, Object> tyreConfig = (Map<String, Object>) configManager.getConfigValue("car", "tyres", tyreName);
        return new Tyre(
            tyreName,
            ((Number) tyreConfig.get("weight")).doubleValue(),
            ((Number) tyreConfig.get("price")).doubleValue(),
            (String) tyreConfig.get("manufacturer"),
            ((Number) tyreConfig.get("grip")).doubleValue(),
            ((Number) tyreConfig.get("wearRate")).doubleValue(),
            ((Number) tyreConfig.get("durability")).doubleValue(),
            Tyre.TyreCompound.valueOf((String) tyreConfig.get("compound"))
        );
    }

    @SuppressWarnings("unchecked")
    private static AerodynamicKit createAeroKitFromConfig(String kitName) {
        Map<String, Object> kitConfig = (Map<String, Object>) configManager.getConfigValue("car", "aerodynamicKits", kitName);
        return new AerodynamicKit(
            kitName,
            ((Number) kitConfig.get("weight")).doubleValue(),
            ((Number) kitConfig.get("price")).doubleValue(),
            (String) kitConfig.get("manufacturer"),
            ((Number) kitConfig.get("dragCoefficient")).doubleValue(),
            ((Number) kitConfig.get("downforce")).doubleValue(),
            ((Number) kitConfig.get("topSpeed")).doubleValue(),
            ((Number) kitConfig.get("corneringAbility")).doubleValue(),
            ((Number) kitConfig.get("fuelEfficiency")).doubleValue(),
            AerodynamicKit.KitType.valueOf((String) kitConfig.get("type"))
        );
    }
}
