package org.racing;

/**
 * Abstract base class for all car components.
 * Defines common properties and methods that all components must implement.
 */
public abstract class Component {
    protected String name;
    protected double weight;
    protected double cost;
    protected String manufacturer;

    public Component(String name, double weight, double cost, String manufacturer) {
        this.name = name;
        this.weight = weight;
        this.cost = cost;
        this.manufacturer = manufacturer;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getCost() {
        return cost;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    // Abstract methods that all components must implement
    public abstract double calculatePerformance();
    public abstract String getComponentType();
} 