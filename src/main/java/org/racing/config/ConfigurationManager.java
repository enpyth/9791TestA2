package org.racing.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.racing.track.Track;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages loading and accessing configuration files.
 */
public class ConfigurationManager {
    private static ConfigurationManager instance;
    private final ObjectMapper objectMapper;
    private final Map<String, Track> trackCache;
    private static final String CONFIG_DIR = "src/main/resources/config/";
    private Map<String, Object> carConfig;
    private Map<String, Object> strategyConfig;

    private ConfigurationManager() {
        this.objectMapper = new ObjectMapper();
        this.trackCache = new HashMap<>();
        try {
            loadConfigurations();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configurations", e);
        }
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    private void loadConfigurations() throws IOException {
        carConfig = loadConfig("config/car-config.json");
        strategyConfig = loadConfig("config/strategy-config.json");
    }

    private Map<String, Object> loadConfig(String configPath) throws IOException {
        try (InputStream is = ConfigurationManager.class.getClassLoader().getResourceAsStream(configPath)) {
            if (is == null) {
                throw new IOException("Configuration file not found: " + configPath);
            }
            return objectMapper.readValue(is, Map.class);
        }
    }

    public Object getConfigValue(String configType, String... path) {
        Map<String, Object> config = switch (configType.toLowerCase()) {
            case "car" -> carConfig;
            case "strategy" -> strategyConfig;
            default -> throw new IllegalArgumentException("Invalid config type: " + configType);
        };

        Object current = config;
        for (String key : path) {
            if (current instanceof Map) {
                current = ((Map<?, ?>) current).get(key);
            } else {
                return null;
            }
        }
        return current;
    }

    public Track loadTrack(String trackName) {
        if (trackCache.containsKey(trackName)) {
            return trackCache.get(trackName);
        }

        try {
            File configFile = new File(CONFIG_DIR + "tracks.json");
            JsonNode rootNode = objectMapper.readTree(configFile);
            JsonNode tracksNode = rootNode.get("tracks");

            for (JsonNode trackNode : tracksNode) {
                if (trackNode.get("name").asText().equals(trackName)) {
                    Track track = objectMapper.treeToValue(trackNode, Track.class);
                    trackCache.put(trackName, track);
                    return track;
                }
            }
            throw new IllegalArgumentException("Track not found: " + trackName);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load track configuration: " + e.getMessage(), e);
        }
    }

    public Map<String, Track> getAllTracks() {
        try {
            File configFile = new File(CONFIG_DIR + "tracks.json");
            JsonNode rootNode = objectMapper.readTree(configFile);
            JsonNode tracksNode = rootNode.get("tracks");

            Map<String, Track> tracks = new HashMap<>();
            for (JsonNode trackNode : tracksNode) {
                Track track = objectMapper.treeToValue(trackNode, Track.class);
                tracks.put(track.getName(), track);
            }
            return tracks;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load tracks configuration: " + e.getMessage(), e);
        }
    }
} 