package org.racing.car;

import org.racing.Component;

/**
 * Represents an aerodynamic kit with specific performance characteristics.
 */
public class AerodynamicKit extends Component {
    private double dragCoefficient;
    private double downforce; // in kg
    private double topSpeed; // in km/h
    private double fuelEfficiency; // in km/l
    private double corneringAbility; // 0-10 rating
    private KitType type;

    public enum KitType {
        STANDARD,
        DOWNFORCE_FOCUSED,
        LOW_DRAG,
        ADJUSTABLE,
        GROUND_EFFECT,
        DRS,
        WET_WEATHER,
        HYBRID,
        EXTREME
    }

    public AerodynamicKit(String name, double weight, double cost, String manufacturer,
                         double dragCoefficient, double downforce, double topSpeed,
                         double fuelEfficiency, double corneringAbility, KitType type) {
        super(name, weight, cost, manufacturer);
        this.dragCoefficient = dragCoefficient;
        this.downforce = downforce;
        this.topSpeed = topSpeed;
        this.fuelEfficiency = fuelEfficiency;
        this.corneringAbility = corneringAbility;
        this.type = type;
    }

    @Override
    public double calculatePerformance() {
        // Performance calculation based on multiple factors
        // Lower drag coefficient is better, higher values for others are better
        return ((1 - dragCoefficient) * 0.2) +
               (downforce * 0.2) +
               (topSpeed * 0.2) +
               (fuelEfficiency * 0.2) +
               (corneringAbility * 0.2);
    }

    @Override
    public String getComponentType() {
        return "Aerodynamic Kit";
    }

    // Getters
    public double getDragCoefficient() {
        return dragCoefficient;
    }

    public double getDownforce() {
        return downforce;
    }

    public double getTopSpeed() {
        return topSpeed;
    }

    public double getFuelEfficiency() {
        return fuelEfficiency;
    }

    public double getCorneringAbility() {
        return corneringAbility;
    }

    public KitType getType() {
        return type;
    }
} 