package edu.bbte.quiz.backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class ConfigurationFactory {
    private static final Logger LOG = LoggerFactory.getLogger(ConfigurationFactory.class);

    // A Jackson JSON olvasó objektuma
    private static final ObjectMapper OBJECT_MAPPER;

    private static Config mainConfiguration = new Config();

    public static final String CONFIG_PROFILE_ENV = "PROFILE";

    static {
        // JSON olvasó inicializálása
        OBJECT_MAPPER = new ObjectMapper();

        String configProfile = System.getenv(CONFIG_PROFILE_ENV);

        StringBuilder configFile = new StringBuilder();

        if (configProfile == null || configProfile.isEmpty()) {
            configFile.append("/config");
        } else {
            LOG.info(configProfile + "is used");
            configFile.append("/config-").append(configProfile);
        }
        configFile.append(".json");

        // kérünk olvasási streamet a JSON állományhoz
        try (InputStream inputStream = ConfigurationFactory.class.getResourceAsStream(configFile.toString())) {
            // JSON állomány beolvasása az általunk megadott osztály egy példányába
            mainConfiguration = OBJECT_MAPPER.readValue(inputStream, Config.class);
            LOG.info("Read following configuration: {} - {}", mainConfiguration, configFile.toString());
        } catch (IOException e) {
            LOG.error("Error loading configuration", e);
        }
    }

    public static Config getMainConfiguration() {
        return mainConfiguration;
    }
}
