package org.racing.simulation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.racing.strategy.RaceStrategy;
import org.racing.track.WeatherCondition;

import static org.junit.jupiter.api.Assertions.*;

class RacingSimulationTest {

    // 1. Test to validate car configurations(incompatible parts cannot be combined)

    // 1.1 Track and Kit Compatibility
    @Test
    @DisplayName("Downforce-Focused Kit in Technical Park(twisty tracks)")
    void runSimulationWithIncompatibleAeroKitInTwistyTrack() {
        RacingSimulation simulation = new RacingSimulation("car", "V10", "Soft Compound", "Downforce-Focussed Kit", "Technical Park", 50, WeatherCondition.DRY, 100);
        RaceStrategy strategy = simulation.run();
        assertNull(strategy);
    }

    @Test
    @DisplayName("Low-Drag Kit in Speedway Oval(straight tracks)")
    void runSimulationWithIncompatibleAeroKitInStraightTrack() {
        RacingSimulation simulation = new RacingSimulation("car", "V10", "Soft Compound", "Low-Drag Kit", "Speedway Oval", 50, WeatherCondition.DRY, 100);
        RaceStrategy strategy = simulation.run();
        assertNull(strategy);
    }

    // 1.2 Weather and Kit Compatibility
    @Test
    @DisplayName("Wet Weather Kit in dry conditions")
    void runSimulationWithWetWeatherKitInDryConditions() {
        RacingSimulation simulation = new RacingSimulation("car", "Turbo V6", "Soft Compound", "Wet Weather Kit", "Technical Park", 50, WeatherCondition.DRY, 100);
        RaceStrategy strategy = simulation.run();
        assertNull(strategy);
    }

    @Test
    @DisplayName("Standard Kit in wet conditions")
    void runSimulationWithStandardKitInWetConditions() {
        RacingSimulation simulation = new RacingSimulation("car", "Turbo V6", "Soft Compound", "Standard Kit", "Technical Park", 50, WeatherCondition.WET, 100);
        RaceStrategy strategy = simulation.run();
        assertNull(strategy);
    }

    // 1.3 Engine Performance and Kit Compatibility
    @Test
    @DisplayName("High downforce with low power engine")
    void runSimulationWithHighDownforceAndLowPower() {
        RacingSimulation simulation = new RacingSimulation("car", "Turbo V6", "Medium Compound", "Downforce-Focussed Kit", "Technical Park", 50, WeatherCondition.DRY, 100);
        RaceStrategy strategy = simulation.run();
        assertNotNull(strategy);
    }

    @Test
    @DisplayName("High downforce with high power engine")
    void runSimulationWithHighDownforceAndHighPower() {
        RacingSimulation simulation = new RacingSimulation("car", "V10", "Soft Compound", "Downforce-Focussed Kit", "Speedway Oval", 50, WeatherCondition.DRY, 100);
        RaceStrategy strategy = simulation.run();
        assertNull(strategy);
    }

    @Test
    @DisplayName("Low downforce with high power engine")
    void runSimulationWithLowDownforceAndHighPower() {
        RacingSimulation simulation = new RacingSimulation("car", "V10", "Medium Compound", "Low-Drag Kit", "Technical Park", 50, WeatherCondition.DRY, 100);
        RaceStrategy strategy = simulation.run();
        assertNull(strategy);
    }

    @Test
    @DisplayName("Low downforce with low power engine")
    void runSimulationWithLowDownforceAndLowPower() {
        RacingSimulation simulation = new RacingSimulation("car", "V10", "Medium Compound", "Low-Drag Kit", "Technical Park", 50, WeatherCondition.DRY, 100);
        RaceStrategy strategy = simulation.run();
        assertNull(strategy);
    }
    
    // 1.4 Tyre Compatibility Tests
    @Test
    @DisplayName("Hard Compound in wet conditions")
    void runSimulationWithHardCompoundInWetConditions() {
        RacingSimulation simulation = new RacingSimulation("car", "Turbo V6", "Hard Compound", "Wet Weather Kit", "Technical Park", 50, WeatherCondition.WET, 100);
        RaceStrategy strategy = simulation.run();
        assertNull(strategy);
    }

    @Test
    @DisplayName("Soft Compound in high-speed track")
    void runSimulationWithSoftCompoundInHighSpeedTrack() {
        RacingSimulation simulation = new RacingSimulation("car", "V10", "Soft Compound", "Low-Drag Kit", "Speedway Oval", 50, WeatherCondition.DRY, 100);
        RaceStrategy strategy = simulation.run();
        assertNull(strategy);
    }

    // 2. Test strategy outcomes under various race scenarios

    // 2.1 wet vs dry
    @Test
    @DisplayName("Dry Conditions")
    void runSimulationWithDryConditions() {
        RacingSimulation simulation = new RacingSimulation("car", "V10", "Soft Compound", "Standard Kit", "Coastal Run", 20, WeatherCondition.DRY, 100);
        RaceStrategy strategy = simulation.run();
        assertNotNull(strategy);
    }

    @Test
    @DisplayName("Wet Conditions")
    void runSimulationWithWetConditions() {
        RacingSimulation simulation = new RacingSimulation("car", "V10", "Soft Compound", "Standard Kit", "Coastal Run", 20, WeatherCondition.WET, 100);
        RaceStrategy strategy = simulation.run();
        assertNotNull(strategy);
    }

    // 2.2 short vs long races
    @Test
    @DisplayName("Short Race")
    void runSimulationWithShortRace() {
        RacingSimulation simulation = new RacingSimulation("car", "V10", "Soft Compound", "Standard Kit", "Coastal Run", 20, WeatherCondition.DRY, 100);
        RaceStrategy strategy = simulation.run();
        assertNotNull(strategy);
    }

    @Test
    @DisplayName("Long Race")
    void runSimulationWithLongRace() {
        RacingSimulation simulation = new RacingSimulation("car", "V10", "Soft Compound", "Standard Kit", "Coastal Run", 100, WeatherCondition.DRY, 100);
        RaceStrategy strategy = simulation.run();
        assertNotNull(strategy);
    }

    // 2.3 twisty vs straight tracks
    @Test
    @DisplayName("Twisty Track")
    void runSimulationWithTwistyTrack() {
        RacingSimulation simulation = new RacingSimulation("car", "V10", "Soft Compound", "Standard Kit", "Technical Park", 20, WeatherCondition.DRY, 100);
        RaceStrategy strategy = simulation.run();
        assertNotNull(strategy);
    }
    
    @Test
    @DisplayName("Straight Track")
    void runSimulationWithStraightTrack() {
        RacingSimulation simulation = new RacingSimulation("car", "V10", "Soft Compound", "Standard Kit", "Speedway Oval", 20, WeatherCondition.DRY, 100);
        RaceStrategy strategy = simulation.run();
        assertNotNull(strategy);
    }
    
    // 3. Test for edge cases 
    // 3.1 negative values
    @Test
    @DisplayName("Negative fuel")
    void runSimulationWithNegativeFuel() {
        RacingSimulation simulation = new RacingSimulation("car", "V10", "Soft Compound", "Standard Kit", "Coastal Run", 20, WeatherCondition.DRY, -1);
        RaceStrategy strategy = simulation.run();
        assertNull(strategy);
    }

    @Test
    @DisplayName("Negative laps")
    void runSimulationWithNegativeLaps() {
        RacingSimulation simulation = new RacingSimulation("car", "V10", "Soft Compound", "Standard Kit", "Coastal Run", -1, WeatherCondition.DRY, 100);
        RaceStrategy strategy = simulation.run();
        assertNull(strategy);
    }

    // 3.2 non-existent parts
    @Test
    @DisplayName("Non-existent engine")
    void runSimulationWithNonExistentEngine() {
        RacingSimulation simulation = new RacingSimulation("car", "non-existent engine", "Soft Compound", "Standard Kit", "Coastal Run", 20, WeatherCondition.DRY, 100);
        RaceStrategy strategy = simulation.run();
        assertNull(strategy);
    }
    
    @Test
    @DisplayName("Non-existent tyre")
    void runSimulationWithNonExistentTyre() {
        RacingSimulation simulation = new RacingSimulation("car", "V10", "non-existent tyre", "Standard Kit", "Coastal Run", 20, WeatherCondition.DRY, 100);
        RaceStrategy strategy = simulation.run();
        assertNull(strategy);
    }
    
    @Test
    @DisplayName("Non-existent aero kit")
    void runSimulationWithNonExistentAeroKit() {
        RacingSimulation simulation = new RacingSimulation("car", "V10", "Soft Compound", "non-existent aero kit", "Coastal Run", 20, WeatherCondition.DRY, 100);
        RaceStrategy strategy = simulation.run();
        assertNull(strategy);
    }
    
    @Test
    @DisplayName("Non-existent track")
    void runSimulationWithNonExistentTrack() {
        RacingSimulation simulation = new RacingSimulation("car", "V10", "Soft Compound", "Standard Kit", "non-existent track", 20, WeatherCondition.DRY, 100);
        RaceStrategy strategy = simulation.run();
        assertNull(strategy);
    }

    @Test
    @DisplayName("Non-existent weather")
    void runSimulationWithNonExistentWeather() {
        RacingSimulation simulation = new RacingSimulation("car", "V10", "Soft Compound", "Standard Kit", "Coastal Run", 20, null, 100);
        RaceStrategy strategy = simulation.run();
        assertNull(strategy);
    }

    // 4. Test multiple races to test the accuracy and consistency

    @Test
    @DisplayName("Race 1")
    void runSimulationWithRace1() {
        RacingSimulation simulation = new RacingSimulation("car", "V10", "Soft Compound", "Standard Kit", "Technical Park", 20, WeatherCondition.DRY, 100);
        RaceStrategy strategy = simulation.run();
        assertTrue(strategy.toString().contains("Duration: 50.78 seconds, Fuel: 96.55 liters"));
        assertTrue(strategy.toString().contains("Duration: 50.78 seconds, Fuel: 93.1 liters"));
    }

    @Test
    @DisplayName("Race 2")
    void runSimulationWithRace2() {
        RacingSimulation simulation = new RacingSimulation("car", "V10", "Soft Compound", "Standard Kit", "Speedway Oval", 20, WeatherCondition.DRY, 100);
        RaceStrategy strategy = simulation.run();
        assertTrue(strategy.toString().contains("Duration: 52.40 seconds, Fuel: 99.80 liters"));
        assertTrue(strategy.toString().contains("Duration: 52.40 seconds, Fuel: 99.60 liters"));
    }

    @Test
    @DisplayName("Race 3")
    void runSimulationWithRace3() {
        RacingSimulation simulation = new RacingSimulation("car", "V10", "Soft Compound", "Standard Kit", "Mountain Pass", 20, WeatherCondition.DRY, 100);
        RaceStrategy strategy = simulation.run();
        assertTrue(strategy.toString().contains("Duration: 49.98 seconds, Fuel: 94.95 liters"));
        assertTrue(strategy.toString().contains("Duration: 49.98 seconds, Fuel: 89.90 liters"));
    }

    @Test
    @DisplayName("Race 4")
    void runSimulationWithRace4() {
        RacingSimulation simulation = new RacingSimulation("car", "V10", "Soft Compound", "Standard Kit", "City Circuit", 20, WeatherCondition.DRY, 100);
        RaceStrategy strategy = simulation.run();
        assertTrue(strategy.toString().contains("Duration: 51.02 seconds, Fuel: 97.03 liters"));
        assertTrue(strategy.toString().contains("Duration: 51.02 seconds, Fuel: 94.06 liters"));
    }
    
    @Test
    @DisplayName("Race 5")
    void runSimulationWithRace5() {
        RacingSimulation simulation = new RacingSimulation("car", "V10", "Soft Compound", "Standard Kit", "Coastal Run", 20, WeatherCondition.DRY, 100);
        RaceStrategy strategy = simulation.run();
        assertTrue(strategy.toString().contains("Duration: 51.43 seconds, Fuel: 97.87 liters"));
        assertTrue(strategy.toString().contains("Duration: 51.43 seconds, Fuel: 95.74 liters"));
    }
}