package dev.darkboost;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DarkBoostClient implements ClientModInitializer {
    public static final String MOD_ID = "darkboost";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        PackSpooferConfig.load();
        LOGGER.info("PackSpoofer initialised for Minecraft 26.3.");
    }
}
