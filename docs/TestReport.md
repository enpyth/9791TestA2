I'll check the configuration files to list all available parameters.


Ran tool
Let me check each configuration file to list all available parameters:


Read file: src/main/resources/config/car-config.json

Read file: src/main/resources/config/tracks.json
Based on the configuration files, here are all the available parameters you can set in the simulation:

1. Car Configuration:
   - `carName`: Any string name for your race car
   
   - `engineName`: Choose from:
     * "Turbo V6" (450hp, good fuel efficiency)
     * "V8 Hybrid" (1000hp, low fuel efficiency)
     * "V10" (1200hp, very low fuel efficiency)
   
   - `tyreName`: Choose from:
     * "Soft Compound" (high grip, low durability)
     * "Medium Compound" (balanced grip and durability)
     * "Hard Compound" (low grip, high durability)
   
   - `aeroKitName`: Choose from:
     * "Standard Kit" (balanced performance)
     * "Downforce-Focussed Kit" (high downforce, lower top speed)
     * "Low-Drag Kit" (low drag, high top speed)
     * "Adjustable Aero Kit" (versatile performance)
     * "Ground Effect Kit" (high downforce)
     * "Drag Reduction System Kit" (DRS for overtaking)
     * "Wet Weather Kit" (optimized for wet conditions)
     * "Hybrid Kit" (balanced performance)
     * "Extreme Aero Kit" (maximum downforce)

2. Track Configuration:
   - `trackName`: Choose from:
     * "Technical Park" (technical circuit, 16 corners)
     * "Speedway Oval" (high-speed oval, 4 corners)
     * "Mountain Pass" (mixed circuit, 12 corners)
     * "City Circuit" (technical street circuit, 14 corners)
     * "Coastal Run" (mixed circuit, 10 corners)
   
   - `totalLaps`: Any positive integer
   
   - `weather`: Can be null (uses track's default weather) or set to:
     * "DRY"
     * "WET"
     * "VARIABLE"

Example usage with specific configurations:
```java
RacingSimulation simulation = new RacingSimulation();

// High-speed configuration
SimulationResult speedResult = simulation.runSimulation(
    "Speed Demon",
    "V10",                    // High power engine
    "Hard Compound",          // Durable tyres
    "Low-Drag Kit",           // Low drag aero
    "Speedway Oval",          // High-speed track
    10,                       // 10 laps
    null                      // Default weather
);

// Technical track configuration
SimulationResult technicalResult = simulation.runSimulation(
    "Corner Master",
    "Turbo V6",              // Balanced engine
    "Soft Compound",         // High grip tyres
    "Downforce-Focussed Kit", // High downforce aero
    "Technical Park",        // Technical track
    5,                       // 5 laps
    null                     // Default weather
);

// Wet weather configuration
SimulationResult wetResult = simulation.runSimulation(
    "Rain Warrior",
    "V8 Hybrid",            // Hybrid engine
    "Medium Compound",      // Balanced tyres
    "Wet Weather Kit",      // Wet weather aero
    "Mountain Pass",        // Mixed track
    8,                      // 8 laps
    "WET"                   // Wet weather
);
```

Each configuration will affect the car's performance differently:
- Engine choice affects power and fuel efficiency
- Tyre choice affects grip and durability
- Aero kit choice affects downforce, top speed, and cornering
- Track choice affects the type of racing (technical vs speed)
- Weather conditions affect track grip and car performance

Would you like me to provide more specific details about any of these parameters or their effects on performance?
