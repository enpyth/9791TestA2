# Racing Simulation System

This project is for Assignment2, and the report path is 'docs/REPORT.pdf'.

## Overview
This Java-based application simulates a racing system that models race cars, tracks, and racing strategies. The system provides detailed track characteristics, weather conditions, and performance calculations for racing simulations.

## Features

### Track Management
- **Track Types**: Technical, Speed, and Mixed circuits
- **Track Characteristics**:
  - Length and number of corners
  - Average speed and grip level
  - Weather conditions
  - Detailed track descriptions

### Weather System
- Dynamic weather conditions affecting track performance
- Weather impact on racing strategy
- Default weather conditions for each track

### Performance Calculations
- Fuel consumption rate calculations
- Estimated lap time calculations
- Performance metrics based on track characteristics
- Car performance impact on lap times

## Technical Specifications

### Track Properties
Each track includes the following characteristics:
- Name and description
- Length (in appropriate units)
- Number of corners
- Track type (Technical, Speed, Mixed)
- Average speed
- Grip level
- Weather conditions

### Performance Metrics
The system calculates:
- Fuel consumption based on track length and corners
- Lap times considering:
  - Track length
  - Number of corners
  - Car performance
  - Weather conditions

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 11 or higher
- Maven build tool

### Installation
1. Clone the repository
2. Build the project using Maven
3. Run the application

## Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── org/
│   │       └── racing/
│   │           ├── track/
│   │           │   ├── Track.java
│   │           │   └── WeatherCondition.java
│   │           └── car/
│   └── resources/
└── test/
    └── java/
        └── org/
            └── racing/
```

## Usage
1. Create track configurations with specific characteristics
2. Set weather conditions for the track
3. Calculate performance metrics
4. Analyze lap times and fuel consumption

## Config

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
## Contributing
Contributions are welcome! Please feel free to submit a Pull Request.

## License
This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments
- Racing simulation communities for feedback and testing 
