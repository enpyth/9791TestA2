package org.racing.car;

import org.racing.Component;

/**
 * Represents a car engine with specific performance characteristics.
 */
public class Engine extends Component {
    public enum EngineType {
        TURBO,
        HYBRID,
        NATURALLY_ASPIRATED
    }

    private final String name;
    private final double weight;
    private final double price;
    private final String manufacturer;
    private final double power;
    private final double fuelEfficiency;
    private final double reliability;
    private final EngineType type;

    public Engine(String name, double weight, double price, String manufacturer,
                 double power, double fuelEfficiency, double reliability, EngineType type) {
        super(name, weight, price, manufacturer);
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.manufacturer = manufacturer;
        this.power = power;
        this.fuelEfficiency = fuelEfficiency;
        this.reliability = reliability;
        this.type = type;
    }

    @Override
    public double calculatePerformance() {
        // Basic performance calculation based on power and fuel efficiency
        return (power * 0.6) + (fuelEfficiency * 0.4);
    }

    @Override
    public String getComponentType() {
        return "Engine";
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getPower() {
        return power;
    }

    public double getFuelEfficiency() {
        return fuelEfficiency;
    }

    public double getReliability() {
        return reliability;
    }

    public EngineType getType() {
        return type;
    }
} 