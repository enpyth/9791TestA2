package org.racing;

import org.racing.car.*;
import org.racing.config.ConfigurationManager;
import org.racing.strategy.RaceStrategy;
import org.racing.strategy.StrategyOptimizer;
import org.racing.track.Track;
import org.racing.track.WeatherCondition;
import java.util.Map;

/**
 * Example class demonstrating how to use the racing strategy system.
 */
public class RacingSimulation {
    private static final ConfigurationManager configManager = ConfigurationManager.getInstance();

    public static void main(String[] args) {
        // Create a race car with components from configuration
        Engine engine = createEngineFromConfig("Turbo V6");
        Tyre tyres = createTyreFromConfig("Soft Compound");
        AerodynamicKit aeroKit = createAeroKitFromConfig("Standard Kit");

        RaceCar raceCar = new RaceCar("Speed Demon", engine, tyres, aeroKit);

        // Load track from configuration
        Track track = configManager.loadTrack("Technical Park");

        // Create strategy optimizer
        int totalLaps = 50;
        StrategyOptimizer optimizer = new StrategyOptimizer(raceCar, track, totalLaps);

        // Optimize race strategy
        RaceStrategy strategy = optimizer.optimizeStrategy();

        // Print race information
        System.out.println("=== Race Information ===");
        System.out.println("Track: " + track.getName());
        System.out.println("Total Laps: " + totalLaps);
        System.out.println("Weather: " + track.getWeatherCondition());
        System.out.println("\n=== Car Configuration ===");
        System.out.println("Car: " + raceCar.getName());
        System.out.println("Engine: " + engine.getName() + " (" + engine.getType() + ")");
        System.out.println("Tyres: " + tyres.getName() + " (" + tyres.getCompound() + ")");
        System.out.println("Aero Kit: " + aeroKit.getName() + " (" + aeroKit.getType() + ")");
        System.out.println("\n=== Performance Metrics ===");
        PerformanceMetrics metrics = raceCar.getPerformanceMetrics();
        System.out.printf("Speed: %.2f km/h\n", metrics.getSpeed());
        System.out.printf("Handling: %.2f/10\n", metrics.getHandling());
        System.out.printf("Fuel Efficiency: %.2f km/l\n", metrics.getFuelEfficiency());
        System.out.printf("Cornering Ability: %.2f/10\n", metrics.getCorneringAbility());
        System.out.printf("Overall Performance: %.2f/100\n", metrics.getOverallPerformance());
        System.out.println("\n=== Optimized Race Strategy ===");
        System.out.println(strategy);
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