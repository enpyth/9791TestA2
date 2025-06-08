package org.racing;

import org.racing.simulation.RacingSimulation;
import org.racing.strategy.RaceStrategy;
import org.racing.track.WeatherCondition;

public class Main {
    public static void main(String[] args) {
        RacingSimulation simulation = new RacingSimulation("car", "V10", "Soft Compound", "Low-Drag Kit", "Speedway Oval", 50, WeatherCondition.DRY, 100);
        RaceStrategy strategy = simulation.run();
        System.out.println(strategy);
    }
}