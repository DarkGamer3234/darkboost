package dev.darkboost;

import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public final class PackSpooferConfig {
    private static boolean enabled = true;

    private PackSpooferConfig() {}

    public static void load() {
        Path file = FabricLoader.getInstance().getConfigDir().resolve("darkboost.properties");
        Properties properties = new Properties();

        try {
            Files.createDirectories(file.getParent());
            if (Files.notExists(file)) {
                properties.setProperty("spoof-server-resource-pack", "true");
                try (OutputStream output = Files.newOutputStream(file)) {
                    properties.store(output, "DarkBoost resource-pack response settings");
                }
            }

            try (InputStream input = Files.newInputStream(file)) {
                properties.load(input);
            }
            enabled = Boolean.parseBoolean(properties.getProperty("spoof-server-resource-pack", "true"));
        } catch (IOException exception) {
            enabled = true;
            DarkBoostClient.LOGGER.error("Could not read darkboost.properties; resource-pack spoofing remains enabled.", exception);
        }

        DarkBoostClient.LOGGER.info("Server resource-pack response spoofing: {}", enabled ? "enabled" : "disabled");
    }

    public static boolean isEnabled() {
        return enabled;
    }
}
