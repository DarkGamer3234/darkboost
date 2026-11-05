package dev.darkboost;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public final class PackSpooferConfig {
    private static final Set<String> FAKE_SERVERS = new HashSet<>();
    private static Path configFile;

    private PackSpooferConfig() {}

    public static void load() {
        configFile = FabricLoader.getInstance().getConfigDir().resolve("darkboost.properties");
        Properties properties = new Properties();
        try {
            Files.createDirectories(configFile.getParent());
            if (Files.exists(configFile)) {
                try (InputStream input = Files.newInputStream(configFile)) {
                    properties.load(input);
                }
            }
            String servers = properties.getProperty("fake-server-addresses", "");
            for (String address : servers.split(",")) {
                if (!address.isBlank()) FAKE_SERVERS.add(address.trim().toLowerCase());
            }
            save();
        } catch (IOException exception) {
            DarkBoostClient.LOGGER.error("Could not load PackSpoofer settings.", exception);
        }
    }

    public static boolean isEnabledFor(String address) {
        return address != null && FAKE_SERVERS.contains(normalize(address));
    }

    public static boolean isEnabledForCurrentServer() {
        ServerData server = Minecraft.getInstance().getCurrentServer();
        return server != null && isEnabledFor(server.ip);
    }

    public static boolean toggle(String address) {
        String key = normalize(address);
        if (FAKE_SERVERS.remove(key)) {
            save();
            return false;
        }
        FAKE_SERVERS.add(key);
        save();
        return true;
    }

    private static String normalize(String address) {
        return address.trim().toLowerCase();
    }

    private static void save() {
        if (configFile == null) return;
        Properties properties = new Properties();
        properties.setProperty("fake-server-addresses", String.join(",", FAKE_SERVERS));
        try (OutputStream output = Files.newOutputStream(configFile)) {
            properties.store(output, "PackSpoofer per-server settings");
        } catch (IOException exception) {
            DarkBoostClient.LOGGER.error("Could not save PackSpoofer settings.", exception);
        }
    }
}
