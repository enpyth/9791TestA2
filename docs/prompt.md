# Prompt 1

```
I need reate a java project. Give me a plan with no code about how to do step by step. The specification is following:

# Race Strategy Optimiser and Car Customisation Tool

## Description
You are to build a program that acts as a race team management simulation. The system allows users to customise race cars with different engines, tyres, and aerodynamic features. It also includes a strategy optimiser to plan pit stops, fuel usage, and tyre changes based on race conditions such as track type, weather, and distance.

## Key Features:
1. a Car Customisation:
    - Users can select and upgrade components like engines (e.g., standard, turbocharged),
    - tyres (soft, medium, hard), and aerodynamic kits*(see page 3). Each choice affects performance metrics such as speed, fuel efficiency, and handling.
2. a Race Strategy Optimisation:
    - Users can input race details (track length, weather, fuel capacity, etc.) to generate an optimal pit stop and tyre change strategy for a minimum of 5 race tracks.
    - Your program should take into account factors such as wear rates, and fuel consumption based on the selected car components.

Some details are left open, such as acceleration, fuel tank capacity, and what effect the cornering ability rating has. In this case you are at liberty to decide these details but must include at least 3 variations of each.

1. Standard Kit (Basic Aerodynamics)

    Description: Includes basic front and rear spoilers to reduce drag and provide minimal downforce.

    Performance Impact: Suitable for general-purpose tracks; balances speed and stability without extreme effects.

2. Downforce-Focused Kit

    Description: Features large front splitters and a prominent rear wing to maximise downforce.

    Performance Impact: Improves cornering ability and traction, especially on twisty tracks, but increases drag and reduces top speed.

3. Low-Drag Kit

    Description: Designed with sleek, minimal features to reduce air resistance (e.g., smaller spoilers or absence of a rear wing).

    Performance Impact: Prioritises top speed on long straight tracks but sacrifices cornering stability.

4. Adjustable Aero Kit

    Description: Equipped with adjustable components like movable wings or variable angle splitters.

    Performance Impact: Allows for on-the-fly adjustments to suit different sections of a track-low downforce for straights and high downforce for corners.

5. Ground Effect Kit

    Description: Includes components like underbody diffusers and side skirts to channel airflow underneath the car, creating suction to stick the car to the track.

    Performance Impact: Increases downforce significantly without adding much drag, making it ideal for high-speed tracks.

6. Drag Reduction System (DRS) Kit

    Description: Features a mechanism to temporarily reduce drag by altering aerodynamic components, commonly seen in Formula 1 cars.

    Performance Impact: Allows for better overtaking by boosting straight-line speed when activated.

7. Wet Weather Kit

    Description: Designed for races in wet conditions, with features like extended wheel covers or deflectors to minimise water spray and improve traction.

    Performance Impact: Enhances stability and visibility in rainy conditions, sacrificing some top speed.

8. Hybrid Kit

    Description: Combines elements of low-drag and downforce-focused kits to achieve a middle ground between speed and stability.
    
    Performance Impact: Offers versatility for tracks with a mix of straights and corners.

9. Extreme Aero Kit

    Description: Pushes the limits of aerodynamics with aggressive features like oversized wings, massive splitters, and large diffusers

    Performance Impact: Maximises downforce at the cost of significant drag, ideal for short, technical circuits.


| Aerodynamic Kit| Drag Coefficient(Cd)| Downforce(kg)| Top Speed(km/h)| Fuel Efficiency(km/l)| Cornering Ability(rating out of 10) |
|--|--|--|--|--|--|
|Standard Kit |0.30 |200 |250| 12| 6|
|Downforce-Focussed Kit|0.35| 350 |220| 10 |9|
|Low-Drag Kit| 0.25| 150| 280| 14 |5|
|Adjustable Aero Kit|0.28 - 0.34(variable)|200 - 300(variable)|240 - 260(variable)|11 - 13(variable)|7 - 8(variable)|
|Ground Effect Kit|0.27| 400| 240| 12| 8|
|Drag Reduction System Kit|0.25(activated)|200 |290 (with DRS)|13 |6|
|Wet Weather Kit|0.32| 220| 230| 11 |7|
|Hybrid Kit |0.29 |260 |260 |12 |7|
|Extreme Aero Kit|0.4| 500| 200| 9 |10|
```

# Prompt 2 

At first, create a readme.md document to introduce this project.

# Prompt 3

1. Update the Phase 3 plan, do not use console interface, use configuration file to set the option.
2. Update the Phase 4 plan, do not implement test case, I will implement it independently.
3. Start the Phase 1.
4. Tell me TODO List.

# Prompt 4

yes go on next step

# Prompt 5

Move on the Strategy System

# Prompt 6

Have you finish all Phase? Show me an example how to use the project.

# Prompt 7

I found 2 bugs should be fixed. 1. You should not set all parameters of engine, tyres and aerokit, but only a name of them, and get the attributes from configuration file. 2. I gave you 9 Aerodynamic kits information but you only have 3.

# Prompt 8

You should use my Aerodynamic Kit parameter instead of yours, update the configuration file.

# Prompt 9

You should keep parameters defination be consistent. In configuration file, what the hadling and stability means?

# Prompt 10

You miss fuel efficiency.

# Prompt 11

The program can not be running. Fix it. /opt/homebrew/opt/java/libexec/openjdk.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=64713 -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath /Users/zhangsu/Desktop/s2/Test/Assignment2/target/classes:/Users/zhangsu/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.15.2/jackson-databind-2.15.2.jar:/Users/zhangsu/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.15.2/jackson-annotations-2.15.2.jar:/Users/zhangsu/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.15.2/jackson-core-2.15.2.jar org.example.racing.RacingSimulation
Exception in thread "main" java.lang.ClassCastException: class java.util.LinkedHashMap cannot be cast to class com.fasterxml.jackson.databind.JsonNode (java.util.LinkedHashMap is in module java.base of loader 'bootstrap'; com.fasterxml.jackson.databind.JsonNode is in unnamed module of loader 'app')
	at org.example.racing.RacingSimulation.createEngineFromConfig(RacingSimulation.java:63)
	at org.example.racing.RacingSimulation.main(RacingSimulation.java:17)

# Prompt 12

Optimize the track features. 
1. use configuration files to define the track. 
2. Think about how many track should be, and update the track config if you think it is necessary.

# Prompt 13
I delete example path, the project path is src/main/java/org/racing and the configuration path is src/main/resources/config. Now, you need to optimize the track features. 
1. use configuration files to define the track. 
2. Think about how many track should be, and update the track config if you think it is necessary.

# Prompt 14
In the last change, you delete ConfigurationManager.getConfigValue result the program error. And you set 2 repeat track configuration tacks.json and track-config.json. Fix it.

# Prompt 15
I cannot run the RacingSimulation.java. /opt/homebrew/opt/java/libexec/openjdk.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=49666 -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath /Users/zhangsu/Desktop/s2/Test/Assignment2/target/classes:/Users/zhangsu/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.15.2/jackson-databind-2.15.2.jar:/Users/zhangsu/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.15.2/jackson-annotations-2.15.2.jar:/Users/zhangsu/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.15.2/jackson-core-2.15.2.jar org.racing.RacingSimulation
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "java.util.Map.get(Object)" because "engineConfig" is null
	at org.racing.RacingSimulation.createEngineFromConfig(RacingSimulation.java:59)
	at org.racing.RacingSimulation.main(RacingSimulation.java:17)

# Prompt 16
/Users/zhangsu/Desktop/s2/Test/Assignment2/src/main/java/org/racing/strategy/StrategyOptimizer.java:47:16
java: constructor RaceStrategy in class org.racing.strategy.RaceStrategy cannot be applied to given types;
  required: double,org.racing.car.Tyre,org.racing.strategy.RaceStrategy.FuelStrategy
  found:    int,int,int[],java.lang.String,java.lang.String
  reason: actual and formal argument lists differ in length

# Prompt 17
/opt/homebrew/opt/java/libexec/openjdk.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=49913 -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath /Users/zhangsu/Desktop/s2/Test/Assignment2/target/classes:/Users/zhangsu/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.15.2/jackson-databind-2.15.2.jar:/Users/zhangsu/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.15.2/jackson-annotations-2.15.2.jar:/Users/zhangsu/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.15.2/jackson-core-2.15.2.jar org.racing.RacingSimulation
Exception in thread "main" java.lang.IllegalArgumentException: No enum constant org.racing.car.Engine.EngineType.TURBO
	at java.base/java.lang.Enum.valueOf(Enum.java:293)
	at org.racing.car.Engine$EngineType.valueOf(Engine.java:14)
	at org.racing.RacingSimulation.createEngineFromConfig(RacingSimulation.java:67)
	at org.racing.RacingSimulation.main(RacingSimulation.java:19)

Process finished with exit code 1

# Prompt 18

In RacingSimulation.java, create a simulation method in which user can set necessary parameter. And in main mthod, you should call the simulation method. Simulation method should be testable.

# Prompt 19

You should consider code structure, move SimulationResult to a new file. And write the command what parameter can I set.
