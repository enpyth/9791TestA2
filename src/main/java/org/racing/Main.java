package org.racing;

import org.racing.simulation.RacingSimulation;
import org.racing.strategy.RaceStrategy;
import org.racing.track.WeatherCondition;

public class Main {
    public static void main(String[] args) {
        RacingSimulation simulation = new RacingSimulation("Speed Demon", "Turbo V6", "Soft Compound", "Standard Kit", "Technical Park", 5, WeatherCondition.DRY);
        RaceStrategy strategy = simulation.runSimulation();
        System.out.println(strategy);
    }
}