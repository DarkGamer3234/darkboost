# PackSpoofer (project folder: DarkBoost)

A Minecraft 26.3 Fabric client mod that intercepts server resource-pack requests and, when enabled, sends the normal accepted and applied responses without downloading or loading the pack.

## What it does

- Intercepts the vanilla server-pack manager before the download begins.
- Sends the normal ACCEPTED update and APPLIED final response.
- Cancels the normal download and resource reload.
- Does not modify the server or require a server-side plugin.

## Important limitations

This only changes the resource-pack status reported by the client. A server can still reject the connection if it independently verifies the pack, checks custom assets, or uses another mechanism. Without the pack, server-specific textures, sounds, models, fonts, and UI may be missing or incorrect.

## Install

1. Build the mod using the GitHub Actions workflow.
2. Install Fabric Loader for Minecraft 26.3 and Java 25.
3. Put the generated darkboost-0.1.0.jar in your client mods folder.
4. Launch Minecraft.

## Configuration

On first launch, the mod creates .minecraft/config/darkboost.properties:

    spoof-server-resource-pack=true

Set it to false to restore normal Minecraft resource-pack handling. Restart Minecraft after changing it.

## Build

The GitHub Actions workflow builds the Fabric mod and uploads the JAR artifact.
