package org.racing.car;

/**
 * Represents a complete race car with all its components and performance metrics.
 */
public class RaceCar {
    private String name;
    private Engine engine;
    private Tyre tyres;
    private AerodynamicKit aerodynamicKit;
    private PerformanceMetrics performanceMetrics;

    public RaceCar(String name, Engine engine, Tyre tyres, AerodynamicKit aerodynamicKit) {
        this.name = name;
        this.engine = engine;
        this.tyres = tyres;
        this.aerodynamicKit = aerodynamicKit;
        calculatePerformanceMetrics();
    }

    private void calculatePerformanceMetrics() {
        // Calculate combined performance metrics based on all components
        double speed = calculateSpeed();
        double handling = calculateHandling();
        double fuelEfficiency = calculateFuelEfficiency();
        double corneringAbility = calculateCorneringAbility();

        this.performanceMetrics = new PerformanceMetrics(
            speed, handling, fuelEfficiency, corneringAbility
        );
    }

    private double calculateSpeed() {
        // Speed calculation based on engine power and aerodynamic drag
        return engine.getPower() * (1 - aerodynamicKit.getDragCoefficient());
    }

    private double calculateHandling() {
        // Handling calculation based on tyres and aerodynamic downforce
        return (tyres.getGripLevel() * 0.6) + (aerodynamicKit.getDownforce() * 0.4);
    }

    private double calculateFuelEfficiency() {
        // Fuel efficiency calculation based on engine and aerodynamic efficiency
        return (engine.getFuelEfficiency() * 0.7) + (aerodynamicKit.getFuelEfficiency() * 0.3);
    }

    private double calculateCorneringAbility() {
        // Cornering ability calculation based on tyres and aerodynamic kit
        return (tyres.getGripLevel() * 0.5) + (aerodynamicKit.getCorneringAbility() * 0.5);
    }

    // Getters
    public String getName() {
        return name;
    }

    public Engine getEngine() {
        return engine;
    }

    public Tyre getTyres() {
        return tyres;
    }

    public AerodynamicKit getAerodynamicKit() {
        return aerodynamicKit;
    }

    public PerformanceMetrics getPerformanceMetrics() {
        return performanceMetrics;
    }
} 